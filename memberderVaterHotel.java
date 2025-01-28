import java.util.InputMismatchException;
import java.util.Scanner;

class Customers {
    private boolean isCouple;
    private boolean ismember;
    private boolean isMarried;

    public Customers(boolean isCouple, boolean ismember, boolean isMarried) {
        this.isCouple = isCouple;
        this.ismember = ismember;
        this.isMarried = isMarried;
    }

    public boolean isCouple() {
        return isCouple;
        
    }

    public boolean ismember() {
        return ismember;
    }

    public boolean isMarried() {
        return isMarried;
    }
}

class Food {
    public static final double SHORSHE_ILISH = 500;
    public static final double CHINGRI_MALAI_CURRY = 400;
    public static final double KACCHI_BIRYANI = 300;
    public static final double PANTA_BHAAT_HILSA = 200;
}

class Order extends Customers {
    private double foodBill = 0;
    private double discount = 0;

    public Order(boolean isCouple, boolean ismember, boolean isMarried) {
        super(isCouple, ismember, isMarried);
    }

    public void addFood(int choice) {
        switch (choice) {
            case 1:
                foodBill += Food.SHORSHE_ILISH;
                break;
            case 2:
                foodBill += Food.CHINGRI_MALAI_CURRY;
                break;
            case 3:
                foodBill += Food.KACCHI_BIRYANI;
                break;
            case 4:
                foodBill += Food.PANTA_BHAAT_HILSA;
                break;
            default:
                System.out.println("Invalid choice. Please select a valid food item.");
        }
    }

    public void calculateDiscount() {
        if (isCouple()) {
            discount += ismember() ? 5 : 0;
            discount += isMarried() ? 15 : 0;
        } else {
            discount += ismember() ? 40 : 25;
        }
    }

    public double getFinalBill() {
        return foodBill - (foodBill * discount / 100);
    }

    public double getDiscountAmount() {
        return (foodBill * discount / 100);
    }

    public double getFoodBill() {
        return foodBill;
    }

    public double getDiscountPercentage() {
        return discount;
    }
}

public class memberderVaterHotel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to memberder Vater Hotel!");
        try {
            System.out.print("Enter the number of customers: ");
            int customerCount = scanner.nextInt();

            double totalSales = 0;
            double totalDiscountGiven = 0;
            int totalCustomers = 0;

            for (int i = 1; i <= customerCount; i++) {
                System.out.println("\nCustomer " + i + ":");

                String relationshipStatus;
                while (true) {
                    System.out.print("Are you a couple or single? (couple/single): ");
                    relationshipStatus = scanner.next().toLowerCase();
                    if (relationshipStatus.equals("couple") || relationshipStatus.equals("single")) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'couple' or 'single'.");
                    }
                }

                boolean isCouple = relationshipStatus.equals("couple");
                boolean ismember = false;
                boolean isMarried = false;

                if (isCouple) {
                    while (true) {
                        System.out.print("Are both of you 'member'? (yes/no): ");
                        String input = scanner.next().toLowerCase();
                        if (input.equals("yes") || input.equals("no")) {
                            ismember = input.equals("yes");
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    }
                    while (true) {
                        System.out.print("Are you married? (yes/no): ");
                        String input = scanner.next().toLowerCase();
                        if (input.equals("yes") || input.equals("no")) {
                            isMarried = input.equals("yes");
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    }
                } else {
                    while (true) {
                        System.out.print("Are you 'member'? (yes/no): ");
                        String input = scanner.next().toLowerCase();
                        if (input.equals("yes") || input.equals("no")) {
                            ismember = input.equals("yes");
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    }
                }

                Order order = new Order(isCouple, ismember, isMarried);

                System.out.println("Select Bengali food items (type the corresponding number):");
                System.out.println("1. Shorshe Ilish - BDT 500");
                System.out.println("2. Chingri Malai Curry - BDT 400");
                System.out.println("3. Kacchi Biryani - BDT 300");
                System.out.println("4. Panta Bhaat with Hilsa - BDT 200");

                boolean addingFood = true;
                while (addingFood) {
                    try {
                        System.out.print("Enter food item number (or 0 to finish): ");
                        int foodChoice = scanner.nextInt();
                        if (foodChoice == 0) {
                            addingFood = false;
                        } else {
                            order.addFood(foodChoice);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                    }
                }

                order.calculateDiscount();
                System.out.println("Food Bill: BDT " + order.getFoodBill());
                System.out.println("Discount Applied: " + order.getDiscountPercentage() + "% (BDT " + order.getDiscountAmount() + ")");
                System.out.println("Final Bill: BDT " + order.getFinalBill());

                totalDiscountGiven += order.getDiscountAmount();
                totalSales += order.getFinalBill();
                totalCustomers++;
            }

            System.out.println("\n======= memberder Vater Hotel Summary =======");
            System.out.println("Total Customers Served: " + totalCustomers);
            System.out.println("Total Sales: BDT " + totalSales);
            System.out.println("Total Discount Given: BDT " + totalDiscountGiven);
            System.out.println("==========================================");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input detected. Please restart the program and provide correct inputs.");
        } finally {
            scanner.close();
        }
    }
}
