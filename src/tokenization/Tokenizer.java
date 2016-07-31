package tokenization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Class for tokenizing text.
 */
public class Tokenizer {

    private static String filePath;
    private static String wordRegex = "\\s+";
    private static Pattern wordPattern;

    /**
     * Constructor
     * @param filePath The file containing the text to tokenize.
     */
    public Tokenizer(String filePath) {
        this.filePath = filePath;
        wordPattern = Pattern.compile(wordRegex);
    }

    public ArrayList<ArrayList<String>> tokenize() {
        FileReader fil = null;
        BufferedReader buf = null;
        String line = null;
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        StringBuilder sb = new StringBuilder();
        try {
            fil = new FileReader(filePath);
            buf = new BufferedReader(fil);
            while ((line = buf.readLine()) != null) {
                sb.append(line);
            }
            buf.close();
            fil.close();
            result = tokenizeSentences(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Basic word tokenizer
     * @param str String to tokenize
     * @return String[] of words
     */
    public static ArrayList<String> tokenizeWords(String str) {
        return new ArrayList<String>(Arrays.asList(wordPattern.split(str)));
    }

    /**
     * Splits a text corpus into its component sentences
     * using "?", "!", or EXACTLY ONE "." as delimiter.
     * @param text The text to split into sentences
     * @return ArrayList<String> of sentences.
     */
    public ArrayList<ArrayList<String>> tokenizeSentences(String text) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        int len = text.length();
        int i = 0; //Char counter
        int j = 0; //Placemarker - set after each sentence boundary
        int breakPoint = 0;
        boolean periodCheck = false;
        boolean otherDelimCheck = false;
        for (; i < len; i++) {
            //Special handling for periods:
            //make sure we're only splitting on single period, not ellipsis:
            //also account for sentences ending in quoted text:
            char currCh = text.charAt(i);
            boolean isPer = (currCh == '.');
            boolean isOther = (currCh == '?' || currCh == '!');
            boolean onLastChar = (i == len - 1);
            boolean hasQuote = (!onLastChar && ((text.charAt(i + 1) == '"') || (text.charAt(i + 1) == '\'')));
            breakPoint = i + (onLastChar? 1 : 2);
            periodCheck = (isPer &&
                ((onLastChar ||
                ((i < len - 1 && text.charAt(i + 1) != '.') &&
                (i > 0 && text.charAt(i - 1) != '.')) ||
                (text.charAt(i + 1) == '"'))));
            //Look for other delimiters, in this case only '?' or '!':
            otherDelimCheck = isOther &&
                ((onLastChar ||
                (text.charAt(i + 1) == '"')));
            if (periodCheck || otherDelimCheck) {
                String sentence = text.substring(j, breakPoint);
                result.add(tokenizeWords(sentence));
                j = breakPoint;
            }
        }
        return result;
    }
}
