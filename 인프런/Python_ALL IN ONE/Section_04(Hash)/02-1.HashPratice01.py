
'''
Two Sum 문제 활용

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

def twoSum(nums, target):
    memo = {}
    for v in nums:
        memo[v] = 1
    
    for v in nums:
        needed_number = target - v
        if needed_number in memo and needed_number != v:
            return True
    return False
# [4, 1, 9, 7, 5, 3, 16], 14
# [2, 1, 5, 7], 4
print(twoSum([2, 1, 5, 7], 4))


