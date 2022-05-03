import java.math.BigDecimal;

public class PostageCalculator {
    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        return new Money(BigDecimal.valueOf(120), Currency.GBP);
    }
}
