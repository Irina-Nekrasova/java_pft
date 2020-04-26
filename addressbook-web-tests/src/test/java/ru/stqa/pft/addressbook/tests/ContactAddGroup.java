package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroup extends TestBase {


  @Test
  public void testContactAddGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    GroupData selectedGroup = groups.iterator().next();
    ContactData modifiedContact = contacts.iterator().next();
    Groups allGroupsForContactBefore = modifiedContact.getGroups();

    if (! allGroupsForContactBefore.contains(selectedGroup) ) {
      app.contact().addGroupToContact(modifiedContact, selectedGroup);
      Groups allGroupsForContactAfter = modifiedContact.getGroups();
      assertThat(allGroupsForContactAfter.size(), equalTo(allGroupsForContactBefore.size() + 1));
      Assert.assertTrue(allGroupsForContactAfter.contains(selectedGroup));
    } else  if (!allGroupsForContactBefore.equals(groups)) {
      List<Gr> Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> !s.equals(""))
              .map(ContactPhoneTest::cleaned)
              .collect(Collectors.joining("\n"));


    }
  }
}
