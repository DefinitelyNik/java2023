/**
 * @author Непочатый Никита Владимирович
 * @version 2
 */
package education;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        Student student = new Student("Непочатый Никита Владимирович", 21, new int[] {}, new String[]{""});
        double taskDiff = Task.taskDifficulty("IT", -15, student, "выиграть игру в Dota 2");
        System.out.println("Оценка сложности задания составляет " + taskDiff);
        System.out.println(Mark.isDeserved(5,student));
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
        private String description;
        private String deadline; // дедлайн в днях, например (4)
        private boolean isMandatory;
        private String subject; // школьный предмет, по которому задано задание
        private String subTask; // подзадание(если оно есть)

        public Task(String description, String deadline, boolean isMandatory, String subject, String subTask) {
            this.description = description;
            this.deadline = deadline;
            this.isMandatory = isMandatory;
            this.subject = subject;
            this.subTask = subTask;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public boolean getMandatory() {
            return isMandatory;
        }

        public void setMandatory(boolean isMandatory) {
            this.isMandatory = isMandatory;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getSubTask() {
            return subTask;
        }

        public void setSubTask(String subTask) {
            this.subTask = subTask;
        }

        /*
        Метод вычисления сложности задания.
        Учитываются предмет, дедлайн, оценки студента, доп. задание, если оно есть
         */
        public static double taskDifficulty(String subject, int deadline, Student student, String subTask) {
            double diffScore; //итоговая сложность задания
            int subjectScore = 1; //коэффицент сложности предмета
            int deadlineScore; //коэффицент сложности дедлайна
            int studentScore; //коэффицент сложности относительно оценок студента
            int subTaskScore; //коэффицент сложности доп. задания

            String[] hardSubjects = {"Math", "Physics", "IT", "Chemistry"};
            String[] easySubjects = {"Russian", "English", "History", "Biology"};

            if(subject.length() > 0) {
                for (int i = 0; i < hardSubjects.length; i++) {
                    if (Objects.equals(subject, hardSubjects[i])) { //если предмет сложный, коэффицент сложности высокий
                        subjectScore = 5;
                        break;
                    } else if (Objects.equals(subject, easySubjects[i])) { //если предмет относительно простой, коэффицент сложности низкий
                        subjectScore = 2;
                        break;
                    }  // если предмет совсем простой, коэффицент сложности очень низкий

                }
            }

            //Оценивается, сколько времени даётся на выполнение задания
            if(deadline > 7) {
                deadlineScore = 1;
            } else if(deadline > 3) {
                deadlineScore = 2;
            } else if(deadline > 0) {
                deadlineScore = 4;
            } else deadlineScore = 6;

            /*
            Оцениваются знания и навыки студента
            Чем лучше оценки, тем легче будет выполнить задание
            Чем хуже оценки, тем труднее его выполнить
             */
            if(student.marks.length > 0){
                int sum = 0;
                for (int mark : student.marks) {
                    sum += mark;
                }
                double avg = sum/student.marks.length;

                if(avg > 4) studentScore = 2;
                else if(avg > 3) studentScore = 3;
                else studentScore = 4;
            } else studentScore = 3;

            //Оценивается доп. задание. Если оно есть, задание сложнее, чем если доп. задания нет
            if(subTask.length() > 0) subTaskScore = 3;
            else subTaskScore = 1;

            diffScore = (subjectScore * deadlineScore * studentScore * subTaskScore) / 10.0; // формула вычисления итоговой оценки сложности задания

            return diffScore;
        }
    }

    static class Mark {
        private int value;
        private String subject;
        private String studentName;

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

        /*
        Метод isDeserved определяет, заслуженна оценка или нет
        Если оценка совпадает со средним арифметическим оценок студента, то она заслужена
        В противном случае она не заслужена
        Если оценок нет, определить степень заслуженности невозможно
         */
        public static String isDeserved(int value, Student student) {
            if(student.marks.length == 0) return "We cannot tell whether the mark is deserved or not";
            int sum = 0;
            for (int mark : student.marks) {
                sum += mark;
            }
            double avg = sum/student.marks.length;

            if(value == (int)avg) return "Deserved :)";
            return "Undeserved :(";
        }
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

