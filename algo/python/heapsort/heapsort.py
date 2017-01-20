from random import randint
import time

def parent(i):
    return i>>1

def right(i):
    return (i<<1) + 1

def left(i):
    return i<<1

class Heap: 
    def __init__(self, data):
        self.A = data
        self.size = len(self.A)
        self.length = len(self.A)

    @classmethod
    def rand(cls, x):
        nums = list(range(1,x))
        data = []
        while len(nums)>0:
            data.append(nums.pop(randint(0,len(nums)-1)))
        return cls(data)

    @classmethod
    def list(cls, x):
        return cls(x)

    def get(self, i):
        return self.A[i-1]

    def swap(self, a, b):
        temp = self.A[a-1]
        self.A[a-1] = self.A[b-1]
        self.A[b-1] = temp

    def heapsize(self):
        return self.size

    def dec(self):
        self.size -= 1

    def print_heap(self):
        for i in range(self.length):
            print(self.A[i], end=" ")
        print()

# #################################################
# 
# clrs section 6.2
# returns a max heap at node i in the heap a
# if both the left and right subtrees are max heaps
# 
# #################################################
def max_heapify(a, i):
    # a.print_heap()
    l = left(i)
    r = right(i)
    if(l <= a.heapsize() and a.get(l)>a.get(i)):
        largest = l
    else: largest = i
    if(r <= a.heapsize() and a.get(r)>a.get(largest)):
        largest = r
    if largest != i:
        a.swap(i, largest)
        max_heapify(a, largest)
    return a


# #################################################
# 
# clrs section 6.3
# returns a max heap from heap a in $O(n)$ time
# 
# #################################################
def build_max_heap(a):
    for i in range(a.length//2, 0, -1):
        a = max_heapify(a, i)
    return a

# #################################################
# 
# clrs section 6.4
# returns a max heap from heap a in $O(n)$ time
# 
# #################################################
def heap_sort(a):
    a = build_max_heap(a)
    for i in range(a.heapsize(), 1, -1):
        a.swap(1,i)
        a.dec()
        a = max_heapify(a,1)
    return a

def main():
    # a = Heap.list([5,3,17,10,84,19,6,22,9])
    a = Heap.rand(100000)
    # a.print_heap()
    start = time.clock()
    a = heap_sort(a)
    end = time.clock()
    print(end - start)
    # a.print_heap()

if __name__ == "__main__":
    main()
