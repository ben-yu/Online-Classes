import sys
from random import randrange

def is_multiple(n, m):
    return n % m == 0

def is_even(k):
    return k % 2 == 0

def minmax(list_of_int):
    min_int = sys.maxint
    max_int = -sys.maxint - 1

    for i in list_of_int:
        if i > max_int:
            max_int = i
        if i < min_int:
            min_int = i

    return min_int, max_int

def sum_of_sq(x):
    result = 0

    for i in range(x):
        result += i ** 2

    return result

def sum_of_odd_sq(x):
    result = 0

    for i in range(x):
        if i % 2 == 1:
            result += i ** 2

    return result

def choice(elems):
    return elems[randrange(len(elems))]


print(is_multiple(9,3))
print(is_multiple(10,4))

print(is_even(10000))
print(is_even(12345))

print(minmax([1,2,3,4,5,6,7,8,9]))
print(minmax([1,-1,6,100000,4252,12521521515251]))

print(sum_of_sq(0))
print(sum_of_sq(2))
print(sum_of_sq(5))
print(sum(i**2 for i in range(5)))

print(sum_of_odd_sq(0))
print(sum_of_odd_sq(2))
print(sum_of_odd_sq(5))
print(sum(i**2 for i in range(5) if i % 2 == 1))

negative_idx_test = "some_string"
print(negative_idx_test[4]==negative_idx_test[4-len(negative_idx_test)])
print(range(50,90,10))
print(range(8,-10,-2))
print([2 ** i for i in range(9)])

print(choice([1,2,3]))
