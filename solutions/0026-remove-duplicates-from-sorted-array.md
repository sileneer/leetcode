# 26. Remove Duplicates from Sorted Array

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int prev = -101;
        int counter = 0;
        for (int num : nums) {
            if (num > prev) {
                nums[counter++] = num;
                prev = num;
            }
        }
        return counter;
    }
}
```