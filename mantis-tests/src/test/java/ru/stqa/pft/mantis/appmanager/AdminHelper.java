package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword(UserData selectedUser) {
    gotoManagePage();
    gotoManageUsers();
    selectUser(selectedUser.getId());
    submitResetPassword();
  }

  private void submitResetPassword() {
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }

  private void selectUser(int id) {
    click(By.xpath("//a[contains(@href, 'user_id=" + id + "' )]"));
  }

  private void gotoManageUsers() {
    //click(By.linkText("Управление пользователями"));
    click(By.xpath("//a[contains(@href, 'manage_user_page.php')]"));
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

  public void updatePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void logout() {
    click(By.xpath("//a[contains(@href, 'logout_page.php')]"));
  }
}
