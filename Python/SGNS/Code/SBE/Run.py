# #Train
#
# from Code.Algorithm.PPMI_SVD import Algorithm
#
# ppmi_svd = Algorithm(11)
# ppmi_svd.Calculation()

# #ppmi_svd 학습 유사도 계산

from SBE.Code.Algorithm.PPMI_SVD import PPMI_SVD_Algorithm
from SBE.Code.Algorithm.Word2Vec import SGNS_Algorithm

methods = ["PPMI_SVD","SGNS"]
foldNum = [1,2,3,4,5,6,7,8,9,10]
windowNum = [1,2,3,4,5,6,7,8,9,10]
postpositions = ["Ey","Eyse","Lo"]
postpositions_ko = ["에", "에서","(으)로"]
#/JKB

functionEy = ["LOC","GOL","EFF","CRT","THM","INS","AGT","FNS"]
functionEyse = ["SRC","LOC"]
functionLo = ["FNS","INS","DIR","EFF","CRT","LOC"]

functionEyR = ["Total","TotalAverage","LOC","GOL","EFF","CRT","THM","INS","AGT","FNS"]
functionEyseR = ["Total","TotalAverage","SRC","LOC"]
functionLoR = ["Total","TotalAverage","FNS","INS","DIR","EFF","CRT","LOC"]

# # for fold in foldNum:
# #     for postposition in postpositions:
# #         if postposition == "Ey":
# #             postposition_ko = postpositions_ko[0]
# #             ppmi_svd = PPMI_SVD_Algorithm(fold, postposition, postposition_ko,  11)
# #             ppmi_svd.PPMI_SVD_Calculation()
# #
# #         elif postposition == "Eyse":
# #             postposition_ko = postpositions_ko[1]
# #             ppmi_svd = PPMI_SVD_Algorithm(fold, postposition, postposition_ko,  11)
# #             ppmi_svd.PPMI_SVD_Calculation()
# #
# #         elif postposition == "Lo":
# #             postposition_ko = postpositions_ko[2]
# #             ppmi_svd = PPMI_SVD_Algorithm(fold, postposition, postposition_ko,  11)
# #             ppmi_svd.PPMI_SVD_Calculation()
#
#
# #sgns 학습 유사도 계산
#
# # for fold in foldNum:
# #     for postposition in postpositions:
# #         if postposition == "Ey":
# #             postposition_ko = postpositions_ko[0]
# #             sgns = SGNS_Algorithm(fold, postposition, postposition_ko,  11)
# #             sgns.SGNS_Calculation()
# #
# #         elif postposition == "Eyse":
# #             postposition_ko = postpositions_ko[1]
# #             sgns = SGNS_Algorithm(fold, postposition, postposition_ko,  11)
# #             sgns.SGNS_Calculation()
# #
# #         elif postposition == "Lo":
# #             postposition_ko = postpositions_ko[2]
# #             sgns = SGNS_Algorithm(fold, postposition, postposition_ko,  11)
# #             sgns.SGNS_Calculation()


from SBE.Code.Evaluation.SimilarityBasedEstimation import SBEs

#Similarity Based Estimate
for method in methods:
    for postposition in postpositions:
        # if postposition == "Ey":
        #     #print("Postposition :", postposition)
        #     postposition_ko = postpositions_ko[0]
        #     for window in windowNum:
        #         # print("")
        #         # print("Window :", window)
        #         foldAverage = {}
        #         for function in functionEyR:
        #             foldAverage[function] = 0;
        #         for fold in foldNum:
        #             #print("Fold :", fold)
        #             sbm = SBEs(method, postposition, postposition_ko, fold, window, functionEy)
        #             result = sbm.Processing()
        #             #print("TotalAverage : ", result.get("TotalAverage"))
        #             #print(result)
        #             for function in functionEyR:
        #                 foldAverage[function] = foldAverage.get(function) + result.get(function)
        #         for function in functionEyR:
        #             foldAverage[function] = foldAverage.get(function) / 10
        #
        #         #print(foldAverage.get("FNS"),",", end=' ')
        #         # print("Window Fold Average : ", foldAverage)
        #         # LOC, GOL, EFF, CRT, THM, INS, AGT, FNS
        #         # "#0066CC", "#000080", "#FF00FF", "#FF99CC", "#666699", "#003300", "#993366", "#FF6600"


        # elif postposition == "Eyse":
        #     print("Postposition :", postposition)
        #     postposition_ko = postpositions_ko[1]
        #     for window in windowNum:
        #         # print("")
        #         # print("Window :", window)
        #         foldAverage = {}
        #         for function in functionEyseR:
        #             foldAverage[function] = 0;
        #         for fold in foldNum:
        #             #print("Fold :", fold)
        #             sbm = SBEs(method, postposition, postposition_ko, fold, window, functionEyse)
        #             result = sbm.Processing()
        #             #print("TotalAverage : ", result.get("TotalAverage"))
        #             #print(result)
        #             for function in functionEyseR:
        #                 foldAverage[function] = foldAverage.get(function) + result.get(function)
        #         for function in functionEyseR:
        #             foldAverage[function] = foldAverage.get(function) / 10
        #         print(foldAverage.get("LOC"), ",", end=' ')
        #         # print("Window Fold Average : ", foldAverage)
        #         # SRC, LOC
        #         # "#FFCC00", "#0066CC"

        if postposition == "Lo":
            print("Postposition :", postposition)
            postposition_ko = postpositions_ko[2]
            for window in windowNum:
                # print("")
                # print("Window :", window)
                foldAverage = {}
                for function in functionLoR:
                    foldAverage[function] = 0;
                for fold in foldNum:
                    #print("Fold :", fold)
                    sbm = SBEs(method, postposition, postposition_ko, fold, window, functionLo)
                    result = sbm.Processing()
                    #print("TotalAverage : ",result.get("TotalAverage"))
                    #print(result)
                    for function in functionLoR:
                        foldAverage[function] = foldAverage.get(function) + result.get(function)
                for function in functionLoR:
                    foldAverage[function] = foldAverage.get(function) / 10
                print(foldAverage.get("LOC"), ",", end=' ')
                # print("Window Fold Average : ", foldAverage)
                # "FNS", "INS", "DIR", "EFF", "CRT", "LOC"
                # "#FF6600", "#003300", "#5b2e90", "#FF00FF", "#FF99CC", "#0066CC"




