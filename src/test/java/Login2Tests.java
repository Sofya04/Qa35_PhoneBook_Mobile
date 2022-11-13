import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class Login2Tests extends AppiumConfig {
    @Test
    public void loginSuccess()  {

        new AuthenticationScreen(driver)
                .fillEmail("sonka04@gmail.com") //AuthenticationScreen
                .fillPassword("Sonka04$")  //AuthenticationScreen
                .submitLogin()//ContactListScreen
                .assertContactListActivityPresent()
                .logout();

    }
    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("sonka04@gmail.com").password("Sonka04$").build())
                .assertContactListActivityPresent()
                .logout();

    }
}
