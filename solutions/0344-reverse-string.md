# 344. Reverse String

```java
class Solution {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            s[left] = (char) (s[left] ^ s[right]);
            s[right] = (char) (s[right] ^ s[left]);
            s[left] = (char) (s[left] ^ s[right]);
            left = left + 1;
            right = right - 1;
        }
    }
}
```