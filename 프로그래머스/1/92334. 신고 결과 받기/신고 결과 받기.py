def solution(id_list, report, k):
    answer = { id_list[i]:0 for i in range(len(id_list)) }
    report = list(set(report))  # 중복 제거
    receivedReport = { id_list[i]:0 for i in range(len(id_list)) }  # 유저별 신고당한 횟수
    userId_to_reportId = { id_list[i]:list() for i in range(len(id_list)) }  # 유저ID와 유저가 신고한 ID
    
    for x in report:
        request, response = x.split()
        receivedReport[response] += 1
        userId_to_reportId[request].append(response)
    
    stopId = list()  # 게시판 이용정지된 ID
    for key, value in receivedReport.items():
        if value >= k :  # k번 이상 신고당한 유저라면 추가
            stopId.append(key)
    
    # 유저ID와 유저가 신고한 ID 중에서 이용정지된 ID가 있다면 +1
    for key, value in userId_to_reportId.items():
        cnt = 0
        for id in stopId:
            if id in value:
                answer[key] += 1
    
    answer = list(answer.values())  # 유저ID와 유저가 신고한 ID의 이용정지된 ID 총 갯수
    
    return answer
