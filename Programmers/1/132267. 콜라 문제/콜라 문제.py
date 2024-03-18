def solution(a, b, n):
    answer = 0
    while n//a >= 1:
        answer += b * (n//a)
        n = (n%a) + b * (n//a)
        
    return answer