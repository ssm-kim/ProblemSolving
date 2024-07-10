import sys
sys.stdin = open('./public_input.txt', 'r')
'''
    SWEA D3 정렬조건
        - 정답율순, Python
        - 1페이지 단위 ( 10문제 )
      * 너무 쉬운 문제는 Pass

    풀이방법
        1. 문제 이해하기
            - input, output 특징 확인, input size N (시간복잡도 계산)
            - 제약조건 확인 (시간복잡도 제한 체크 및 알고리즘 선택) 및 예상할 수 있는 오류 파악

        2. 접근 방법
            2-1. 직관적으로 생각하기
                - 보통 완전탐색으로 시작
                - 문제 상황을 단순화/극한화하여 생각하기         
            2-2. 자료구조와 알고리즘 활용
            3-3. 메모리 사용
                - 시간복잡도를 줄이기 위해 사용하는 방법
                - 대표적으로 해시테이블 (딕셔너리)

        3. 코드 설계
        4. 코드 구현
'''

# 1228.[S/W 문제해결 기본] 8일차 - 암호문1  >  1230. 암호문3 풀이했으므로 Pass
'''
     문제 이해 및 접근방법
'''

# ----------------------------------------------------------------------

# 4466. 최대 성적표 만들기
'''
     문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, k = map(int, input().split())
#     score = list(map(int, input().split()))
#     score.sort(reverse=True)
#     total = 0
#     for i in range(k):
#         total+=score[i]
#     print('#{} {}'.format(t, total))

# ----------------------------------------------------------------------

# 9700. USB 꽂기의 미스터리  >  문제이해가 어려움 (확률문제)
'''
     문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     p, q = map(float, input().split())
    
#     # 1번 뒤집어서 올바른 면으로 꽂고 작동할 확률
#     # 처음에 뒤집어서 꽂고 다음에 제대로 꽂는다.
#     s1 = (1-p) * q

#     # 2번 뒤집어서 올바른 면으로 꽂고 작동할 확률
#     # 처음에 올바른 면 & 비정상 꽂기, 그다음 뒤집은 면(생략), 마지막 제대로 꽂기
#     s2 = p * (1-q) * q
    
#     answer = 'NO'
#     if s1 < s2:
#         answer = 'YES'
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 14178. 1차원 정원
'''
    문제 이해 및 접근방법
    - 좌표 x에 분무기를 놓았을 경우 닫힌 구간 [x - D, x + D]에 심긴 모든 꽃들에 물을 줄 수 있다.
'''

# tc = int(input())
# for t in range(1, tc+1):
#     n, c = map(int, input().split())
#     sprayer = n // (c*2+1)   # 좌표에 놓을 수 있는 최소 분무기 갯수
#     remainder = n % (c*2+1)  # 나머지가 있다면 +1
#     if remainder:
#         sprayer += 1
#     print('#{} {}'.format(t, sprayer))

# ---------------------------------------------------------------------- 

# 14555. 공과 잡초
'''
    문제 이해 및 접근방법
        - 문제에서 요구하는 것은 공이 하나 완전히 있든 반쪽만 있든 공의 개수를 구하는 것이다.
        - 즉 '(', ')', '()'세 가지를 구하면 되는데 반쪽은 잡초가 터트려서 잡초랑 같이 있는 (|, |)를 구하면 된다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     answer = s.count('()')
#     s = s.replace('()', '.')
#     answer += s.count('(') + s.count(')')
#     print('#{} {}'.format(t, answer))

# ---------------------------------------------------------------------- 

# 6692. 다솔이의 월급 상자
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     answer = 0
#     for _ in range(n):
#         p, x = map(float, input().split())
#         answer += (p * x)
    
#     print('#{} {:.6f}'.format(t, answer))

# ---------------------------------------------------------------------- 

# 1208. [S/W 문제해결 기본] 1일차 - Flatten    
'''
    문제 이해 및 접근방법
        - 가장 높은 곳에 있는 상자를 가장 낮은 곳으로 옮기는 작업을 덤프라고 정의한다
'''
# tc = 10
# for t in range(1, tc+1):
#     dumpCnt = int(input())  # 덤프횟수
#     box = list(map(int, input().split()))
#     for _ in range(dumpCnt):
#         box[box.index(min(box))] += 1
#         box[box.index(max(box))] -= 1
#     print('#{} {}'.format(t, max(box)-min(box)))

# 3142. 영준이와 신비한 뿔의 숲
'''
    문제 이해 및 접근방법
        - 이 숲에는 뿔이 한 개 달린 유니콘과 뿔이 두 개 달린 트윈혼이 살고 있다.
        - 영준이는 n개의 뿔과 m마리의 짐승을 보았다
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     u = 2 * m - n
#     tw = m - u
#     print('#{} {} {}'.format(t, u, tw))

# ---------------------------------------------------------------------- 
    
# 5162. 두가지 빵의 딜레마
    
'''
    문제 이해 및 접근방법
        - 은비는 지금 C원을 가지고 있으며, 어떤 빵이든 상관 없이 그냥 많은 개수의 빵을 사고 싶다.
        - 최대 몇 개의 빵을 살 수 있을까?
'''
# tc = int(input())
# for t in range(1, tc+1):
#     a, b, c = map(int, input().split())
#     cheap = min(a, b)
#     print('#{} {}'.format(t, c//cheap))

# ---------------------------------------------------------------------- 
    
# 10200. 구독자 전쟁

'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, a, b = map(int, input().split())
    
#     if a+b > n:
#         print('#{} {} {}'.format(t, min(a, b), a+b-n))
#     else:
#         print('#{} {} {}'.format(t, min(a, b), 0))