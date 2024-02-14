def expiredCheck(today, period, date):  # 오늘날짜, 약관에 따른 유효기간, 수집일자
    # 연월일범위 : 2000 <= YYYY <= 2022, 1 <= MM <= 12, 1 <= DD <= 28
    addDays = int(period) * 28  # date(수집일자)에 period(유효기간)를 더한 값 출력
    year, month, day = map(int, date.split('.'))
    
    day += (addDays-1)  # 일자 계산
    addMonth = day//28
    day %= 28
    if day == 0:  # 일자가 0일 때
        day = 28
        addMonth -= 1
    
    month += addMonth  # 월 계산
    if month > 12:
        addYear = month//12
        month %= 12
    else:
        addYear = 0
    if month == 0:   # 월 계산이후 0월달이라면
        month = 12
        year -= 1
        
    if addYear:  # 연 계산
        year += addYear
    
    if day < 10:
        day = '0' + str(day)
    if month < 10:
        month = '0' + str(month)
    
    date = str(year) + '.' + str(month) + '.' + str(day)  # yyyy.mm.dd 형태로 변경 후 today와 비교

    if today > date:
        return True
    else:
        return False

def solution(today, terms, privacies):
    answer = []
    terms_dict = {}
    for x in terms:  # terms를 key, value 한 쌍인 딕셔너리로 변환
        k, v = x.split()
        terms_dict[k] = v
    
    for i in range(len(privacies)):  # privacies 유효기간이 지나지 않은 약관의 번호를 answer에 추가
        date, condition = privacies[i].split()
        if expiredCheck(today, terms_dict[condition], date):   # 오늘날짜, 약관에 따른 유효기간, 수집일자
            answer.append(i+1)
    
    return answer
