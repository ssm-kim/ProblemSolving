'''
    https://velog.io/@oxboxx/%EA%B0%80%EC%9E%A5-%EB%A7%8E%EC%9D%B4-%EB%B0%9B%EC%9D%80-%EC%84%A0%EB%AC%BC-Python
        from collections import defaultdict  ->  라이브러리 사용 풀이
'''
from pprint import pprint  # pprint(pretty print) : 중첩된 딕셔너리(json 유사), 리스트등의 데이터타입 구조를 줄바꿈 처리해서 한눈에 파악하기 쉽게 출력해주는 모듈
def solution(friends, gifts):
    giveCnt = { friend:0 for friend in friends }     # 준 횟수
    receiveCnt = { friend:0 for friend in friends }  # 받은 회수
    nextMonthCnt = { friend:0 for friend in friends }     # 다음달에 받는 횟수
    gr_records = {}
    
    for friend in friends:  # 주고 받는 횟수 기록 : 딕셔너리 - 초기화
        gr_records[friend] = {}
        for other in friends:
            if other != friend:
                gr_records[friend][other] = [0, 0]

    for gift in gifts:      # 주고 받는 횟수 기록
        giver, receiver = gift.split()
        giveCnt[giver] += 1
        receiveCnt[receiver] += 1
        gr_records[giver][receiver][0] += 1
        gr_records[receiver][giver][1] += 1
        
    # pprint(gr_records)
    # print()    
    # print('선물 준 횟수: ', giveCnt)
    # print('선물 받은 횟수: ', receiveCnt)

    giveCntlst = list(giveCnt.values())
    receiveCntlst = list(receiveCnt.values())
    presentCntlst = [ giveCntlst[i] - receiveCntlst[i] for i in range(len(friends))]
    presentCnt = { friend:presentCntlst[i] for i, friend in enumerate(friends) }  # 선물지수점수
    # print('이름별 선물지수: ', presentCnt)
    # print()
    
    answer = -1
    for i in range(len(friends)-1):
        friend = friends[i]
        others = friends[i+1:]
        # print(friend, others)
        for other in others:
            AtoB = gr_records[friend][other][0]  # A가 B에게 준 것
            BtoA = gr_records[friend][other][1]  # B가 A에게 준 것

            if AtoB > BtoA:
                nextMonthCnt[friend] += 1
            elif AtoB < BtoA:
                nextMonthCnt[other] += 1
            elif AtoB == BtoA:  # 선물을 주고받은 적이 없거나 같은 수로 주고 받았다면 선물지수가 큰사람에게 준다
                if presentCnt[friend] > presentCnt[other]:  # 만약 선물지수도 같다면 아무런 변화 없음
                    nextMonthCnt[friend] += 1
                if presentCnt[friend] < presentCnt[other]:
                    nextMonthCnt[other] += 1
    
    answer = max(list(nextMonthCnt.values()))
        
    return answer
