# 다트를 세 차례 던져 그 점수의 합계로 실력을 겨룬다.
def solution(dartResult):
    total = 0       # 총 점수 합계
    dartScore = { 'S': 1,
                  'D': 2,
                  'T': 3 }
    round = list()
    option = list()
    score = ''
    for i in range(len(dartResult)):
        val = dartResult[i]
        if val.isdigit():    # 숫자라면
            score += val     # 두자리 정수 체크
        elif val.isupper():  # 대문자 알파벳
            round.append(int(score) ** dartScore[val])
            score = ''
        else:   # 옵션
            option.append(val)
            # 스타상: 해당 점수와 바로전에 얻은 점수 2배이며, 중첩된 스타상의 점수배는 4배
            if val == '*':   # 현재 옵션
                if len(round) > 1:  # 바로전에 얻은 점수가 있을 때
                    round[-1] *= 2
                    round[-2] *= 2
                else:               # 단, 첫번째 기회에 나오면 첫번째 점수만 2배
                    round[-1] *= 2
            # 아차상: 해당 점수만 마이너스가 되며, 중첩된 아차상의 점수는 -2배
            if val == '#':
                round[-1] *= -1
        # print(round, score)
    total = sum(round)
    return total