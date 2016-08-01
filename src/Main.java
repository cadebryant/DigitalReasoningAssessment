import structures.TokenizedAggregateCollection;
import structures.TokenizedCorpus;
import tokenization.AggregateTokenizer;
import tokenization.Tokenizer;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by cadebryant on 7/29/16.
 */
public class Main {
    public static void main(String[] args) {
        String inputFile = null;
        String outputFile = null;
        String namedEntityFile = null;

        //Pass -z as the first argument if we are doing aggregate tokenization from a zip file:
        if (args != null && args.length > 0 && args[0] == "-z") {
            switch (args.length) {
                case 2:
                    inputFile = args[1];
                    break;
                case 3:
                    inputFile = args[1];
                    outputFile = args[2];
                    break;
                case 4:
                    inputFile = args[1];
                    outputFile = args[2];
                    namedEntityFile = args[3];
                    break;
                default:
                    break;
            }
        } else {
            inputFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/in/nlp_data.zip";
            outputFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/out/nlp_output_with_ner_aggregate_zip.xml";
            namedEntityFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/in/NER.txt";
        }

//        if (args != null && args.length > 0) {
//            switch (args.length) {
//                case 1:
//                    inputFile = args[0];
//                    break;
//                case 2:
//                    inputFile = args[0];
//                    outputFile = args[1];
//                    break;
//                case 3:
//                    inputFile = args[0];
//                    outputFile = args[1];
//                    namedEntityFile = args[2];
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            inputFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/in/nlp_data.txt";
//            outputFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/out/nlp_output_with_ner.xml";
//            namedEntityFile = "/home/cadebryant/Dropbox/Development/Java/DigitalReasoningAssessment/data/in/NER.txt";
//        }
        //This is from our first iteration:
        //new TokenizedCorpus(inputFile).asXml(outputFile);

        //This is from our second iteration:
        //new TokenizedCorpus(inputFile, true, namedEntityFile).asXml(outputFile);

        //This is from our third iteration:
        ArrayList<ArrayList<ArrayList<String>>> tokenizedCollection = new AggregateTokenizer(inputFile).tokenizeCollection();
        new TokenizedAggregateCollection(tokenizedCollection, namedEntityFile).asXml(outputFile);
    }
}
