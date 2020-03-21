package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.gotoNewContactPage();
    app.fillNewContactForm(new ContactData("Irina", "Nekras", "Sankt-Peter", "89213336677", "true@mail.ru"));
    app.submitContactCreation();
    app.returnToHomePage();

  }

}
