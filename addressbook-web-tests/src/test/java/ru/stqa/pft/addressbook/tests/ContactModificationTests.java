package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePreCondition() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Irina").withLastname("Nekras").withAddress("Sankt-Peter")
              .withMobile("89213336677").withEmail("true@mail.ru").withGroup("test1"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Arina").withLastname("Nekrasva")
            .withAddress("Sankt-Peterburg").withMobile("89223336677").withEmail("true@mail.ru");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after =app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
