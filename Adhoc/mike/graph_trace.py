from collections import Counter

def graph_trace(input):
    cap_c = next(c for i, c in enumerate(input) if i == c)
    result = [0] * len(input)
    result[cap_c] = -1

    for i in range(len(input)):
        gt(result, i, input)

    result[cap_c] = 0
    c = Counter(result)
    return [c[i] for i in range(max(c) + 1)]

def gt(result, i, input):
    if result[i] > 0 or result[i] < 0:
        return max(result[i], 0)

    result[i] += gt(result, input[i], input) + 1
    return result[i]

print(graph_trace([3, 0, 2, 2, 0]))
