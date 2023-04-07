package net.silentj.prospring5.ch3;

import net.silentj.prospring5.ch2.MessageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedConfigurableMessageProvider implements MessageProvider {

    private final String message;

    public AnnotatedConfigurableMessageProvider(@Value("Hello from the configurable message!") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
