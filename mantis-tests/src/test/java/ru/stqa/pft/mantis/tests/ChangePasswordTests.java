package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() throws IOException, MessagingException {
    app.mail().start();
  }

  @Test
  public void testChangePasword() throws IOException, MessagingException, InterruptedException {
    long now = System.currentTimeMillis();
    UserData selectedUser = app.db().users().iterator().next();
    String email = selectedUser.getEmail();
    String password = "password" + now;
    app.admin().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.admin().resetPassword(selectedUser);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.admin().updatePassword(confirmationLink, password);

    HttpSession session = app.newSession();
    assertTrue(session.login(selectedUser.getUsername(), password));
    assertTrue(session.isLoggedInAs(selectedUser.getUsername()));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() throws IOException, MessagingException {
    app.mail().stop();
  }

}
