package spell;

import java.io.IOException;
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
        editSetOne.clear();
        editSetTwo.clear();
        inputWord.toLowerCase();
        if (trie.find(inputWord.toLowerCase()) != null) {
            return inputWord.toLowerCase();
        }
        deletionDistance(inputWord);
        transpositionDistance(inputWord);
        alterationDistance(inputWord);
        insertionDistance(inputWord);

        int maxFrequency = 0;
        String finalWord = null;

        if (editSetOne.size() > 0) {
            for (String s : editSetOne) {
                INode editNode = trie.find(s);
                if (editNode.getValue() > maxFrequency) {
                    maxFrequency = editNode.getValue();
                    finalWord = s;
                }
                if (editNode.getValue() == maxFrequency) {
                    if (s.compareTo(finalWord) < 0) {
                        finalWord = s;
                    }
                }
            }
            return finalWord;
        }
        else {
            editSetOne.clear();
            Set<String> copyEditSet = new HashSet<String>(editSetTwo);
            for (String newWord : copyEditSet) {
                doubleDistance(newWord);
            }
        }
        if (editSetOne.size() > 0) {
            Object[] myArr = editSetOne.toArray();
            finalWord = myArr[0].toString();
        }

        return finalWord;
    }

    public void deletionDistance(String inputString) {
        for (int i = 0; i < inputString.length(); ++i) {
            StringBuilder word = new StringBuilder(inputString);
            word.deleteCharAt(i);
            if (trie.find(word.toString()) != null) {
                editSetOne.add(word.toString());
            }
            editSetTwo.add(word.toString());
        }
    }

    public void transpositionDistance(String inputString) {
        for (int i = 0; i < inputString.length() - 1; ++i) {
            StringBuilder word = new StringBuilder(inputString);
            char char1 = word.charAt(i);
            char char2 = word.charAt(i + 1);
            word.setCharAt(i, char2);
            word.setCharAt(i + 1, char1);
            if (trie.find(word.toString()) != null) {
                editSetOne.add(word.toString());
            }
            editSetTwo.add(word.toString());
        }
    }

    public void alterationDistance(String inputString) {
        for (int i = 0; i < inputString.length(); ++i) {
            for (char j = 'a'; j < 'z'; ++j) {
                StringBuilder word = new StringBuilder(inputString);
                word.setCharAt(i, j);
                if (trie.find(word.toString()) != null) {
                    editSetOne.add(word.toString());
                }
                editSetTwo.add(word.toString());
            }
        }
        for (char c = 'a'; c < 'z'; ++c) {
            StringBuilder word = new StringBuilder(inputString);
            word.append(c);
            if (trie.find(word.toString()) != null) {
                editSetOne.add(word.toString());
            }
            editSetTwo.add(word.toString());
        }
    }

    public void insertionDistance(String inputString) {
        for (int i = 0; i < inputString.length(); ++i) {
            for (char j = 'a'; j < 'z'; ++j) {
                StringBuilder word = new StringBuilder(inputString);
                word.insert(i, j);
                if (trie.find(word.toString()) != null) {
                    editSetOne.add(word.toString());
                }
                editSetTwo.add(word.toString());
            }
        }
    }

    public void doubleDistance(String inputWord) {
        deletionDistance(inputWord);
        transpositionDistance(inputWord);
        alterationDistance(inputWord);
        insertionDistance(inputWord);
    }
}
