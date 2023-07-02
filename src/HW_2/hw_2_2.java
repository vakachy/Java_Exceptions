

/*
Если необходимо, исправьте данный код
(задание 2 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)
 */


package HW_2;

public class hw_2_2 {

    public static void main(String[] args) {
//        int[] intArray = null;
//        int[] intArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] intArray = {0, 1, 2, 3};
        try {
            int d = 0;
            double catchedRes1 = (double) intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }
}
