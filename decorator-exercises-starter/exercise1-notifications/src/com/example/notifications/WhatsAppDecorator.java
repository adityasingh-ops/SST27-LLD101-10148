package com.example.notifications;

public class WhatsappDecorator extends NotifierDecorator {
    private final String username;

    public WhatsappDecorator(Notifier notifier, String username) {
        super(notifier);
        this.username = username;
    }
    
    @Override
    public void notify(String text) {
        super.notify(text);
        sendWhatsApp(text);
    }
    
    private void sendWhatsApp(String text) {
        System.out.println("[WHATSAPP -> " + username + "]: " + text);
    }
}