def solution(number):
    answer = 0
    
    for i in range(len(number)):
        for j in range(i+1, len(number)):
            for k in range(j+1, len(number)):
                sum = number[i] + number[j] + number[k]
                if sum == 0:
                    answer += 1
    return answer