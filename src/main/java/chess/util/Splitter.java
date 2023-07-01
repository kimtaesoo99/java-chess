package chess.util;

public class Splitter {

    private static final String SEPARATOR = " ";

    public static String splitString(final String input, final int index) {
        String[] strings = input.split(SEPARATOR);
        return strings[index];
    }

    public static int splitStringLength(final String input, final int index) {
        String[] strings = input.split(SEPARATOR);
        return strings[index].length();
    }

    public static int splitSize(final String input) {
        String[] strings = input.split(SEPARATOR);
        return strings.length;
    }
}
