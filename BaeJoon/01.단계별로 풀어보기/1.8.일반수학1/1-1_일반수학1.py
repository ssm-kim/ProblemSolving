import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 2745. 진법 변환
'''
    문제풀이방법 및 접근
'''
# n, b = input().split()
# num_list = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
# answer = 0
# for i, num in enumerate(n[::-1]):
#     answer += (int(b)**i) * num_list.index(num)
# print(answer)

# --------------------------------------------------

# 11005. 진법 변환 2
'''
    문제풀이방법 및 접근
'''
# n, b = map(int, input().split())
# num_list = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
# answer = ''
# while n > 0:
#     remain = (n % b)
#     n = n // b
#     answer = num_list[remain] + answer
# print(answer)

# --------------------------------------------------

# 2720. 세탁소 사장 동혁
'''
    문제풀이방법 및 접근
'''
# tc = int(input())
# dollars = [25, 10, 5, 1]
# for _ in range(tc):
#     c = int(input())
#     answer = list()
#     for i in dollars:
#         remain = c // i
#         answer.append(remain)
#         c %= i
#     print(' '.join(map(str, answer)))

# --------------------------------------------------

# 2903. 중앙 이동 알고리즘
'''
    문제풀이방법 및 접근
'''
# n = int(input())
# col = 2     # 초기 가로세로 길이는 2
# area = [4]  # 넓이는 4
# for i in range(1, n+1):
#     col = col * 2 - 1
#     area.append(col**2)
# print(area[n])

# --------------------------------------------------

# 2292. 벌집
'''
    문제풀이방법 및 접근
        - 6개씩 방이 늘어난다.
'''
# n = int(input())
# idx, cur_pos = 1, 1
# while cur_pos < n:
#     cur_pos = cur_pos + (6*idx)
#     idx += 1
# print(idx)

# --------------------------------------------------

# 1193. 분수찾기
'''
    문제풀이방법 및 접근
'''
# n = int(input())
# line = 0     # 사선 라인
# max_num = 0  # 사선에서 가장 큰 횟수

# while max_num < n:
#     line += 1        # 1 2
#     max_num += line  # 1 3

# diff = max_num - n

# if line % 2 == 0:  # 짝수
#     top = line - diff
#     floor = diff + 1
# else:              # 홀수
#     top = diff + 1
#     floor = line - diff

# print('{}/{}'.format(top, floor))

# --------------------------------------------------

# 2869. 달팽이는 올라가고 싶다
'''
    문제풀이방법 및 접근
        - 하루에 올라갈 수 있는 최대 높이
        - 미끄러지지 않는 부분 구하기
        - 미끄러진것이라면 하루를 더해준다.
'''
# a, b, v = map(int, input().split())
# goUp = a - b  # 밤낮포함해서 하루에 올라갈 수 있는 높이
# keep = v - b  # 미끄러지지 않는 것
# answer = (keep // goUp)

# if keep % goUp != 0:  # 0이 아니라면 미끄러진 것이므로 +1을 해준다.
#     answer += 1
# print(answer)