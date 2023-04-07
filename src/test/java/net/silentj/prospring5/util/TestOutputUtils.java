package net.silentj.prospring5.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class TestOutputUtils {
    public static String tapSystemOut(Runnable runnable) {
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

    private TestOutputUtils() {
        // Utility class that shouldn't be instantiated
    }
}
