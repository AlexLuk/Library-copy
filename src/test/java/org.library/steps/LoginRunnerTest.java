package org.library.steps;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "json:target/cucumber/userLogin.json",
                "html:target/cucumber/userLogin.html",
                "pretty"
        },
        features = "src/test/resources/org.library/features/userLogin.feature",
        tags = "~@ignore"
)
public class LoginRunnerTest {
}
