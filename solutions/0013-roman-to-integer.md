# 13. Roman to Integer

## M1. From front

```java
class Solution {
    public int romanToInt(String s) {
        String chars = "IVXLCDM";
        int[] nums = {1, 5, 10, 50, 100, 500, 1000};
        boolean isBefore = false;
        int ans = 0;
        for (int i = 0; i<s.length()-1; i++) {
            int curr = nums[chars.indexOf(s.charAt(i))];
            int next = nums[chars.indexOf(s.charAt(i+1))];
            ans += curr>=next ? curr: -curr;
        }
        ans += nums[chars.indexOf(s.charAt(s.length()-1))];
        return ans;
    }
}
```

## M2. From back

```java
class Solution {
    public int romanToInt(String s) {
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            num = switch (s.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> num;
            };
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }
}
```