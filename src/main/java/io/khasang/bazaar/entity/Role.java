package io.khasang.bazaar.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    @Column(nullable = false, name = "role_name")
    private String roleName;

    @Column(nullable = false, name = "role_id")
    private Integer roleID;

    @Column(nullable = false, name = "superuser", columnDefinition = "boolean DEFAULT false")
    private Boolean superuser = false;

    @Column(nullable = false, name = "isactive", columnDefinition = "boolean DEFAULT true")
    private Boolean isactive = true;

    @Column(nullable = false)
    private Integer connectionLimit = -1;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    //TODO если отдельно завести полномочия, то как их хранить?
    //TODO списком? Или энумом?


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

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Boolean getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Boolean superuser) {
        this.superuser = superuser;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (!getId().equals(role.getId())) return false;
        if (!getRoleName().equals(role.getRoleName())) return false;
        if (!getRoleID().equals(role.getRoleID())) return false;
        if (!getSuperuser().equals(role.getSuperuser())) return false;
        if (!getIsactive().equals(role.getIsactive())) return false;
        if (!getConnectionLimit().equals(role.getConnectionLimit())) return false;
        return getUsers() != null ? getUsers().equals(role.getUsers()) : role.getUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getRoleName().hashCode();
        result = 31 * result + getRoleID().hashCode();
        result = 31 * result + getSuperuser().hashCode();
        result = 31 * result + getIsactive().hashCode();
        result = 31 * result + getConnectionLimit().hashCode();
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleID=" + roleID +
                ", superuser=" + superuser +
                ", isactive=" + isactive +
                ", connectionLimit=" + connectionLimit +
                ", users=" + users +
                '}';
    }
}