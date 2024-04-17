
# Q6. nums=[1,2,3,4]로 만들 수 있는 모든 순열을 반환하시오. (중복 허용)

def permute(nums):
    def backtrack(curr):
        if len(curr) == len(nums):
            ans.append(curr[:])
            return
        for num in nums:
            if num not in curr:
                curr.append(num)
                backtrack(curr)
                curr.pop()
    ans = []
    backtrack([])
    return ans

print(permute([1, 2, 3, 4]))