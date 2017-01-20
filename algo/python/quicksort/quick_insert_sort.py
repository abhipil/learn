import time
from quicksort import quick_sort, parition, swap, print_list, rand
from insertionsort import insertion_sort

def k():
    return 100

def quick_insert_sort(A, p, r):
    if p<r:
        q = parition(A, p, r)
        if q>k():
            quick_sort(A, p, q-1)
            quick_sort(A, q+1, r)
        else:
            insertion_sort(A)
    return A

def main():
    # a = [5,3,17,10,84,19,6,22,9]
    # a = [13,11,9,12,12,4,9,4,21,2,6,11]
    print("Quick-Insertion sort :", end=' ')
    a = rand(100000)
    # print_list(a)
    start = time.clock()
    a = quick_insert_sort(a, 0, len(a)-1)
    end = time.clock()
    # print_list(a)
    print(end - start)
    print("Quick sort :", end=' ')
    a = rand(100000)
    start = time.clock()
    a = quick_sort(a, 0, len(a)-1)
    end = time.clock()
    print(end - start)
    print("Insertion sort :", end=' ')
    a = rand(100000)
    start = time.clock()
    insertion_sort(a)
    end = time.clock()
    print(end - start)

if __name__ == "__main__":
    main()
