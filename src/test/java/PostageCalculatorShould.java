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
    void calculate_for_a_fixed_cost_of_120(int weight, int height, int width, int depth, int expected) {

        PostageCalculator postageCalculator = new PostageCalculator();

        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }

    @ParameterizedTest
    @CsvSource({
            "250, 220, 150, 10, 1000",
            "150, 180, 125, 22, 600",
            "40,300, 100,50, 160",
    })
    void calculate_variable_cost_dependant_on_weight(int weight, int height, int width, int depth, int expected) {
        PostageCalculator postageCalculator = new PostageCalculator();

        Money actualAmount = postageCalculator.calculate(weight, height, width, depth, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(expected), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }
}