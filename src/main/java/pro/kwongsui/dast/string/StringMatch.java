package pro.kwongsui.dast.string;

public class StringMatch {

    public int bf(String s, String t) {
        if (t.length() == 0) {
            return 0;
        }
        int m = s.length(), n = t.length();
        for (int idx = 0; idx <= m - n; idx++) {
            int i = idx;
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                } else {
                    break;
                }
            }
            if (i == idx + n) {
                return idx;
            }
        }
        return -1;
    }

    public int rk(String s, String t) {
        if (t.length() == 0) {
            return 0;
        }
        int m = s.length(), n = t.length();
        if(m < n) {
            return -1;
        }
        long[] table = new long[n];
        table[0] = 1L;
        for (int i = 1; i < n; i++) {
            table[i] = 26L * table[i - 1];
        }
        long h = 0L;
        for (int i = 0; i < n; i++) {
            h += (t.charAt(i) - 'a') * table[n - i - 1];
        }
        long[] hash = new long[m - n + 1];
        for (int i = 0; i <= m - n; i++) {
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    hash[i] += (s.charAt(j) - 'a') * table[n - j - 1];
                }
            } else {
                hash[i] =
                    (hash[i - 1] - table[n - 1] * (s.charAt(i - 1) - 'a')) * 26 + (
                        s.charAt(i + n - 1)
                            - 'a');
            }
            if (hash[i] == h) {
                return i;
            }
        }
        return -1;
    }
}
