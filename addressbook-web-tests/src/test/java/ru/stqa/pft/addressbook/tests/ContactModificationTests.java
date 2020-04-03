package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification () {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Irina", "Nekras", "Sankt-Peter", "89213336677", "true@mail.ru", "test1"), true);
      app.getNavigationHelper().returnToHomePage();
      before += 1;
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new ContactData("Irina", "Nekrasova", "Sankt-Peter", "89213336677", "true@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }

}
