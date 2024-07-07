import sys
n = int(sys.stdin.readline().strip())

words = [sys.stdin.readline().strip() for _ in range(n)]
words = list(set(words))
words.sort()
words.sort(key=lambda words:(len(words)))
for i in words:
    sys.stdout.write(i+'\n')