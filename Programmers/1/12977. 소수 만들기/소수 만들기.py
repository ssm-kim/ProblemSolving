from itertools import combinations

def solution(nums):
    answer = 0
    check = [True, True] + [False] * 3000
    for i in combinations(nums, 3):
        tmp = sum(i)
        print(tmp, end=' ')
        prime = 0
        for j in range(1, tmp+1):
            if tmp%j == 0:
                prime += 1
            if prime >= 3:
                break
        else:
            answer += 1
        
    return answer