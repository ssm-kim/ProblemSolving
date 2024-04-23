
# Q5. nums=[1,2,3,4]로 만들 수 있는 부분집합을 모두 반환하시오.

'''
    k = 0  >  []
    k = 1  >  [1], [2], [3], [4]
    k = 2  >  [1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]
    k = 3  >  [1, 2, 3], [1, 2, 4], [2, 3, 4]
    k = 4  >  [1, 2, 3, 4]
'''

# def backtrack(start, curr):
#     if len(curr) == k:
#         result.append(curr[:])
#         return

#     for i in range(start, len(nums)):
#         if nums[i] not in result:
#             curr.append(nums[i])
#             backtrack(i+1, curr)
#             curr.pop()
#     return result

# result = []
# nums = [1,2,3,4]
# for i in range(len(nums)+1):
#     k = i
#     print(backtrack(0, []))