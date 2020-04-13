package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void insurePreCondition () {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Irina").withLastname("Nekras").withAddress("Sankt-Peter")
              .withMobile("89213336677").withEmail("true@mail.ru").withGroup("test1"), true);
      app.goTo().homePage();
    }
  }

  public void testPhoneInformation() {



  }
}
