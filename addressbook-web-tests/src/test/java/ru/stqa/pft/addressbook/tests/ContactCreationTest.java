package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before=app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Irina").withLastname("Nekras").withAddress("Sankt-Peter").withMobile("89213336677").withEmail("true@mail.ru").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Set<ContactData> after=app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }

}
