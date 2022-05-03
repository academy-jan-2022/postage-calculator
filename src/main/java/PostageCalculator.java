import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        BigDecimal amount;

        if (isTierTwoPrice(weight, height)) {
            amount = BigDecimal.valueOf(weight).multiply(TIMES_4);
        } else {
            amount = BigDecimal.valueOf(TIER_ONE_PRICE);
        }

        return new Money(amount, Currency.GBP);
    }

    private boolean isTierTwoPrice(int weight, int height) {
        return weight > 60 || height < 324 && height > 229;
    }
}
