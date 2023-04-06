package net.silentj.prospring5.ch2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer renderer(MessageProvider messageProvider) {
        return new SystemOutMessageRenderer(messageProvider);
    }
}
