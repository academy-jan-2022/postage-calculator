import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        Dimension dimension = new Dimension(weight, height, width, depth);
        BigDecimal amount = getAmountByTier(dimension);

        return new Money(amount, Currency.GBP);
    }

    private BigDecimal getAmountByTier(Dimension dimension) {
        if (isTierTwoPrice(dimension)) {
            return BigDecimal.valueOf(dimension.weight).multiply(TIMES_4);
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

    private boolean isTierTwoPrice(Dimension dimension) {
        return dimension.weight > 60 || (!(dimension.height > 324) && dimension.height > 229) || (dimension.width > 162 && dimension.width <= 229) || dimension.depth > 50;
    }


    private record Dimension(int weight, int height, int width, int depth) {
    }
}

