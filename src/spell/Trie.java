package spell;

public class Trie implements ITrie {
    private Node root;
    private int wordCount;
    private int nodeCount;

    @Override
    public void add(String word) {

    }

    @Override
    public INode find(String word) {
        return null;
    }

    @Override
    public int getWordCount() {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
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
            // append the node's word to the output
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
        return equals_Helper(n1, n2);
    }

    @Override
    public int hashCode() {
        // rewrite this to make it return a better number
        return wordCount * nodeCount;
        // combine the following values in some way
            // wordCount
            // nodeCount
            // the index of each of the root node's non-null children
    }
}
