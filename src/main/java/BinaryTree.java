import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree {
    public static class Node {
        int key;
        public Node left, right, parent;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of Binary Tree
    protected Node root;

    public void initialize(Integer[] values) {
        root = insertLevelOrder(values, 0, null);
    }

    public int sumPath(int n1, int n2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
            System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
            return -1;
        }

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {

            if (!path1.get(i).equals(path2.get(i)))
                break;
        }

        int sum = 0;
        for (int k = i; k < path1.size(); ++k) {
            sum += path1.get(k);
        }
        for (int k = i; k < path2.size(); ++k) {
            sum += path2.get(k);
        }
        return sum + path1.get(i - 1);
    }

    public boolean findPath(Node root, int n, List<Integer> path) {
        if (root == null) {
            return false;
        }

        path.add(root.key);

        if (root.key == n) {
            return true;
        }

        if (findPath(root.left, n, path)) {
            return true;
        }

        if (findPath(root.right, n, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    // Function to insert nodes in level order
    public Node insertLevelOrder(Integer[] arr, int i, Node parent) {
        Node root = null;
        // Base case for recursion
        if (i < arr.length) {
            Integer value = arr[i];
            if (value != null) {
                root = new Node(arr[i]);
                root.parent = parent;

                // insert left child
                root.left = insertLevelOrder(arr, 2 * i + 1, root);
                // insert right child
                root.right = insertLevelOrder(arr, 2 * i + 2, root);
            }
        }
        return root;
    }

    public void traverse(Consumer<Node> preOrderVisitor,
                         Consumer<Node> inOrderVisitor,
                         Consumer<Node> postOrderVisitor) {
        traverse(root, preOrderVisitor, inOrderVisitor, postOrderVisitor);
    }

    public void traverse(Node node,
                         Consumer<Node> preOrderVisitor,
                         Consumer<Node> inOrderVisitor,
                         Consumer<Node> postOrderVisitor) {
        if (node == null)
            return;
        if (preOrderVisitor != null) {
            preOrderVisitor.accept(node);
        }
        traverse(node.left, preOrderVisitor, inOrderVisitor, postOrderVisitor);
        if (inOrderVisitor != null) {
            inOrderVisitor.accept(node);
        }
        traverse(node.right, preOrderVisitor, inOrderVisitor, postOrderVisitor);
        if (postOrderVisitor != null) {
            postOrderVisitor.accept(node);
        }
    }
}

