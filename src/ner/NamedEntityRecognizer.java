package ner;

import utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by cadebryant on 7/31/16.
 */
public class NamedEntityRecognizer {
    /**
     * Default parameterless constructor
     */
    public NamedEntityRecognizer() {

    }

    /**
     * Constructor
     * @param nameFilePath The file containing the list of named entities
     */
    public NamedEntityRecognizer(String nameFilePath) {
        FileReader fil = null;
        BufferedReader buf = null;
        String line = null;
        namedEntitiesByPrefix = new Hashtable<String, ArrayList<String>>();
        try {
            fil = new FileReader(nameFilePath);
            buf = new BufferedReader(fil);
            while ((line = buf.readLine()) != null) {
                if (line.trim().length() > 0) {
                    String prefix = line.split("\\s+")[0];
                    if (!namedEntitiesByPrefix.containsKey(prefix)) {
                        ArrayList<String> entities = new ArrayList<String>();
                        entities.add(line);
                        namedEntitiesByPrefix.put(prefix, entities);
                    } else {
                        namedEntitiesByPrefix.get(prefix).add(line);
                    }
                }
            }
            buf.close();
            fil.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the given word is the prefix to a named entity
     * @param word
     * @return
     */
    public boolean isNamedEntityPrefix(String word) {
        ArrayList<String> entity = namedEntitiesByPrefix.get(word);
        if (entity != null) {
            return Utils.removePunctuation(Utils.listToString(entity, " ")).contains(word);
        }
        return false;
    }

    public Hashtable<String, ArrayList<String>> namedEntitiesByPrefix;
}
