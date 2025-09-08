package com.example.notifications;

/**
 * Starter demo that uses only the existing Email notifier.
 * TODOs guide you to add decorators and compose them.
 */
public class Demo {
    public static void main(String[] args) {
        Notifier base = new EmailNotifier("user@example.com");

        // Baseline behavior (already works)
        base.notify("Baseline email only.");

        // === YOUR TASKS ===
        // 1) Create a base decorator class: NotifierDecorator implements Notifier and wraps another Notifier.
        // 2) Create concrete decorators:
        //      - SmsDecorator (adds SMS send)
        //      - WhatsAppDecorator (adds WhatsApp send)
        //      - SlackDecorator (adds Slack send)
        // 3) Compose at runtime to achieve these combinations:
        //      a) Email + SMS
        //      b) Email + WhatsApp
        //      c) Email + Slack
        //      d) Email + WhatsApp + Slack
        //
        // Example (after you implement):
        // Notifier smsAndEmail = new SmsDecorator(base, "+91-99999-11111");
        // smsAndEmail.notify("Build green ✅");
        //
        // Notifier full = new SlackDecorator(new WhatsAppDecorator(base, "user_wa"), "deployments");
        // full.notify("Deployment completed 🚀");

        System.out.println("\n=== Decorated Notifications ===");
        
        // a) Email + SMS
        Notifier smsAndEmail = new SmsDecorator(base, "+91-99999-11111");
        smsAndEmail.notify("Build green ✅");
        
        System.out.println();
        
        // b) Email + WhatsApp
        Notifier whatsAppAndEmail = new WhatsAppDecorator(base, "user_wa");
        whatsAppAndEmail.notify("Code review completed ✨");
        
        System.out.println();
        
        // c) Email + Slack
        Notifier slackAndEmail = new SlackDecorator(base, "deployments");
        slackAndEmail.notify("Tests passed successfully 🎯");
        
        System.out.println();
        
        // d) Email + WhatsApp + Slack
        Notifier full = new SlackDecorator(new WhatsAppDecorator(base, "user_wa"), "deployments");
        full.notify("Deployment completed 🚀");
        
        System.out.println();
        
        // Bonus: All channels
        Notifier allChannels = new SlackDecorator(
            new WhatsAppDecorator(
                new SmsDecorator(base, "+91-99999-11111"), 
                "user_wa"
            ), 
            "alerts"
        );
        allChannels.notify("Critical system alert! 🚨");
    }
}
