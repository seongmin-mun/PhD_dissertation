class SVD_Algorithm:

    def setdata(self, windowsize, iteration_num, postposition, train_route, store_route):
        self.windowsize = windowsize
        self.iteration_num = iteration_num
        self.postposition = postposition
        self.train_route = train_route
        self.store_route = store_route

    def SVD_Calculation(self):

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

        # print('skipgram_counts',skipgram_counts)
        # print('tok2indx', tok2indx)

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
        wwcnt_norm_mat = normalize(wwcnt_mat, norm='l2', axis=1)
        print('done')

        print('wwcnt_mat', wwcnt_mat)



        # Singular Value Decomposition
        matrix_use = wwcnt_norm_mat

        if wordType < 500:
            embedding_size = wordType-1
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

        print('type',type(word_vecs_norm))

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
                print(indx2tok[tsnenum]," ",cos_sim(target,source))
                normal_sim = (cos_sim(target,source)+1)/2

                data = str(indx2tok[tsnenum])+","+str(normal_sim)
                f.write(data+"\n")
            tsnenum = tsnenum + 1
        f.close()



        #isWork = "System is work!"
        #return isWork