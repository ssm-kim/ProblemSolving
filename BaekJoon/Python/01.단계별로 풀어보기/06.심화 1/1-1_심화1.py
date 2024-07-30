import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 3003. 킹, 퀸, 룩, 비숍, 나이트, 폰
# chess = list(map(int, input().split()))
# peace = [1, 1, 2, 2, 2, 8]

# for i, j in zip(chess, peace):
#     need = j-i
#     print(need, end=' ')
        
# --------------------------------------------------

# 2444. 별 찍기 - 7
# n = int(input())
# last = n*2-1
# answer = [[' '] * last for _ in range(n*2-1)]
# half = last//2
# for i in range(last):
#     if i > half:
#         lt, rt = i-half, last+half-i-1
#     else:
#         lt, rt = half-i, half+i
#     for j in range(lt, rt+1):
#         answer[i][j] = '*'
    
#     print('{}'.format(''.join(answer[i]).rstrip()))

# --------------------------------------------------

# 10988.팰린드롬인지 확인하기
# s = input()
# answer = '1'
# for i in range(len(s)//2):
#     if s[i] != s[-i-1]:
#         answer = '0'
#         break
# print(answer)

# --------------------------------------------------

# 1157. 단어 공부
# s = input().upper()
# words = dict()
# for i in s:
#     if i not in words:
#         words[i] = 1
#     else:
#         words[i] += 1

# cnt = 0
# for k, v in words.items():
#     if v == max(words.values()):
#         cnt += 1
#         answer = k
# if cnt == 1:
#     print(answer)
# else:
#     print('?')

# --------------------------------------------------

# 2941. 크로아티아 알파벳
'''
    문제풀이방법 및 접근
        - 빈 문자열 변수에 문자를 하나씩 더한다.
        - 크로아티아알파벳이라면 cnt +1를 해주고 문자열 변수는 초기화한다.
        - 크로아티아알파벳 문자들의 최대길이는 3이므로 문자열 변수 길이가 3이 되었음에도
          크로아티아알파벳이 아니라면 cnt +3를 해주고 문자열 변수를 초기화한다.
          
'''
# croatia_alpha = ['c=', 'c-', 'dz=', 'd-',
#                  'lj', 'nj', 's=', 'z=']
# s = input()
# build, cnt = '', 0
# for i in croatia_alpha:
#     if i in s:
#         cnt += s.count(i)
#         s = s.replace(i, ' ')  # 크로아티아 알파벳을 공백으로 치환

# s = s.replace(' ', '')  # 공백제거
# cnt += len(s)

# print(cnt)

# --------------------------------------------------

# 1316. 그룹 단어 체커
'''
    문제풀이방법 및 접근
        - 그룹 단어란 단어에 존재하는 모든 문자에 대해서, 각 문자가 연속해서 나타나는 경우만을 말한다.
        - ccazzzzbb는 c, a, z, b가 모두 연속해서 나타나고, kin도 k, i, n이 연속해서 나타남.
        - aabbbccb는 b가 떨어져서 나타나기 때문에 그룹 단어가 아니다.
'''

# 01번 방법
# def check(word):
#     for i in word:
#         if i not in st:
#             st.append(i)
#             curr = i     # 현재문자
#         elif curr == i:  # 다음문자와 현재문자가 같다면 반복문 유지
#             continue
#         else:            # 현재문자가 st에 포함되고 다음문자와 다르다면 False 리턴
#             return False
#     return True

# tc = int(input())
# cnt = 0
# for t in range(1, tc+1):
#     word = input()
#     st = list()  # 중복문자확인
#     if check(word):  # 그룹 단어라면 True 반환
#         cnt += 1
# print(cnt)

# 02번 방법
# n = int(input())
# for _ in range(n):
#     word = list(input())
#     for i in range(len(word)-1):
#         # 현재단어와 다음단어가 다르고 현재단어에 다음단어 리스트에 포함된다면 그룹단어가 아님.
#         if (word[i] != word[i+1]) and (word[i] in word[i+1:]):
#             n -= 1
# print(n)

# --------------------------------------------------

# 25206. 너의 평점은
score = { 'A+': 4.5, 'A0': 4.0, 'B+': 3.5,
          'B0': 3.0, 'C+': 2.5, 'C0': 2.0,
          'D+': 1.5, 'D0': 1.0, 'F': 0.0   }

n, sum, totalGrade = 20, 0, 0

for i in range(n):
    subject, grade, rank = input().split()
    grade = float(grade)   # 학점
    if rank == 'P':        # P는 pass이므로 계산제외
        continue
    
    rank = score[rank]
    totalGrade += grade    # 학점의 총합
    sum += (grade * rank)  # 학점 * 과목평점

print('{:.6f}'.format(sum / totalGrade))