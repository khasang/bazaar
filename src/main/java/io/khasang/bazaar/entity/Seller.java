package io.khasang.bazaar.entity;

import javax.persistence.*;

/**
 * This class describes a database entity that represents a seller.
 *
 * @author Zulfia Garifullina
 * @date 11.10.2017.
 */
@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    private Integer balance;

    @Column(name = "role_id")
    private Long roleId;

    public Seller() {
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

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seller seller = (Seller) o;

        if (id != null ? !id.equals(seller.id) : seller.id != null) return false;
        if (login != null ? !login.equals(seller.login) : seller.login != null) return false;
        if (balance != null ? !balance.equals(seller.balance) : seller.balance != null) return false;
        return roleId != null ? roleId.equals(seller.roleId) : seller.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}