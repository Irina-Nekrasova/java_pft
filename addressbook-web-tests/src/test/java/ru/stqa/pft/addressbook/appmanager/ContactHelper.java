package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillNewContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
  }

  public void gotoNewContactPage() {
    click(By.linkText("add new"));
  }

  public void initContactModification(int id) {
    wd.findElement(By.xpath("//a[contains(@href, 'id=" + id + "' )]//img[@alt='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean creation) {
    gotoNewContactPage();
    fillNewContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillNewContactForm(contact, false);
    contactCache = null;
    submitContactModification();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(lastname).withAddress(address)
              .withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String laststname = wd.findElement(By.name("lastname")).getAttribute("value");
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(laststname)
            .withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobile(mobile).withWorkPhone(work);
  }

  public void addGroupToContact(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupForContact(group.getId());
    submitAddingGroup();
  }

  private void submitAddingGroup() {
    click(By.name("add"));
  }

  private void selectGroupForContact(int groupId) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue("" + groupId);
  }

  public void removeContactFromSelectedGroup(ContactData contact, GroupData group) {
    selectGroupForFilter(group.getId());
    selectContactById(contact.getId());
    submitDeletingGroup();
  }

  private void selectGroupForFilter(int groupId) {
    new Select(wd.findElement(By.name("group"))).selectByValue("" + groupId);
  }

  private void submitDeletingGroup() {
    click(By.name("remove"));
  }
}
