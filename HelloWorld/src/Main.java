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
    static class Mathematics {
        public static long fact(int n) {
            if (n < 0) return -1;
            else if (n < 2) return 1;
            return n * fact(n-1);
        }

        public static int sign(double n) {
            if(n > 0) return 1;
            else if (n < 0) return -1;
            return 0;
        }
    }
}

