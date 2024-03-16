import sys

# Reading input
N = int(input())
ary = list(map(int, input().split()))

ary.sort()

# Calculating differences
diff = [ary[i + 1] - ary[i] for i in range(N - 1)]

# Initializing dp array
dp = [[0, 0] for _ in range(N - 1)]

# Dynamic programming
dp[0][1] = diff[0]
for i in range(1, N - 1):
    if i % 2 != 0:
        dp[i][0] = dp[i - 1][0] + diff[i]
        dp[i][1] = dp[i - 1][1]
    else:
        dp[i][1] = dp[i - 1][1] + diff[i]
        dp[i][0] = dp[i - 1][0]

# Finding minimum answer
min_answer = sys.maxsize
for i in range(N):
    if i % 2 == 0:
        continue

    left = dp[i - 2][1] if i >= 2 else 0
    right = dp[N - 2][0] - dp[i][0]
    middle = diff[i - 1] + diff[i]

    answer = left + middle + right
    min_answer = min(answer, min_answer)

print(min_answer)