def solution(food):
    answer = ''
    water = str(food.pop(0)-1)
    batch = ''
    for i in range(len(food)):
        cnt = food[i]//2
        batch += str(i+1) * cnt
    
    otherBatch = batch[::-1]  # 반대전환
    
    answer = batch + water + otherBatch

    return answer












#     answer = ''
#     water = str(food.pop(0) - 1)
    
#     for i in range(len(food)):
#         if food[i] >= 2:
#             answer += str(i+1) * (food[i]//2)
#     answer += water + answer[::-1]