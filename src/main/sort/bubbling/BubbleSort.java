package main.sort.bubbling;

import java.util.concurrent.atomic.AtomicInteger;

public class BubbleSort {
    // 排序轮数统计
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        /*int[] array = {5, 8, 6, 3, 9, 2, 1, 7};
        sort(array);
        for (int element : array) {
            System.out.println(element);
        }*/

       /* int[] array = {5, 8, 6, 3, 9, 2, 1, 7};
          sortOptimize1(array);
        for (int element : array) {
            System.out.println(element);
        }*/

        /*int[] array = {5, 1, 6, 3, 2, 7, 8, 9};
        sortOptimize2(array);
        for (int element : array) {
            System.out.println(element);
        }*/
        int[] array = {5, 1, 6, 3, 2, 7, 8, 9};
        sortFinal(array);
        for (int element : array) {
            System.out.println(element);
        }

    }
    // 原始冒泡法排序
    private static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    // 优化版1 -可能不需要length-1轮排序，数列就已经是有序的了
    /* 比如{2,1,6,4,3}，第一轮排序后为12436，第二轮排序后为12346，已经是有序的了，
       第三轮排序发现没有进行元素交换，就不必在进行最后一轮排序了*/
    private static void sortOptimize1(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true; // 有序标记,每一轮都是true，假设当前轮已经是有序的
            for (int j = 0; j < array.length - 1 -i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false; //发生交换后，说明数列当前还是无序的
                }
            }
            if (isSorted) { //当前轮没有发生元素交换
                break;
            }
        }
    }

    // 优化版2 - 数组中最后一些元素，可能已经是有序的
    /* 比如{3,1,2,7,8,9}，后面三个元素已经是有序的，排序时只需要对数组下标3之前的元素进行对比交换 */
    private static void sortOptimize2(int[] array){
        int sortBorder = array.length - 1; // 定义无序数列的边界，每次只需对比到这里
        int lastExchangeIndex = 0; // 定义最后交换的位置
        for (int i = 0; i < array.length -1; i++) {
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    lastExchangeIndex = j; // 更新最后一次交换的位置
                }
            }
            sortBorder = lastExchangeIndex; // 更新边界
        }
    }

    // 最终版本(优化版1+优化版2)
    private static void sortFinal(int[] array){
        int sortBorder = array.length - 1; // 定义无序数列的边界，每次只需对比到这里
        int lastExchangeIndex = 0; // 定义最后交换的位置
        for (int i = 0; i < array.length -1; i++) {
            boolean isSorted  = true; // 有序标记,每一轮都是true
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    lastExchangeIndex = j; // 更新最后一次交换的位置
                    isSorted = false; //发生交换后，说明数列当前还是无序的
                }
            }
            if (isSorted) {
                break;
            }
            sortBorder = lastExchangeIndex; // 更新边界
        }
    }






}
