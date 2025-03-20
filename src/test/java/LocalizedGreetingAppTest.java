import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class LocalizedGreetingAppTest {
    @Test
    void testCalculateTotalPrice() {
        String input = "10\n2\n5\n3\n";
        Scanner scanner = new Scanner(input);

        ResourceBundle rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));

        double result = LocalizedGreetingApp.calculateTotalPrice(2, rb, scanner);

        // Expected total cost = (10 * 2) + (5 * 3) = 20 + 15 = 35
        assertEquals(35.0, result, 0.001);

        // Should fail
        assertNotEquals(33.0, result);
    }
}
