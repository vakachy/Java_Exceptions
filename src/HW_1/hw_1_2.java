package HW_1;

/*Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?*/

public class hw_1_2 {
    public static void main(String[] args) throws Exception {
//        String[][] arr = null;
        String[][] arr = {{"12", "0", "20", "21", "2u2", "23"}};
        try {
            System.out.println(sum2d(arr));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e)
        {System.err.println(e);}
    }

    public static int sum2d(String[][] arr) throws Exception {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(arr[i][j]);
                sum += val;
            }
        }
        return sum;
    }
}
