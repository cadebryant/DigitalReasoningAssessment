import structures.TokenizedCorpus;
import tokenization.Tokenizer;
import java.util.ArrayList;

/**
 * Created by cadebryant on 7/29/16.
 */
public class Main {
    public static void main(String[] args) {
        String inputFile = null;
        String outputFile = null;
        String namedEntityFile = null;

        if (args != null && args.length > 0) {
            switch (args.length) {
                case 1:
                    inputFile = args[0];
                    break;
                case 2:
                    inputFile = args[0];
                    outputFile = args[1];
                    break;
                case 3:
                    inputFile = args[0];
                    outputFile = args[1];
                    namedEntityFile = args[2];
                    break;
                default:
                    break;
            }
        } else {
            inputFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/in/nlp_data.txt";
            outputFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/out/nlp_output_with_ner.xml";
            namedEntityFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/in/NER.txt";
        }
        //This is from our first iteration:
        //new TokenizedCorpus(inputFile).asXml(outputFile);
        //This is from our second iteration:
        new TokenizedCorpus(inputFile, true, namedEntityFile).asXml(outputFile);
    }
}
