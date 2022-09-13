package spell;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.*;

public class SpellCorrector implements ISpellCorrector {
    public Trie trie = new Trie();
    Set<String> editSetOne = new HashSet<String>();
    Set<String> editSetTwo = new HashSet<String>();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        File file = new File(dictionaryFileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.next();
            trie.add(line);
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        // clear all the sets and make sure they are empty
        // make sets for edit distance one and run it through the functions
        // make a set for edit distance two and run through the functions again on a copy of edit distance one set
        // we want to return the string that we see the most within the set
        // find its index and return the string version

        // write multiple functions here
        // deletion distance
        // transposition distance
        // alteration distance
        // insertion distance
        // for edit distance two, run a copy of editSetOne through all of the functions again


        return null;
    }

    public void deletionDistance() {

    }

    public void transpositionDistance() {

    }

    public void alterationDistance() {

    }

    public void insertionDistance() {

    }

}
