package education;

/**
 * Класс "Оценка" имеет 3 поля: значение оценки, предмет, имя студента
 * Также имеет метод, определяющий, заслужена ли поставленная оценка
 */
public class Mark {
    private int value; //Значение оценки
    private String subject; //Предмет, по которому ставится оценка
    private String studentName; //Полное имя студента, которому ставится оценка

    public Mark(int value, String subject, String studentName) {
        this.value = value;
        this.subject = subject;
        this.studentName = studentName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Метод isDeserved определяет, заслуженна оценка или нет
     * Если оценка совпадает со средним арифметическим оценок студента, то она заслужена
     * В противном случае она не заслужена
     * Если оценок нет, определить степень заслуженности невозможно
     */
    public static String isDeserved(int value, Student student) {
        if(student.getMarks().length == 0) return "We cannot tell whether the mark is deserved or not";
        int sum = 0;
        for (int mark : student.getMarks()) {
            sum += mark;
        }
        double avg = sum/ student.getMarks().length;

        if(value == (int)avg) return "Deserved :)";
        return "Undeserved :(";
    }
}
