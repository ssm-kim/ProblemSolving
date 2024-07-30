import sys
sys.stdin = open('../input.txt', 'r')

# set으로 중복제거
# remainder = list()
# for _ in range(10):
#     num = int(sys.stdin.readline())
#     remainder.append(num % 42)
# print(len(list(set(remainder))))

# 리스트로 중복제거
remainder = list()
for _ in range(10):
    num = int(sys.stdin.readline())
    if num % 42 not in remainder:
        remainder.append(num % 42)
print(len(remainder))


# a = list()
# while True:
#     try:
#         a.append(int(input()))
#     except:
#         break
# res = list()
# for x in a:
#     if x % 42 not in res:
#         res.append(x % 42)
# print(len(res))