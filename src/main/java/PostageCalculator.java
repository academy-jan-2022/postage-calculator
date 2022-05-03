import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    private static final BigDecimal TIMES_6 = BigDecimal.valueOf(6);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        ParcelDimension parcelDimension = new ParcelDimension(new Weight(weight), new Height(height), new Width(width), new Depth(depth));
        Tier tier = parcelDimension.getTier();
        BigDecimal amount = getAmountBy(tier, parcelDimension);

        return new Money(amount, Currency.GBP);
    }

    private BigDecimal getAmountBy(Tier tier, ParcelDimension parcelDimension) {
        if (Tier.TWO == tier) {
            return BigDecimal.valueOf(parcelDimension.getWeight()).multiply(TIMES_4);
        }

        if(Tier.THREE == tier){
            return BigDecimal.valueOf(parcelDimension.getWeight()).multiply(TIMES_6);
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

}

