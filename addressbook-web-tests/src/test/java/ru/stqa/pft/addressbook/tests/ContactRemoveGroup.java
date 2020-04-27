package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveGroup extends TestBase {

  @Test
  public void testContactRemoveGroup () {

    Groups groups = app.db().groups();
    GroupData selectedGroup = groups.iterator().next();
    ContactData modifiedContact = app.db().contacts().iterator().next();
    //Contacts allContactsForGroupBefore = selectedGroup.getContacts();
    //Groups allGroupsForContactBefore = modifiedContact.getGroups();

    app.contact().removeContactFromSelectedGroup(modifiedContact, selectedGroup);

  }
}
