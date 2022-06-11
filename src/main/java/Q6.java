import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q6 {
    private static void makeCombinations(int[] array, int pos, List<int[]> memo) {
        if (pos >= array.length - 1) {
            memo.add(array.clone());
            return;
        }

        for (int i = pos; i < array.length; i++) {

            int t = array[pos];
            array[pos] = array[i];
            array[i] = t;

            makeCombinations(array, pos + 1, memo);

            t = array[pos];
            array[pos] = array[i];
            array[i] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println("ok");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7};
        Integer[] treeKeys = {1, 1, 1, 1, 1, 1, 1};

        LinkedList<int[]> memo = new LinkedList<>();
        makeCombinations(numbers, 0, memo);
        int minSum = 0;

        for (int[] ints : memo) {
            BinaryTree tree = new BinaryTree();
            int j = 0;
            Integer[] values = treeKeys.clone();
            for (int i = values.length - 1; i >= 0; i--) {
                if (values[i] != null) {
                    values[i] = ints[j];
                    j++;
                }
            }
            tree.initialize(values);

            ArrayList<Integer> preOrderSequence = new ArrayList<>();
            ArrayList<Integer> inOrderSequence = new ArrayList<>();
            tree.traverse(x -> preOrderSequence.add(x.key), null, null);
            tree.traverse(null, x -> inOrderSequence.add(x.key), null);

            int sum = 0;
            for (int i = preOrderSequence.size() - 1; i >= 0; i--) {
                int a = preOrderSequence.get(i);
                int b = inOrderSequence.get(i);
                sum += Math.abs(a - b);
            }

            if(minSum == 0) {
                minSum = sum;
            }

            minSum = Math.min(sum, minSum);
        }

        System.out.println(minSum);
    }
}
