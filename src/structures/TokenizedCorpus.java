package structures;

import ner.NamedEntityRecognizer;
import tokenization.Tokenizer;
import utils.Utils;

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
     * @param filePath The corpus file
     * @param findNamedEntities Indicates whether to account for named entities in corpus
     * @param namedEntitiesFile File containing a list of named entities
     */
    public TokenizedCorpus(String filePath, boolean findNamedEntities, String namedEntitiesFile) {
        if (findNamedEntities) {
            namedEntityRecognizer = new NamedEntityRecognizer(namedEntitiesFile);
        }
        Tokenizer tokenizer = new Tokenizer(filePath);
        ArrayList<ArrayList<String>> tokenized = tokenizer.tokenize();
        sentences = new ArrayList<TokenizedSentence>();
        for (ArrayList<String> s : tokenized) {
            sentences.add(new TokenizedSentence(s, findNamedEntities, namedEntityRecognizer));
        }
    }

    /**
     * Constructor for use when input text has already been tokenized
     * @param tokenizedSentences Tokenized sentences
     * @param namedEntitiesFile File containing named entities
     */
    public TokenizedCorpus(ArrayList<ArrayList<String>> tokenizedSentences, String namedEntitiesFile) {
        namedEntityRecognizer = new NamedEntityRecognizer(namedEntitiesFile);
        sentences = new ArrayList<TokenizedSentence>();
        for (ArrayList<String> s : tokenizedSentences) {
            sentences.add(new TokenizedSentence(s, true, namedEntityRecognizer));
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

    private NamedEntityRecognizer namedEntityRecognizer;
}
