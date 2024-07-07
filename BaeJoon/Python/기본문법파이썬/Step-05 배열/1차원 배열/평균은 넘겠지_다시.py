import sys
sys.stdin = open('../input.txt', 'r')

for _  in range(int(sys.stdin.readline())):
    score = list(map(int, sys.stdin.readline().split()))
    student_count = score.pop(0) #  총 학생 수
    Avg = sum(score)/student_count # 평균
    Up_Avg = 0 # 평균 이상인 학생 수
    for x in score:
        if x > Avg:
            Up_Avg+=1
    print('{:.3f}%'.format(Up_Avg*100 / student_count)) # 비율 : *100 곱해야 함.