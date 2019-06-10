package com.mohyehia.bookstore.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name = "users")
public class ApiUser implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "EMAIL")
	@NotEmpty(message = "Email address can not be empty")
	private String email;
	
	@Column(name = "FIRST_NAME")
	@NotEmpty
	private String firstName;
	
	@Column(name = "LAST_NAME")
	@NotEmpty
	private String lastName;
	
	@Column(name = "PASSWORD")
	@NotEmpty(message = "Password can not be empty")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "PHONE")
	@NotEmpty
	private String phone;
	
	@Column(name = "ENABLED")
	private boolean enabled;
	
	@Column(name = "CREATED")
	private Date created;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLES", 
		joinColumns = { @JoinColumn(name = "USER_ID") },
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;
	
	public ApiUser() {
		this.enabled = true;
		this.created = new Date();
	}
	
	public ApiUser(@NotEmpty String email, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String password, @NotEmpty String phone) {
		this();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phone = phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		roles.forEach(role -> authorities.add(new Authority(role.getName())));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
