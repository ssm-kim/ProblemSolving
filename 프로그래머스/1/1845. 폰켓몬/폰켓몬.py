def solution(nums):
    bring = len(nums)//2
    nums = list(set(nums))
    
    if bring < len(nums):
        answer = bring
    else:
        answer = len(nums)
        
    return answer