import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 2750. 수 정렬하기
'''
    문제풀이방법 및 접근
'''
# tc = int(input())
# n = [int(input()) for _  in range(tc)]
# n.sort()
# for i in n:
#     print(i)

# --------------------------------------------------

# 2587. 대표값2

# n = [int(input()) for _  in range(5)]
# n.sort()
# print('{}\n{}'.format(sum(n)//5, n[2]))

# --------------------------------------------------

# 25305. 커트라인 

# n, k = map(int, input().split())
# score = list(map(int, input().split()))
# score.sort(reverse=True)
# print(score[k-1])

# --------------------------------------------------

# 2751. 수 정렬하기 2

# import sys
# tc = int(sys.stdin.readline())
# n = [int(sys.stdin.readline()) for _  in range(tc)]
# n.sort()
# for i in n:
#     print(i)

# --------------------------------------------------

# 10989. 수 정렬하기 3

# import sys
# tc = int(sys.stdin.readline())
# n = [0] * 10001
# for _  in range(tc):
#     n[int(sys.stdin.readline())] += 1

# for i in range(1, 10001):
#     for j in range(n[i]):
#         print(i)

# --------------------------------------------------

# 1427. 소트인사이드

# n = list(input())
# n.sort(reverse=True)
# print(''.join(n))

# --------------------------------------------------

# 11650. 좌표정렬하기

# tc = int(input())
# n = [list(map(int, input().split())) for _ in range(tc)]

# n.sort(key=lambda x:(x[0], x[1]))

# for i in n:
#     print(' '.join(map(str, i)))

# --------------------------------------------------

# 11651. 좌표 정렬하기 2

# tc = int(input())
# n = [list(map(int, input().split())) for _ in range(tc)]

# n.sort(key=lambda x:(x[1], x[0]))

# for i in n:
#     print(' '.join(map(str, i)))

# --------------------------------------------------

# 1181. 단어 정렬 
'''
    문제풀이방법 및 접근
        - 길이가 짧은 것부터
        - 길이가 같으면 사전 순으로
'''
# n = int(input())
# words = []
# for _ in range(n):
#     val = input()
#     if [len(val), val] not in words:
#         words.append([len(val), val])

# words.sort(key=lambda x:(x[0], x[1]))

# for length, value in words:
#     print(value)

# --------------------------------------------------

# 10814. 나이순 정렬
'''
    문제풀이방법 및 접근
        - 온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 
        - 회원들을 나이가 증가하는 순, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬
'''
# n = int(input())
# people = []
# for seq in range(1, n+1):
#     age, name = input().split()
#     people.append([int(age), name, seq])

# people.sort(key=lambda x:(x[0], x[2]))

# for age, name, seq in people:
#     print(age, name)

# --------------------------------------------------

# 18870. 좌표 압축
'''
    문제풀이방법 및 접근
        - 현재 인덱스값이 다른 인덱스값보다 크다면 갯수를 센다.
    * sort, dict 함수 활용
'''
n = int(input())
x = list(map(int, input().split()))  # x, y 좌표이므르 중복제거
copy_x = list(set(x[:]))
copy_x.sort()  # 값이 작은 순으로 정렬한다.
dict = {}

for i in range(len(copy_x)):  # 정렬된 순서에 따라 0 ~ n까지 인덱스를 부여한다. 
                              # 인덱스는 나보다 작은 수의 개수라고 생각하면 된다.
    dict[copy_x[i]] = i

for i in x:
    print(dict[i], end=' ')