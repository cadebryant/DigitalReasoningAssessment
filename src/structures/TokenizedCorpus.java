package structures;

import tokenization.Tokenizer;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static com.sun.xml.internal.bind.v2.WellKnownNamespace.JAXB;

/**
 * Created by cadebryant on 7/31/16.
 */
public class TokenizedCorpus {

    /**
     * Default parameterless constructor
     */
    public TokenizedCorpus() {

    }

    /**
     * Constructor
     * @param filePath
     */
    public TokenizedCorpus(String filePath) {
        Tokenizer tokenizer = new Tokenizer(filePath);
        ArrayList<ArrayList<String>> tokenized = tokenizer.tokenize();
        sentences = new ArrayList<TokenizedSentence>();
        for (ArrayList<String> s : tokenized) {
            sentences.add(new TokenizedSentence(s));
        }
    }

    private ArrayList<TokenizedSentence> sentences;
    public ArrayList<TokenizedSentence> getSentences() {
        return sentences;
    }
    public void setSentences(ArrayList<TokenizedSentence> sents) {
        sentences = sents;
    }

    public void asXml(String filePath) {
        XMLEncoder xmlEncode = null;
        try {
            xmlEncode = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (xmlEncode != null) {
            xmlEncode.writeObject(this);
            xmlEncode.close();
        }
    }
}
