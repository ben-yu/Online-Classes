def max_elem(elems):
    if len(elems) == 1:
        return elems[0]
    return max(elems[0], max_elem(elems[1:]))

def harmonic(n):
    if n == 1:
        return 1

    return (1/n) + harmonic(n-1)

def atoi_helper(input_str, n):
    if n == 1:
        return ord(input_str[0]) - ord('0')

    return (10 * atoi_helper(input_str, n-1)) + ord(input_str[n-1]) - ord('0')

def atoi(input_str):
    return atoi_helper(input_str, len(input_str))

print(max_elem([10,4,100,52,2151,52,2521]))
print(harmonic(5))
print(atoi('12345'))
