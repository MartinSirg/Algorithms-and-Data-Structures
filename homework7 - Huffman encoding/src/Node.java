public class Node {

    private Node left;
    private Node right;
    private Node parent;
    private byte byteValue;
    private int frequency;
    private boolean hasByteValue;

    public Node(int frequency) {
        this.frequency = frequency;
    }

    public Node(byte byteValue, int frequency) {
        this.byteValue = byteValue;
        this.hasByteValue = true;
        this.frequency = frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public void setByteValue(byte byteValue) {
        hasByteValue = true;
        this.byteValue = byteValue;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String goToParent() {
        if(parent == null) return "";
        String codePart = parent.left.equals(this) ? "0" : "1";
        return parent.goToParent() + codePart;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Value: ").append(new String(new byte[]{byteValue})).append("\n");
        if(left != null) sb.append("Left: ").append(new String(new byte[]{left.byteValue})).append("\n");
        if(right != null) sb.append("Right: ").append(new String(new byte[]{right.byteValue})).append("\n");
        return sb.toString();
    }

    public boolean hasByteValue(){
        return hasByteValue;
    }
}
