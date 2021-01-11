class SVD_PPMI_Algorithm:

    def setdata(self, windowsize, iteration_num, postposition, train_route, store_route):
        self.windowsize = windowsize
        self.iteration_num = iteration_num
        self.postposition = postposition
        self.train_route = train_route
        self.store_route = store_route

    def SVD_PPMI_Calculation(self):

        # https://www.kaggle.com/gabrielaltay/word-vectors-from-pmi-matrix

        #install packages
        from collections import Counter
        import itertools
        import nltk
        from nltk.corpus import stopwords
        import numpy as np
        import pandas as pd
        from scipy import sparse
        from scipy.sparse import linalg
        from sklearn.preprocessing import normalize
        from sklearn.metrics.pairwise import cosine_similarity

        #학습 데이터 불러오기 = content단어로 구성되어 있는 문장
        df = pd.read_csv(self.train_route)
        print(df.head())
        headlines = df['Sentence'].tolist()
        headlines = [[tok for tok in headline.split()] for headline in headlines]
        # remove single word headlines
        # 하나의 단어로 구성된 문장은 제거한다.
        headlines = [hl for hl in headlines if len(hl) > 1]
        # show results
        # 결과 확인
        print(headlines[0:20])

        # calculate a unigram vocabulary
        # 단일 단어 사전 생성 (얼마나 많은 타입의 단어를 포함하는가?)
        tok2indx = dict()
        unigram_counts = Counter()
        for ii, headline in enumerate(headlines):
            if ii % 200000 == 0:
                print(f'finished {ii/len(headlines):.2%} of headlines')
            for token in headline:
                unigram_counts[token] += 1
                if token not in tok2indx:
                    tok2indx[token] = len(tok2indx)
        indx2tok = {indx: tok for tok, indx in tok2indx.items()}
        print('done')
        print('vocabulary size: {}'.format(len(unigram_counts)))
        print('most common: {}'.format(unigram_counts.most_common(10)))

        # 단어의 타입 수
        wordType = len(unigram_counts);

        # Skipgrams
        # 윈도우 사이즈 적용해서 단어X단어 행렬 생성하기
        back_window = self.windowsize
        front_window = self.windowsize
        skipgram_counts = Counter()
        for iheadline, headline in enumerate(headlines):
            for ifw, fw in enumerate(headline):
                icw_min = max(0, ifw - back_window)
                icw_max = min(len(headline) - 1, ifw + front_window)
                icws = [ii for ii in range(icw_min, icw_max + 1) if ii != ifw]
                for icw in icws:
                    skipgram = (headline[ifw], headline[icw])
                    skipgram_counts[skipgram] += 1
            if iheadline % 200000 == 0:
                print(f'finished {iheadline/len(headlines):.2%} of headlines')
        print('done')
        print('number of skipgrams: {}'.format(len(skipgram_counts)))
        print('most common: {}'.format(skipgram_counts.most_common(10)))

        # Word-Word Count Matrix
        row_indxs = []
        col_indxs = []
        dat_values = []
        ii = 0
        for (tok1, tok2), sg_count in skipgram_counts.items():
            ii += 1
            if ii % 1000000 == 0:
                print(f'finished {ii/len(skipgram_counts):.2%} of skipgrams')
            tok1_indx = tok2indx[tok1]
            tok2_indx = tok2indx[tok2]

            row_indxs.append(tok1_indx)
            col_indxs.append(tok2_indx)
            dat_values.append(sg_count)

        wwcnt_mat = sparse.csr_matrix((dat_values, (row_indxs, col_indxs)))
        print('done')



        # Pointwise Mutual Information Matrices
        num_skipgrams = wwcnt_mat.sum()
        assert (sum(skipgram_counts.values()) == num_skipgrams)

        # for creating sparce matrices
        row_indxs = []
        col_indxs = []

        pmi_dat_values = []
        ppmi_dat_values = []
        spmi_dat_values = []
        sppmi_dat_values = []

        # smoothing
        alpha = 0.75
        nca_denom = np.sum(np.array(wwcnt_mat.sum(axis=0)).flatten() ** alpha)
        sum_over_words = np.array(wwcnt_mat.sum(axis=0)).flatten()
        sum_over_words_alpha = sum_over_words ** alpha
        sum_over_contexts = np.array(wwcnt_mat.sum(axis=1)).flatten()

        ii = 0
        for (tok1, tok2), sg_count in skipgram_counts.items():
            ii += 1
            if ii % 1000000 == 0:
                print(f'finished {ii/len(skipgram_counts):.2%} of skipgrams')
            tok1_indx = tok2indx[tok1]
            tok2_indx = tok2indx[tok2]

            nwc = sg_count
            Pwc = nwc / num_skipgrams
            nw = sum_over_contexts[tok1_indx]
            Pw = nw / num_skipgrams
            nc = sum_over_words[tok2_indx]
            Pc = nc / num_skipgrams

            nca = sum_over_words_alpha[tok2_indx]
            Pca = nca / nca_denom

            pmi = np.log2(Pwc / (Pw * Pc))
            ppmi = max(pmi, 0)

            spmi = np.log2(Pwc / (Pw * Pca))
            sppmi = max(spmi, 0)

            row_indxs.append(tok1_indx)
            col_indxs.append(tok2_indx)
            pmi_dat_values.append(pmi)
            ppmi_dat_values.append(ppmi)
            spmi_dat_values.append(spmi)
            sppmi_dat_values.append(sppmi)

        pmi_mat = sparse.csr_matrix((pmi_dat_values, (row_indxs, col_indxs)))
        ppmi_mat = sparse.csr_matrix((ppmi_dat_values, (row_indxs, col_indxs)))
        spmi_mat = sparse.csr_matrix((spmi_dat_values, (row_indxs, col_indxs)))
        sppmi_mat = sparse.csr_matrix((sppmi_dat_values, (row_indxs, col_indxs)))

        print('done')

        # Singular Value Decomposition
        matrix_use = ppmi_mat

        if wordType < 500:
            embedding_size = wordType - 1
        else:
            embedding_size = 500

        uu, ss, vv = linalg.svds(matrix_use, embedding_size)
        print('vocab size: {}'.format(len(unigram_counts)))
        print('embedding size: {}'.format(embedding_size))
        print('uu.shape: {}'.format(uu.shape))
        print('ss.shape: {}'.format(ss.shape))
        print('vv.shape: {}'.format(vv.shape))

        unorm = uu / np.sqrt(np.sum(uu * uu, axis=1, keepdims=True))
        vnorm = vv / np.sqrt(np.sum(vv * vv, axis=0, keepdims=True))
        # word_vecs = unorm
        # word_vecs = vnorm.T
        word_vecs = uu + vv.T
        word_vecs_norm = word_vecs / np.sqrt(np.sum(word_vecs * word_vecs, axis=1, keepdims=True))

        print(word_vecs_norm)

        import numpy as np
        from sklearn.manifold import TSNE
        X_embedded = TSNE(n_components=2).fit_transform(word_vecs_norm)

        # print(word_vecs_norm)

        print(indx2tok)
        print(X_embedded)

        TSNE_dic = {}

        typenum = 0
        for typeeach in indx2tok:
            TSNE_dic[indx2tok[typenum]] = [X_embedded[typenum][0], X_embedded[typenum][1]]
            print(indx2tok[typenum])
            print(X_embedded[typenum][0])
            typenum = typenum + 1

        print(TSNE_dic)

        from numpy import dot
        from numpy.linalg import norm
        import numpy as np
        def cos_sim(A, B):
            return dot(A, B) / (norm(A) * norm(B))

        # print(indx2tok)
        # print(X_embedded)

        target = np.array(TSNE_dic[self.postposition])

        f = open(self.store_route, 'w')
        tsnenum = 0
        for typeeach in indx2tok:
            if indx2tok[tsnenum] != self.postposition:
                source = np.array(TSNE_dic[indx2tok[tsnenum]])
                print(indx2tok[tsnenum], " ", cos_sim(target, source))
                normal_sim = (cos_sim(target, source) + 1) / 2

                data = str(indx2tok[tsnenum]) + "," + str(normal_sim)
                f.write(data + "\n")
            tsnenum = tsnenum + 1
        f.close()


        # print(indx2tok)
        # print(X_embedded)


        #isWork = "System is work!"
        #return isWork