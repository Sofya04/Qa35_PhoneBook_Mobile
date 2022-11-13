package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    //@FindBy (xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/inputEmail']")
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement editTextEmail;
    //@FindBy (xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPassword']")
    @FindBy (id= "com.sheygam.contactapp:id/inputPassword")
    AndroidElement editTextPassword;
    //@FindBy (xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/loginBtn']")
    // @FindBy (id = "com.sheygam.contactapp:id/loginBtn")
    @FindBy (xpath= "//*[@text='LOGIN']")
    AndroidElement loginButton;
    @FindBy (id = "com.sheygam.contactapp:id/regBtn")
    AndroidElement registrationButton;
    @FindBy(id = "android:id/message")
    AndroidElement errorTextView;
    @FindBy(id="android:id/button1")
    AndroidElement okBtn;

    public AuthenticationScreen isErorrMessageContaisText(String text){
        pause(2000);
        Assert.assertTrue(errorTextView.getText().contains(text));
        okBtn.click();

        return this;
    }
    public AuthenticationScreen isErorrMessageContaisTextInAlert(String text){
        Alert alert = new WebDriverWait(driver,3)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

        return this;
    }

    public ContactListScreen submitRegistration(){
        registrationButton.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen submitRegistrationNegative(){
        registrationButton.click();
        return this;
    }

    public ContactListScreen login(Auth auth){
        shouldWait(editTextEmail,5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        loginButton.click();

        return new ContactListScreen(driver);
    }
    public AuthenticationScreen loginUnsuccessful(Auth auth){
        shouldWait(editTextEmail,5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        loginButton.click();

        return this;
    }
    //loginUnsuccessful

    public ContactListScreen registration(Auth auth){
        shouldWait(editTextEmail,5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        registrationButton.click();

        return new ContactListScreen(driver);
    }
    public AuthenticationScreen registrationUnsuccessful(Auth auth){
        shouldWait(editTextEmail,5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        registrationButton.click();

        return this;
    }


    public AuthenticationScreen fillEmail(String email)  {
        shouldWait(editTextEmail,7);
        type(editTextEmail,email);
        // return new AuthenticationScreen(driver);
        return this;
    }
    public AuthenticationScreen fillPassword(String password){

        type(editTextPassword,password);
        //return new AuthenticationScreen(driver);
        return this;

    }
    public ContactListScreen submitLogin(){
        System.out.println(loginButton.getAttribute("text"));
        loginButton.click();
        return new ContactListScreen(driver);
    }
}
