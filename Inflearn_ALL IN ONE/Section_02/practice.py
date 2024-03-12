import sys
sys.stdin = open('input.txt', 'r')


# List [코테 적용]  >  반복문

nums = list(map(int, input().split()))
target = int(input())
print(nums, target)

ans = False
for i in range(len(nums)):
    for j in range(i+1, len(nums)):
        sum = nums[i] + nums[j]
        if target == sum:
            ans = True
            break
    if ans:
        break
    
print(ans, i, j)