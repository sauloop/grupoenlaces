package com.pablogiraldo.grupoenlaces.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	@Length(min = 5)
	@NotEmpty
	private String username;

	@Column(name = "password", nullable = false)
	@Length(min = 5)
	@NotEmpty
	@JsonIgnore
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	public User() {
	}

	public User(
			@Length(min = 5, message = "*Tu nombre de usuario debe tener al menos 5 letras") @NotEmpty(message = "*Por favor introduce tu nombre") String username,
			@Length(min = 5, message = "*Tu contraseña debe tener al menos 5 letras") @NotEmpty(message = "*Por favor introduce tu contraseña") String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	@Override
	public int compareTo(User compareuser) {
		Long compareids = ((User) compareuser).getId();
		return (int) (compareids - this.id);
	}
}
