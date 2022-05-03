import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PostageCalculatorShould {

    @Test
    void calculate_for_a_fixed_cost_of_120() {

        PostageCalculator postageCalculator = new PostageCalculator();

        Money actualAmount = postageCalculator.calculate(50, 220, 150, 10, Currency.GBP);

        Assertions.assertEquals(BigDecimal.valueOf(120), actualAmount.amount());
        Assertions.assertEquals(Currency.GBP, actualAmount.currency());
    }
}