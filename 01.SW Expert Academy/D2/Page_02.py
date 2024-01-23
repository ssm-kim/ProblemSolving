import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D2
      - 참여자순 
      - 페이지 단위 ( 10문제 )
'''

# 1989. 초심자의 회문 검사
# 접근방법
# 1. 입력값의 길이를 절반으로 나눈다.
# 2. 인덱스 앞에서 시작하고 인덱스 뒤에서 시작해서 길이의 절반까지 순회해서 같다면 1 다르면 0을 반환
# ※ 회문 : 거꾸로 읽어도 제대로 읽은 것과 같은 문장이나 낱말
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     lenHalf = len(s)//2  # 길이 절반
#     answer = 1
#     for i in range(lenHalf):
#         if s[i] != s[len(s)-i-1]:
#             answer = 0
#             break
    
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1961. 숫자 배열 회전
# 접근방법
# 1. 1열씩 반대방향에서 값을 읽어서 리스트 변수에 저장한다.
# 2. 반복되는 for문은 함수로 정의한다.
# 3. 90도씩 회전할때마다 함수 사용

# def rotate(block):
#     store = list()
#     for i in range(n):
#         tmp = list()
#         for j in range(n):
#             tmp.insert(0, block[j][i])    
#         store.append(''.join(map(str, tmp)))
#     return store

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     origin = [list(map(int, input().split())) for _ in range(n)]
    
#     origin_90 = rotate(origin)
#     origin_180 = rotate(origin_90)
#     origin_270 = rotate(origin_180)
    
#     print('#{}'.format(t))
#     for a, b, c in zip(origin_90, origin_180, origin_270):
#         print(a, b, c)

# ----------------------------------------------------------------------

# 1986. 지그재그숫자
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     answer = 0
#     for i in range(1, n+1):
#         if i%2 == 0:
#             answer -= i
#         else:
#             answer += i
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1984.중간 평균값 구하기
# tc = int(input())
# for t in range(1, tc+1):
#     num_list = list(map(int, input().split()))

#     num_list.sort()
#     print('#{} {:.0f}'.format(t, sum(num_list[1:-1])/(len(num_list)-2)))

# ----------------------------------------------------------------------

# 1983. 조교의 성적 매기기
# 접근방법
# 1. 학점을 A+, A+, A0, A0 ... 순서로 n/10명 학생만큼 리스트에 저장
# 2. 총점 계산 후 딕셔너리 두번째 인덱스(value) 값으로 정렬 (key값은 정렬되지 않음)
# 3. 총점수리스트의 첫번째인덱스번호(정렬되지 않은 학생등수)가 k번째와 같다면 value 출력

# tc = int(input())
# for t in range(1, tc+1):
#     n, k = map(int, input().split())
#     score = [list(map(int, input().split())) for _ in range(n)]
#     defalutGrade = ['A+', 'A0', 'A-', 'B+', 'B0', 'B-', 'C+', 'C0', 'C-', 'D0']
#     grade = list()

#     for i in defalutGrade:
#         for _ in range(n//10):  # 총 학생수만큼 학점 부여해야되므로
#             grade.append(i)
    
#     sequence = dict()  # 학생 총점수 높은순서대로
    
#     for i in range(len(score)):
#         mid, final, assignment = score[i]
#         sequence[i+1] = mid*0.35 + final*0.45 + assignment*0.2

#     sequence = sorted(sequence.items(), key=lambda x:x[1], reverse=True)  # 총 점수별로 정렬

#     for i in range(len(sequence)):
#         rank, totalScore = sequence[i]
#         if rank == k:
#             answer = grade[i]
#             break
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------
    
# 1928. Base64 Decoder  >  Pass

# ----------------------------------------------------------------------

# 1959.두 개의 숫자열
# 접근방법
# 1. 두 개의 숫자열 길이의 절댓값만큼 첫번째 반복
# 2. 길이가 더 짧은 숫자열만큼 j번, 긴 숫자열은 i+j번 두번째 반복한다.
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     a = list(map(int, input().split()))
#     b = list(map(int, input().split()))
#     answer = 0

#     for i in range(abs(n - m)+1):
#         tmp = 0
#         for j in range(min(n, m)):
#             if n <= m:
#                 tmp += (a[j] * b[i+j])
#             else:
#                 tmp += (a[i+j] * b[j])
#         answer = max(answer, tmp)
    
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1288. 새로운 불면증 치료법
# 단순구현
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     nn = n
#     check = list()
#     while len(check) != 10:
#         tmp = list(str(nn))
#         for i in tmp:
#             if i not in check:
#                 check.append(i)
#         nn = nn + n
#     print('#{} {}'.format(t, nn-n))
    
# ----------------------------------------------------------------------

# 1945.간단한 소인수분해
# 단순 구현
# tc = int(input())
# div = [2, 3, 5, 7, 11]
# for t in range(1, tc+1):
#     n = int(input())
#     answer = list()
    
#     for i in div:
#         cnt = 0
#         while (n % i) == 0:
#             cnt += 1
#             n = n // i
#         answer.append(cnt)

#     print('#{} {}'.format(t, ' '.join(map(str, answer))))
    
# ----------------------------------------------------------------------

# 1970. 쉬운 거스름돈
# 단순 구현
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     moneyKind = [50000, 10000, 5000, 1000, 500, 100, 50, 10]
#     answer = list()

#     for x in moneyKind:
#         cnt = 0
#         if x <= n:
#             cnt = n//x
#             n -= (x * cnt)
#         answer.append(str(cnt))
#     print('#{}\n{}'.format(t, ' '.join(answer)))