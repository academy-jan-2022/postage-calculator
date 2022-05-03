import java.math.BigDecimal;

public class PostageCalculator {
    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        if(weight > 60){
            return new Money(BigDecimal.valueOf(1000), Currency.GBP);
        }
        return new Money(BigDecimal.valueOf(120), Currency.GBP);
    }
}
