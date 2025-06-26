# 27. Remove Element

## M1. Simplest

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int ans = 0;

        for (int num : nums){
            if (num != val){
                nums[ans++] = num;
            }
        }
        return ans;
    }
}
```

## M2. Err...

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                j++;
                nums[i] = -1;
            }
        }
        int k = nums.length - j;

        int p1 = 0, p2 = k;
        while (p1 < k && p2 < nums.length) {
            while (p1 < k && nums[p1] != -1) {
                p1++;
            }
            while (p2 < nums.length && nums[p2] == -1) {
                p2++;
            }
            if (p1 < k && p2 < nums.length)
                nums[p1] = nums[p2];
            p1++;
            p2++;
        }
        return k;
    }
}
```