# Write your code here
class CoffeeMachine:
    def __init__(self):
        self.available_money = 550
        self.available_water = 400
        self.available_milk = 540
        self.available_coffee_beans = 120
        self.available_disposable_cups = 9

    def print_coffee_machine_stats(self):
        print("The coffee machine has:")
        print(self.available_water, "of water")
        print(self.available_milk, "of milk")
        print(self.available_coffee_beans, "of coffee beans")
        print(self.available_disposable_cups, "of disposable cups")
        print(self.available_money, "of money")

    def update_resources(self, water, milk, coffee_beans, cost):
        if self.available_water < water:
            return "Sorry, not enough water!"
        if self.available_milk < milk:
            return "Sorry, not enough milk!"
        if self.available_coffee_beans < coffee_beans:
            return "Sorry, not enough coffee beans!"
        if self.available_disposable_cups < 1:
            return "Sorry, not enough cups!"
        self.available_money += cost
        self.available_water -= water
        self.available_milk -= milk
        self.available_coffee_beans -= coffee_beans
        self.available_disposable_cups = self.available_disposable_cups - 1
        return "I have enough resources, making you a coffee!"

    def fill(self):
        self.available_water += int(input("Write how many ml of water do you want to add:"))
        self.available_milk += int(input("Write how many ml of milk do you want to add:"))
        self.available_coffee_beans += int(input("Write how many grams of coffee beans do you want to add:"))
        self.available_disposable_cups += int(input("Write how many disposable cups of coffee do you want to add:"))

    def take(self):
        print("I gave you $" + str(self.available_money))
        self.available_money = 0


coffee_machine = CoffeeMachine()
choice = input("Write action (buy, fill, take, remaining, exit):")
while choice != "exit":
    if choice == "buy":
        option = input("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        if option == "1":
            print(coffee_machine.update_resources(250, 0, 16, 4))
        elif option == "2":
            print(coffee_machine.update_resources(350, 75, 20, 7))
        elif option == "3":
            print(coffee_machine.update_resources(200, 100, 12, 6))
    elif choice == "fill":
        coffee_machine.fill()
    elif choice == "take":
        coffee_machine.take()
    elif choice == "remaining":
        coffee_machine.print_coffee_machine_stats()
    choice = input("Write action (buy, fill, take, remaining, exit):")
