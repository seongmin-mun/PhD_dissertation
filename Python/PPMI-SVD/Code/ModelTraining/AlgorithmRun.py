
particle = ["Ey","Eyse","Lo"]
particle_ko = ["에/JKB","에서/JKB","(으)로/JKB"]
function_ey = ["AGT","CRT","EFF","FNS","GOL","INS","LOC","THM"]
function_eyse = ["LOC","SRC"]
function_lo = ["CRT","DIR","EFF","FNS","INS","LOC"]


from Code.Algorithms.SVD import SVD_Algorithm
svd_class = SVD_Algorithm()
svd_class.setdata(2, 10, particle_ko[0], "../../Data/ByParticles/Train/Ey/Ey_AGT_contentOnly.csv","../../Data/test.txt")
svd_class.SVD_Calculation()



#SVD
# first_num = 0
# for parti in particle:
#     if parti == "Ey":
#         # pass
#         for func in function_ey:
#             print(parti," ",func)
#             for win in range(1, 11):
#                 from Code.Algorithms.SVD import SVD_Algorithm
#                 svd_class = SVD_Algorithm()
#                 svd_class.setdata(win, 10, particle_ko[first_num], "../../Data/ByParticles/Train/"+parti+"/"+parti+"_"+func+"_contentOnly.csv","../../Data/Similarity/SVD/"+parti+"/"+parti+"_"+func+"_window"+str(win)+".txt")
#                 svd_class.SVD_Calculation()
#     if parti == "Eyse":
#         for func in function_eyse:
#             print(parti, " ", func)
#             for win in range(1, 11):
#                 from Code.Algorithms.SVD import SVD_Algorithm
#
#                 svd_class = SVD_Algorithm()
#                 svd_class.setdata(win, 10, particle_ko[first_num],
#                                   "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
#                                   "../../Data/Similarity/SVD/" + parti + "/" + parti + "_" + func + "_window" + str(win) + ".txt")
#                 svd_class.SVD_Calculation()
#     if parti == "Lo":
#         for func in function_lo:
#             print(parti, " ", func)
#             for win in range(1, 11):
#                 from Code.Algorithms.SVD import SVD_Algorithm
#
#                 svd_class = SVD_Algorithm()
#                 svd_class.setdata(win, 10, particle_ko[first_num],
#                                   "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
#                                   "../../Data/Similarity/SVD/" + parti + "/" + parti + "_" + func + "_window" + str(win) + ".txt")
#                 svd_class.SVD_Calculation()
#     first_num = first_num + 1


# #SVD_PPMI
# first_num = 0
# for parti in particle:
#     if parti == "Ey":
#         for func in function_ey:
#             print(parti," ",func)
#             for win in range(1, 11):
#                 from Code.Algorithms.SVDwithPPMI import SVD_PPMI_Algorithm
#                 svd_ppmi_class = SVD_PPMI_Algorithm()
#                 svd_ppmi_class.setdata(win, 10, particle_ko[first_num],
#                                        "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
#                                        "../../Data/Similarity/SVD_PPMI/" + parti + "/" + parti + "_" + func + "_window" + str(
#                                            win) + ".txt")
#                 svd_ppmi_class.SVD_PPMI_Calculation()
#     if parti == "Eyse":
#         for func in function_eyse:
#             print(parti, " ", func)
#             for win in range(1, 11):
#                 from Code.Algorithms.SVDwithPPMI import SVD_PPMI_Algorithm
#                 svd_ppmi_class = SVD_PPMI_Algorithm()
#                 svd_ppmi_class.setdata(win, 10, particle_ko[first_num],
#                                        "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
#                                        "../../Data/Similarity/SVD_PPMI/" + parti + "/" + parti + "_" + func + "_window" + str(
#                                            win) + ".txt")
#                 svd_ppmi_class.SVD_PPMI_Calculation()
#     if parti == "Lo":
#         for func in function_lo:
#             print(parti, " ", func)
#             for win in range(1, 11):
#                 from Code.Algorithms.SVDwithPPMI import SVD_PPMI_Algorithm
#                 svd_ppmi_class = SVD_PPMI_Algorithm()
#                 svd_ppmi_class.setdata(win, 10, particle_ko[first_num],
#                                   "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
#                                   "../../Data/Similarity/SVD_PPMI/" + parti + "/" + parti + "_" + func + "_window" + str(win) + ".txt")
#                 svd_ppmi_class.SVD_PPMI_Calculation()
#     first_num = first_num + 1




# #SGNS
first_num = 0
for parti in particle:
    if parti == "Ey":
        for func in function_ey:
            print(parti," ",func)
            for win in range(1, 11):
                from Code.Algorithms.SGNS import SGNS_Algorithm

                sgns_class = SGNS_Algorithm()
                sgns_class.setdata(win, 10, particle_ko[first_num],
                                   "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
                                   "../../Data/Similarity/SGNS/" + parti + "/" + parti + "_" + func + "_window" + str(
                                       win) + ".txt")
                sgns_class.SGNS_Calculation()
    if parti == "Eyse":
        for func in function_eyse:
            print(parti, " ", func)
            for win in range(1, 11):
                from Code.Algorithms.SGNS import SGNS_Algorithm

                sgns_class = SGNS_Algorithm()
                sgns_class.setdata(win, 10, particle_ko[first_num],
                                   "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
                                   "../../Data/Similarity/SGNS/" + parti + "/" + parti + "_" + func + "_window" + str(
                                       win) + ".txt")
                sgns_class.SGNS_Calculation()
    if parti == "Lo":
        for func in function_lo:
            print(parti, " ", func)
            for win in range(1, 11):
                from Code.Algorithms.SGNS import SGNS_Algorithm
                sgns_class = SGNS_Algorithm()
                sgns_class.setdata(win, 10, particle_ko[first_num],
                                  "../../Data/ByParticles/Train/" + parti + "/" + parti + "_" + func + "_contentOnly.csv",
                                  "../../Data/Similarity/SGNS/" + parti + "/" + parti + "_" + func + "_window" + str(win) + ".txt")
                sgns_class.SGNS_Calculation()
    first_num = first_num + 1

