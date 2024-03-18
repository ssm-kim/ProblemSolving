'''
    주어진 방향으로 이동할 때 범위를 벗어나거나 이동하면서 장애물을 만나는지 확인
    위 사항 중 하나라도 해당된다면 Pass 후 다음 명령 수행
    S: 시작 지점, O: 이동 가능, X: 장애물이며 직사각형이다.

    풀이 방법
        1. 시작 지점을 찾는다
        2. routes만큼 순회한다.
        3. 이동할 위치 좌표와 현재 위치 좌표를 교환한다.
           단, 범위를 벗어나거나 장애물을 만나면 다음 명령을 수행한다.
'''
def solution(park, routes):
    answer = []
    park = [list(i) for i in park]  # 문자열을 리스트로 변환
    for i in range(len(park)):
        coordinate = park[i]
        if 'S' in coordinate:  # start는 시작점 x, y 좌표값
            start, totXlen, totYlen = (i, coordinate.index('S')), len(park), len(coordinate)
            break

    # dx direct x  /  nx next x  /  cx current x
    for route in routes:
        nx, ny = start           # 현재 시작지점 x, y 좌표
        way, mv = route.split()  # 방향, 이동거리
        mv = int(mv)
        print(nx, ny, way, mv)
        if (way == 'E') and (0 <= ny+mv < totYlen):  # 오른쪽으로 이동시 범위벗어나는지 확인
            for i in range(ny, ny+mv+1):
                s = park[nx][i]
                if s == 'X':
                    break
            else:
                park[nx][ny], park[nx][ny+mv] = park[nx][ny+mv], park[nx][ny]  # 현재위치와 이동된 위치 값 교환
                start = (nx, ny+mv)
        
        elif (way == 'W') and (0 <= ny-mv < totYlen):  # 왼쪽으로 이동시 범위벗어나는지 확인
            for i in range(ny, ny-mv-1, -1):
                s = park[nx][i]
                if s == 'X':
                    break
            else:
                park[nx][ny], park[nx][ny-mv] = park[nx][ny-mv], park[nx][ny]  # 현재위치와 이동된 위치 값 교환
                start = (nx, ny-mv)
            
        elif (way == 'S') and (0 <= nx+mv < totXlen):  # 아래로 이동시 범위벗어나는지 확인
            for i in range(nx, nx+mv+1):
                s = park[i][ny]
                if s == 'X':
                    break
            else:
                park[nx][ny], park[nx+mv][ny] = park[nx+mv][ny], park[nx][ny]   # 현재위치와 이동된 위치 값 교환
                start = (nx+mv, ny)

        elif (way == 'N') and (0 <= nx-mv < totXlen):  # 위로 이동시 범위벗어나는지 확인
            for i in range(nx, nx-mv-1, -1):
                s = park[i][ny]
                if s == 'X':
                    break
            else:
                park[nx][ny], park[nx-mv][ny] = park[nx-mv][ny], park[nx][ny]   # 현재위치와 이동된 위치 값 교환
                start = (nx-mv, ny)
    
    answer = list(start)
    
    return answer