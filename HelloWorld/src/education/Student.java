package education;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс "Студент" имеет 4 поля: полное имя(ФИО), возраст, оценки, уроки
 * Также имеет метод, определяющий настроение студента по его текущим оценкам
 */

public class Student {
    @Getter @Setter private String fullName; /**ФИО студента*/
    @Getter @Setter private int age; /**Возраст студента*/
    @Getter @Setter private int[] marks; /**Текущие оценки студента*/
    @Getter @Setter private String[] lessons; /**Уроки студента*/

    private Student (StudentBuilder studentBuilder) {
        fullName = studentBuilder.fullName;
        age = studentBuilder.age;
        marks = studentBuilder.marks;
        lessons = studentBuilder.lessons;
    }

    @Builder
    public static class StudentBuilder {
        private String fullName; /**ФИО студента*/
        private int age; /**Возраст студента*/
        private int[] marks; /**Текущие оценки студента*/
        private String[] lessons; /**Уроки студента*/

        public Student build() {
            return new Student(this);
        }
    }

    /**
     * Метод по оценкам определяет настроение студента
     * Если среднее арифметическое всех оценок >= 4, возвращает хорошее настроение
     * Если среднее арифметическое всех оценок >=3 и <4, возвращает нормальное настроение
     * В противном случае возвращает плохое настроение
     */
    public static String getMood(int[] marks) {
        if (marks.length == 0) return "Student is fine(no marks found)";

        double avg = avgMarks(marks);

        if (avg >= 4) return "Student is happy";
        else if(avg >= 3) return "Student is ok";
        return "Student is sad";
    }

    public static double avgMarks(int[] marks) {
        double sum = 0;

        for (int mark : marks) {
            sum += mark;
        }

        return sum / marks.length;
    }
}
