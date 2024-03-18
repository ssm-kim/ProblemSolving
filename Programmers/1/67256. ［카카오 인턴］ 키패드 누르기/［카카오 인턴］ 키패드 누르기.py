def solution(numbers, hand):
    answer = ''
    keyPad = {'1': (0, 0) , '2': (0, 1) , '3': (0, 2),
              '4': (1, 0) , '5': (1, 1) , '6': (1, 2),
              '7': (2, 0) , '8': (2, 1) , '9': (2, 2),
              '*': (3, 0) , '0': (3, 1) , '#': (3, 2) }
    
    l_Pos, r_Pos = (3, 0), (3, 2)  # 두 손가락의 처음 시작 위치
    cur_Pos = tuple()
    for num in numbers:
        num = str(num)
        
        if num in ['1', '4', '7']:
            l_Pos = keyPad[num]
            answer += 'L'
            
        elif num in ['3', '6', '9']:
            r_Pos = keyPad[num]
            answer += 'R'
            
        elif num in ['2', '5', '8', '0']:
            lx, ly = l_Pos
            rx, ry = r_Pos
            tgtx, tgty = keyPad[num]  # target (x, y) 좌표
            leftMv, rightMv = abs(lx-tgtx) + abs(ly-tgty), abs(rx-tgtx) + abs(ry-tgty)  # 양손의 이동거리 계산
            
            if leftMv < rightMv:  # 이동거리 값이 더 작은 손을 이동
                l_Pos = keyPad[num]
                answer += 'L'
            elif leftMv > rightMv:
                r_Pos = keyPad[num]
                answer += 'R'
            else:  # 두 손 이동거리가 같을 때
                if hand == 'left':   # 왼손잡이
                    l_Pos = keyPad[num]
                    answer += 'L'
                if hand == 'right':  # 오른손잡이
                    r_Pos = keyPad[num]
                    answer += 'R'

    return answer