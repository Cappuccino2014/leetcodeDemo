package com.example.demo.main;

import java.util.HashMap;

/**
 * @Auther: wilbur
 * @Date: 2019/1/28 11:28
 * @Description:
 */
public class TwoSum {
    /**
     * 方法一：暴力法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法二：两遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num =target - nums[i];
            if (map.containsKey(num)&&map.get(num)!=i) {
                return new int[] { i, map.get(num)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法三：一遍哈希表
     * 事实证明，我们可以一次完成。在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     * 复杂度分析：
     *
     * 时间复杂度：O(n)， 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 O(1) 的时间。
     *
     * 空间复杂度：O(n)， 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n 个元素。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num =target - nums[i];
            if (map.containsKey(num)){
                return new int[] { i, map.get(num)};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum twoSum = new TwoSum();
        long past = System.currentTimeMillis();
        int[] ints = twoSum.twoSum(nums, target);
        System.out.println(System.currentTimeMillis()-past);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }
}
