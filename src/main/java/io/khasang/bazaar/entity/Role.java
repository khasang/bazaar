package io.khasang.bazaar.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean object describes a user role that regulates the rights of app's {@link User}
 *
 * @author Denis Tyurin
 * @date 08-10-2017
 * @version 1.1
 **/
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "role_name", unique = true)
    private String roleName;

    @Column(nullable = false, name = "role_id", unique = true)
    private Integer roleId;

    @Column(nullable = false, name = "isactive", columnDefinition = "INTEGER DEFAULT 1")
    private Integer isActive = 1;

    @Column(nullable = false, name = "connection_limit")
    private Integer connectionLimit = -1;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleID) {
        this.roleId = roleID;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isactive) {
        this.isActive = isactive;
    }

    public Integer getConnectionLimit() {
        return connectionLimit;
    }

    public void setConnectionLimit(Integer connectionLimit) {
        this.connectionLimit = connectionLimit;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}