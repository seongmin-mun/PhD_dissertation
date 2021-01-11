class PPMI_SVD_Algorithm:

    def __init__ (self, fold, postposition, postposition_ko, window):
        self.fold = fold
        self.postposition = postposition
        self.postposition_ko = postposition_ko
        self.window = window

    def PPMI_SVD_Calculation(self):

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

        trainDir = "../../Data/Input/Fold/" + str(self.fold) + "Fold/"+ self.postposition +"_train_" + str(self.fold) + ".csv"

        # 학습 데이터 불러오기 = content단어로 구성되어 있는 문장
        df = pd.read_csv(trainDir)
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
                print(f'finished {ii / len(headlines):.2%} of headlines')
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

        for j in range(1, self.window):
            # Skipgrams
            # 윈도우 사이즈 적용해서 단어X단어 행렬 생성하기
            back_window = j
            front_window = j
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
                    print(f'finished {iheadline / len(headlines):.2%} of headlines')
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
                    print(f'finished {ii / len(skipgram_counts):.2%} of skipgrams')
                tok1_indx = tok2indx[tok1]
                tok2_indx = tok2indx[tok2]

                row_indxs.append(tok1_indx)
                col_indxs.append(tok2_indx)
                dat_values.append(sg_count)

            wwcnt_mat = sparse.csr_matrix((dat_values, (row_indxs, col_indxs)))
            print('done')

            # normalize each row using L2 norm
            wwcnt_norm_mat = normalize(wwcnt_mat, norm='l2', axis=1)

            ##Word Similarity with Sparse Count Matrices
            def ww_sim(word, mat, topn=len(tok2indx)):
                """Calculate topn most similar words to word"""
                indx = tok2indx[word]
                if isinstance(mat, sparse.csr_matrix):
                    v1 = mat.getrow(indx)
                else:
                    v1 = mat[indx:indx + 1, :]
                sims = cosine_similarity(mat, v1).flatten()
                sindxs = np.argsort(-sims)
                sim_word_scores = [(indx2tok[sindx], sims[sindx]) for sindx in sindxs[0:topn]]
                return sim_word_scores

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
                    print(f'finished {ii / len(skipgram_counts):.2%} of skipgrams')
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

            ##Word Similarity with Sparse PMI Matrices
            # print(ww_sim('(으)로_FNS/JKB', ppmi_mat))
            # print(ww_sim('(으)로_INS/JKB', ppmi_mat))
            # print(ww_sim('(으)로_DIR/JKB', ppmi_mat))

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

            ##Word Similarity
            def word_sim_report(word, sim_mat):
                output = {}
                sim_word_scores = ww_sim(word, word_vecs)
                for sim_word, sim_score in sim_word_scores:
                    output[sim_word] = ((sim_score + 1) / 2)
                return output

            functionEy = ["LOC", "GOL", "EFF", "CRT", "THM", "INS", "AGT", "FNS"]
            functionEyse = ["SRC", "LOC"]
            functionLo = ["FNS", "INS", "DIR", "EFF", "CRT", "LOC"]

            if self.postposition == "Ey":
                for function in functionEy:
                    word = self.postposition_ko + "/JKB" + "_" + function
                    result = word_sim_report(word, word_vecs)

                    outDir = "../../Data/Output/PPMI_SVD/" + self.postposition + "/" + str(self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(j) + ".csv"

                    f = open(outDir, 'w')
                    head = "word,similarity"
                    f.write(head + "\n")
                    for key, value in result.items():
                        data = str(key) + "," + str(value)
                        f.write(data + "\n")
                    f.close()




            elif self.postposition == "Eyse":
                for function in functionEyse:
                    word = self.postposition_ko + "/JKB" + "_" + function
                    result = word_sim_report(word, word_vecs)

                    outDir = "../../Data/Output/PPMI_SVD/" + self.postposition + "/" + str(self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(
                        j) + ".csv"

                    f = open(outDir, 'w')
                    head = "word,similarity"
                    f.write(head + "\n")
                    for key, value in result.items():
                        data = str(key) + "," + str(value)
                        f.write(data + "\n")
                    f.close()

            elif self.postposition == "Lo":
                for function in functionLo:
                    word = self.postposition_ko + "/JKB" + "_" + function
                    result = word_sim_report(word, word_vecs)

                    outDir = "../../Data/Output/PPMI_SVD/" + self.postposition + "/" + str(self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(
                        j) + ".csv"

                    f = open(outDir, 'w')
                    head = "word,similarity"
                    f.write(head + "\n")
                    for key, value in result.items():
                        data = str(key) + "," + str(value)
                        f.write(data + "\n")
                    f.close()



