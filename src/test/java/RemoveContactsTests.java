import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactsTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("sonka04@gmail.com").password("Sonka04$").build())
                .isContactListActivityPresent();
    }
    @BeforeMethod
    public void checkAmountOfContacts(){
        new ContactListScreen(driver).contactsProvider();
    }

    @Test
    public void removeOneContactSuccess(){
        new ContactListScreen(driver).removeOneContact()
                .isContactListActivityPresent();

    }

    @Test
    public void removeAllContacts(){
        new ContactListScreen(driver).removeAllContacts()
                .assertNoContactsHerePresent();
    }
}
