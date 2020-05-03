package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="mantis_user_table")
public class UserData {
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name="username")
  private String username;

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public String getEmail() {
    return email;
  }

  @Expose
  @Column(name="email")
  private String email;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(username, userData.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username);
  }

}
