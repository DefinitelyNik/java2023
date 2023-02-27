/**
 * Добавил класс Mathematics, а не Math, так как при добавлении Math всё ломалось.
 * В классе реализованы 2 метода fact и sign.
 * fact принимает целое число и вычисляет его факториал.
 * Возвращает -1, если число отрицательно.
 * Возвращает 1, если число равно 0 или 1.
 * Вазвращает обычный факториал при других положительных числах.
 * sign принимает число типа double и возвращает:
 * 1, если число положительное
 * -1, если число отрицательное
 * 0, если число равно нулю
 */
public class Mathematics {
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
