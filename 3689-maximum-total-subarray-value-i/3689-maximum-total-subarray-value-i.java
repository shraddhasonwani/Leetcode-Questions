class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long min = nums[0];
        long max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return (max - min) * k;
    }
}