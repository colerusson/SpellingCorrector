package spell;

public class Node implements INode {
    private int count = 0; // how many of each word there are
    private INode[] children = new INode[26];

    @Override
    public int getValue() { return count; };

    @Override
    public void incrementValue() { ++count; };

    @Override
    public INode[] getChildren() { return children; }
}
