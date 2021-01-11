class SGNS_Algorithm:

    def setdata(self, windowsize, iteration_num, postposition, train_route, store_route):
        self.windowsize = windowsize
        self.iteration_num = iteration_num
        self.postposition = postposition
        self.train_route = train_route
        self.store_route = store_route

    def SGNS_Calculation(self):

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


        from gensim.models import Word2Vec

        #https://ratsgo.github.io/natural%20language%20processing/2017/03/08/word2vec/

        #https://towardsdatascience.com/a-beginners-guide-to-word-embedding-with-gensim-word2vec-model-5970fa56cc92

        #size: The number of dimensions of the embeddings and the default is 100.
        #window: The maximum distance between a target word and words around the target word. The default window is 5.
        #min_count: The minimum count of words to consider when training the model; words with occurrence less than this count will be ignored.The default for min_count is 5.
        #workers: The number of partitions during training and the default workers is 3.
        #sg: The training algorithm, either CBOW(0) or skip gram(1). The default training algorithm is CBOW.

        if wordType < 500:
            embedding_size = wordType - 1
        else:
            embedding_size = 500

        embedding_model = Word2Vec(headlines, size=embedding_size, window=self.windowsize, min_count=0, workers=4, iter=self.iteration_num, sg=1)

        matrix = []
        indxnum = 0
        for typeeach in indx2tok:
            line = list(embedding_model[indx2tok[typeeach]])
            matrix.append(line)
            indxnum = indxnum + 1

        embedded_matrix = np.array(matrix, dtype=np.float64)

        print(embedded_matrix)

        import numpy as np
        from sklearn.manifold import TSNE
        X_embedded = TSNE(n_components=2).fit_transform(embedded_matrix)

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

    # print(X_embedded)


        #isWork = "System is work!"
        #return isWork