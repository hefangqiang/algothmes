package main.math.secondpower;

/**
 * @description: 判断一个正整数，是否为2的整次幂
 * @author: Mr.He
 * @date: 2020-03-02 09:47
 **/
public class WhetherSecondPower {
    public static void main(String[] args) {
        int a = 8;
        System.out.println(a + "  是否是2的整数次幂：" + isSecondPower(a));

        int b = 16;
        System.out.println(b + "  是否是2的整数次幂：" + isSecondPower(b));

        int c = 1;
        System.out.println(c + "  是否是2的整数次幂：" + isSecondPower(c));

        int d = 0;
        System.out.println(d + "  是否是2的整数次幂：" + isSecondPower(d));

    }

    // 如果某个正整数是2的整次幂，那么 这个数&(这个数-1)=0，比如4 0100 4-1 0011   0100 & 0011 = 0
    private static boolean isSecondPower(int a){
        return (a & (a - 1)) == 0;
    }

}
