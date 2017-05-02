package com.example.user.calculator.utils.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {


    // Regexp of correct number.
    private static final String NUMB_EXP = "\\-?\\d+(\\.{1}\\d+)?";


    // Regexp of correct operation.
    private static final Pattern OPERATION_PAT = Pattern.compile(NUMB_EXP + "[" + Operator.REGEX_ALL + "]{1}" + NUMB_EXP);


    // Regexp of correct operations in (..).
    private static final Pattern BRACKETS_PAT = Pattern.compile("\\({1}((" + NUMB_EXP + ")|((" + NUMB_EXP + "[" + Operator.REGEX_ALL + "]{1})+" + NUMB_EXP + ")){1}\\){1}");

    //
    public String calculate(String expression)  {
        expression = expression.replaceAll("\\s", "");
        return brackets(expression);
    }

    //
    private String brackets(String expression) {
        Matcher matcher = BRACKETS_PAT.matcher(expression);
        if (matcher.find()) {
            String innerExp = matcher.group(0).substring(1, matcher.group(0).length() - 1);
            expression = brackets(expression.substring(0, matcher.start()) + operators(innerExp) + expression.substring(matcher.end()));
        }
        return operators(expression);
    }

    //
    private String operators(String expression) {

        // Check operations.
        if (OPERATION_PAT.matcher(expression).find()) {

            // Replace -- with +.
            expression = expression.replaceAll("\\-\\-", "+");
            for (Operator operator : Operator.values()) {
                Matcher matcher = Pattern.compile(NUMB_EXP + operator.regEx() + NUMB_EXP).matcher(expression);

                // Check operators.
                if (matcher.find()) {
                    String operation = matcher.group(0);

                    // Trim minus if need.
                    if (expression.indexOf(operation) != 0 && operation.charAt(0) == '-') {
                        operation = operation.substring(1);
                    }

                    // Find operator index.
                    int operatorPosition = operation.lastIndexOf(operator.toString());

                    // Left operand.
                    String leftNumb = operation.substring(0, operatorPosition);

                    // Right operand.
                    String rightNumb = operation.substring(operatorPosition + operator.toString().length());

                    // Operation is applies to right and left operands.
                    Double result = operator.apply(Double.parseDouble(leftNumb), Double.parseDouble(rightNumb));

                    // New expression.
                    expression = operators(expression.substring(0, expression.indexOf(operation)) + result + expression.substring(matcher.end()));
                }
            }
        } else if (!Pattern.compile(NUMB_EXP).matcher(expression).matches()) {

            // Unknown expression.
            expression = "";
        }
        return expression;
    }
}