#
#
# #ppmi_svd TSNE 계산
#
# from SBE.Code.Algorithm.PPMI_SVD_tSNE import PPMI_SVD_tSNE_Algorithm
# from SBE.Code.Algorithm.Word2Vec_tSNE import SGNS_tSNE_Algorithm
#
# methods = ["PPMI_SVD","SGNS"]
#
# windowNum = [1,2,3,4,5,6,7,8,9,10]
# postpositions = ["Ey","Eyse","Lo"]
# postpositions_ko = ["에", "에서","(으)로"]
# #/JKB
#
# functionEy = ["LOC","GOL","EFF","CRT","THM","INS","AGT","FNS"]
# functionEyse = ["SRC","LOC"]
# functionLo = ["FNS","INS","DIR","EFF","CRT","LOC"]
#
# functionEyR = ["Total","TotalAverage","LOC","GOL","EFF","CRT","THM","INS","AGT","FNS"]
# functionEyseR = ["Total","TotalAverage","SRC","LOC"]
# functionLoR = ["Total","TotalAverage","FNS","INS","DIR","EFF","CRT","LOC"]
#
# for postposition in postpositions:
#     if postposition == "Ey":
#         postposition_ko = postpositions_ko[0]
#         sgns = SGNS_tSNE_Algorithm(postposition, postposition_ko, 11)
#         sgns.SGNS_tSNE_Calculation()
#
#     elif postposition == "Eyse":
#         postposition_ko = postpositions_ko[1]
#         sgns = SGNS_tSNE_Algorithm(postposition, postposition_ko, 11)
#         sgns.SGNS_tSNE_Calculation()
#
#     elif postposition == "Lo":
#         postposition_ko = postpositions_ko[2]
#         sgns = SGNS_tSNE_Algorithm(postposition, postposition_ko, 11)
#         sgns.SGNS_tSNE_Calculation()

# for method in methods:
#     if method == "PPMI_SVD":
#         for postposition in postpositions:
#             if postposition == "Ey":
#                 postposition_ko = postpositions_ko[0]
#                 ppmi_svd = PPMI_SVD_tSNE_Algorithm(postposition, postposition_ko,  11)
#                 ppmi_svd.PPMI_SVD_tSNE_Calculation()
#
#             elif postposition == "Eyse":
#                 postposition_ko = postpositions_ko[1]
#                 ppmi_svd = PPMI_SVD_tSNE_Algorithm(postposition, postposition_ko,  11)
#                 ppmi_svd.PPMI_SVD_tSNE_Calculation()
#
#             elif postposition == "Lo":
#                 postposition_ko = postpositions_ko[2]
#                 ppmi_svd = PPMI_SVD_tSNE_Algorithm(postposition, postposition_ko,  11)
#             ppmi_svd.PPMI_SVD_tSNE_Calculation()
#
#     if method == "SGNS":
#         for postposition in postpositions:
#             if postposition == "Ey":
#                 postposition_ko = postpositions_ko[0]
#                 sgns = SGNS_tSNE_Algorithm(postposition, postposition_ko, 11)
#                 sgns.SGNS_tSNE_Calculation()
#
#             elif postposition == "Eyse":
#                 postposition_ko = postpositions_ko[1]
#                 sgns = SGNS_tSNE_Algorithm(postposition, postposition_ko, 11)
#                 sgns.SGNS_tSNE_Calculation()
#
#             elif postposition == "Lo":
#                 postposition_ko = postpositions_ko[2]
#                 sgns = SGNS_tSNE_Algorithm(postposition, postposition_ko, 11)
#                 sgns.SGNS_tSNE_Calculation()


#sgns TSNE 계산

#sgns 학습 유사도 계산

# for fold in foldNum:
#     for postposition in postpositions:
#         if postposition == "Ey":
#             postposition_ko = postpositions_ko[0]
#             sgns = SGNS_Algorithm(fold, postposition, postposition_ko,  11)
#             sgns.SGNS_Calculation()
#
#         elif postposition == "Eyse":
#             postposition_ko = postpositions_ko[1]
#             sgns = SGNS_Algorithm(fold, postposition, postposition_ko,  11)
#             sgns.SGNS_Calculation()
#
#         elif postposition == "Lo":
#             postposition_ko = postpositions_ko[2]
#             sgns = SGNS_Algorithm(fold, postposition, postposition_ko,  11)
#             sgns.SGNS_Calculation()


