'''
    Recursion (재귀)
        - 자신을 정의할 때 자기 자신을 재참조하는 함수
    시간복잡도
        - 재귀함수 전체 시간복잡도 = 재귀 함수 호출 수 * (재귀 함수 하나당) 시간복잡도
        ※ Execution Tree(recursion tree) : 재귀 함수의 실행 흐름을 나타내는데 사용되는 트리
        
    구성요소 2가지
    recurrence relation (점화식)
        - fn을 f(n-1), f(n-2), ... , f(2), f()1의 관계식으로 표현하는 것을 말한다.
        - 팩토리얼, 피보나치수열, 파스칼의 삼각형

    base case
        - 더 이상 재귀호출을 하지 않아도 계산값을 반환할 수 있는 상황(조건)을 말한다.
        - 모든 입력이 최종적으로 base case을 이용해서 문제를 해결할 수 있어야 한다.
        - basecase가 무조건 있어야 재귀함수의 무한루프를 방지할 수 있다.
'''

# 팩토리얼
def factorial(n):
    if n == 1:
        return 1
    else:
        return n * factorial(n-1)

print(factorial(4))

print('------------------------------')
# 피보나치 수열
def fibo(n):
    if n == 1 or n == 2:
        return 1
    return fibo(n-1) + fibo(n-2)

print(fibo(5))
