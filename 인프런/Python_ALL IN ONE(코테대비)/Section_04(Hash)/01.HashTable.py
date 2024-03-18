
'''
    Hash Table (Dictionary)
        - 효율적인 탐색을 위한 자료구조로 key-value쌍의 데이터를 입력받는다.
        - 저장, 삭제, 검색 시간복잡도는 모두 O(1)이다.
'''

score = {
    'math' : 97,
    'eng' : 49,
    'kor' : 89
}

score['math'] = 45
score['sci'] = 100

print(score, end='\n\n')

if 'sci' in score:  # 시간복잡도 O(1)
    print('sci : {}'.format(score['sci']))
else:
    print('딕셔너리에 key값이 없습니다.')