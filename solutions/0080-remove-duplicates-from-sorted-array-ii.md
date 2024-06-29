# 80. Remove Duplicates from Sorted Array II

## M1. Normal method

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int counter = 0;
        int prev = -10001;
        int equalCounter = 0;
        for (int num : nums) {
            if (num > prev) {
                equalCounter = 0;
                nums[counter++] = num;
                prev = num;
            }
            else if (num == prev) {
                equalCounter++;
                if (equalCounter < 2) {
                    nums[counter++] = num;
                }
            }
        }
        return counter;
    }
}
```

## M2. Two pointers (Compare with int @ counter-2)

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int counter = 1;
        for (int i = 1; i < nums.length; i++) {
            if (counter == 1 || nums[i] != nums[counter - 2]) {
                nums[counter++] = nums[i];
            }
        }
        return counter;
    }
}
```