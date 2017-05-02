package com.example.user.calculator.utils.calculator;

public enum Operator implements Comparable<Operator> {

    // Div.
    DIVIDE("/") {
        Double apply(final Double x, final Double y) {
            return x / y;
        }
        String regEx() {
            return "\\" + toString();
        }
    },
    // Multiplication.
    MULTIPLY("*") {
        Double apply(final Double x, final Double y) {
            return x * y;
        }
        String regEx() {
            return "\\" + toString();
        }
    },
    // Sub.
    MINUS("-") {
        Double apply(final Double x, final Double y) {
            return x - y;
        }
        String regEx() {
            return "\\" + toString();
        }
    },
    // Plus.
    PLUS("+") {
        Double apply(final Double x, final Double y) {
            return x + y;
        }
        String regEx() {
            return "\\" + toString();
        }
    };

    // Regexp of all operators.
    public static final String REGEX_ALL;

    // Building regexp.
    static {
        StringBuilder builder = new StringBuilder();
        for (Operator operator : Operator.values()) {
            builder.append(operator.regEx());
        }
        REGEX_ALL = builder.toString();
    }

    // Operations.
    private final String symbols;

    //
    Operator(final String symbols) {
        this.symbols = symbols;
    }

    //
    @Override
    public String toString() {
        return symbols;
    }

    // Must implemented for all operations.
    abstract String regEx();
    abstract Double apply(final Double x, final Double y);
}