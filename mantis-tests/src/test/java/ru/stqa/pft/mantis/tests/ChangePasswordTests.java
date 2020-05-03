package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePasword() throws IOException {

    Users users = app.db().users();
    UserData selectedUser = users.iterator().next();
    app.admin().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

    app.admin().manageUser(selectedUser);

  }

}
