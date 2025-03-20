import java.util.Locale;
import java.util.Scanner;
import java.util.ResourceBundle;

public class LocalizedGreetingApp {

    public static double calculateTotalPrice(int itemAmount, ResourceBundle rb, Scanner scanner) {
        double totalCost = 0;

        for (int i = 0; i < itemAmount; i++) {
            

            System.out.printf(rb.getString("price"), i + 1);
            double price = scanner.nextDouble();

            System.out.printf(rb.getString("quantity"), i + 1);
            int quantity = scanner.nextInt();

            double calculatedPrice = price * quantity;

            totalCost += calculatedPrice;
            
        }
        scanner.close();
        

        return totalCost;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our Total Cost Calculator!");
        System.out.println("Please choose the language: ");
        System.out.println("1. Finnish");
        System.out.println("2. Swedish");
        System.out.println("3. Japanese");
        int language = scanner.nextInt();

        Locale locale;

        switch (language) {
            case 1:
                locale = new Locale("fi", "FI");
                break;
            case 2:
                locale = new Locale("sv", "SE");
                break;
            case 3:
                locale = new Locale("ja", "JP");
                break;
            default:
                System.out.println("Invalid language selection! Switching to default (en) language.");
                locale = new Locale("en", "US");
                break;
        }

        ResourceBundle rb;

        try {
            rb = ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            System.out.println("Invalid language is not in the list");
            rb = ResourceBundle.getBundle("messages", locale);
        }

        
        System.out.print(rb.getString("amountOfItems"));
        int amountOfItems = scanner.nextInt();

        // Incrementing everytime a product is processed
        double totalCost = calculateTotalPrice(amountOfItems, rb, scanner);


        System.out.printf(rb.getString("totalCost"), totalCost);


        scanner.close();

    }
}
