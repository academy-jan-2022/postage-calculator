public enum Tier {
    ONE(1),
    TWO(2);

    private final int priority;

    Tier(int priority) {
        this.priority = priority;
    }

    public Tier comparePriority(Tier otherTier) {
        if (this.priority > otherTier.priority) {
            return this;
        }
        return otherTier;
    }
}
