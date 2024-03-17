'''
  Valid Parentheses(유효한 괄호) 문제
    - '(){}[]'를 포함하고 있는 문자열 s가 주어졌을때, 괄호가 유효한지 아닌지 판별하시오
  
  제약조건
    - 1 <= n <= 10**4
    - 문자열 s는 괄호들로만 구성되어 있다

  Test Case : ')(', '([]}', '{()[]}', '(()}', '(({[]}()[[]]))'
  Output : false, false, true, false, true
'''

def isValid(s):
    stack = []
    for i in s:
        if i == '(':
            stack.append(')')
        elif i == '[':
            stack.append(']')
        elif i == '{':
            stack.append('}')
        elif not stack or stack.pop() != i:
            return False
        # print(stack)  # 검증
    return not stack  # True와 같다

print(isValid('(()}'))