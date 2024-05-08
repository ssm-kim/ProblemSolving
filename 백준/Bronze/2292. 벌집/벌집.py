n = int(input())
idx, cur_pos = 1, 1
while cur_pos < n:
    cur_pos = cur_pos + (6*idx)
    idx += 1
print(idx)