package main.math.deleteknum;

import java.util.*;

/**
 * @description: 删去k个数的最小值
 *     例子：258312 删除1个数后的最小值为25312，删除两个数后的最的最小值为2512
 * @author: Mr.He
 * @date: 2020-03-06 13:55
 **/
public class DeleteKMinNum {

    public static void main(String[] args) {
        Integer[] num = {1, 2, 3, 6, 5, 4};
        for ( Integer n : getDeleteKNumAfterMin(num,1)){
            System.out.println(n);
        }
    }

   /* 思路：贪心算法，从局部最优解 最后得到全局最优解
           从数的左侧往右侧遍历，如果左侧的数大于右侧，则删除左侧的那个数，经过k次这样的删除操作，得到的就是最小值*/
    private static Integer[] getDeleteKNumAfterMin(Integer[] nums, int k){


        List<Integer> linkedList = new LinkedList<>(Arrays.asList(nums));
        for (int i = 0; i < k; i++) {
            boolean delFlag = false;
            for (int j = 0; j < linkedList.size() - 1; j++) {
                if (linkedList.get(j) > linkedList.get(j + 1)) {
                    linkedList.remove(j);
                    delFlag = true;
                    break;
                }
            }
            if (!delFlag) {
                linkedList.remove(linkedList.size() - 1);
            }
        }

        Integer[] result = new Integer[nums.length - k];
        return linkedList.toArray(result);
    }
}
