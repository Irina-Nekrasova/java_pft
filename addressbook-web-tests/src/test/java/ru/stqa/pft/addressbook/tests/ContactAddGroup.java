package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddGroup extends TestBase {

  @Test
  public void testContactAddGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    //GroupData selectedGroup = groups.iterator().next();
    //ContactData modifiedContact = contacts.iterator().next();
    app.contact().addGroupToContact(contacts.iterator().next(), groups.iterator().next());

  }
}
