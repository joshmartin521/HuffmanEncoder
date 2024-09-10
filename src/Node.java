class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    public Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = this.right = null;
    }

    public Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}
