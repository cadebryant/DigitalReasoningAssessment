package structures;

import java.io.Serializable;

/**
 * Created by cadebryant on 7/31/16.
 */
public class Word {
    /**
     * Default parameterless constructor
     */
    public Word() {

    }

    /**
     * Constructor
     * @param text
     */
    public Word(String text) {
        word = text;
    }

    private String word;
    public String getWord() {
        return word;
    }
    public void setWord(String wd) {
        word = wd;
    }
}
