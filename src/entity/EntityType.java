package entity;

public enum EntityType {
    PREDATOR("\uD83E\uDD8A", 0.05, 0.0),
    HERBIVORE("\uD83D\uDC30", 0.1, 0.4),
    GRASS("\uD83C\uDF3F", 0.2, 0.5),
    ROCK("\uD83D\uDDFB", 0.1, 0.0),
    TREE("\uD83C\uDF33", 0.15, 0.0),
    EMPTINESS("\uD83D\uDFEB", null, 0.0);

    private final String value;
    private final Double coefficient;
    private final Double coefficientCrises;

    EntityType(String value, Double coefficient, Double coefficientCrises) {
        this.value = value;
        this.coefficient = coefficient;
        this.coefficientCrises = coefficientCrises;
    }

    public String getValue() {
        return value;
    }

    public Double getCoefficient () {
        return coefficient;
    }

    public Double getCoefficientCrises() {
        return coefficientCrises;
    }
}
