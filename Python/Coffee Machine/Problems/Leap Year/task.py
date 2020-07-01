# put your python code here
year = int(input())
if year % 4 == 0 and year % 100 != 0:
    print('Leap')
elif year % 400 == 0:
    print('Leap')
else:
    print('Ordinary')
