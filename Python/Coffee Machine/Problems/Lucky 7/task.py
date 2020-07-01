size = int(input())
numbers = []
for _i in range(size):
    numbers.append(int(input()))
for n in numbers:
    if n % 7 == 0:
        print(n * n)
