package education;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс "Задание" имеет 5 полей: описание задания, дедлайн, обязательность выполнения, предмет и доп. задание
 * Также имеет метод, вычисляющий сложность заданного задания по некоторым параметрам
 */
public class Task {
    @Getter @Setter private String description; /**описание задания */
    @Getter @Setter private String deadline; /**дедлайн в днях, например (4)*/
    @Getter @Setter private boolean isMandatory; /** обязательное задание или нет*/
    @Getter @Setter private String subject; /**школьный предмет, по которому задано задание*/
    @Getter @Setter private String subTask; /**подзадание(если оно есть)*/

    private Task(TaskBuilder taskBuilder) {
        description = taskBuilder.description;
        deadline = taskBuilder.deadline;
        isMandatory = taskBuilder.isMandatory;
        subject = taskBuilder.subject;
        subTask = taskBuilder.subTask;
    }
    @Builder
    public static class TaskBuilder {
        private String description; /**описание задания */
        private String deadline; /**дедлайн в днях, например (4)*/
        private boolean isMandatory; /** обязательное задание или нет*/
        private String subject; /**школьный предмет, по которому задано задание*/
        private String subTask; /**подзадание(если оно есть)*/

        public Task build() {
            return new Task(this);
        }
    }

    /**
     * Метод вычисления сложности задания.
     * Учитываются такие параметры, как: предмет, дедлайн, оценки студента, доп. задание (если оно есть)
     * Считаются коэффиценты сложности по каждому параметру, которые затем используются в итоговой формуле
     * Итоговая формула представляет собой перемножение коэффицентов сложности и деление их на 10
     */
    public static double taskDifficulty(String subject, int deadline, Student student, String subTask) {
        double diffScore; //итоговая сложность задания
        int subjectScore = 1; //коэффицент сложности предмета
        int deadlineScore = 6; //коэффицент сложности дедлайна
        int studentScore; //коэффицент сложности относительно оценок студента
        int subTaskScore; //коэффицент сложности доп. задания

        String[] hardSubjects = {"Math", "Physics", "IT", "Chemistry"};

        for (String hardSubject : hardSubjects) {
            if (Objects.equals(subject, hardSubject)) { //если предмет сложный, коэффицент сложности высокий
                subjectScore = 5;
                break;
            } else { //если предмет относительно простой, коэффицент сложности низкий
                subjectScore = 2;
            }
        }

        /*
         * Оценивается, сколько времени даётся на выполнение задания
         * Чем больше дедлайн, тем меньше коэффицент сложности
         */
        if(deadline > 7) {
            deadlineScore = 1;
        } else if(deadline > 3) {
            deadlineScore = 2;
        } else if(deadline > 0) {
            deadlineScore = 4;
        }

        /*
        Оцениваются знания и навыки студента
        Чем лучше оценки, тем легче будет выполнить задание
        Чем хуже оценки, тем труднее его выполнить
         */
        if(student.getMarks().length > 0){
            double avg = Student.avgMarks(student.getMarks());

            if(avg > 4) studentScore = 2;
            else if(avg > 3) studentScore = 3;
            else studentScore = 4;
        } else studentScore = 3;

        /*
         * Оценивается доп. задание.
         * Если оно есть, задание сложнее, чем если доп. задания нет
         */
        if(subTask.length() > 0) subTaskScore = 3;
        else subTaskScore = 1;

        diffScore = (subjectScore * deadlineScore * studentScore * subTaskScore) / 10.0; // формула вычисления итоговой оценки сложности задания

        return diffScore;
    }
}
