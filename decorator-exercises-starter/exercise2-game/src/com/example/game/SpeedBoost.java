package com.example.game;

/**
 * Decorator that adds speed boost to the character.
 */
public class SpeedBoost extends CharacterDecorator {
    private final int speedBonus;
    
    public SpeedBoost(Character character, int speedBonus) {
        super(character);
        this.speedBonus = speedBonus;
    }
    
    @Override
    public void move() {
        System.out.println("Moving at speed " + getSpeed() + " with sprite " + getSprite() + " [SPEED BOOSTED]");
    }
    
    @Override
    public int getSpeed() {
        return character.getSpeed() + speedBonus;
    }
}
