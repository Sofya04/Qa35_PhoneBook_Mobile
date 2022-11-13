import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+1000;
        new AuthenticationScreen(driver)
                .fillEmail("cat"+i+"@mail.com")
                .fillPassword("Ccat12345!")
                .submitRegistration()
                .assertContactListActivityPresent()
                .logout();


    }
    @Test
    public void registrationNegativeWrongEmail(){

        new AuthenticationScreen(driver)
                .fillEmail("negamail.com")
                .fillPassword("Ww12345!")
                .submitRegistrationNegative()
                .isErorrMessageContaisText("must be a well-formed email address");

    }

    @Test
    public void registrationNegativeWrongPassword(){

        new AuthenticationScreen(driver)
                .registrationUnsuccessful(Auth.builder().email("natalie@mail.com").password("Nn123").build())
                .isErorrMessageContaisTextInAlert("At least 8 character");
    }

    @Test
    public void registrationSuccessModel(){
        int i = new Random().nextInt(1000)+1000;
        new AuthenticationScreen(driver)
                .registration(Auth.builder().email("natalie"+i+"@mail.com").password("Nn12345$").build())
                .assertContactListActivityPresent()
                .logout();
    }
}
