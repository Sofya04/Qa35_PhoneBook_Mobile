package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(id="com.sheygam.contactapp:id/inputName")
    AndroidElement nameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputLastName")
    AndroidElement lastNameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputEmail")
    AndroidElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPhone")
    AndroidElement phoneEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputAddress")
    AndroidElement addressEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputDesc")
    AndroidElement descriptionEditText;
    @FindBy(id="com.sheygam.contactapp:id/createBtn")
    AndroidElement createButton;
    @FindBy(id = "android:id/message")
    AndroidElement errorMessage;
    @FindBy(id = "android:id/button1")
    AndroidElement okButton;

    public AddNewContactScreen fillContactForm(Contact contact){
        shouldWait(nameEditText,3);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastname());
        type(emailEditText, contact.getEmail());
        type2(phoneEditText, contact.getPhone());
        type2(addressEditText, contact.getAddress());
        type2(descriptionEditText, contact.getDescription());
        return  this;
    }

    public ContactListScreen  submitContactForm(){
        createButton.click();
        pause(2000);
        return new ContactListScreen(driver);
    }
    public AddNewContactScreen submitContactFormNegative(){
        createButton.click();
        pause(2000);
        return this;
    }
    public AddNewContactScreen isErorrMessageContaisText(String text){
        pause(2000);
        Assert.assertTrue(errorMessage.getText().contains(text));
        okButton.click();
        pause(2);
        driver.navigate().back();
        return this;
    }
}
