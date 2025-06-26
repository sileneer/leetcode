# 41. First Missing Positive

## M1. Boolean array

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        boolean[] seen = new boolean[len + 1];
        for (int i : nums) {
            if (i <= len && i > 0) {
                seen[i] = true;
            }
        }
        for (int i = 1; i <= len; i++) {
            if (!seen[i]) {
                return i;
            }
        }
        return len + 1;
    }
}
```

## M2. Index as hash key

From my perspective, this method is just to combine the information stored in 2 arrays into the original array. The +/- sign provides the additional information of whether `index` has appreared in the original array, so that no additional space is required and the algorithm is in-place. 

This key to this method is to reduce the extra space required but to use the elements in the original array themselves to store the necessary information.

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean contains1 = false;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                contains1 = true;
            }
            if (nums[i] > len || nums[i] < 1) {
                nums[i] = 1;
            }
        }
        if (!contains1) {
            return 1;
        }

        for (int i = 0; i < len; i++) {
            int value = Math.abs(nums[i]);
            if (value == len) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[value] = -Math.abs(nums[value]);
            }
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) {
            return len;
        }

        return len + 1;
    }
}
```