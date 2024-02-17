'''
    붕대감기기술
        1. t초 동안 붕대를 감으면서 1초마다 x만큼의 체력회복
        2. t초 연속으로 붕대를 감는 데 성공한다면 y만큼의 추가 체력회복
        단, 현재 체력이 최대 체력보다 커지는 것은 불가 ( 현재 체력 < 최대 체력 )
            체력이 0이하면 체력회복불가  >  캐릭터사망
'''
def solution(bandage, health, attacks):  # 붕대감기기술정보, 최대체력, 공격패턴
    answer = 0
    lastAttack, maxHealth, success = attacks[-1][0], health, -1  # 몬스터의 마지막 공격(초), 최대 체력, 연속성공횟수
    useTime, secRecover, addRecover = bandage  # 시전 시간, 초당 회복량, 추가회복량
    attackTime, damage = attacks.pop(0)        # 첫번째 공격패턴 꺼내기
    
    # print(bandage, health, attacks, lastAttack, attackTime, damage)
    
    for i in range(lastAttack+1):
        if i == attackTime:   # 현재 시간이 몬스터 공격시간과 같다면
            health -= damage  # 피해량만큼 현재체력감소
            success = 0       # 성공횟수 초기화
            # print('----', attackTime, damage)
            if attacks:
                attackTime, damage = attacks.pop(0)  # 다음 공격패턴
        else:  # 공격을 받지 않으면
            if health <= 0:  # 현재체력이 0이하면 반복문 종료
                answer = -1
                break

            success += 1                # 성공횟수
            if health < maxHealth:      # 현재 체력이 최대체력보다 작다면 회복 시작이며, 성공 횟수는 증가
                health += secRecover    # 초당회복량 (기본값)
                
                if success == useTime:  # 기술사용시간과 성공횟수가 같다면 기술 시전
                    health += addRecover
                    success = 0         # 성공횟수 초기화
                
                if health > maxHealth:  # 현재체력이 최대체력보다 크면 최대체력 대입
                    health = maxHealth
        
        # print(i, health, success)

    answer = health
    if health <= 0:
        answer = -1        
    
    print(answer)
    
    return answer