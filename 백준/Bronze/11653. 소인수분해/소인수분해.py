n = int(input())
division_num = 2
while n > 1:  
    if n % division_num:
        division_num += 1
    else:
        n = n / division_num
        print(division_num)