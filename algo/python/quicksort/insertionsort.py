import time

def print_list(A):
    for i in A :
        print(i, end=" ")
    print()

def insertion_sort(A):
    for j in range(1, len(A)):
        key = A[j]
        i = j - 1
        while(i>=0 and A[i]>key):
            A[i+1] = A[i]
            i = i - 1
        A[i+1] = key

def main():
    a = [5,3,17,10,84,19,6,22,9]
    # a = [13,11,9,12,12,4,9,4,21,2,6,11]
    # a = rand(100000)
    print_list(a)
    # print(parition(a, 0, len(a)-1))
    start = time.clock()
    # a = insertion_sort(a)
    insertion_sort(a)
    end = time.clock()
    print_list(a)
    print(end - start)
    # a.print_heap()

if __name__ == "__main__":
    main()
