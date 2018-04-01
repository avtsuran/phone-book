package com.lardi.model;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "contact")
public class Contact {

    private static final String NAME_MESSAGE = "*Your name must have at least 4 characters";
    private static final String PHONE_MESSAGE = "*Please enter the correct phone number, like +380560001100";
    private static final String PHONE_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    private static final String HOME_PHONE_PATTERN = "(^$|^\\+(?:[0-9] ?){6,14}[0-9]$)";
    private static final String EMAIL_MESSAGE = "*Please enter correct email";
    private static final String EMAIL_PATTERN = "(^$|^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "first_name")
    @Size(min = 4, message = NAME_MESSAGE)
    private String firstName;

    @Column(name = "second_name")
    @Size(min = 4, message = NAME_MESSAGE)
    private String secondName;

    @Column(name = "middle_name")
    @Size(min = 4, message = NAME_MESSAGE)
    private String middleName;

    @Column(name = "phone")
    @Pattern(regexp = PHONE_PATTERN, message = PHONE_MESSAGE)
    private String phone;

    @Column(name = "home_phone")
    @Pattern(regexp = HOME_PHONE_PATTERN, message = PHONE_MESSAGE)
    private String homePhone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    @Pattern(regexp = EMAIL_PATTERN, message = EMAIL_MESSAGE)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
