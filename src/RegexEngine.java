import java.util.Random;

public class RegexEngine {
    private static final Random RANDOM = new Random();
    private static final int MAX_LIMIT = 5;

    public String generate(String regex) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < regex.length()) {
            char current = regex.charAt(i);
            String atom = "";

            // 1. Resolve the "Atom"
            if (current == '(') {
                int closing = findClosingBracket(regex, i);
                String group = regex.substring(i + 1, closing);
                String[] options = group.split("\\|");
                atom = options[RANDOM.nextInt(options.length)];
                i = closing + 1;
            } else if (Character.isLetterOrDigit(current) || current == ' ') {
                atom = String.valueOf(current);
                i++;
            } else {
                i++;
                continue;
            }

            // 2. Handle Quantifiers with "Next Character" protection
            int count = 1;
            if (i < regex.length()) {
                char q = regex.charAt(i);
                if (q == '?') {
                    count = RANDOM.nextBoolean() ? 1 : 0;
                    i++;
                } else if (q == '*') {
                    count = RANDOM.nextInt(MAX_LIMIT + 1);
                    i++;
                } else if (q == '+') {
                    count = 1 + RANDOM.nextInt(MAX_LIMIT);
                    i++;
                } else if (q == '^') {
                    i++; // Skip '^'
                    // IMPORTANT: Only take the FIRST digit for the power
                    // if it's followed by another literal or quantifier
                    if (i < regex.length() && Character.isDigit(regex.charAt(i))) {
                        count = Character.getNumericValue(regex.charAt(i));
                        i++;
                    }
                }
            }

            for (int k = 0; k < count; k++) result.append(atom);
        }
        return result.toString();
    }

    private int findClosingBracket(String str, int start) {
        int count = 0;
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            if (str.charAt(i) == ')') count--;
            if (count == 0) return i;
        }
        return -1;
    }
}