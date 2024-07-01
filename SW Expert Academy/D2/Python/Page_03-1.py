import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D2
      - 정답율순
      - 1페이지 단위 ( 10문제 )
'''

# 1983. 조교의 성적 매기기

# tc = int(input())
# for t in range(1, tc+1):
#     n, k = map(int, input().split())
#     score = [list(map(int, input().split())) for _ in range(n)]
#     grade = [0] * n  # 학생별 평균평점
#     tmp = ['A+','A0','A-','B+','B0','B-','C+','C0','C-','D0']
#     rank = list()
#     for i in range(len(tmp)):  # 순서대로 학점을 채운다. A+, A+, A0, A0, A- ...
#         for _ in range(n//10):
#             rank.append(tmp[i])
    
#     for i in range(len(score)):
#         mid, final, assignment = score[i]
#         total = (mid * 0.35) + (final * 0.40) + (assignment * 0.20)
#         grade[i] = (total, i+1)  # 평점, (i+1)번째 학생
#     grade.sort(key=lambda x:x[0], reverse=True)
    
#     for i in range(len(grade)):
#         scoreOfStudent, numOfStudent = grade[i]       
#         if numOfStudent == k:
#             rankOfStudent = i  # 학생순위 인덱스번호
#             answer = rank[rankOfStudent]
#             break
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1946. 간단한 압축 풀기
#  - 원본 문서의 너비는 10으로 고정이다.

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     st, answer = '', ''
#     for _  in range(n):
#         c, k = input().split()
#         st += (c * int(k))
    
#     for i in range(len(st)//10 + 1):
#         answer += st[:10] + '\n'
#         st = st[10:]  # answer 10 저장하면 11번째부터 시작
#     print('#%d' % t)
#     print(answer, end='')

# ----------------------------------------------------------------------

# 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기

# tc = int(input())
# for t in range(1, tc+1):
#     t = int(input())
#     student_score = list(map(int, input().split()))
#     score = {i:0 for i in range(101)}
#     answer = 0

#     for i in student_score:  # 점수별 나온 횟수
#         score[i] += 1

#     mostScore = max(score.values())  # 최빈수구하기
#     for k, cnt in score.items():
#         if mostScore == cnt:
#             answer = max(answer, k)
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1984. 중간 평균값 구하기
#  - 소수점 첫번째자리에서 반올림.

# tc = int(input())
# for t in range(1, tc+1):
#     num = list(map(int, input().split()))
#     num.sort()
#     # 최대 수와 최소 수를 제외하므로 전체길이 -2를 해준다.
#     print('#{} {:.0f}'.format(t, sum(num[1:-1])/(len(num)-2)))

# ----------------------------------------------------------------------

'''
    1859. 백만 장자 프로젝트
        1. 원재는 연속된 N일 동안의 물건의 매매가를 예측하여 알고 있다.
        2. 당국의 감시망에 걸리지 않기 위해 하루에 최대 1만큼 구입할 수 있다.
        3. 판매는 얼마든지 할 수 있다.
    
    문제 이해하기
        - 원재가 최대한 많은 이익을 볼 수 있도록 코드를 구현해라.
    
    접근방법
        - 물건의 매매가를 예측하여 알고 있으므로 반대로 순회한다.
        - 현재 매매가가 다음 매매가보다 크다면 구매해서 이익을 남긴다.
        - 현재 매매가가 다음 매매가보다 작다면 손해를 보기 때문에 구매하고 현재매매가를 다음매매가로 갱신한다.
          왜냐하면 더 큰 이익을 남길 수 있기 때문이다.
'''
tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    buy_sell = list(map(int, input().split()))
    cur_price = buy_sell[-1]  # 순회를 뒤에서부터 시작하므로 첫매매가는 마지막 인덱스 값.
    profit = 0
    for i in range(n-1, -1, -1):
        next_price = buy_sell[i]      # 다음매매가
        if cur_price == next_price:   # 같다면 넘어간다.
            continue
        
        elif cur_price > next_price:  # 원료를 구매한다.
            profit += (cur_price - next_price)
        
        elif cur_price < next_price:  
            cur_price = next_price    # 원료를 구매하지 않고 현재매매가 갱신
    print('#{} {}'.format(t, profit))
