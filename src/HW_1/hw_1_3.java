package HW_1;

import java.util.Arrays;

public class hw_1_3 {

    /*
    Реализуйте метод, принимающий в качестве аргументов два целочисленных
массива, и возвращающий новый массив, каждый элемент которого равен
разности элементов двух входящих массивов в той же ячейке.
Если длины массивов не равны, необходимо как-то оповестить
пользователя.
     */

    public static void main(String[] args) throws Exception {
        int[] arrA = {0, 2, 5, 6, 8, 9};
        int[] arrB = {2, -5, 6, 0, 8, 12, 7};

        System.out.println(Arrays.toString(arraysSubtraction(arrA, arrB)));
    }

    public static int[] arraysSubtraction(int[] a, int[] b) throws Exception {

        if (a == null || b == null) throw new Exception("One or both array(s) cannot be null!");

        int[] c = new int[a.length];

        if (a.length != b.length) throw new Exception("Arrays are of unequal lengths!");

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }
}
