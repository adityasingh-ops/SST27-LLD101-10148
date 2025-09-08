package com.example.game;

/**
 * Starter demo using only the base character.
 * TODOs guide you to implement decorators and compose them.
 */
public class GameDemo {
    public static void main(String[] args) {
        Character base = new BaseCharacter();

        System.out.println("--- Base ---");
        base.move();
        base.attack();

        // === YOUR TASKS ===
        // 1) Create CharacterDecorator that implements Character and wraps another Character.
        // 2) Create concrete decorators, for example:
        //      - SpeedBoost (adds +N to speed, overrides getSpeed() and move() print)
        //      - DamageBoost (adds +N to damage, overrides getDamage() and attack() print)
        //      - GoldenAura (changes sprite, small buffs, logs aura on actions)
        // 3) Show composition:
        //      a) Base + SpeedBoost + DamageBoost
        //      b) Add GoldenAura (sprite change + buffs)
        //      c) Remove GoldenAura by recomposing (rebuild chain without it)
        //
        // Example (after you implement):
        // Character buffed = new DamageBoost(new SpeedBoost(base, 3), 15);
        // buffed.move();
        // buffed.attack();
        //
        // Character shiny = new GoldenAura(buffed);
        // shiny.move();
        // shiny.attack();
        //
        // Character withoutAura = buffed; // removal by recomposition
        // withoutAura.move();
        // withoutAura.attack();

        System.out.println("\n--- Implementations ---");
        
        // a) Base + SpeedBoost + DamageBoost
        System.out.println("\n=== Speed & Damage Buffed ===");
        Character buffed = new DamageBoost(new SpeedBoost(base, 3), 15);
        buffed.move();
        buffed.attack();
        System.out.println("Stats - Speed: " + buffed.getSpeed() + ", Damage: " + buffed.getDamage() + ", Sprite: " + buffed.getSprite());
        
        // b) Add GoldenAura (sprite change + buffs)
        System.out.println("\n=== With Golden Aura ===");
        Character shiny = new GoldenAura(buffed);
        shiny.move();
        shiny.attack();
        System.out.println("Stats - Speed: " + shiny.getSpeed() + ", Damage: " + shiny.getDamage() + ", Sprite: " + shiny.getSprite());
        
        // c) Remove GoldenAura by recomposition (rebuild chain without it)
        System.out.println("\n=== Aura Removed (Recomposed) ===");
        Character withoutAura = buffed; // removal by recomposition
        withoutAura.move();
        withoutAura.attack();
        System.out.println("Stats - Speed: " + withoutAura.getSpeed() + ", Damage: " + withoutAura.getDamage() + ", Sprite: " + withoutAura.getSprite());
        
        // Additional examples
        System.out.println("\n=== Additional Compositions ===");
        
        // Only Golden Aura on base
        Character goldenBase = new GoldenAura(base);
        System.out.println("\n--- Golden Base ---");
        goldenBase.move();
        goldenBase.attack();
        
        // Multiple speed boosts
        Character speedDemon = new SpeedBoost(new SpeedBoost(new SpeedBoost(base, 5), 3), 2);
        System.out.println("\n--- Speed Demon ---");
        speedDemon.move();
        System.out.println("Final Speed: " + speedDemon.getSpeed());
        
        // Complex layering
        Character ultimate = new GoldenAura(
            new DamageBoost(
                new SpeedBoost(
                    new DamageBoost(base, 8), 
                    4
                ), 
                12
            )
        );
        System.out.println("\n--- Ultimate Character ---");
        ultimate.move();
        ultimate.attack();
        System.out.println("Ultimate Stats - Speed: " + ultimate.getSpeed() + ", Damage: " + ultimate.getDamage() + ", Sprite: " + ultimate.getSprite());
    }
}
