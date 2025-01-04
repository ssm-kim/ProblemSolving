import sys
sys.stdin = open('../input.txt', 'r')

# A+B-5
# while True:
#     a, b = map(int, input().split())
#     if a <= 0 and b <= 0:
#         break
#     print(a + b)

# A+B-4
while True:
    try:
        a, b = map(int, input().split())
        print(a + b)
    except:
        break