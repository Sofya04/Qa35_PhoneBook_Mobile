import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class LoginTests extends AppiumConfig {
    @Test
    public void loginSuccess()  {
//        boolean res =new SplashScreen(driver)
//                .checkVersion("1.0.0") // AuthenticationScreen

        boolean res = new AuthenticationScreen(driver)
                .fillEmail("noa@gmail.com") //AuthenticationScreen
                .fillPassword("Nnoa12345$")  //AuthenticationScreen
                .submitLogin()//ContactListScreen
                .isContactListActivityPresent();
        Assert.assertTrue(res);


    }
    @Test
    public void loginSuccessModel(){
//        boolean res  = new SplashScreen(driver)
//                .checkVersion("1.0.0")
        boolean res = new AuthenticationScreen(driver)
                .login(Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);

    }
    @Test
    public void loginNegativeWrongEmail(){
        new AuthenticationScreen(driver)
                .loginUnsuccessful(Auth.builder().email("noagmail.com").password("Nnoa12345$").build())
                .isErorrMessageContaisText("Login or Password incorrect");
        //   x@X
    }

    @AfterMethod
    public void logoutFromSys(){
        new ContactListScreen(driver)
                .logout3();
    }
}
