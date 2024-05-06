def check(word):
    for i in word:
        if i not in st:
            st.append(i)
            curr = i
        elif curr == i:
            continue
        else:
            return False
    return True

tc = int(input())
cnt = 0
for t in range(1, tc+1):
    word = input()
    st = list()
    if check(word):
        cnt += 1
print(cnt)