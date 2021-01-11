class SGNS_Algorithm:

    def __init__(self, fold, postposition, postposition_ko, window):
        self.fold = fold
        self.postposition = postposition
        self.postposition_ko = postposition_ko
        self.window = window

    def SGNS_Calculation(self):

        # https://www.kaggle.com/gabrielaltay/word-vectors-from-pmi-matrix

        # https://www.kaggle.com/gabrielaltay/word-vectors-from-pmi-matrix

        # install packages
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

        trainDir = "../../Data/Input/Fold/" + str(self.fold) + "Fold/" + self.postposition + "_train_" + str(self.fold) + ".csv"

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

            from gensim.models import Word2Vec

            # https://ratsgo.github.io/natural%20language%20processing/2017/03/08/word2vec/

            # https://towardsdatascience.com/a-beginners-guide-to-word-embedding-with-gensim-word2vec-model-5970fa56cc92

            # size: The number of dimensions of the embeddings and the default is 100.
            # window: The maximum distance between a target word and words around the target word. The default window is 5.
            # min_count: The minimum count of words to consider when training the model; words with occurrence less than this count will be ignored.The default for min_count is 5.
            # workers: The number of partitions during training and the default workers is 3.
            # sg: The training algorithm, either CBOW(0) or skip gram(1). The default training algorithm is CBOW.

            if wordType < 500:
                embedding_size = wordType - 1
            else:
                embedding_size = 500

            embedding_model = Word2Vec(headlines, size=embedding_size, window=j, min_count=0, workers=4, iter=100, sg=1, negative=15, ns_exponent=0.75)

            matrix = []
            indxnum = 0
            for typeeach in indx2tok:
                line = list(embedding_model[indx2tok[typeeach]])
                matrix.append(line)
                indxnum = indxnum + 1

            embedded_matrix = np.array(matrix, dtype=np.float64)

            print(embedded_matrix)

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

            ##Word Similarity
            def word_sim_report(word, sim_mat):
                output = {}
                sim_word_scores = ww_sim(word, embedded_matrix)
                for sim_word, sim_score in sim_word_scores:
                    output[sim_word] = ((sim_score + 1) / 2)
                return output



            functionEy = ["LOC", "GOL", "EFF", "CRT", "THM", "INS", "AGT", "FNS"]
            functionEyse = ["SRC", "LOC"]
            functionLo = ["FNS", "INS", "DIR", "EFF", "CRT", "LOC"]

            if self.postposition == "Ey":
                for function in functionEy:
                    word = self.postposition_ko + "/JKB" + "_" + function
                    result = word_sim_report(word, embedded_matrix)

                    outDir = "../../Data/Output/SGNS/" + self.postposition + "/" + str(
                        self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(j) + ".csv"

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
                    result = word_sim_report(word, embedded_matrix)

                    outDir = "../../Data/Output/SGNS/" + self.postposition + "/" + str(
                        self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(
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
                    result = word_sim_report(word, embedded_matrix)

                    outDir = "../../Data/Output/SGNS/" + self.postposition + "/" + str(
                        self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(
                        j) + ".csv"

                    f = open(outDir, 'w')
                    head = "word,similarity"
                    f.write(head + "\n")
                    for key, value in result.items():
                        data = str(key) + "," + str(value)
                        f.write(data + "\n")
                    f.close()
