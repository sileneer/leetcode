import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    static Scanner in = new Scanner(System.in);
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        int n = in.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = in.nextInt();
            nums[i][1] = in.nextInt();
        }
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.merge(nums)));
    }

    public int[][] merge(int[][] intervals) {

        final int MAX_SIZE = 10001;

        if (intervals.length == 1) {
            return intervals;
        }

        int[] prefixSum = new int[MAX_SIZE];
        int lastIndex = -1; // record the last index that appears in the input
        Set<Integer> points = new HashSet<>(); // record the intervals that represent a point only, e.g. [5,5]

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            lastIndex = Math.max(lastIndex, Math.max(start, end));

            prefixSum[start]++;
            prefixSum[end]--;

            if (start == end) {
                points.add(start);
            } // if the interval is a point only, record it in the points temporarily
        } // the start and end info is written into the prefixSum

        System.out.println("prefixSum: " + Arrays.toString(prefixSum));
        System.out.println("points: " + points);

        int[][] result = new int[lastIndex + 1][2];
        int counter = 0; // record how many intervals in the answer

        for (int i = 1; i <= lastIndex; i++) {
            prefixSum[i] += prefixSum[i - 1];
        } // now prefixSum is the number of times a number appears across all the intervals
        System.out.println("prefixSum: " + Arrays.toString(prefixSum));

        for (int i = 0; i <= lastIndex; i++) {
            int start = i;
            while (prefixSum[i] >= 1) {
                points.remove(i);
                i++;
            }
            if (start != i) {
                points.remove(i);
                result[counter] = new int[]{start, i};
                counter++;
            }
        } // points is finalised. the points array contains the independent point representing an interval only

        for (int point : points) {
            result[counter] = new int[]{point, point};
            counter++;
        } // add the independent point to the answer

        // return the answer without the empty int[]
        return Arrays.copyOf(result, counter);
    }
}