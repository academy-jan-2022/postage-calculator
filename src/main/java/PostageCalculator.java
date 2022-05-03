import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        BigDecimal amount;

        if (weight > 60) {
            amount = BigDecimal.valueOf(weight).multiply(TIMES_4);
        } else {
            amount = BigDecimal.valueOf(120);
        }

        return new Money(amount, Currency.GBP);
    }
}
