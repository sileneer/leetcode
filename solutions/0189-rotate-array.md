# 189. Rotate Array

## M1. Use additional array

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] ans = new int[nums.length];
        int j = 0;
        for (int i = nums.length - k; i < nums.length; i++) {
            ans[j++] = nums[i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            ans[j++] = nums[i];
        }
        for (int i = 0; i< ans.length; i++) {
            nums[i] = ans[i];
        }
    }
}
```

## M2: Reverse array

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```