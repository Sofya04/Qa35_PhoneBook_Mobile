import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DatePickerTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("sonka04@gmail.com").password("Sonka04$").build());
    }

    @Test
    public void selectDateInCurrentMonth(){
        new ContactListScreen(driver)
                .openDatePickerScreen()
                .openCalendar()
                .selectDate("30 December 2022")
                .isDateChanged("30 December 2022");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout3();
    }
}
