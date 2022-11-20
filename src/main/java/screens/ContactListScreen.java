package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement moreOptions;
    @FindBy(id = "com.sheygam.contactapp:id/title")
    AndroidElement logoutButton;
    @FindBy (xpath = "//*[@text = 'Date picker']")
    AndroidElement datePickerButton;
    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusButton;
    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    List<AndroidElement> contactNamesList;
    @FindBy(id = "com.sheygam.contactapp:id/rowPhone")
    List<AndroidElement> contactPhonesList;
    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    List<AndroidElement> contacts;
    @FindBy(id = "android:id/button1")
    AndroidElement yesButton;
    @FindBy(id = "android:id/button2")
    AndroidElement cancelButton;
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement emptyContacts;
    @FindBy(id = "com.sheygam.contactapp:id/rowPhone")
    AndroidElement phone;
    @FindBy(id = "com.sheygam.contactapp:id/emailTxt")
    AndroidElement emailEditField;
    @FindBy(id = "com.sheygam.contactapp:id/addressTxt")
    AndroidElement addressEditField;
    @FindBy(id = "com.sheygam.contactapp:id/descTxt")
    AndroidElement descriptionEditField;

    int countBefore;
    int countAfter;

    public DatePickerScreen openDatePickerScreen(){
        shouldWait(moreOptions,5);
        moreOptions.click();
        datePickerButton.click();
        return new DatePickerScreen(driver);
    }
    public EditContactScreen openEditForm(){
        shouldWait(plusButton,5);
        AndroidElement contact = contacts.get(0);
        Dimension dimension = driver.manage().window().getSize();

        Rectangle rect = contact.getRect();
        int xA = rect.getX() + rect.getWidth() / 8*7;
        int xB = rect.getX() + rect.getWidth() / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(xA, y))
                .moveTo(PointOption.point(xB, y))
                .release().perform();//necessary methods for executing the entire method
        return new EditContactScreen(driver);
    }
    public ContactListScreen removeOneContact() {
        //shouldHave(activityViewText, "Contact list", 5);

        countBefore = contacts.size();
        System.out.println(countBefore);
        AndroidElement contact = contacts.get(0);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension.getHeight());
        System.out.println(dimension.getWidth());

        Rectangle rect = contact.getRect();
        int xA = rect.getX() + rect.getWidth() / 8;
        int xB = rect.getX() + (rect.getWidth() / 8) * 7;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(xA, y))
                .moveTo(PointOption.point(xB, y))
                .release().perform();//necessary methods for executing the entire method
        shouldWait(yesButton, 5);
        yesButton.click();
        pause(2);
        countAfter = contacts.size();
        System.out.println(countAfter);
        return this;
    }
    public ContactListScreen isListSizeOneLess(){
        Assert.assertEquals(countBefore-countAfter,1);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        while (contacts.size() > 0) {
//            AndroidElement contact = contacts.get(0);
//            Rectangle rect = contact.getRect();
//            int xA = rect.getX() + rect.getWidth() / 8;
//            int xB = rect.getX() + rect.getWidth() / 8 * 7;
//            int y = rect.getY() + rect.getHeight() / 2;
//
//            TouchAction<?> touchAction = new TouchAction<>(driver);
//            touchAction.longPress(PointOption.point(xA, y))
//                    .moveTo(PointOption.point(xB, y))
//                    .release().perform();
//            shouldWait(yesButton, 5);
//            yesButton.click();
//            pause(7);
            removeOneContact();
        }
        return this;

    }

    public ContactListScreen isContactAddedByName(String name) {
        checkContainsText(contactNamesList, name);
        return this;
    }

    public ContactListScreen isContactAddedByPhone(String phone) {
        checkContainsText(contactPhonesList, phone);
        return this;
    }
    public ContactListScreen isContactAddedByEmail(String email) {
        boolean isPresent=false;
            phone.click();
            if (emailEditField.getText().equals(email)) {
                isPresent = true;
        }
        driver.navigate().back();
        Assert.assertTrue(isPresent);
        return this;
    }
    public ContactListScreen isContactAddedByAddress(String address) {
        boolean isPresent=false;
            phone.click();
            if (addressEditField.getText().equals(address)) {
                isPresent = true;
            }
            driver.navigate().back();
        Assert.assertTrue(isPresent);
        return this;
    }
    public ContactListScreen isContactAddedByDescription(String description) {
        boolean isPresent=false;
            phone.click();
            if (descriptionEditField.getText().equals(description)) {
                isPresent = true;
            }
            driver.navigate().back();
        Assert.assertTrue(isPresent);
        return this;
    }

    private void checkContainsText(List<AndroidElement> list, String text) {
        pause(5);
        boolean isPresent = false;
        for (AndroidElement el : list) {
            //el.click();
            if (el.getText().contains(text)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
    }

    public boolean isNoContactsHerePresent() {
        return isShouldHave(emptyContacts, "No Contacts. Add One more!", 5);
    }

    public ContactListScreen assertNoContactsHerePresent() {
        Assert.assertTrue(isNoContactsHerePresent());
        return this;
    }


    public void contactsProvider() {

        if (contacts.size() < 4) {
            for (int i = 0; i < 3;i++){
                int random = new Random().nextInt(1000) + 1000;
                Contact contact = Contact.builder().name("Alice")
                        .lastname("Stepanovski"+random)
                        .email("alice" + random + "@mail.ru")
                        .phone("85947302" + random)
                        .address("Moscow").build();

                new ContactListScreen(driver).openContactForm()
                        .fillContactForm(contact)
                        .submitContactForm();
            }
        }
    }



    public AddNewContactScreen openContactForm() {
        if(isDisplayedWithExeption(plusButton)){
            plusButton.click();
        }
        return new AddNewContactScreen(driver);
    }


    public AuthenticationScreen logout() {
        if (driver.findElements(By.xpath("//*[@content-desc='More options']")).size() > 0) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout2() {
        // ,moreOptions.isEnabled(),moreOptions.isSelected()
        //this method can be called when the element in DOM and can be displayed,
        // otherwise the method will throw an NoSuchElementException
        if (moreOptions.isDisplayed()) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout3() {

        if (isDisplayedWithExeption(moreOptions)) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout4() {

        if (activityViewText.getText().equals("Contact list")) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }


    public ContactListScreen assertContactListActivityPresent() {
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }

    public boolean isContactListActivityPresent() {
        shouldWait(plusButton, 5);
        return isShouldHave(activityViewText, "Contact list", 5);
    }



}
