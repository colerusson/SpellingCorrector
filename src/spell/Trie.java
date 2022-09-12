package spell;

public class Trie implements ITrie {
    private Node root = new Node();
    private int wordCount = 0; // counts how many unique words there are total
    private int nodeCount = 1; // counts how many nodes there are total

    @Override
    public void add(String word) {
        int index = 0;
        INode current = root;
        for (int i = 0; i < word.length(); ++i) {
            index = word.charAt(i) - 'a';
            if (current.getChildren()[index] == null) {
                current.getChildren()[index] = new Node();
                // can increment node count
                ++nodeCount;
            }
            current = current.getChildren()[index];
        }
        // if the current node is 0 then increment word count
        current.incrementValue();
        if (current.getValue() == 1) {
            ++wordCount;
        }
    }

    @Override
    public INode find(String word) {
        int index = 0;
        INode current = root;
        for (int i = 0; i < word.length(); ++i) {
            index = word.charAt(i) - 'a';
            if (current.getChildren()[index] == null) {
                return null;
            }
            current = current.getChildren()[index];
        }
        if (current.getValue() >= 1) {
            return current;
        }
        else {
            return null;
        }
    }

    @Override
    public int getWordCount() {
        return this.wordCount;
    }

    @Override
    public int getNodeCount() {
        return this.nodeCount;
    }

    @Override
    public String toString() {
        StringBuilder currWord = new StringBuilder();
        StringBuilder output = new StringBuilder();
        toString_Helper(root, currWord, output);
        return output.toString();
    }

    private void toString_Helper(INode n, StringBuilder currWord, StringBuilder output) {
        if (n.getValue() > 0) {
            output.append(currWord.toString());
            output.append("\n");
        }
        for (int i = 0; i < n.getChildren().length; ++i) {
            INode child = n.getChildren()[i];
            if (child != null) {
                char childLetter = (char)('a' + i);
                currWord.append(childLetter);
                toString_Helper(child, currWord, output);
                currWord.deleteCharAt(currWord.length() - 1);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Trie trieObj = (Trie)o;
        if (trieObj.getNodeCount() != this.getNodeCount() || trieObj.getWordCount() != this.getWordCount()) {
            return false;
        }
        return equals_Helper(this.root, trieObj.root);
    }

    private boolean equals_Helper(Node n1, Node n2) {
        // compare n1 and n2 to see if they are the same
            // do they have the same count
            // do they have non-null children in exactly the same indexes
        // recurse on the children and compare the children subtrees
        if (n1 == null && n2 != null) {
            return false;
        }
        else if (n1 != null && n2 == null) {
            return false;
        }
        else if (n1 != null && n2 != null) {
            if (n1.getValue() != n2.getValue()) {
                return false;
            }
        }
        else {
            return true;
        }
        for (int i = 0; i < n1.getChildren().length; ++i) {
            Node child1 = (Node)n1.getChildren()[i];
            Node child2 = (Node)n2.getChildren()[i];
            if (equals_Helper(child1, child2) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int index = 0;
        Node n = this.root;
        if (this != null) {
            for (int i = 0; i < n.getChildren().length; ++i) {
                Node child = (Node) n.getChildren()[i];
                if (child != null) {
                    index += i;
                }
            }
        }
        return wordCount * nodeCount * index;
    }


}
