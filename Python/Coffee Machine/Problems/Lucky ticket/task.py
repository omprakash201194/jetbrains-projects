# Save the input in this variable
ticket = input()
ticket = list(ticket)

# Add up the digits for each half
half1 = 0
counter = 0
while counter < (len(ticket) // 2):
    half1 += int(ticket[counter])
    counter += 1

half2 = 0
counter = len(ticket) // 2
while counter < len(ticket):
    half2 += int(ticket[counter])
    counter += 1

# Thanks to you, this code will work
if half1 == half2:
    print("Lucky")
else:
    print("Ordinary")
