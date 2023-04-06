package net.silentj.prospring5.ch2;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpringContextTest {
    @Test
    public void shouldCreateRendererAndRenderToSystemOut() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("systemOutConfiguration.xml");

        String output = tapSystemOut(() -> {
            MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
            mr.render();
        });

        // Test the renderer actually works
        assertEquals("Hello, world!\n", output);

        // Test that the beans aren't proxies
        assertEquals(SystemOutMessageRenderer.class, ctx.getBean("renderer").getClass());
        assertEquals(HelloWorldMessageProvider.class, ctx.getBean("provider").getClass());
    }

    private static String tapSystemOut(Runnable runnable) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(testOutput));
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }

        return testOutput.toString(StandardCharsets.UTF_8);
    }
}
