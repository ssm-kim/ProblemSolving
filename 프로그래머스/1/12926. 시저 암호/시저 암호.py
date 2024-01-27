def solution(s, n):
    # chr() ord() 활용
    # A ~ Z 65 ~ 90 / a ~ z 97 ~ 122
    answer = ''    
    for i in s:
        if i.isalpha():
            if ord(i) in range(65, 91):
                if ord(i)+n > 90:
                    answer += chr(ord(i)+n-26)
                else:
                    answer += chr(ord(i)+n)
            elif ord(i) in range(97, 123): 
                
                if ord(i)+n > 122:
                    answer += chr(ord(i)+n-26)
                else:
                    answer += chr(ord(i)+n)
        else:
            answer += i

    return answer