package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    List<ContactData> before=app.contact().list();
    ContactData contact = new ContactData("Irina", "Nekras", "Sankt-Peter", "89213336677", "true@mail.ru", "test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after=app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(after, before);

  }

}
