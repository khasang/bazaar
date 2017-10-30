package io.khasang.bazaar.entity;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents a User (dummy object with two fields: login and password)
 *
 * @author Denis Tyurin
 * @version 1.0
 * @date 11-10-2017
 **/
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", unique = true, length = 30)
    private String login;

    @Column(name = "password", length = 20)
    private String password;

    @Transient
    transient private String confirmPassword;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "role", nullable = false)
//    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String userName) {
        this.login = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

