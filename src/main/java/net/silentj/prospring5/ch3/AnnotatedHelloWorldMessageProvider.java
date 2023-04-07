package net.silentj.prospring5.ch3;

import net.silentj.prospring5.ch2.MessageProvider;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedHelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello from annotation-land!";
    }
}
