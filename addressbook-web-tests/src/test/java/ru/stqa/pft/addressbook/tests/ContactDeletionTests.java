package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePreCondition () {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Irina").withLastname("Nekras").withAddress("Sankt-Peter")
              .withMobile("89213336677").withEmail("true@mail.ru").withGroup("test1"), true);
      app.goTo().homePage();
    }
  }

  @Test
    public void testContactDeletion () {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(after, before);
  }

}
