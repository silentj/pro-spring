package net.silentj.prospring5.ch3;

import net.silentj.prospring5.ch2.MessageProvider;
import net.silentj.prospring5.ch2.MessageRenderer;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static net.silentj.prospring5.util.TestOutputUtils.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationDrivenSpringContextTest {
    @Test
    public void shouldCreateRendererAndRenderToSystemOutWithAnnotationsAndXmlConfig() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ch3/componentScanConfiguration.xml");

        String output = tapSystemOut(() -> {
            MessageRenderer mr = ctx.getBean(MessageRenderer.class);
            mr.render();
        });

        // Test the renderer actually works
        assertEquals("Hello from the configurable message!\n", output);

        // Test that the beans aren't proxies
        assertEquals(AnnotatedSystemOutMessageRenderer.class, ctx.getBean(MessageRenderer.class).getClass());
        assertEquals(AnnotatedConfigurableMessageProvider.class, ctx.getBean(MessageProvider.class).getClass());

        // Make some assertions about the beans to see what's right
        // (This isn't really something we care about... more just exploring)
        assertThat(ctx.getBeanDefinitionNames(), hasItemInArray("annotatedConfigurableMessageProvider"));
        assertThat(ctx.getBeanDefinitionNames(), hasItemInArray("annotatedSystemOutMessageRenderer"));

        // These assertions are false...
        // assertThat(ctx.getBeanDefinitionNames(), arrayWithSize(2)); // There are 4 other beans related to configuration
    }

    @Test
    public void shouldCreateRendererAndRenderToSystemOutWithAnnotationsAndConfigClass() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentScanningConfiguration.class);

        String output = tapSystemOut(() -> {
            MessageRenderer mr = ctx.getBean(MessageRenderer.class);
            mr.render();
        });

        // Test the renderer actually works
        assertEquals("Hello from the configurable message!\n", output);
    }
}
