
'''
    Longest Consecutive Sequence
      - 정렬되어 있지 않은 정수형 배열 nums가 주어졌다.
        nums 원소를 가지고 만들 수 있는 가장 긴 연속된 수의 갯수는 몇개인지 반환하시오.

    TestCase
        [100, 4, 200, 1, 3, 2], 4
        [0, 3, 7, 2, 5, 8, 4, 6, 0, 1], 9 
'''

# 딕셔너리로 정렬
def longestConsecutive(nums):
    answer = 0
    new_dict = {num:1 for num in nums}
    for num in nums:
        if num-1 not in new_dict:  # 현재 숫자보다 앞에 있는 숫자가 있는지 체크하고 없으면 실행 -> 중복제거
            cnt = 1
            target = num+1
            while target in new_dict:
                cnt += 1
                target += 1
            answer = max(answer, cnt)
    return answer

print(longestConsecutive(nums = [6, 7, 100, 5, 4, 4])) # 4