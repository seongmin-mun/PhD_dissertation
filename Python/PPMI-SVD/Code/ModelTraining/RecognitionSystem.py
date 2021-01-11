class Recognition_System:

    def setdata(self, windowsize, postposition, similarity_route):
        self.windowsize = windowsize
        self.postposition = postposition
        self.similarity_route = similarity_route

    def Recognition_Processing(self):
        # install packages
        import numpy as np
        import pandas as pd


        # 학습 데이터 불러오기 = content단어로 구성되어 있는 문장
        df = pd.read_csv()
        print(df.head())
        headlines = df['Sentence'].tolist()
        headlines = [[tok for tok in headline.split()] for headline in headlines]
        # remove single word headlines
        # 하나의 단어로 구성된 문장은 제거한다.
        headlines = [hl for hl in headlines if len(hl) > 1]
        # show results
        # 결과 확인
        print(headlines[0:20])