# 9. Palindrome Number

## M1. Use string

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        char[] newStr = new char[str.length()];

        int i = str.length() - 1;
        int j = 0;
        while (i >= 0) {
            newStr[j] = str.charAt(i);
            i--;
            j++;
        }

        int newNum;
        try {
            newNum = Integer.parseInt(new String(newStr));
        } catch (NumberFormatException e) {
            return false;
        }
        return x == newNum;
    }
}
```

## M2. Not use string

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        long reversed = 0;
        long temp = x;

        while (temp != 0) {
            int digit = (int) (temp % 10);
            reversed = reversed * 10 + digit;
            temp /= 10;
        }

        return (reversed == x);
    }
}
```