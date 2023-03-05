/**
 * @author Непочатый Никита Владимирович
 * @version 2
 */
package education;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите имя пользователя: ");
        String name = reader.next();
        reader.close();
        System.out.println("Привет, " + name);

        List<Double> sinArray = new ArrayList<>();
        List<Integer> intArray = new ArrayList<>();

        for(int i = 0; i < 46; i += 5){
            sinArray.add(Math.sin(Math.toRadians(i)));
            intArray.add(i);
        }

        System.out.println("| a  | sin(a)|");
        for(int i = 0; i < sinArray.toArray().length; i++){
            String deg = intArray.toArray()[i].toString(); // Градус в строковом виде
            String sin = sinArray.toArray()[i].toString(); // Значение синуса градуса в строковом виде

            if(i < 2) {
                System.out.println("| " + deg + "  | " + String.format("%.3f", Double.parseDouble(sin)) + " |");
            } else System.out.println("| " + deg + " | " + String.format("%.3f", Double.parseDouble(sin)) + " |");
        }
    }

    static class Student {
        private String fullName;
        private int age;
        private int[] marks;
        private String[] lessons;

        public Student (String fullName, int age, int[] marks, String[] lessons) {
            this.fullName = fullName;
            this.age = age;
            this.marks = marks;
            this.lessons = lessons;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int[] getMarks() {
            return marks;
        }

        public void setMarks(int[] marks) {
            this.marks = marks;
        }

        public String[] getLessons() {
            return lessons;
        }

        public void setLessons(String[] lessons) {
            this.lessons = lessons;
        }

        /*
        Метод по оценкам определяет настроение студента
        Если среднее арифметическое всех оценок >= 4, возвращает хорошее настроение
        Если среднее арифметическое всех оценок >=3 и <4, возвращает нормальное настроение
        В противном случае возвращает плохое настроение
         */
        public static String getMood(int[] marks) {
            if (marks.length == 0) return "Student is fine(no marks found)";

            double sum = 0;

            for (int mark : marks) {
                sum += mark;
            }

            double avg = sum/marks.length;

            if (avg >= 4) return "Student is happy";
            else if(avg >= 3) return "Student is ok";
            return "Student is sad";
        }
    }

    static class Task {

    }

    static class Mark {

    }
    static class Mathematics {
        /*
            Метод принимает целое значение и вычисляет его факториал
            При отрицательном входном значении возвращает -1
            При входном значении, равном 0 или 1, возвращает 1
            В остальных случаях вычисляется классический факториал
         */
        public static long fact(int n) {
            if (n < 0) return -1;
            else if (n < 2) return 1;
            return n * fact(n-1);
        }
        /*
        Метод принимает вещественное значение и вычисляет функцию sign
        При отрицательном входном значении возвращает -1
        При положительном входном значении возвращает 1
        При входном значении, равном 0, возвращает 0
         */
        public static int sign(double n) {
            if(n > 0) return 1;
            else if (n < 0) return -1;
            return 0;
        }
    }
}

