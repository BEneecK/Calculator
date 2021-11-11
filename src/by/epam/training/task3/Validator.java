package by.epam.training.task3;

public class Validator {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char DIVIDE = '/';
    private static final char MULTIPLY = '*';
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    public static void stringValidator(char firstSymbol, char secondSymbol) {
        switch (firstSymbol) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE:
                switch (secondSymbol) {
                    case PLUS:
                    case MINUS:
                    case MULTIPLY:
                    case DIVIDE:
                        throw new RuntimeException("Unexpected operator: " + secondSymbol);
                }
            case LEFT_BRACKET:
            case RIGHT_BRACKET:
                break;
            default: {

                if(!Character.isDigit(firstSymbol)) {
                    throw new RuntimeException("Unexpected character: " + firstSymbol);
                }
            }
        }
    }

    public static void stringValidator(char lastSymbol) {
        switch (lastSymbol) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE:
            case LEFT_BRACKET:
            case RIGHT_BRACKET:
                break;
            default: {
                if(!Character.isDigit(lastSymbol)) {
                    throw new RuntimeException("Unexpected character: " + lastSymbol);
                }
            }
        }
    }

    public static void bracketsValidator(String s) {
        char openBracket = LEFT_BRACKET;
        char closeBracket = RIGHT_BRACKET;

        int countOfOpenBrackets = 0;
        int countOfCloseBrackets = 0;

        int indexOfFirstSymbol = 0;
        int indexOfLastSymbol = s.length() - 1;

        if(s.charAt(indexOfFirstSymbol) == closeBracket) {
            throw new RuntimeException("Unexpected character: " + closeBracket);
        }
        if(s.charAt(indexOfLastSymbol) == openBracket) {
            throw new RuntimeException("Unexpected character: " + openBracket);
        }

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == openBracket) {
                countOfOpenBrackets++;
            }
            if(s.charAt(i) == closeBracket) {
                countOfCloseBrackets++;
            }
        }

        if(countOfOpenBrackets != countOfCloseBrackets) {
            throw new RuntimeException("Different amount of brackets");
        }
    }
}
