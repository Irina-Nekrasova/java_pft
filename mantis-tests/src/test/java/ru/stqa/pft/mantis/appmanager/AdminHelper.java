package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUser(UserData selectedUser) {
    gotoManagePage();
    gotoManageUsers();
   // selectUser(selectedUser.getId());

  }

  private void gotoManageUsers() {
    click(By.linkText("Управление пользователями"));
  }

  private void gotoManagePage() {
    click(By.xpath("//a[contains(@href, 'manage_overview_page.php')]"));
   }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.xpath("//input[@value='Войти']"));
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Войти']"));
  }


}
