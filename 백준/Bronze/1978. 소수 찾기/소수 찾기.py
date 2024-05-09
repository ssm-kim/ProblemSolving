n = int(input())
num_list = list(map(int, input().split()))
check_prime = [False, False] + [True] * 1000
primeNum = list()
cnt = 0
for i in range(2, len(check_prime)):
    if check_prime[i]:  # i가 소수인지 확인
        primeNum.append(i)
        for j in range(i*2, len(check_prime), i):
            check_prime[j] = False
 
for i in num_list:
    if check_prime[i]:
        cnt += 1
print(cnt)