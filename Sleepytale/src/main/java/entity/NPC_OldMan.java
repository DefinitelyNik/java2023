package entity;

import org.game.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    /**
     * Метод, получающий изобращения этой сущности
     */
    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    /**
     * Метод, устанавливающий все возможные строки диалога с этой сущностью
     */
    public void setDialogue() {
        dialogues[0] = "Hello, traveller!";
        dialogues[1] = "So you've come to this island to find the treasure, right?";
        dialogues[2] = "Go ahead and find it if you are brave enough";
        dialogues[3] = "Good luck, traveller!";
    }


    /**
     * Метод, позволяющий сущности делать что-то на карте
     * По сути, это очень простой ИИ
     */
    public void setAction()
    {
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // выбираем число от 1 до 100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    /**
     * Метод, позволяющий сущности говорить,
     * но в нем можно установить логику характерную только для этой сущности
     */
    public void speak() {
        // тут будет нечто, характерное только для этого персонажа
        super.speak();
    }
}
