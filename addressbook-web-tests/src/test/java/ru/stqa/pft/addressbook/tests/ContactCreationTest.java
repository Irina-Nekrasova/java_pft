package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before=app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Irina", "Nekras", "Sankt-Peter", "89213336677", "true@mail.ru", "test1"), true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
