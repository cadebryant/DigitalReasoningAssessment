package tokenization;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cadebryant on 8/1/16.
 * Class for performing tokenization on a
 * ZIP archive containing multiple corpi
 * and aggregating the results.
 */
public class AggregateTokenizer {
    private static String zipFilePath;

    /**
     * Constructor
     * @param zipPath Path to ZIP archive
     */
    public AggregateTokenizer(String zipPath) {
        zipFilePath = zipPath;
    }

    /**
     * Tokenizes all documents in a ZIP archive of documents
     * @return
     */
    public ArrayList<ArrayList<ArrayList<String>>> tokenizeCollection() {
        ExecutorService exec;
        final ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<ArrayList<ArrayList<String>>>();
        final ZipFile zipFile;
        try {
            zipFile = new ZipFile(zipFilePath);
            final Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
            int numThreads = zipFile.size();
            exec = Executors.newFixedThreadPool(numThreads);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (zipEntries != null && zipEntries.hasMoreElements()) {
                        ZipEntry zipEntry = zipEntries.nextElement();
                        if (!zipEntry.isDirectory() && !zipEntry.getName().startsWith("_")) {
                            try {
                                InputStream zipStream = zipFile.getInputStream(zipEntry);
                                BufferedReader rdr = new BufferedReader(new InputStreamReader(zipStream));
                                result.add(new Tokenizer(rdr).tokenize(true));
                                zipStream.close();
                                rdr.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            while (zipEntries.hasMoreElements()) {
                exec.execute(runnable);
            }
            exec.shutdown();
            //zipFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
