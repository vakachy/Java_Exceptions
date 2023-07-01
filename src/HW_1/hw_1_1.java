package HW_1;

public class hw_1_1 {

    public static String getItem(String[] arr, int index) throws Exception {
        if (arr == null) throw new Exception("Null array!");
        if (index >= arr.length) throw new Exception("Index out of range!");
        return arr[index];
    }

    public static Float divide(Float a, Float b) throws Exception {

        if (a == null || b == null) throw new Exception("Neither a nor b could be null");
        if (b == 0) throw new Exception("Can't divide by zero");
        return (Float) a / b;
    }

    public static int add(Object a, Object b) throws ClassCastException, NullPointerException {
        return (int) a + (int) b;
    }


    public static void main(String[] args) throws Exception {
/*
        Для метода getItem
*/
//        String[] arr = {"a", "b", "c"};
//        String[] arr = null;
//        System.out.println(getItem(arr, 5));

/*
        Для метода divide
*/
//        System.out.println(divide(null,null));
//        System.out.println(divide(1.56f,0f));
//        System.out.println(divide(1.56f, 0.1f));

/*
        Для метода add
*/

//        Object a = null;
//        Object a = "ntl";
//        Object a = 36.6f;
        Object a = 90;
        Object b = 3;
        System.out.println(add(a, b));
    }
}