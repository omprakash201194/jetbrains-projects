# put your python code here
num = int(input())
first = (num // 100)
mid = (num % 100) // 10
last = num % 10
print(first + mid + last)