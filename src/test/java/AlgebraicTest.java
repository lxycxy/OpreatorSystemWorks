import org.edu.discrete.AlgebraicSystem;

/**
 * Created by Lxy on 2023/9/23 9:19
 */
public class AlgebraicTest {

    public static void main(String[] args) {

        System.out.println("循环测试 100 组随机数据");
        for (int i = 1; i < 100; i ++) {
            int x = (int) (Math.random() * 100000000);
            int y = (int) (Math.random() * 100000000);

            System.out.println("\n<===== 第"  + i + "组测试数据 =====>");

            System.out.println(
                    "测试数据 x : "
                    + x
                    + " y : "
                    + y
            );

            System.out.println(
                    "一般运算结果：o1 : "
                    + (x + y - x * y)
                    + " o2 : "
                    + Math.abs(x - y)
            );

            System.out.println(
                    "代数系统运算结果：o1 : "
                    + AlgebraicSystem.algebraicOperationOne(x, y)
                    + " o2 : "
                    + AlgebraicSystem.algebraicOperationTwo(x, y)
            );

        }

    }
}
