package main.sort.count;

import java.util.Arrays;

/**
 * @description: 计数排序
 * @author: Mr.He
 * @date: 2020-03-02 10:23
 **/
public class CountSort {

    public static void main(String[] args) {
        int[] array = {3, 6, 2, 7, 8, 4, 1, 9, 2};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        // 获得数组中元素的最大值
        int max = 0;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        // 设置计数数组长度，可以存储最大值
        int[] countArray = new int[max + 1];
        // 将原数组的元素的值，对应到计数数组的下标，下标对应的值为元素个数
        for (int i = 0; i < array.length ; i++) {
            countArray[array[i]] = countArray[array[i]] + 1;
        }
        // 遍历计数数组，进行排序
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[index++] = i;
            }
        }
    }
}
