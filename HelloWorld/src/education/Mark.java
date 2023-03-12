package education;

/**
 * Класс "Оценка" имеет 3 поля: значение оценки, предмет, имя студента
 * Также имеет метод, определяющий, заслужена ли поставленная оценка
 */
public class Mark {
    private int value; /**Значение оценки */
    private String teacherName; /**Полное имя учителя, поставившего оценку */
    private Student student; /** Класс студент */

    public Mark(int value, String subject, Student thisStudent) {
        this.value = value;
        this.teacherName = subject;
        this.student = thisStudent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

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
