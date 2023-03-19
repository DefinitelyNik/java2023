package education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс "Оценка" имеет 3 поля: значение оценки, предмет, имя студента
 * Также имеет метод, определяющий, заслужена ли поставленная оценка
 */
@AllArgsConstructor
public class Mark {
    @Getter @Setter private int value; /**Значение оценки */
    @Getter @Setter private String teacherName; /**Полное имя учителя, поставившего оценку */
    @Getter @Setter private Student student; /**Класс студент */

    /**
     * Метод isDeserved определяет, заслуженна оценка или нет
     * Если оценка совпадает со средним арифметическим оценок студента, то она заслужена
     * В противном случае она не заслужена
     * Если оценок нет, определить степень заслуженности невозможно
     */
    public static String isDeserved(int value, Student student) {
        if(student.getMarks().length == 0) return "We cannot tell whether the mark is deserved or not";

        double avg = Student.avgMarks(student.getMarks());

        if(value == (int)avg) return "Deserved :)";
        return "Undeserved :(";
    }
}
