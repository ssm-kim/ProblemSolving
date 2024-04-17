
# Q. 리스트 [4,9,7,5,1]에서 두 개의 숫자를 더해서 12이 될 수 있나요? (중복 X)

def solution(nums, target):
    def backtrack(start, curr):
        if len(curr) == 3 and sum(nums[i] for i in curr) == target:
            return curr
        
        for i in range(start, len(nums)):
            curr.append(i)
            res = backtrack(i+1, curr)
            
            if res:
                return res
            
            curr.pop()
        return None
    return backtrack(0, [])


print(solution([4,9,7,5,1], 15))


# def dfs(start, curr):
#     if len(curr) == 3 and sum(nums[i] for i in curr) == target:
#         return curr
    
#     for i in range(start, len(nums)):
#         curr.append(i)
#         ret = dfs(i+1, curr)
        
#         if ret:
#             return ret
        
#         curr.pop()
#     return None

# nums = [4,9,7,5,1]
# target = 15
# print(dfs(0, []))