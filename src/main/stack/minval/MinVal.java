package main.stack.minval;

import java.util.Stack;

/**
 * @description: 实现入栈、出栈、获取栈中的最小值。要求时间复杂度为O(1)
 *    思路：使用辅助栈来存放最小值的历史记录。
 *    在元素入栈时，如果比辅助栈的栈顶元素小，则将该元素放到主栈的同时也存入辅助栈。此时辅助栈顶的元素为主栈的最小值
 *    在元素出栈时，如果和辅助栈的栈顶元素一样大，则将该元素从辅助栈中出栈（也就是删除）。此时辅助栈顶元素的元素依旧是当前主栈中的最小值
 * @author: Mr.He
 * @date: 2020-03-01 11:34
 **/
public class MinVal {
    // 主栈
    private static Stack<Integer> stack = new Stack<>();
    // 辅助栈，用来存放最小值的历史记录
    private static Stack<Integer> minStack = new Stack<>();

    public static void main(String[] args) {
        MinVal.push(4);
        MinVal.push(7);
        MinVal.push(5);
        MinVal.push(3);
        MinVal.push(1);
        MinVal.push(8);

        System.out.println("栈中最小值：" + MinVal.getMinValue());
        System.out.println("出栈：" + MinVal.pop());
        System.out.println("出栈：" + MinVal.pop());
        System.out.println("栈中最小值：" + MinVal.getMinValue());
    }

    private static void push(Integer element){
        stack.push(element);
        if(minStack.empty() || element <= minStack.peek()){
            minStack.push(element);
        }
    }

    private static Integer pop(){
        if(stack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        return stack.pop();
    }

    private static Integer getMinValue(){
        if (minStack.empty()) {
            throw new RuntimeException("stack is empty！！");
        }
        return minStack.peek();
    }

}
