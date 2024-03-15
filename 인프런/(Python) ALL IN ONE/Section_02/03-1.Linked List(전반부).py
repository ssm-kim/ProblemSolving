# Linked List
# 배열로 하면, visit 할 때마다 clear up all the forward history에서 O(n)이 나온다. 그치만, step = n은 100에 불과하고,  call의 총 수는 5000이니까 시간초과가 나진 않을 것이다.
class ListNode(object):
    def __init__(self, val=0, next=None, prev=None):
        self.val = val
        self.next_ = next
        self.prev = prev

class BrowserHistory(object):
    def __init__(self, homepage):
        # 이 문제에서는 head가 없어도 된다.
        self.head = self.current = ListNode(val=homepage)

    def visit(self, url):
        # clear up memory free 안해줘도 되는건가? => GC
        self.current.next_ = ListNode(val=url, prev=self.current)
        self.current = self.current.next_
        return None

    def back(self, steps):
        while steps > 0 and self.current.prev != None:
            steps -= 1
            self.current = self.current.prev
        return self.current.val

    def forward(self, steps):
        while steps > 0 and self.current.next_ != None:
            steps -= 1
            self.current = self.current.next_
        return self.current.val