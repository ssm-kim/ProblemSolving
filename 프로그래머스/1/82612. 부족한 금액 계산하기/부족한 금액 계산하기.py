def solution(price, money, count):

    for i in range(1, count+1):
        money -= (price * i)
    
    if money > 0:
        answer = 0
    else:
        answer = abs(money)
    
    return answer









# def solution(price, money, count):
#     answer = 0
#     cost = 0
#     for i in range(1, count+1):
#         cost += price*i    
    
#     if cost > money:
#         answer = cost - money

#     return answer