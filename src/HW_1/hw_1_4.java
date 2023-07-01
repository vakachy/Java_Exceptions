package HW_1;

import java.util.Arrays;

public class hw_1_4 {

    /*
    Реализуйте метод, принимающий в качестве аргументов два целочисленных
массива, и возвращающий новый массив, каждый элемент которого равен
частному элементов двух входящих массивов в той же ячейке. Если длины
массивов не равны, необходимо как-то оповестить пользователя.
Важно: При выполнении метода единственное исключение, которое
пользователь может увидеть - RuntimeException, т.е. ваше
     */

    public static void main(String[] args) throws Exception {
        int[] arrA = {0, 2, 5, 6, 8, 9};
//        int[] arrA = {0, 2, 5, 6, 8, 9, 0};
//        int[] arrA = null;
        int[] arrB = {2, -5, 6, 10, 8, 12};

        System.out.println(Arrays.toString(arraysDivision(arrA, arrB)));
    }

    public static float[] arraysDivision(int[] a, int[] b) throws RuntimeException {

        if (a == null || b == null) throw new RuntimeException("One or both array(s) cannot be null!");

        float[] c = new float[a.length];

        if (a.length != b.length) throw new RuntimeException("Arrays are of unequal lengths!");

        for (int i = 0; i < a.length; i++) {
            if (b[i] == 0) {
                throw new RuntimeException("Division by zero!");
            } else {
                c[i] = (float) a[i] / b[i];
            }
        }
        return c;
    }
}
