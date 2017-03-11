package fii.practic.utils;

/**
 * Created by xR86 on 11-Mar-17.
 */
public class OutputHelpers {
    /* Lambda approach to this util function */
    interface StringOp {
        String operation(String content, int n);
    }

    public String operateBinary(String content, int n, StringOp op) {
        return op.operation(content, n);
    }

    /**
     * Classical approach to this util function
     * @param content - the string to be sliced
     * @param n - the number of characters to be displayed (will display the biggest possible from two)
     * @return sliced list
     */
    public String getUpToNCharacters(String content, int n){
        return content.substring(0, Math.min(content.length(), n)) + "...";
    }

    public static void main(String[] args) {
//        StringOp upToNCharacters = (content, n) -> {
//            return content.toString().substring(0, Math.min(content.length(), n));
//        };
    }
}
