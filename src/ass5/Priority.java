package ass5;

public enum Priority {

    LOW(2),
    NORMAL(1),
    HIGH(0);

    private int weight;

    Priority(int weight) {
        this.weight = weight;
    }

    int weight() {
        return weight;
    }

}
