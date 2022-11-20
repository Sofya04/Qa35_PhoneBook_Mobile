package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class EditContactScreen extends BaseScreen {

    public EditContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    AndroidElement nameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    AndroidElement lastNameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement emailEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    AndroidElement phoneEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    AndroidElement addressEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    AndroidElement descriptionEditText;
    @FindBy(id = "com.sheygam.contactapp:id/updateBtn")
    AndroidElement updateButton;

    public EditContactScreen editFieldContact2(String option, String text) {
        shouldHave(activityViewText, "Edit contact", 5);
        if (option.equals("name")) {
            type2(nameEditText, text);
        } else if (option.equals("lastname")) {
            type2(lastNameEditText, text);
        } else if (option.equals("email")) {
            type2(emailEditText, text);
        } else if (option.equals("phone")) {
            type2(phoneEditText, text);
        } else if (option.equals("address")) {
            type2(addressEditText, text);
        } else if (option.equals("description")) {
            type2(descriptionEditText, text);
        }
        return this;
    }
    public EditContactScreen editFieldContact(String option, String text){
        shouldHave(activityViewText,"Edit contact",6);
        switch (option){
            case "name":
                type2(nameEditText,text);
                break;
            case "lastname":
                type2(lastNameEditText,text);
                break;
            case "email":
                type2(emailEditText,text);
                break;
            case "phone":
                type2(phoneEditText,text);
                break;
            case "address":
                type2(addressEditText,text);
                break;
            case "description":
                type2(descriptionEditText,text);
                break;
        }
        return this;
    }
    public ContactListScreen updateChanges(){
        updateButton.click();
        return new ContactListScreen(driver);
    }
    public EditContactScreen editNameOnly(String name){
        shouldHave(activityViewText,"Edit contact",5);
        type2(nameEditText,name);
        return this;
    }
}
