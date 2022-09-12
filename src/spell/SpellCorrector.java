package spell;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class SpellCorrector implements ISpellCorrector {
    public Trie trie = new Trie();
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        File file = new File(dictionaryFileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.next();
            System.out.println(line);
            trie.add(line);
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {






        return null;
    }
    // clear all the sets and make sure they are empty
    // make sets for edit distance one and run it through the functions
    // make a set for edit distance two and run through the functions again on a copy of edit distance one set
    // we want return the string that we see the most within the set
    // find its index and return the string version

    // add the functionality here
    // this is where most of the algorithms and methods will go to solve the word corrector
    // write multiple functions here
        // deletion distance
        // transposition distance
        //  alteration distance
        // insertion distance
    // for edit distance two,


}
