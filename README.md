# DigitalReasoningAssessment

This project implements the requirements of the programming assessment for Digital Reasoning, Inc.

# Challenges Faced

One of the early challenges was with sentence boundary recognition.  My initial instinct was to use regular expressions, since this is usually a straightforward way to do this......i.e., look for terminal punctuation to indicate sentence boundaries.

However, this proved problematic, as the data contains ellipses (which are not boundaries).  In this case, a regex would need to do look-ahead processing in order to determine that a period is not followed by another period.  My efforts to do this utilizing regexes proved unfruitful (even though the regex language supports lookahead and lookbehind).

Thus, I ended up writing my own method to identify sentence boundaries (looking at sentences on a character-by-character basis and incorporating my own look-ahead logic).  A potential problem with this is that it may not perform on a very large dataset as well as a specialized/compiled regex library would.
 
Finding named entities was relatively easy - although I needed to accommodate the fact that NE's are sometimes n-grams rather than just single words.

# Results

The application performs as expected according to my understanding of the assessment objectives.  

By executing the main() method with the -z switch, the path to the ZIP file, the path to the named entity file, and the path to the XML output file, it returns a list of named entities at the console and saves the object representation of the collection of documents as an XML file (with corpuses, sentences, words, and named entitites tagged accordingly in the XML).