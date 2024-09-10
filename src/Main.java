import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;

public class Main extends JFrame {
    private JButton encodeButton;
    private JTextField textField1;
    private JPanel MainPanel;

    public Main() {
        setContentPane(MainPanel);
        setTitle("Huffman Encode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String variable = textField1.getText();
                String encoded = encode(variable);
                JOptionPane.showMessageDialog(Main.this, encoded);
            }
        });
    }

    public static void main(String[] args) {
        new Main();

    }

    public static String encode(String input) {
        // Step 1: Frequency count
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Build Priority Queue (Min-Heap)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Step 3: Build the Huffman Tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node newNode = new Node(left.freq + right.freq, left, right);
            pq.add(newNode);
        }

        // Root of the tree
        Node root = pq.poll();

        // Step 4: Generate Huffman Codes
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Step 5: Encode the input string
        StringBuilder encodedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedString.append(huffmanCodes.get(c));
        }

        return encodedString.toString();
    }

    // Helper method to generate Huffman codes
    private static void generateCodes(Node node, String code, HashMap<Character, String> huffmanCodes) {
        if (node == null) return;

        // If this is a leaf node, store the code
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.ch, code);
        }

        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }
}
