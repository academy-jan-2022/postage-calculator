import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

class PostageCalculatorShould {

    @ParameterizedTest
    @CsvSource({
            "50, 220, 150, 10, 120",
            "60, 180, 125, 22, 120",
    })
    void calculate_tier_one_price_dependant_on(int weight, int height, int width, int depth, int expected) {

        PostageCalculator postageCalculator = new PostageCalculator();

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
        PostageCalculator postageCalculator = new PostageCalculator();

        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }

    @ParameterizedTest
    @CsvSource({
            "600,0,0,0,3600",
            "400,325,0,0,2400",
            "300,0,230,0,1800",
            "200,0,0,101,1200"
    })
    void calculate_tier_three_price_dependant_on(int weight, int height, int width, int depth, int expected) {
        PostageCalculator postageCalculator = new PostageCalculator();

        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }
}