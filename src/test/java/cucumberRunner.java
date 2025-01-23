import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/resources"},
        glue = {"org.example"},
        monochrome = true, tags = "@Regression or @Positive or @Negative",   //tags =" @Regression AND @positive"
        plugin = {"pretty"
        })
public class cucumberRunner
{

}
