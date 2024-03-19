'''
  Daily Temperatures(일일 온도) 문제
    - 매일의 온도를 나타내는 int형 배열 temperatures
    - answer배열의 원소 i번째날의 온도보다 더 따뜻해지기까지 며칠을 기다려야하는지 나타낸다
    - 더 따뜻해지는 날이 없다면 answer[i] == 0이다. answer배열을 반환하는 함수를 구현하시오
  
  제약조건
    - 1 <= temperatures.length <= 10**5
    - 30 <= temperatures[i] <= 100
    - 문자열 s는 괄호들로만 구성되어 있다

  Test Case : [73, 74, 75, 71, 69, 72, 76, 73]  [30, 40, 50, 60]  [30, 60, 90]
  Output :    [1, 1, 4, 2, 1, 1, 0, 0]          [1, 1, 1, 0]      [1, 1, 0]
'''

# 재풀이
def daliyTemperatures(temperatures):
    answer = [0] * len(temperatures)
    stack = list()
    for cur_day, cur_temp in enumerate(temperatures):
        while stack and stack[-1][1] < cur_temp:
            prev_day, _ = stack.pop()
            answer[prev_day] = cur_day - prev_day
        stack.append((cur_day, cur_temp))
    return
print(daliyTemperatures([73, 74, 75, 71, 69, 72, 76, 73]))








# def daliyTemperatures(temperatures):
#     ans = [0] * len(temperatures)
#     stack = []
#     for cur_day, cur_temp in enumerate(temperatures):
#         while stack and stack[-1][1] < cur_temp:
#             prev_day, _ = stack.pop()
#             ans[prev_day] = cur_day - prev_day
#         stack.append((cur_day, cur_temp))
#     return ans

# print(daliyTemperatures([73, 74, 75, 71, 69, 72, 76, 73]))