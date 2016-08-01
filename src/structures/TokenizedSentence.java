package structures;

import ner.NamedEntityRecognizer;

import java.util.ArrayList;

import utils.Utils;

import javax.rmi.CORBA.Util;

import static utils.Utils.listToString;
import static utils.Utils.removePunctuation;

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
        this(sent, false, null);
    }

    /**
     * Constructor
     * @param sent An ArrayList of words which make up a sentence
     * @param findNamedEntities Indicates whether to account for named entities
     * @param namedEntityRecognizer Named entity recognizer instance
     */
    public TokenizedSentence(ArrayList<String> sent, boolean findNamedEntities, NamedEntityRecognizer namedEntityRecognizer) {
        words = new ArrayList<Object>();
        ArrayList<String> entities = null;
        int i = 0;
        int numWords = sent.size();
        for (; i < numWords; i++) {
            String w = sent.get(i);
            if (findNamedEntities) {
                if (namedEntityRecognizer.isNamedEntityPrefix(removePunctuation(w))) {
                    entities = namedEntityRecognizer.namedEntitiesByPrefix.get(removePunctuation(w));
                    //Now we have a list of ngrams, one of which may be contained in the current sentence
                    if (entities != null && entities.size() > 0) {
                        for (String ngram : entities) {
                            if (ngram.trim() != "") {
                                System.out.println(ngram);
                                if (removePunctuation(Utils.listToString(sent, " ")).contains(removePunctuation(ngram))) {
                                    words.add(new NamedEntity(ngram));
                                    i += ngram.split("\\s+").length - 1;
                                }
                            }
                        }
                    }
                } else {
                    words.add(new Word(w));
                }
            } else {
                words.add(new Word(w));
            }
        }
    }

    private NamedEntityRecognizer namedEntityRecognizer;

    private ArrayList<Object> words;
    public ArrayList<Object> getWords() {
        return words;
    }
    public void setWords(ArrayList<Object> wds) {
        words = wds;
    }

    private ArrayList<NamedEntity> namedEntities;
    public ArrayList<NamedEntity> getNamedEntities() {
        return namedEntities;
    }
    public void setNamedEntities(ArrayList<NamedEntity> entities) {
        namedEntities = entities;
    }
}
