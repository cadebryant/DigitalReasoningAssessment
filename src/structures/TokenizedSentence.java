package structures;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer;
import tokenization.Tokenizer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cadebryant on 7/31/16.
 */
public class TokenizedSentence {
    /**
     * Default parameterless constructor
     */
    public TokenizedSentence() {

    }

    /**
     * Constructor
     * @param sent An ArrayList of words which make up a sentence
     */
    public TokenizedSentence(ArrayList<String> sent) {
        words = new ArrayList<Word>();
        for (String w : sent) {
            words.add(new Word(w));
        }
    }

    private ArrayList<Word> words;
    public ArrayList<Word> getWords() {
        return words;
    }
    public void setWords(ArrayList<Word> wds) {
        words = wds;
    }
}
