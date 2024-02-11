def lastPoint(tmp):
    
    if tmp and tmp[0] == '.':
        tmp.pop(0)
    if tmp and tmp[-1] == '.':
        tmp.pop()
        
    return tmp

def solution(new_id):
    answer = ''
    # 1단계 : 모든 대문자를 소문자로 치환
    new_id = new_id.lower()
    
    # 2단계 : 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제
    tmp = ''
    for i in new_id:
        if i.islower() or i.isdigit() or i in ('-', '_', '.'):
            tmp += i
    
    # 3단계 : 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환
    while '..' in tmp:
        tmp = tmp.replace('..', '.')

    # 4단계 : 마침표(.)가 처음이나 끝에 위치한다면 제거
    tmp = lastPoint(list(tmp))  #  리스트로 변환

    # 5단계 : 빈 문자열이라면, new_id에 "a"를 대입
    if not tmp:
        tmp.append('a')
    
    # 6단계 : 길이가 16자 이상이면 new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거
    #         제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거
    if len(tmp) >= 16:
        tmp = tmp[:15]
    tmp = lastPoint(list(tmp))
    
    # 7단계 : 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙인다.
    if len(tmp) <= 2:
        lastChar = tmp[-1]
        while len(tmp) != 3:
            tmp += lastChar
    
    answer = ''.join(tmp)
    
    return answer