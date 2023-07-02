
/*
Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
*/


package HW_2;

import java.io.*;

public class hw_2_4 {
    public static void main(String[] args) throws Exception, NoTextProvided {
        System.out.println(enterText());
    }

    public static String enterText() throws Exception, NoTextProvided {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.print("Enter any text here: ");

        String string = bufferedReader.readLine();
        if (string.equals("")) {
            throw new NoTextProvided();
        }
        return string;
    }

    public static class NoTextProvided extends Throwable {
        public NoTextProvided() {
            super("No text provided! You should enter anything...");
        }
    }
}


