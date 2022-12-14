package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/version_text']")
    AndroidElement versionTextView;

    public String getCurrencyVersion(){
        return versionTextView.getText();
    }


    public AuthenticationScreen checkVersion(String version){
        versionTextView.getText().contains(version);
        return new AuthenticationScreen(driver);
    }
}
