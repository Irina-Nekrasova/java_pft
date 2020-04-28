package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveGroupTests extends TestBase {

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
  public void testContactRemoveGroup() {

    GroupData selectedGroup = app.db().groups().iterator().next();
    modifiedContact = app.db().contacts().iterator().next();

    if (!selectedGroup.getContacts().contains(modifiedContact)) {
         app.contact().addGroupToContact(modifiedContact, selectedGroup);
    }
    app.goTo().mainPage();
    app.contact().removeContactFromSelectedGroup(modifiedContact, selectedGroup);
    Groups allGroupsForContactAfter = app.db().contacts().stream().filter((c) -> c.getId() == modifiedContact.getId()).findFirst().get().getGroups();
    Assert.assertFalse(allGroupsForContactAfter.contains(selectedGroup));
  }
}


