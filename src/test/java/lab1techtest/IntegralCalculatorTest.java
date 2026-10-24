package lab1techtest;

import lab1tech.Function;
import lab1tech.IntegralCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegralCalculatorTest {

    @Test
    public void testFunction() {
        // Заменили Variant3Function на Function
        Function f = new Function();
        assertEquals(6.0, f.calculate(1), 1e-9);
        assertEquals(36.0, f.calculate(4), 1e-9);
    }

    @Test
    public void testIntegralCalculator() throws InterruptedException {
        // Заменили Variant3Function на Function
        Function f = new Function();
        IntegralCalculator calculator = new IntegralCalculator();

        // Перевірка на різній кількості потоків
        double result1 = calculator.calculate(1, 9, 1000, f, 1);
        double result4 = calculator.calculate(1, 9, 1000, f, 4);

        assertEquals(172.0, result1, 1e-6);
        assertEquals(172.0, result4, 1e-6);
    }
}