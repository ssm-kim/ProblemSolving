import sys
sys.stdin = open('(Python) ALL In ONE/input.txt', 'r', encoding='utf-8')

'''
    Array list (Array, Dynamic array)
    nums = [4 1 9 7 5 3 16], [2 1 5 7] / target = 14, 4
    [코테 적용]  >  반복문
    
    직관적으로 생각하기
    - 보통 완전탐색으로 시작 (n**2)
    - 문제 상황을 단순화/극한화 해보기

    자료구조와 알고리즘 활용 (정렬 - nlogn)
    - 문제이해에서 파악한 내용을 토대로 어떤 자료구조를 사용화는게 가장 적합한지 결정
    - 대놓고 특정 자료구조와 알고리즘을 묻는 문제도 많음
    - 자료구조에 따라 선택할 수 있는 알고르짐을 문제에 적용

    메모리 사용
    - 시간복잡도를 줄이기 위해 메모리를 사용하는 방법
    - 대표적으로 해시테이블 !!
'''
# nums = list(map(int, input().split()))
# target = int(input())
# print(nums, target)

# ans = False
# for i in range(len(nums)):
#     for j in range(i+1, len(nums)):
#         sum = nums[i] + nums[j]
#         if target == sum:
#             ans = True
#             break
#     if ans:
#         break
    
# print(ans, i, j)

'''
[코테 적용]  >  two pointer
    two pointer
        - 제일 왼쪽 인덱스와 오른쪽 인덱스에서 목표인덱스의 거리가 가까운 인덱스부터 움직이며 접근
        - 정형화된 알고리즘은 아니며, 보통 정렬된 이후에 사용한다.
'''
nums = list(map(int, input().split()))
target = int(input())

def twoSum(nums, target):
    nums.sort()              # 정렬의 시간복잡도 : O(nlogn)
    lt, rt = 0, len(nums)-1  # 왼쪽 출발 인덱스, 오른쪽 출발 인덱스
    while lt < rt:           # 시간복잡도 : O(n)
        if nums[lt] + nums[rt] < target:
            lt += 1
        if nums[lt] + nums[rt] > target:
            rt -= 1
        if nums[lt] + nums[rt] == target:
            return True
    return False

print(twoSum(nums, target))