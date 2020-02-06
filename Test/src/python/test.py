def reconstruct_jumbled(array):
    answer = []
    n = len(array)-1
    stack = []
    for i in range(n):
        if array[i+1]=='-':
            stack.append(i)
        else:
            answer.append(i)
            while stack:
                answer.append(stack.pop())

    stack.append(n)
    while stack:
        answer.append(stack.pop())
    
    return answer


print(reconstruct_jumbled([None, '+', '+', '-', '-']))
                            # 0,   1,   2,   3,  4
