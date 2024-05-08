a, b, v = map(int, input().split())
goUp = a - b  # 밤낮포함해서 하루에 올라갈 수 있는 높이
keep = v - b  # 미끄러지지 않는 것
answer = (keep // goUp)

if keep % goUp != 0:  # 0이 아니라면 미끄러진 것이므로 +1을 해준다.
    answer += 1
print(answer)