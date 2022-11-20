package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerScreen extends BaseScreen{
    public DatePickerScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy(id = "com.sheygam.contactapp:id/dateTxt")
    AndroidElement dateTextView;
    @FindBy(id = "com.sheygam.contactapp:id/dateBtn")
    AndroidElement changeDateButton;
    @FindBy(id = "android:id/date_picker_header_date")
    AndroidElement headerDateView;
    @FindBy(id = "android:id/button1")
    AndroidElement okButton;
    @FindBy(xpath = "//*[@content-desc = '15 December 2022']")
    AndroidElement currentDate;
    public DatePickerScreen openCalendar(){
        shouldHave(activityViewText,"Date picker example",5);
        changeDateButton.click();
        return this;
    }
    public DatePickerScreen selectDate(String date){
        String locator = String.format("//*[@content-desc='%s']",date);
        shouldWait(currentDate,5);
        driver.findElement(By.xpath(locator)).click();
        okButton.click();
        return this;
    }
    public DatePickerScreen isDateChanged(String date){
        String currentDate = dateTextView.getText();
        LocalDate dateTest = LocalDate.parse(currentDate,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(date);

        LocalDate dateElement = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy"));

        Assert.assertEquals(dateTest,dateElement);
        return this;
    }
}
