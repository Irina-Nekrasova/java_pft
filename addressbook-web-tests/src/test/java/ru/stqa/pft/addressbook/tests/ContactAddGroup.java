package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroup extends TestBase {

  public GroupData selectedGroup;

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
  public void testContactAddGroup() {
    Groups groups = app.db().groups();
    selectedGroup = groups.iterator().next();
    ContactData modifiedContact = app.db().contacts().iterator().next();
    Groups allGroupsForContactBefore = modifiedContact.getGroups();


    if (allGroupsForContactBefore.contains(selectedGroup)) {
      if (allGroupsForContactBefore.equals(groups)) {
        app.goTo().groupPage();
        GroupData newGroup = new GroupData().withName("test55").withHeader("header55").withFooter("footer55");
        app.group().create(newGroup);
        selectedGroup = new GroupData().withId(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt())
                .withName(newGroup.getName()).withFooter(newGroup.getFooter()).withHeader(newGroup.getHeader());
        app.goTo().mainPage();
      } else {
        selectedGroup = groups.stream().filter((g) -> !allGroupsForContactBefore.contains(g)).findFirst().get();
      }
    }
    app.contact().addGroupToContact(modifiedContact, selectedGroup);
    Groups allGroupsForContactAfter = app.db().contacts().stream().filter((c) -> c.getId() == modifiedContact.getId()).findFirst().get().getGroups();
    assertThat(allGroupsForContactAfter.size(), equalTo(allGroupsForContactBefore.size() + 1));
    Assert.assertTrue(allGroupsForContactAfter.contains(selectedGroup));
  }
}

