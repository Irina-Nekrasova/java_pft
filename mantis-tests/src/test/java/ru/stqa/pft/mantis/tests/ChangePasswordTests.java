package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePasword() {
    Users users = app.db().users();
    UserData selectedUser = users.iterator().next();

  }

}
