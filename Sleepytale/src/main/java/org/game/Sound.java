package org.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Класс звуков и музыки
 * Получает звуковые файлы и проигрывает их
 */
public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30]; // Максимальное количество звуковых файлов в игре

    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/Bip-Bop16max-ed.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
    }

    /**
     * Метод, устанавливающий звуковой файл
     * Получает на вход индеск звукового файла
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {

        }
    }

    /**
     * Метод, проигрывающий звуковые файлы
     */
    public void play() {
        clip.start();
    }

    /**
     * Метод, зацикливающий звуковые файлы
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Метод, останавливающий звуковые файлы
     */
    public void stop() {
        clip.stop();
    }
}
