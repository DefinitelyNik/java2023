package org.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Хендлер нажатых клавиш
 * Позволяет игроку двигаться в 4 стороны(в зависимости от нажатой клавиши)
 * Позволяет взаимодействовать с объектами и сущностями при нажатии клавиши
 * И многое другое...
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    // Debug
    boolean  checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Загрузочный экран
        if(gp.gameState == gp.titleState) {

            if(gp.ui.titleScreenState == 0) {
                if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1) {
                        // позже
                    }
                    if(gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
             else if(gp.ui.titleScreenState == 1) {
                if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        System.out.println("Fighter stuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1) {
                        System.out.println("Thief stuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2) {
                        System.out.println("Sorcerer stuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0; // !
                    }
                }
            }
        }

        // Состояние игры(идёт игра)
        if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_E) {
                ePressed = true;
            }

            // Debug
            if(code == KeyEvent.VK_T) {
                if(!checkDrawTime) {
                    checkDrawTime = true;
                } else if (checkDrawTime) {
                    checkDrawTime = false;
                }
            }
        }
        // Состояние игры(пауза)
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }

        // Состояние игры(диалог)
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_E) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
