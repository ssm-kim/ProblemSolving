def solution(participant, completion):
    answer = ''
    participant.sort()
    completion.sort()
    
    for x, y in zip(participant, completion):
        if x != y:
            answer = x
            break
    
    if answer == '':
        answer = participant[-1]        
    
    return answer