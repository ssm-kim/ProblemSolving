n = int(input())
start = 665  # 첫 시작 666-1
cnt = 0      # 종말의 숫자라면 카운트

while True:
    if n == cnt:
        break
    start += 1
    if '666' in str(start):
        cnt += 1
print(start)