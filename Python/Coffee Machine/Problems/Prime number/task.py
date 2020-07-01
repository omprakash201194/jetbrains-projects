num = int(input())
i = 3
is_prime = False
while i < num:
    if num % i == 0:
        is_prime = False
        break
    else:
        is_prime = True
    i = i + 2
if num == 2 or num == 3 or is_prime:
    print("This number is prime")
else:
    print("This number is not prime")
