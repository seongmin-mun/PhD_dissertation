class SBEs:

    def __init__(self, method, postposition, postposition_ko, fold, window, function):
        self.method = method
        self.postposition = postposition
        self.postposition_ko = postposition_ko
        self.fold = fold
        self.window = window
        self.function = function

    def Processing(self):
        import numpy as np
        import pandas as pd

        functions = self.function

        functionDicDic = {}

        for function in functions:
            ##Function
            functionDic = {}
            functionDir = "../../Data/Output/" + self.method + "/" + self.postposition + "/" + str(self.fold) + "Fold/" + self.postposition + "_" + function + "_window_" + str(self.window) + ".csv"

            dfFunction = pd.read_csv(functionDir)
            words = dfFunction['word'].tolist()
            sims = dfFunction['similarity'].tolist()
            for k in range(0, len(words)):
                functionDic[words[k]] = sims[k]

            functionDicDic[function] = functionDic

        ##테스트 데이터 사용해서 평가하기
        testDir = "../../Data/Input/Fold/" + str(self.fold) + "Fold/" + self.postposition + "_test_" + str(self.fold) + ".csv"

        # 학습 데이터 불러오기 = content단어로 구성되어 있는 문장
        df = pd.read_csv(testDir)
        # print(df.head())
        headlines = df['Sentence'].tolist()

        countDic = {}  # corect
        frequencyDic = {}

        countDic["Total"] = 0
        frequencyDic["Total"] = 0

        for function in functions:
            countDic[function] = 0
            frequencyDic[function] = 0


        for sentence in headlines:
            originClass = ""

            token = sentence.split(" ")
            for eachToken in token:
                if (self.postposition_ko + "/") in eachToken and "JKB_" in eachToken:
                    originClass = eachToken.replace((self.postposition_ko + "/"), "").replace("JKB_", "")

            classifiedFunc = {}

            funcScore = {}
            matchNum = 0

            for eachToken in token:
                #사전 학습된 데이터에 인풋된 토큰이 없는 경우나 부사격 조사인 경우 패스
                if functionDicDic.get("LOC").get(eachToken.strip()) == None or ((self.postposition_ko + "/") in eachToken and "JKB_" in eachToken):
                    pass
                else:
                    #그렇지 않는 경우, 매치 횟수를 올리고 각 기능별 유사도 점수를 합산해서 딕셔너리에 넣어주기
                    matchNum = matchNum + 1
                    for function in functions:
                        if funcScore.get(function) == None:
                            funcScore[function] = functionDicDic.get(function).get(eachToken.strip())
                        else:
                            funcScore[function] = funcScore.get(function) + functionDicDic.get(function).get(eachToken.strip())


            #각 기능별로 합산된 유사로를 매치된 토큰의 횟수만큼 나누어 분류 딕셔너리에 넣어주
            for function in functions:
                classifiedFunc[function] = funcScore.get(function) / matchNum


            dic_max = max(classifiedFunc.values())

            # 정확히 테스트 문장을 분류한 횟수 각 기능 및 토탈로 카운트 해주기
            for x, y in classifiedFunc.items():
                if y == dic_max:
                    for function in functions:
                        if originClass == function and originClass == x:
                            countDic[function] = countDic.get(function) + 1
                    if originClass == x:
                        countDic["Total"] = countDic.get("Total") + 1

            # 각 테스트 문장의 분류 및 전체 문장 크기에 대해 카운트 해주기
            frequencyDic["Total"] = frequencyDic.get("Total") + 1

            for function in functions:
                if originClass == function:
                    frequencyDic[function] = frequencyDic.get(function) + 1

        finalResult = {}

        totalAccuracy = countDic.get("Total") / frequencyDic.get("Total")
        finalResult["Total"] = totalAccuracy

        for function in functions:
            funcAccuracy = countDic.get(function) / frequencyDic.get(function)
            finalResult[function] = funcAccuracy

        averageAccuracy = 0
        for function in functions:
            averageAccuracy = averageAccuracy+finalResult.get(function)

        finalResult["TotalAverage"] = averageAccuracy / len(functions)

        return finalResult







