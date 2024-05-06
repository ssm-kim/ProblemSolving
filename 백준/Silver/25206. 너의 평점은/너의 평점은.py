score = { 'A+': 4.5, 'A0': 4.0, 'B+': 3.5,
          'B0': 3.0, 'C+': 2.5, 'C0': 2.0,
          'D+': 1.5, 'D0': 1.0, 'F': 0.0   }

n, sum, totalGrade = 20, 0, 0

for i in range(n):
    subject, grade, rank = input().split()
    grade = float(grade)   # 학점
    if rank == 'P':        # P는 pass이므로 계산제외
        continue
    
    rank = score[rank]
    totalGrade += grade    # 학점의 총합
    sum += (grade * rank)  # 학점 * 과목평점

print('{:.6f}'.format(sum / totalGrade))