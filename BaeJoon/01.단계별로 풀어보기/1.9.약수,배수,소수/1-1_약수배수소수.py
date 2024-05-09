import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 5086. 배수와 약수
'''
    문제풀이방법 및 접근
'''
# while True:
#     a, b = map(int, input().split())
#     if a == 0 and b == 0:
#         break
#     if b % a == 0:  # 첫번째 숫자가 두번째 숫자의 약수이다.
#         print('factor')
#     elif a % b == 0:  # 첫번째 숫자가 두번째 숫자의 배수이다.
#         print('multiple')
#     else:
#         print('neither')    

# --------------------------------------------------

# 2501. 약수 구하기
'''
    문제풀이방법 및 접근
'''
# n, k = map(int, input().split())
# measure = list()
# for i in range(1, n//2+1):  # n의 약수를 구하자
#     lt, rt = i, n-i+1
#     if n % lt == 0:
#         measure.append(lt)
#     if n % rt == 0:
#         measure.append(rt)
# measure.sort()
# if k <= len(measure):
#     print(measure[k-1])
# else:
#     print(0)

# --------------------------------------------------

# 9506. 약수들의 합
'''
    문제풀이방법 및 접근
'''
# while True:
#     n = int(input())
#     measure = list()
#     perfect_num = 0  # 완전수 (자기자신을 제외한 약수의 합이 자기자신과 같은 수)
#     if n == -1:
#         break
#     for i in range(1, n//2+1):
#         lt, rt = i, n+1-i
#         if (n % lt == 0):
#             perfect_num += lt
#             measure.append(lt)
#         if (n % rt == 0) and (n != rt):
#             perfect_num += rt
#             measure.append(rt)
#     measure.sort()
#     if n == perfect_num:  # 완전수이다
#         answer = str(n) + ' = '
#         for i in range(len(measure)):
#             if measure[i] == measure[-1]:
#                 answer = answer + str(measure[i])
#             else:
#                 answer = answer + str(measure[i]) + ' + '
#         print(answer)
#     else:
#         print('{} is NOT perfect.'.format(n))

# --------------------------------------------------

# 1978. 소수 찾기
'''
    문제풀이방법 및 접근
        - 소수판별하는 리스트에 정해진 범위를 넣어서 확인하다.
        - 1과 자기자신만 있는 수를 소수라 한다.
'''
# n = int(input())
# num_list = list(map(int, input().split()))
# check_prime = [False, False] + [True] * 1000
# primeNum = list()
# cnt = 0
# for i in range(2, len(check_prime)):
#     if check_prime[i]:  # i가 소수인지 확인
#         primeNum.append(i)
#         for j in range(i*2, len(check_prime), i):
#             check_prime[j] = False
 
# for i in num_list:
#     if check_prime[i]:
#         cnt += 1
# print(cnt)

# --------------------------------------------------

# 2581. 소수
'''
    문제풀이방법 및 접근
        - 1과 자기자신만 있는 수를 소수라 한다.
'''
# check_prime = [False, False] + [True] * 9999
# primeNum = list()
# for i in range(2, len(check_prime)):
#     if check_prime[i]:
#         primeNum.append(i)
#         for j in range(i*2, len(check_prime), i):
#             check_prime[j] = False
# m = int(input())
# n = int(input())
# totalSum, primeMin = 0, 100000000
# for i in range(m, n+1):
#     if i in primeNum:
#         if i < primeMin:
#             primeMin = i
#         totalSum += i
# if totalSum == 0:
#     print(-1)
# else:
#     print('{}\n{}'.format(totalSum, primeMin))

# --------------------------------------------------
# 11653. 소인수분해 
'''
    문제풀이방법 및 접근
'''
n = int(input())
div_num = 2
while n > 1:  # n이 1이하일때까지 반복
    if n % div_num == 0:
        n //= div_num
        print(div_num)
    else:
        div_num += 1