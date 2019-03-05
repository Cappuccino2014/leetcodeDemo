package com.example.demo.main;

/**
 * @Auther: wilbur
 * @Date: 2019/1/31 17:56
 * @Description:
 */
public class Reverse {
    public int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            x = Math.abs(x);
            flag = false;
        }
        StringBuilder reverse = new StringBuilder(x);
        try {
            int a = Integer.parseInt(reverse.reverse().toString());
            if (flag) {
                return a;
            }else {
                return -a;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        int reverse1 = reverse.reverse(153423);
        System.out.println(reverse1);
    }
}
