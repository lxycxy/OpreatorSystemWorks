package org.edu.discrete;

/**
 * Created by Lxy on 2023/9/23 8:51
 * <--->
 * 定义泛型代数系统类
 */
public class AlgebraicSystem {

    /* o1 运算 */
    public static Integer algebraicOperationOne(Integer x, Integer y) {
        return x + y - x * y;
    }

    /* o2运算 */
    public static Integer algebraicOperationTwo(Integer x, Integer y) {
        return Math.abs(x - y);
    }
}