public class Main {
    public static void main(String[] args) {
        RegexEngine engine = new RegexEngine();

        // Variant 2 Regular Expressions from the image
        String[] variant2Regexes = {
                "M?N^2(O|P)^3Q*R+",
                "(X|Y|Z)^38+(9|0)",
                "(H|i)(J|K)L*N?"
        };

        System.out.println("==============================================");
        System.out.println("   FORMAL LANGUAGES LAB: REGEX GENERATOR      ");
        System.out.println("   VARIANT 2                                  ");
        System.out.println("==============================================\n");

        for (int i = 0; i < variant2Regexes.length; i++) {
            String regex = variant2Regexes[i];
            System.out.println("Testing Regex [" + (i + 1) + "]: " + regex);
            System.out.print("Output: { ");

            for (int j = 0; j < 3; j++) {
                String generated = engine.generate(regex);
                System.out.print("\"" + generated + "\"" + (j < 2 ? ", " : ""));
            }
            System.out.println(" }\n");
        }

        System.out.println("==============================================");
    }
}