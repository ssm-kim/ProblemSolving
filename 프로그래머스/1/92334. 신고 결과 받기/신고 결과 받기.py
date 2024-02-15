def solution(id_list, report, k):
    answer = []
    report = list(set(report))
    receivedReport = { id_list[i]:0 for i in range(len(id_list)) }
    userId_to_reportId = { id_list[i]:list() for i in range(len(id_list)) }
    
    for x in report:
        request, response = x.split()
        receivedReport[response] += 1
        userId_to_reportId[request].append(response)
    
    
    stopId = list()
    for key, value in receivedReport.items():
        if value >= k :
            stopId.append(key)
    
    for key, value in userId_to_reportId.items():
        cnt = 0
        for id in stopId:
            if id in value:
                cnt += 1
        answer.append(cnt)    
    
    return answer