package structures;

import ner.NamedEntityRecognizer;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by cadebryant on 8/1/16.
 */
public class TokenizedAggregateCollection {

    /**
     * Default parameterized contructor (needed by Serializer)
     */
    public TokenizedAggregateCollection() {

    }

    /**
     * Constructor
     * @param tokenizedText
     * @param namedEntitiesFile
     */
    public TokenizedAggregateCollection(ArrayList<ArrayList<ArrayList<String>>> tokenizedText, String namedEntitiesFile) {
        namedEntityRecognizer = new NamedEntityRecognizer(namedEntitiesFile);
        tokenizedCorpusCollection = new ArrayList<TokenizedCorpus>();
        for (ArrayList<ArrayList<String>> c : tokenizedText) {
            tokenizedCorpusCollection.add(new TokenizedCorpus(c, namedEntitiesFile));
        }
    }

    private ArrayList<TokenizedCorpus> tokenizedCorpusCollection;
    public ArrayList<TokenizedCorpus> getTokenizedCorpusCollection() {
        return tokenizedCorpusCollection;
    }
    public void setTokenizedCorpusCollection(ArrayList<TokenizedCorpus> collection) {
        tokenizedCorpusCollection = collection;
    }

    private NamedEntityRecognizer namedEntityRecognizer;

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
