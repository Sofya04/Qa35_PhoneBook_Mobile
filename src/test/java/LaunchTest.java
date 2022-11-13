import config.AppiumConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.BaseScreen;
import screens.SplashScreen;

public class LaunchTest extends AppiumConfig {
    @Test
    public void launch(){

        String version = new SplashScreen(driver).getCurrencyVersion();
        Assert.assertTrue(version.contains("1.0.0"));
    }
}
