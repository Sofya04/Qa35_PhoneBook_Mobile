import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class EditContactsTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("sonka04@gmail.com").password("Sonka04$").build());
    }

    @Test
    public void editNameOnlySuccess(){
        new ContactListScreen(driver)
                .openEditForm()
                .editNameOnly("WWWww")
                .updateChanges()
                .isContactAddedByName("WWWww");
    }

    @Test
    public void editPhoneOnlySuccess(){
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("phone", "00094454536")
                .updateChanges()
                .isContactAddedByPhone("00094454536");
    }
    @Test
    public void editEmailOnlySuccess(){
        int i = new Random().nextInt(1000)+1000;
        String email = "liza"+i+"@yandex.ru";
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("email", email)
                .updateChanges()
                .isContactAddedByEmail(email);
    }
    @Test
    public void editAddressOnlySuccess(){
        int i = new Random().nextInt(1000)+1000;
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("address", "Haifa")
                .updateChanges()
                .isContactAddedByAddress("Haifa");
    }
    @Test
    public void editDescriptionOnlySuccess(){
        int i = new Random().nextInt(1000)+1000;
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("description", "Colleague")
                .updateChanges()
                .isContactAddedByDescription("Colleague");
    }
    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout3();
    }
}
