package spell;

public class Node implements INode {
    private int count;
    private INode[] children;

    @Override
    public int getValue() { return count; };

    @Override
    public void incrementValue() {};

    @Override
    public INode[] getChildren() { return children; }

}
