class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        int best = 0;

        int n = s.length();
        int[] zeroBlocks = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                if (i == 0 || s.charAt(i - 1) == '1') {
                    zeroBlocks[count++] = 1;
                } else {
                    zeroBlocks[count - 1]++;
                }
            }
        }

        for (int i = 0; i + 1 < count; i++) {
            best = Math.max(best, zeroBlocks[i] + zeroBlocks[i + 1]);
        }

        return ones + best;
    }
}