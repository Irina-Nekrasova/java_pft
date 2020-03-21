package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    gotoNewContactPage();
    fillNewContactForm(new ContactData("Irina", "Nekras", "Sankt-Peter", "89213336677", "true@mail.ru"));
    submitContactCreation();
    returnToHomePage();

  }

}
