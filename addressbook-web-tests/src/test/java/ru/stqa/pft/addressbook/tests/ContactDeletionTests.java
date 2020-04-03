package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

  @Test
    public void testContactDeletion () {
      if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Irina", "Nekras", "Sankt-Peter", "89213336677", "true@mail.ru", "test1"), true);
      app.getNavigationHelper().returnToHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().deleteSelectedContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);

  }

}
