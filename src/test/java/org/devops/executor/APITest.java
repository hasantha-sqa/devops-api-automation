package org.devops.executor;

import io.cucumber.core.cli.Main;
import org.junit.jupiter.api.Test;

public class APITest {
    @Test
    void accomSearchFilters()
    {
        Main.run( new String[] {
//                "--threads", "1",
//                "-p", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "-g", "org.devops.steps", "src/test/resources/scenarios",
                "-t", "@RegisterUsers",
        }, APITest.class.getClassLoader() );
    }
}
