import java.util.HashSet;
import java.util.LinkedList;

public class Q7 {
    public static class Q7BinaryTree extends BinaryTree {
        public static class Result {
            public boolean isBalanced;
            public int height;

            public Result(boolean isBalanced, int height) {
                this.isBalanced = isBalanced;
                this.height = height;
            }
        }

        public Result isBalancedRecursive(BinaryTree.Node node, int depth) {
            if (node == null) {
                return new Result(true, -1);
            }

            Result leftSubtreeResult = isBalancedRecursive(node.left, depth + 1);
            Result rightSubtreeResult = isBalancedRecursive(node.right, depth + 1);

            boolean isBalanced = Math.abs(leftSubtreeResult.height - rightSubtreeResult.height) <= 1;
            boolean subtreesAreBalanced = leftSubtreeResult.isBalanced && rightSubtreeResult.isBalanced;
            int height = Math.max(leftSubtreeResult.height, rightSubtreeResult.height) + 1;

            return new Result(isBalanced && subtreesAreBalanced, height);
        }

        public boolean isBalanced() {
            return isBalancedRecursive(root, -1).isBalanced;
        }
    }


    public static void main(String[] args) {
        Q7BinaryTree tree = new Q7BinaryTree();

        Integer[] values = {1, 2, 3, 4, 5, 6, null, 7};
        int newNodeKey = 8;
        tree.initialize(values);
        System.out.println(tree.isBalanced());
        int[] num = {0};
        tree.traverse(x -> {
            if(x.left == null) {
                x.left = new BinaryTree.Node(newNodeKey);
                if(tree.isBalanced()) {
                    num[0]++;
                }
                x.left = null;
            }
            if(x.right == null) {
                x.right = new BinaryTree.Node(newNodeKey);
                if(tree.isBalanced()) {
                    num[0]++;
                }
                x.right = null;
            }
        }, null, null);

        System.out.println(num[0]);
    }
}
