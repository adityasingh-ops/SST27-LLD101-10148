package com.example.game;

/**
 * Decorator that adds damage boost to the character.
 */
public class DamageBoost extends CharacterDecorator {
    private final int damageBonus;
    
    public DamageBoost(Character character, int damageBonus) {
        super(character);
        this.damageBonus = damageBonus;
    }
    
    @Override
    public void attack() {
        System.out.println("Attacking with damage " + getDamage() + " using sprite " + getSprite() + " [DAMAGE BOOSTED]");
    }
    
    @Override
    public int getDamage() {
        return character.getDamage() + damageBonus;
    }
}
