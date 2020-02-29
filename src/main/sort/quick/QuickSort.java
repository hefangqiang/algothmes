package main.sort.quick;

import java.util.Arrays;

/**
 * @description: 快速排序
 * @author: Mr.He
 * @date: 2020-02-28 10:03
 **/
public class QuickSort {

    public static void main(String[] args) {

        int[] array = {4, 7, 6, 5, 3, 2, 8, 1,10,20,13,16,21};
        sort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));

    }

    private static void sort(int[] array, int startIndex, int endIndex) {
        // 递归方法退出的的边界条件
        if(startIndex >= endIndex){
            return;
        }
        // 经过一轮划分后，基数所在的新位置
        int pivotIndex = partition2(array, startIndex, endIndex);
        // 根据基准元素的位置，分成两部分进行递归排序
        sort(array, startIndex, pivotIndex - 1);
        sort(array, pivotIndex + 1, endIndex);
    }

    // 双边循环法
    private static int partition(int[] array, int startIndex, int endIndex) {
        // 选择基准数，可以选取数组的第一个数或者随机选择
        int pivot = array[startIndex];
        // 定义左右指针位置
        int left = startIndex;
        int right = endIndex;

        while (left != right){
            while (left < right && array[right] > pivot) {
                right--;
            }
            while(left < right && array[left] <= pivot){
                left++;
            }
            // 交换left和right指针所指向的元素
            if (left < right) {
                int tmp = array[right];
                array[right] = array[left];
                array[left] = tmp;
            }
        }
        // 基准数和指针重合点元素交换
        array[startIndex] = array[left];
        array[left] = pivot;
        // 返回新基准数的位置
        return left;
    }

    // 单边循环法
    private static int partition2(int[] array, int startIndex, int endIndex){
        int pivot = array[startIndex];
        // 定义mark指针，小于基准数的元素边界
        int mark = startIndex;
        // 遍历数组，如果当前元素比基准数小，则mark指针往右移动，交换当前元素和mark指针指向的元素
        // 遍历和交换完毕后，mark指针的左边的数都比基准数小，右边的数都比基准数大
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark++;
                int tmp = array[mark];
                array[mark] = array[i];
                array[i] = tmp;
            }
        }
        // 基准数和mark指向的元素进行交换
        array[startIndex] = array[mark];
        array[mark] = pivot;
        // 返回新基准数的位置
        return mark;
    }


}
