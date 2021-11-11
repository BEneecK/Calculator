package by.epam.training.task3;

import java.util.LinkedList;

public class Calculate implements Calculation{

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char DIVIDE = '/';
    private static final char MULTIPLY = '*';
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    @Override
    public int eval(String expression) {
        LinkedList<Integer> st = new LinkedList<>();
        LinkedList<Character> op = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            Validator.bracketsValidator(expression);
            if(i < expression.length() - 1) {
                char nextC = expression.charAt(i + 1);
                Validator.stringValidator(c, nextC);
            }
            else {
                Validator.stringValidator(c);
            }

            if (isSpace(c)) {
                continue;
            }
            if (c == LEFT_BRACKET) {
                op.add(LEFT_BRACKET);
            }
            else if (c == RIGHT_BRACKET) {
                while (op.getLast() != LEFT_BRACKET)
                    processOperator(st, op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < expression.length() && Character.isDigit(expression.charAt(i)))
                    operand += expression.charAt(i++);
                --i;
                st.add(Integer.parseInt(operand));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return st.get(0);
    }

    private static boolean isSpace(char c) {
        return c == ' ';
    }

    private static boolean isOperator(char c) {
        return c == PLUS || c == MINUS || c == MULTIPLY || c == DIVIDE;
    }

    private static int priority(char op) {
        switch (op) {
            case PLUS:
            case MINUS:
                return 1;
            case MULTIPLY:
            case DIVIDE:
                return 2;
            default:
                return -1;
        }
    }

    private static void processOperator(LinkedList<Integer> st, char op) {
        int firstNumber = st.removeLast();
        int secondNumber = st.removeLast();
        switch (op) {
            case PLUS:
                st.add(secondNumber + firstNumber);
                break;
            case MINUS:
                st.add(secondNumber - firstNumber);
                break;
            case MULTIPLY:
                st.add(secondNumber * firstNumber);
                break;
            case DIVIDE:
                st.add(secondNumber / firstNumber);
                break;
        }
    }
}
