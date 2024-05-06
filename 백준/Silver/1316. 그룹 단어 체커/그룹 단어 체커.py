def check(word):
    for i in word:
        if i not in st:
            st.append(i)
            curr = i     # 현재문자
        elif curr == i:  # 다음문자와 현재문자가 같다면 반복문 유지
            continue
        else:            # 현재문자가 st에 포함되고 다음문자와 다르다면 False 리턴
            return False
    return True

tc = int(input())
cnt = 0
for t in range(1, tc+1):
    word = input()
    st = list()  # 중복문자확인
    if check(word):  # 그룹 단어라면 True 반환
        cnt += 1
print(cnt)