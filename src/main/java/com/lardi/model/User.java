package com.lardi.model;

import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	private static final String NAME_MESSAGE = "*Your name must have at least 5 characters";
	private static final String LOGIN_MESSAGE = "*Your login must have at least 3 characters";
	private static final String LOGIN_PATTERN = "[A-Za-z]+";
	private static final String LOGIN_PATTERN_MESSAGE = "*Please use only letters";
	private static final String	PASSWORD_MESSAGE = "*Your password must have at least 5 characters";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "login")
	@Size(min = 3, message = LOGIN_MESSAGE)
	@Pattern(regexp = LOGIN_PATTERN, message = LOGIN_PATTERN_MESSAGE)
	private String login;

	@Column(name = "password")
	@Size(min = 5, message = PASSWORD_MESSAGE)
	@Transient
	private String password;

	@Column(name = "first_name")
	@Size(min = 5, message = NAME_MESSAGE)
	private String firstName;

	@Column(name = "second_name")
	@Size(min = 5, message = NAME_MESSAGE)
	private String secondName;

	@Column(name = "middle_name")
	@Size(min = 5, message = NAME_MESSAGE)
	private String middleName;

	@Column(name = "active")
	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
