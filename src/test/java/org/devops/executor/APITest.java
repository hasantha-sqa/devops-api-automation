package org.devops.executor;

import io.cucumber.core.cli.Main;
import org.junit.jupiter.api.Test;

public class APITest {
    @Test
    public void endPointTest() {

        Main.run(new String[]{
                "--threads", "1",
                "-p", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "-g", "org.devops.steps", "src/test/resources/scenarios",
                "-t", "@GetAuthToken",
        }, APITest.class.getClassLoader());

    }
}
