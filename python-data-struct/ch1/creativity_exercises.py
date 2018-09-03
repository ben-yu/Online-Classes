def find_odd_product(elems):
    for i in range(len(elems)):
        for j in range(len(elems)):
            if i != j and elems[i] * elems[j] % 2 != 0:
                return elems[i], elems[j]

def is_uniq(elems):
    return len(set(elems)) == len(elems)

# Stopped at C-1.20

print(find_odd_product([1,2,3,4,5]))
print(is_uniq([1,2,3]))
print(is_uniq([1,2,3,2]))
print([x*(x+1) for x in range(10)]) # Pronic Numbers
print([chr(ord('a') + i) for i in range(26)])
