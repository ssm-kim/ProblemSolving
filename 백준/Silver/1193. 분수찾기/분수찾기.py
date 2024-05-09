n = int(input())
line = 0     # 사선 라인
max_num = 0  # 사선에서 가장 큰 횟수

while max_num < n:
    line += 1        # 1 2
    max_num += line  # 1 3

diff = max_num - n

if line % 2 == 0:  # 짝수
    top = line - diff
    floor = diff + 1
else:              # 홀수
    top = diff + 1
    floor = line - diff

print('{}/{}'.format(top, floor))