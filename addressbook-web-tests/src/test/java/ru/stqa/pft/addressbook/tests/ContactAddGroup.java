package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroup extends TestBase {

  public GroupData selectedGroup;

  @Test
  public void testContactAddGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    selectedGroup = groups.iterator().next();
    ContactData modifiedContact = contacts.iterator().next();
    Groups allGroupsForContactBefore = modifiedContact.getGroups();

    if (allGroupsForContactBefore.contains(selectedGroup)) {
      if (allGroupsForContactBefore.equals(groups)) {
        app.goTo().groupPage();
        selectedGroup = new GroupData().withName("test55").withHeader("header55").withFooter("footer55");
        app.group().create(selectedGroup);
        app.goTo().mainPage();
      } else {
         selectedGroup = groups.stream().filter((g) -> !allGroupsForContactBefore.contains(g)).findFirst().get();
       }

      app.contact().addGroupToContact(modifiedContact, selectedGroup);
      Groups allGroupsForContactAfter = modifiedContact.getGroups();
      assertThat(allGroupsForContactAfter.size(), equalTo(allGroupsForContactBefore.size() + 1));
      Assert.assertTrue(allGroupsForContactAfter.contains(selectedGroup));
    }
  }
}
