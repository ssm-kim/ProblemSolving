
# Q7. nums=[1,2,3,4]에서 두개의 원소를 선택해 만들 수 있는 모든 조합을 반환하시오.

def solution(nums, k):
    res = []
    def backtrack(start, curr):
        if len(curr) == k:
            res.append(curr[:])
            return
        
        for i in range(start, len(nums)):
            if nums[i] not in curr:
                curr.append(nums[i])
                backtrack(i+1, curr)
                curr.pop()

    backtrack(0, [])
    return res

print(solution([1,2,3,4], 2))