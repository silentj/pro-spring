package net.silentj.prospring5.ch3;

import net.silentj.prospring5.ch2.MessageProvider;
import net.silentj.prospring5.ch2.MessageRenderer;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedSystemOutMessageRenderer implements MessageRenderer {

    private final MessageProvider messageProvider;

    public AnnotatedSystemOutMessageRenderer(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }
}
