
/*
Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
и возвращает введенное значение.
Ввод текста вместо числа не должно приводить к падению приложения,
вместо этого, необходимо повторно запросить у пользователя ввод данных.
*/

package HW_2;

import java.io.*;

public class hw_2_1 {

    public static void main(String[] args) throws Exception {
        System.out.println(showNumber());
    }

    public static float showNumber() throws Exception {

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.print("Enter a number: ");

        String string = bufferedReader.readLine();
        float number = 0;

        while (true) {
            try {
                number = Float.parseFloat(string);
                return number;
            } catch (NumberFormatException e) {
                System.out.print("Enter valid number: ");
                string = bufferedReader.readLine();
            }
        }
    }
}

