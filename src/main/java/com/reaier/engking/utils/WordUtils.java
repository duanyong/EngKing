package com.reaier.engking.utils;

public class WordUtils {
        public static boolean isWord(String word) {
            int n = word.length();
            if (1 == n) return false;

            boolean hasHyphens = false;
            for (int i = 0; i < n; i++) {
                if (Character.isDigit(word.charAt(i))) {
                    return false;
                } else if (word.charAt(i) == '-') {
                    if (hasHyphens == true || i == 0 || i == n - 1 || !Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) {
                        return false;
                    }

                    hasHyphens = true;
                } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
                    if (i != n - 1) {
                        return false;
                    }
                }
            }
            return true;
        }
}
