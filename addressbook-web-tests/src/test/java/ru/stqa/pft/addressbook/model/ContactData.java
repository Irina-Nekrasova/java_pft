package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")
public class ContactData {
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name="firstname")
  private String firstname;

  @Expose
  @Column(name="lastname")
  private String lastname;

  @Expose
  @Column(name="address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name="home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name="mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name="work")
  @Type(type = "text")
  private String workPhone;

  @Expose
  @Column(name="email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name="email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name="email3")
  @Type(type = "text")
  private String email3;

  @Expose
  @Transient
  private String group;

  @Expose
  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Column(name="photo")
  @Type(type = "text")
  @Transient
  //private String photo;
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, address, mobilePhone, email);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
