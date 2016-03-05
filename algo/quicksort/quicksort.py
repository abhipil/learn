from random import randint
import time

def parition(A, p, r):
    x = A[r]
    i = p-1
    for j in range(p,r):
        if A[j] <= x :
            i = i + 1
            swap(A, i, j)
    swap(A, i+1, r)
    return i+1


def quick_sort(A, p, r):
    if p<r:
        q = parition(A, p, r)
        quick_sort(A, p, q-1)
        quick_sort(A, q+1, r)
    return A

def print_list(A):
    for i in A :
        print(i, end=" ")
    print()

def swap(A, i, j):
    temp = A[i]
    A[i] = A[j]
    A[j] = temp

def rand(x):
        nums = list(range(1,x))
        data = []
        while len(nums)>0:
            data.append(nums.pop(randint(0,len(nums)-1)))
        return data

def main():
    # a = [5,3,17,10,84,19,6,22,9]
    # a = [13,19,9,5,12,8,7,4,21,2,6,11]
    a = rand(100000)
    # print_list(a)
    start = time.clock()
    a = quick_sort(a, 0, len(a)-1)
    end = time.clock()
    # print_list(a)
    print(end - start)
    # a.print_heap()

if __name__ == "__main__":
    main()