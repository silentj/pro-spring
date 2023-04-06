package net.silentj.prospring5.ch2;

public class SystemOutMessageRenderer implements MessageRenderer {

    private final MessageProvider messageProvider;

    public SystemOutMessageRenderer(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }
}
