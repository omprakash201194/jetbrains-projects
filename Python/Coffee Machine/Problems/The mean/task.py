num = input()
numbers = []
while num != ".":
    numbers.append(int(num))
    num = input()
mean = 0
n: int
for n in numbers:
    mean += n
print(mean / len(numbers))
