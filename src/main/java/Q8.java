public class Q8 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] values = {2, 4, 3, 7, 1, 6, 5};
        tree.initialize(values);
        System.out.println(tree.sumPath(3, 4));
        System.out.println(tree.sumPath(6, 7));
        System.out.println(tree.sumPath(2, 5));
        System.out.println(tree.sumPath(1, 7));
    }
}
