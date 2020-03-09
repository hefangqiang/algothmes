package main.math.permutationnextnum;

import java.util.Arrays;

/**
 * @description: 全排列的下一个数(也就是全排列组合中大于这个数的最小数)
 * @author: Mr.He
 * @date: 2020-03-05 16:46
 **/
public class Permutation {

    public static void main(String[] args) {
        int num = 12453;
        System.out.println(getPermutationNextMinNum(num));
    }
    /*思路： 1.从后往前找到逆序区域的前一个数beforeNum
            2.把逆序区域大于beforeNum的最小的那个数和beforeNum交换
            3.将交换后的逆序区域正序排序*/
    private static int getPermutationNextMinNum(int num){

        // 全排列数转为int数组， 12453 -> {1,2,3,4,5}
        String ns = String.valueOf(num);
        int len = ns.length();
        int[] numArray = new int[len];
        for (int i = 0; i < len; i++) {
            numArray[i] = Integer.parseInt(String.valueOf(ns.charAt(i)));
        }
        // 1.从后往前找到逆序区域的前一个数beforeNum，12453 -> 4  索引为2
        int beforeNumIndex = 0;  //逆序区域的前一个数
        int reverseIndex = 0; //逆序区域的左临界值
        boolean reverseFlag = true;
        for (int i = len - 1; i > 0; i--) {
            if (reverseFlag && numArray[i] > numArray[i-1]) {
                beforeNumIndex = i - 1;
                reverseIndex = i;
                reverseFlag = false;
            }
        }
        if (reverseFlag) { // 整个数都是逆序的，那么这个数已经是最大值了
            return num;
        }

        // 2.把逆序区域大于beforeNum的最小的那个数和beforeNum交换，12453 -> 将5和4进行交换
        // 寻找逆序区域大于beforeNum的最小数索引
        int greaterThanBeforeNumIndex = reverseIndex; // 逆序区域的左临界值一定大于beforeNum
        for (int i = reverseIndex + 1; i < len; i++) {
            if (numArray[i] > numArray[beforeNumIndex] && numArray[i] < numArray[greaterThanBeforeNumIndex]) {
                greaterThanBeforeNumIndex = i;
            }
        }
        // 将beforeNum和greaterThanBeforeNum进行交换
        int tmp = numArray[beforeNumIndex];
        numArray[beforeNumIndex] = numArray[greaterThanBeforeNumIndex];
        numArray[greaterThanBeforeNumIndex] = tmp;

        // 3.将交换后的逆序区域正序排序
        sort(numArray, reverseIndex, len - 1);

        // 将数组转为字符串，然后转为int
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(numArray[i]);
        }

        return Integer.parseInt(sb.toString());

    }

    private static void sort(int[] arrays, int start, int end){
        if (start >= end) {
            return;
        }
        //基准数
        int pivot = arrays[start];
        int left = start;
        int right = end;

        while (left != right) {
            while (left < right && arrays[right] > pivot) {
                right--;
            }
            while(left < right && arrays[left] <= pivot){
                left++;
            }
            if (left < right) {
                int tmp = arrays[left];
                arrays[left] = arrays[right];
                arrays[right] = tmp;
            }
        }
        arrays[start] = arrays[left];
        arrays[left] = pivot;
        sort(arrays, start, left -1);
        sort(arrays, left + 1, end);
    }

}
