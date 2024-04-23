
# Q. 리스트 [4,9,7,5,1]에서 두 개의 숫자를 더해서 12이 될 수 있나요? (중복 X)

nums = [4,9,7,5,1]
target = 15
    
def backtrack(start, curr):
    if len(curr) == 3 and sum(nums[i] for i in curr) == target:
        return curr
    
    for i in range(start, len(nums)):
        curr.append(i)
        ret = backtrack(i+1, curr)
        
        if ret:
            return ret
        
        curr.pop()
    return None

print(backtrack(0, []))