import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactsTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("sonka04@gmail.com").password("Sonka04$").build());
    }

    @Test
    public void addNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("Simon" + i)
                .lastname("Wow")
                .email("simon" + i + "@mail.com")
                .phone("12345678"+i)
                .address("Haifa")
                .description("My best friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName())
                .isContactAddedByPhone(contact.getPhone());
    }

    @Test
    public void addNewContactRequiredFields(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder().name("Luiza"+i)
                .lastname("Manko")
                .email("luiza"+i+"@gmail.com")
                .phone("7463920"+i)
                .address("Rehovot")
                .build();
        new ContactListScreen(driver).openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName())
                .isContactAddedByPhone(contact.getPhone());
    }

    @Test
    public void addNewContactNegativeEmailWithoutSpecialSymbol(){
        Contact contact = Contact.builder().name("Leonid")
                .lastname("Manko")
                .email("leonidmail.com")
                .phone("7463920753")
                .address("Rehovot")
                .build();
        new ContactListScreen(driver).openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErorrMessageContaisText("must be a well-formed email address");
    }
    @Test
    public void addNewContactNegativeEmailWithLackOfSymbols(){
        Contact contact = Contact.builder().name("Leonardo")
                .lastname("Dicaprio")
                .email("l@")
                .phone("74639207536")
                .address("Los Angeles")
                .build();
        new ContactListScreen(driver).openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErorrMessageContaisText("must be a well-formed email address");
    }
    @Test
    public void addNewContactWrongPhoneLackOfSymbols(){
        Contact contact = Contact.builder().name("Leonid")
                .lastname("Manko")
                .email("leonid@mail.ru")
                .phone("647589047")
                .address("Rehovot")
                .build();
        new ContactListScreen(driver).openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErorrMessageContaisText("Phone number must contain only digits!");
    }
    @Test
    public void addNewContactWrongPhoneBigAmountOfSymbols(){
        Contact contact = Contact.builder().name("Leonid")
                .lastname("Manko")
                .email("leonid@mail.ru")
                .phone("6475890478694630")
                .address("Rehovot")
                .build();
        new ContactListScreen(driver).openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErorrMessageContaisText("Phone number must contain only digits!");
    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }
}
