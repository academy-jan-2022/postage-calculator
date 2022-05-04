import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

class PostageCalculatorShould {

    private PostageCalculator postageCalculator;

    @BeforeEach
    void setUp() {
        postageCalculator = new PostageCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "50, 220, 150, 10, 120",
            "60, 180, 125, 22, 120",
    })
    void calculate_tier_one_price_dependant_on(int weight, int height, int width, int depth, int expected) {
        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }

    @ParameterizedTest
    @CsvSource({
            "250, 220, 150, 10, 1000",
            "150, 180, 125, 22, 600",
            "40,300, 0, 0, 160",
            "40,324, 0, 0, 160",
            "40, 0, 170 ,0, 160",
            "50, 0, 229 ,0, 200",
            "50, 0, 229 ,0, 200",
            "20, 0, 0 ,80, 80",
            "20, 0, 0 ,100, 80",
    })
    void calculate_tier_two_price_dependant_on(int weight, int height, int width, int depth, int expected) {
        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }

    @ParameterizedTest
    @CsvSource({
            "600,0,0,0,3600",
            "400,325,0,0,2400",
            "300,0,230,0,1800",
            "200,0,0,101,1200",
    })
    void calculate_tier_three_price_dependant_on(int weight, int height, int width, int depth, int expected) {
        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }

    @Test
    void calculate_tier_three_price_on_big_dimension() {
        Money actualAmount = postageCalculator.calculate(100, 500, 10, 10, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(30_000), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }

    @Test
    void calculate_price_on_EUR() {
        Money actualAmount = postageCalculator.calculate(250, 220, 150, 10, Currency.EUR);

        BigDecimal expected = new BigDecimal("1190.00").add(BigDecimal.valueOf(20));
        Assertions.assertEquals(expected, actualAmount.amount());
        Assertions.assertEquals(Currency.EUR, actualAmount.currency());
    }

    @Test
    void calculate_price_on_USD() {
        Money actualAmount = postageCalculator.calculate(250, 220, 150, 10, Currency.USD);

        BigDecimal expected = new BigDecimal("1250.00").add(BigDecimal.valueOf(20));
        Assertions.assertEquals(expected, actualAmount.amount());
        Assertions.assertEquals(Currency.USD, actualAmount.currency());
    }
}