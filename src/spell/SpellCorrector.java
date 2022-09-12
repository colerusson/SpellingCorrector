package spell;

import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
    public Trie trie;
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        Scanner scan = new Scanner(dictionaryFileName);
        while (scan.hasNext()) {
            String line = scan.next();
            // Trie.add(line);
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        return null;
    }
    // add the functionality here
    // this is where most of the algorithms and methods will go to solve the word corrector







}
