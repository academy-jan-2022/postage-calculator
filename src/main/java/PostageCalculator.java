import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        BigDecimal amount = getAmountByTier(weight, height);

        return new Money(amount, Currency.GBP);
    }

    private BigDecimal getAmountByTier(int weight, int height) {
        if (isTierTwoPrice(weight, height)) {
            return BigDecimal.valueOf(weight).multiply(TIMES_4);
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

    private boolean isTierTwoPrice(int weight, int height) {
        return weight > 60 || height < 324 && height > 229;
    }
}
