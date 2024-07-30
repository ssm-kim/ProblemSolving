s = list(input())
answer = len(set(s))  # 1개짜리 부분문자열 (중복제거)
check_dict = dict()
lt, rt = -1, 1
for i in range(1, len(s)):
    for j in range(i, len(s)):
        st = ''.join(s[lt+j:rt+j])   # 2개 이상의 부분 문자열
        if st not in check_dict:
            check_dict[st] = 1
            answer += 1
    lt -= 1
print(answer)