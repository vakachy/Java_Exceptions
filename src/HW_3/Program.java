package HW_3;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) throws Exception {

        ArrayList<String> register = new ArrayList<>();

        String FILE_NAME = "register.txt";
        String person = "";
        String surname = "";
        String name = "";
        String middlename = "";
        String dateOfBirth = "";
        String tel = "";
        String gender = "";

        // прочитать реестр записей
        readRegister(register, FILE_NAME);

        // тест-строка
        //  String userInput = "Сидоров Иван Михайлович 14.10.2003 5555555 m";

        String userInput = userInput(); // ввод данных пользователем
        ArrayList<String> data = processInput(userInput); // разложить введенные данные в список

        // проверка введенных данных по количеству полей
        if (isValidInput(data)) {
            int index = 0;
            surname = data.get(index++);
            name = data.get(index++);
            middlename = data.get(index++);
            dateOfBirth = data.get(index++);
            tel = data.get(index++);
            gender = data.get(index);
        }

        // проверка введенных данных (дата_рождения, номер_телефона, пол) на корректность
        boolean isCorrectUserInput = false;
        if (isValidInput(data) && isValidDateOfBirth(dateOfBirth) && isValidTelephone(tel) && isValidGender(gender)) {
            isCorrectUserInput = true;
            person = String.join(" ", surname, name, middlename, dateOfBirth, tel, gender);
        }

        // проверка существования идентичных записей (person) в реестре
        boolean duplicated = false;
        if (isCorrectUserInput) {
            for (String itm : register
            ) {
                if (itm.equalsIgnoreCase(person)) {
                    duplicated = true;
                    break;
                }
            }
        }

        // если введенные данные корректны и в реестре нет идентичных записей (person), то
        // производится запись (person) в реестр
        if (isCorrectUserInput && !duplicated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))
            ) {
                writer.write(person + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Duplicated register entry or incorrect user input");
        }


        // произвести новое считывание реестра, если данные реестра были изменены
        if (!duplicated) {
            register.clear();
            readRegister(register, FILE_NAME);
        }

        //проверить реестр на однофамильцев
        boolean containsSameSurname = false;
        for (String items : register
        ) {
            if (items.contains(surname)) {
                containsSameSurname = true;
                break;
            }
        }

        // если введены корректные данные и в реестре ЕСТЬ однофамильцы и добавляемые записи в реестр не идентичны,
        // то добавить запись в файл с такой же фамилией в его названии
        if (isCorrectUserInput && containsSameSurname && !duplicated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname, true))
            ) {
                writer.newLine();
                writer.write(person);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // если введены корректные данные и в реестре НЕТ однофамильцев и добавляемые записи в реестр не идентичны,
        // то сделать запись в новый файл
        else if (isCorrectUserInput && !duplicated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname))
            ) {
                writer.write(person);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Читает файл FILE_NAME, содержащий уникальные записи (реестр)
     * @param register массив строк для хранения данных, считанных их файла FILE_NAME
     * @param FILE_NAME название файла, содержащего реестр записей
     * Бросает исключение: FileNotFoundException
     */
    public static void readRegister(ArrayList<String> register, String FILE_NAME) {
        try (Scanner read = new Scanner(new File(FILE_NAME))) {
            while (read.hasNext()) {
                register.add(read.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ввод данных пользователем и чтение их из консоли.
     * Бросает исключение: Exception
     */
    public static String userInput() {
        try (
                InputStream inputStream = System.in;
                Reader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            System.out.println("Enter NEW PERSON identity as: Surname Name Middlename Date_Of_Birth Telephone_Number Gender.");
            System.out.println("Date_Of_Birth must match \"dd.mm.yyyy\" pattern.");
            System.out.println("Telephone_Number should consist of numbers only.");
            System.out.println("Gender is single letter either 'm' or 'f'.");
            System.out.println("Enter required data here separated by SPACE: ");
            return bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Обработка введенных данных: чтение строки, введенной пользователем, и разбиение ее по полям Ф И О ДР Тел Пол
     * @param string - строка, введенная пользователем
     * @return Возвращает список, состоящий из полей Ф И О ДР Тел Пол
     * Бросает исключения: noDataException, NullPointerException, Exception
     */
    public static ArrayList<String> processInput(String string) {
        try (Scanner scanner = new Scanner(string)) {
            scanner.useDelimiter(" ");
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            return list;
        } catch (NullPointerException e) {
            throw new noDataException("No data provided by user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Проверка введенных пользователем данных по количеству
     *
     * @param list список, состоящий из Ф И О Дата_рождения номер_телефона пол
     * @return boolean
     */
    public static boolean isValidInput(ArrayList<String> list) {
        int TOKEN_QUANTITY = 6; // Ф И О дата_рождения номер_телефона пол = 6
        if (list != null) {
            return list.size() == TOKEN_QUANTITY;
        }
        return false;
    }

    /**
     * Проверка поля "Дата Рождения" на валидность данных: проверяются отдельно число, месяц, год.
     * @param date String - в качестве аргумента передается поле "Дата Рождения"
     * @return boolean
     * Бросает исключения: dayOutOfRange, monthOutOfRange, yearOutOfRange, ProgramExceptions,
     * NumberFormatException, Exception
     */
    public static boolean isValidDateOfBirth(String date) {
        Calendar calendar = new GregorianCalendar();
        int CURRENT_YEAR = calendar.get(Calendar.YEAR);
        byte MAX_DAYS_IN_MONTH = 31;
        byte MAX_MONTHS_IN_YEAR = 12;

        try (Scanner sc = new Scanner(date)) {
            sc.useDelimiter("\\.");

            try {
                int day = Integer.parseInt(sc.next());
                if (day <= 0 || day > MAX_DAYS_IN_MONTH) {
                    throw new dayOutOfRange("Incorrect DAY of birth input");
                }
                int month = Integer.parseInt(sc.next());
                if (month <= 0 || month > MAX_MONTHS_IN_YEAR) {
                    throw new monthOutOfRange("Incorrect MONTH of birth input");
                }
                int year = Integer.parseInt(sc.next());
                if (year < 0 || year > CURRENT_YEAR) {
                    throw new yearOutOfRange("Incorrect YEAR of birth input");
                }
            } catch (NumberFormatException ex) {
                throw new ProgramExceptions("Incorrect DATE OF BIRTH input");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Проверка поля "Номер телефона" на валидность данных
     * @param tel String - в качестве аргумента передается поле "Номер телефона"
     * @return boolean
     * Бросает исключения: telephoneNumberException, NumberFormatException, Exception
     */
    public static boolean isValidTelephone(String tel) {
        try {
            Integer.parseInt(tel);
        } catch (NumberFormatException e) {
            throw new telephoneNumberException("Incorrect telephone number");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Проверка поля "Пол" на валидность данных: принимаются только символы 'm' и 'f'
     * @param gender String - в качестве аргумента передается поле "Пол"
     * @return boolean
     * Бросает исключения: genderIncorrectInput, Exception
     */
    public static boolean isValidGender(String gender) {
        try {
            if (!(gender.equals("m") || gender.equals("f"))) {
                throw new genderIncorrectInput("Incorrect input for GENDER field");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}



