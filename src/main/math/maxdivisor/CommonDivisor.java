package main.math.maxdivisor;

/**
 * @description: 两个数的最大公约数
 * @author: Mr.He
 * @date: 2020-03-01 14:13
 **/
public class CommonDivisor {
    public static void main(String[] args) {
        int a = 45;
        int b = 35;
        int commonDivisor = getCommonDivisor(a,b);
        System.out.println("最大公约数为：" + commonDivisor);

        int commonDivisor2 = getCommonDivisorByEuclid(a, b);
        System.out.println("最大公约数为(欧几里得算法)：" + commonDivisor2);

        int commonDivisor3 = getCommonDivisorByMorePhaseReduction(a, b);
        System.out.println("最大公约数为(更相减损术)：" + commonDivisor3);

        int commonDivisor4= gcd(a, b);
        System.out.println("最大公约数为(最优解)：" + commonDivisor4);
    }

    /* 最原始的方法:暴力枚举法
       T(n) = O(min(a,b))
     */
    private static int getCommonDivisor(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        // 大数除以小数余数为0，则小数为两个数的最大公约数
        if ((big % small) == 0) {
            return small;
        }
        // 使用循环从 小数/2 一直循环到 1,找到在此之间的最大公约数
        for (int i = small/2; i >= 1; i--) {
            if ((big % i == 0) && (small % i == 0)) {
                return i;
            }
        }
        return 1;
    }

     /* 辗转相除法(欧几里得算法)：两个正整数a和b(a>b)，它们的最大公约数等于a除以b的余数c和较小数b之间的最大公约数
                             也就是直到两个数可以整除，或者一个数减小到1时，这个数就是两者最大公约数
        缺点：取模运算不如移位快
        T(n) = O(log(max(a,b)))
        */
    private static int getCommonDivisorByEuclid(int a, int b){
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        // 直到两个数可以整除，或者一个数减小到1时，这个数就是两者的最大公约数
        if((big % small) == 0) {
            return small;
        }
        return getCommonDivisorByEuclid(big % small, small);
    }

    /* 更相减损术(九章算术)：两个正整数a和b(a>b)，它们的最大公约数等于a-b的差值c和较小数b之间的最大公约数
                         也就是直到两个数相等为止，就是两者的最大公约数
        缺点：不稳定。两个数之间相差非常大时，需要递归很多次才能得到最终结果
        T(n) = O(max(a,b))
        */
    private static int getCommonDivisorByMorePhaseReduction(int a, int b) {
        // 相等时，为两者的最大公约数
        if (a == b) {
            return a;
        }
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        return getCommonDivisorByMorePhaseReduction(big - small, small);
    }

    /* 最优解：结合辗转相除法和更相减损术，在更相减损术基础上使用移位运算
             两个正整数a和b(a>b), 如果a和b都为偶数，则gcd(a,b) = 2 * gcd(a/2,b/2)
                                 如果a为偶数，b为奇数，则gcd(a,b) = gcb(a/2,b)
                                 如果a为奇数，b为偶数，则gcb(a,b) = gcb(a,b/2)
                                 如果a和b都为奇数，则使用更相减损术,a-b的差一定为偶数gcb(a,b) = gcb(a-b,b)
                                 直到两个数相等时，此时为两个数的最大公约数
       T(n) = O(log(max(a,b)))
     */
    private static int gcd(int a, int b) {
        if (a == b) { // 直到就两数相等时，为最大公约数
            return a;
        }

        if (((a & 1) == 0) && ((b & 1) == 0)) { // ab都为偶数
            return gcd(a >> 1, b >> 1) << 1;
        } else if (((a & 1) == 0) && ((b & 1) != 0)) { // a为偶数，b为奇数
            return gcd(a >> 1, b);
        } else if (((a & 1) != 0) && ((b & 1) == 0)) { // a为奇数，b为偶数
            return gcd(a, b >> 1);
        } else { // ab都为奇数
            int big = Math.max(a, b);
            int small = Math.min(a, b);
            return gcd(big - small, small);
        }
    }



}
