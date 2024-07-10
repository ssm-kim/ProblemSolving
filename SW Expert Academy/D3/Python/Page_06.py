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

# 5789. 현주의 상자 바꾸기
'''
    문제 이해 및 접근방법
        - 현주는 1번부터 N번까지 N개의 상자를 가지고 있고 처음에는 모두 0으로 적혀있다.
        - 다음 Q회 동안 일정 범위의 연속한 상자를 동일한 숫자로 변경하려고 한다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, q = map(int, input().split())
#     boxes = ['0'] * n
#     for i in range(q):
#         l, r =  map(int, input().split())
#         for j in range(l-1, r):
#             boxes[j] = str(i+1)
#     print('#{} {}'.format(t, ' '.join(boxes)))

# ----------------------------------------------------------------------

# 4676. 늘어지는 소리 만들기
'''
    문제 이해 및 접근방법
        - s문자열사이에 하이픈갯수만큼 삽입
    접근방법
        1. 하이픈 넣을 갯수를 딕셔너리에 정의
        2. s의 길이 + 1 만큼 반복 후 조건처리
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()  # 문자열(알파벳 소문자)
#     h = int(input())  # 하이픈 넣을 갯수
#     pos = list(map(int, input().split()))  # 하이픈 넣을 위치
#     dictPos = {}
#     answer = ''
#     for i in pos:
#         if i not in dictPos:
#             dictPos[i] = 1
#         else:
#             dictPos[i] += 1

#     for i in range(len(s)+1):  # 0번부터 len(s)+1까지 탐색
#         if i in dictPos:
#             answer += ('-' * dictPos[i])
#             if i < len(s):
#                 answer += s[i]
#         elif i < len(s):
#             answer += s[i]
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 10912. 외로운 문자 
'''
    문제 이해 및 접근방법
        - 이 문자열에서 같은 두 문자들을 짝짓고 남는 문자가 무엇인지 구하는 프로그램을 작성하라.
        - 같은 문자를 여러 번 짝지어서는 안 된다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     alphaPair = {}
#     answer = ''
#     for i in s:  # 딕셔너리로 알파벳 갯수 파악
#         if i not in alphaPair:
#             alphaPair[i] = 1
#         else:
#             alphaPair[i] += 1
#     for i in list(sorted(alphaPair.items())):  # 정렬 필수
#         k, v = i
#         if v%2 != 0:
#             answer += k
#     if not answer:  # 전부 짝이지어진다면
#         answer = 'Good'
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 2806. N-Queen  >  DFS+백트래킹을 활용하여 풀어야 함
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     break


# ----------------------------------------------------------------------

# 5603. [Professional] 건초더미
'''
    문제 이해 및 접근방법
        - 건초더미의 크기(길이)를 전부 같게 만들려면 최소 몇번의 이동이 필요한가?
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())  # 건초더미갯수
#     size = [int(input()) for _ in range(n)]
#     avg = sum(size)//n
#     cnt = 0
#     for i in range(n):
#         if size[i] > avg:
#             cnt += (size[i] - avg)  # 건초더미이동횟수
#     print('#{} {}'.format(t, cnt))

# ----------------------------------------------------------------------

# 1493. 수의 새로운 연산  >  Pass?
'''
    문제 이해 및 접근방법
'''

# ----------------------------------------------------------------------

# 5948. 새샘이의 7-3-5 게임
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     num = list(map(int, input().split()))
#     total = list()
#     for i in range(len(num)-2):
#         for j in range(i+1, len(num)-1):
#             for k in range(j+1, len(num)):
#                 sum = num[i]+num[j]+num[k]
#                 if sum not in total:
#                     total.append(sum)
#     total.sort(reverse=True)
#     print('#{} {}'.format(t, total[4]))

# ----------------------------------------------------------------------

# 4299. 태혁이의 사랑은 타이밍
'''
    문제 이해 및 접근방법
        - 일자, 시간을 분으로 변경하는 로직
'''
# tc = int(input())
# for t in range(1, tc+1):
#     d, h, m = map(int, input().split())
#     remain = 0  # 분으로 환산
#     if d <= 11 and h < 11:  # 예외처리
#         remain = -1
#     else:
#         d -= 11
#         if d > 0:  # 일자를 시간으로 계산
#             h += (d*24)

#         h -= 11    # 시간 계산
#         m += (h*60)

#         m -= 11    # 분 계산
#         remain = m
    
#     print('#{} {}'.format(t, remain))

# ----------------------------------------------------------------------


# 12004. 구구단 1
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     gugudan = list()
#     for i in range(1, 10):
#         for j in range(1, 10):
#             gugudan.append(i*j)
    
#     answer = 'No'
#     if n in gugudan:
#         answer = 'Yes'
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 9480. 민정이와 광직이의 알파벳 공부  >  DFS로 풀어야 함
'''
    문제 이해 및 접근방법
        - 광직이는 현재 N개의 영어 단어를 알고 있고, 이 중 몇 개를 골라 하나의 세트로 만드는데, 각 세트 안에 포함된 단어의 순서는 중요하지 않다.
'''

def check(st):
    compare = 'abcdefghijklmnopqrstuvwxyz'  # 영단어 소문자
    tmp, cnt = list(set(st)), 0
    for i in tmp:
        if i in compare:
            cnt += 1
    if cnt == 26:
        return True
    return False

def dfs(cur_v, st):
    global answer
    if check(st):
        answer += 1
    if cur_v == n:
        return
    
    for next_v in range(cur_v, n):
        dfs(next_v+1, st + words[next_v])

    return

tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    words = [input() for _ in range(n)]
    answer = 0
    dfs(0, '')
    print('#{} {}'.format(t, answer))