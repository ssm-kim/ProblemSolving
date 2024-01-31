def solution(answers):
    answer, tmp = [], []
    students = [ [1, 2, 3, 4, 5],
                 [2, 1, 2, 3, 2, 4, 2, 5],
                 [3, 3, 1, 1, 2, 2, 4, 4, 5, 5] ]
    
    for student in students:
        cnt, idx, length = 0, 0, len(answers)
        
        for i in range(length):            # 총 문제만큼 순회
            if (idx % len(student)) == 0:  # 수포자들마다 인덱스번호를 따로 갱신/부여 (5, 8, 10)
                idx = 0
            if student[idx] == answers[i]:
                cnt += 1
            idx += 1
        tmp.append(cnt)
    
    for i in range(len(tmp)):
        if max(tmp) == tmp[i]:
            answer.append(i+1)
            
    return answer