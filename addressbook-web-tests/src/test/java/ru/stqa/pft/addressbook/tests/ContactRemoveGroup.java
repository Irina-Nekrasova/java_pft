package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveGroup extends TestBase {

  public ContactData modifiedContact;

  @BeforeMethod
  public void insurePreCondition() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test 11"));
      }
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstname("Irina").withLastname("Nekras").withAddress("Sankt-Peter")
              .withMobile("89213336677").withEmail("true@mail.ru")
              .inGroup(groups.iterator().next()), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactRemoveGroup () {

    GroupData selectedGroup = app.db().groups().iterator().next();
    modifiedContact = app.db().contacts().iterator().next();
    Groups allGroupsForContactBefore = modifiedContact.getGroups();

    if (selectedGroup.getContacts().contains(modifiedContact)) {
      app.contact().removeContactFromSelectedGroup(modifiedContact, selectedGroup);
      app.goTo().mainPage();
      Groups allGroupsForContactAfter = app.db().contacts().stream().filter((c) -> c.getId() == modifiedContact.getId()).findFirst().get().getGroups();
      assertThat(allGroupsForContactAfter.size(), equalTo(allGroupsForContactBefore.size() - 1));
      Assert.assertFalse(allGroupsForContactAfter.contains(selectedGroup));
    } else if (selectedGroup.getContacts().size() == 0) {
      ContactData newContact = new ContactData()
              .withFirstname("Irina").withLastname("Nekras").withAddress("Sankt-Peter")
              .withMobile("89213336677").withEmail("true@mail.ru")
              .inGroup(selectedGroup);
      app.contact().create(newContact, true);
      app.goTo().mainPage();
      modifiedContact = newContact.withId(app.db().contacts().stream().mapToInt((g) -> g.getId()).max().getAsInt());
      app.contact().removeContactFromSelectedGroup(modifiedContact, selectedGroup);
      Groups allGroupsForContactAfter = app.db().contacts().stream().filter((c) -> c.getId() == modifiedContact.getId()).findFirst().get().getGroups();
      Assert.assertFalse(allGroupsForContactAfter.contains(selectedGroup));
    } else {
      app.contact().addGroupToContact(modifiedContact, selectedGroup);
      app.goTo().mainPage();
      app.contact().removeContactFromSelectedGroup(modifiedContact, selectedGroup);
      Groups allGroupsForContactAfter = app.db().contacts().stream().filter((c) -> c.getId() == modifiedContact.getId()).findFirst().get().getGroups();
      assertThat(allGroupsForContactAfter.size(), equalTo(allGroupsForContactBefore.size()));
      Assert.assertFalse(allGroupsForContactAfter.contains(selectedGroup));
    }
  }
  }


