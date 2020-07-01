package machine;

import java.util.Scanner;

public class CoffeeMachine {
    int money;
    int waterQuantity;
    int milkQuantity;
    int coffeeBeansQuantity;
    int disposableCups;

    public CoffeeMachine(int money, int waterQuantity, int milkQuantity, int coffeeBeansQuantity, int disposableCups) {
        this.money = money;
        this.waterQuantity = waterQuantity;
        this.milkQuantity = milkQuantity;
        this.coffeeBeansQuantity = coffeeBeansQuantity;
        this.disposableCups = disposableCups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public void setWaterQuantity(int waterQuantity) {
        this.waterQuantity = waterQuantity;
    }

    public int getMilkQuantity() {
        return milkQuantity;
    }

    public void setMilkQuantity(int milkQuantity) {
        this.milkQuantity = milkQuantity;
    }

    public int getCoffeeBeansQuantity() {
        return coffeeBeansQuantity;
    }

    public void setCoffeeBeansQuantity(int coffeeBeansQuantity) {
        this.coffeeBeansQuantity = coffeeBeansQuantity;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {
        this.disposableCups = disposableCups;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine(550, 400, 540, 120, 9);
        String action;
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.nextLine();
            machine.simulate(action);
        } while (!Operation.exit.name().equals(action));
    }

    enum Operation {
        buy, fill, take, remaining, exit
    }

    private void simulate(String action) {
        switch (Operation.valueOf(action)) {
            case buy:
                buyProcess();
                break;
            case fill:
                fillProcess();
                break;
            case take:
                takeProcess();
                break;
            case remaining:
                printIngredients();
            default:
                System.out.println("Unknown operation");
        }
    }

    private void takeProcess() {
        System.out.println("I gave you $" + this.getMoney());
        this.setMoney(0);
    }

    private void fillProcess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        this.setWaterQuantity(this.getWaterQuantity() + scanner.nextInt());

        System.out.println("Write how many ml of milk do you want to add:");
        this.setMilkQuantity(this.getMilkQuantity() + scanner.nextInt());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.setCoffeeBeansQuantity(this.getCoffeeBeansQuantity() + scanner.nextInt());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.setDisposableCups(this.getDisposableCups() + scanner.nextInt());

    }

    private void buyProcess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want ot buy? 1 - espresso, " +
                "2 - latte, 3 - cappuccino, back - to main menu:");
        switch (scanner.nextLine()) {
            case "1":
                espressoProcess();
                break;
            case "2":
                latteProcess();
                break;
            case "3":
                cappuccinoProcess();
                break;
        }
    }

    private void cappuccinoProcess() {
        if (validateWater(200) && validateMilk(100) &&
                validateCoffeeBeans(12) && validateCups()) {
            updateResources(6, 200, 100, 12);
        }
    }

    private void updateResources(int cost, int water, int milk, int coffeeBean) {
        System.out.println("I have enough resources, making you a coffee!");
        this.setMoney(this.getMoney() + cost);
        this.setWaterQuantity(this.getWaterQuantity() - water);
        this.setMilkQuantity(this.getMilkQuantity() - milk);
        this.setCoffeeBeansQuantity(this.getCoffeeBeansQuantity() - coffeeBean);
        this.setDisposableCups(this.getDisposableCups() - 1);
    }

    private boolean validateWater(int neededWater) {
        if (this.getWaterQuantity() < neededWater) {
            System.out.println("Sorry, not enough water");
            return false;
        }
        return true;
    }

    private boolean validateMilk(int neededMilk) {
        if (this.getMilkQuantity() < neededMilk) {
            System.out.println("Sorry, not enough milk");
            return false;
        }
        return true;
    }

    private boolean validateCoffeeBeans(int coffeeBeans) {
        if (this.getCoffeeBeansQuantity() < coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans");
            return false;
        }
        return true;
    }

    private boolean validateCups() {
        if (this.getDisposableCups() < 1) {
            System.out.println("Sorry, not enough disposable cups");
            return false;
        }
        return true;
    }

    private void latteProcess() {
        if (validateWater(350) && validateMilk(75)
                && validateCoffeeBeans(20) && validateCups()) {
            updateResources(7, 350, 75, 20);
        }
    }

    private void espressoProcess() {
        if (validateWater(250) && validateCoffeeBeans(16) && validateCups()) {
            updateResources(4, 250, 0, 16);
        }
    }

    private void printIngredients() {
        System.out.println("The coffee machine has:");
        System.out.println(this.getWaterQuantity() + " of water");
        System.out.println(this.getMilkQuantity() + " of milk");
        System.out.println(this.getCoffeeBeansQuantity() + " of coffee beans");
        System.out.println(this.getDisposableCups() + " of disposable cups");
        System.out.println("$"+ this.getMoney() + " of money");
    }
}
