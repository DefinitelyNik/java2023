package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Класс сущностей
 * Содержит данные о всех сущностях в игре, включая игрока
 */
public class Entity {
    public int worldX, worldY; // Координаты игрока на карте
    public int speed; // Скорость передвижения игрока по карте
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Переменнные, отвечающие за направление движения игрока
    public String direction; // Направление движения игрока
    public int spriteCounter = 0; // Переменная для отрисовки анимации передвижения сущностей
    public int spriteNum = 1; // Переменная для отрисовки анимации передвижения сущностей
    public Rectangle solidArea; // Область коллизии сущности(Квадрат, размер которого меньше, чем размер модели сущности)
    public boolean collisionOn = false; // Есть коллизия или нет
}
