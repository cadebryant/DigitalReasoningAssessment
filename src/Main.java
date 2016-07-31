import structures.TokenizedCorpus;
import tokenization.Tokenizer;
import java.util.ArrayList;

/**
 * Created by cadebryant on 7/29/16.
 */
public class Main {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        new TokenizedCorpus(inputFile).asXml(outputFile);
    }
}
