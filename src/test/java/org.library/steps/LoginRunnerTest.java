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
        //features - path to folder with .feature files
        features = "src/test/resources/org.library/features",

        tags = "@database"
//        tags = "~@ignore"
        //glue - name of package with StepDef (in case it is not the same with runner)
        //,glue = "org.library.steps"
)
public class LoginRunnerTest {
}
