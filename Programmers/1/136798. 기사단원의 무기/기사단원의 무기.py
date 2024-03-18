def solution(number, limit, power):
    answer = 0
    for i in range(1, number+1):
        attack = 0             # 현재 숫자 약수 개수를 저장할 변수
        for j in range(1, int(i**0.5) + 1):  # i부터 i의 제곱근까지
            if (i%j)==0:       # i가 j로 나누어 떨어지면 j, i//j 둘 다 약수임
                attack += 2
        
        if (i**0.5) % 1 == 0:  # 완전제곱수일 때 약수 중 하나가 중복되므로 -1
            attack -= 1
        
        if attack > limit:
            attack = power
        answer += attack
        
    return answer