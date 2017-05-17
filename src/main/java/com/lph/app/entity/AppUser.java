package com.lph.app.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_USER")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String ssoId;

    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Size(min=1,max=10)
    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Size(min=1,max=10)
    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Email
    @NotEmpty
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    /*
     * @ManyToMany表示用户和用户配置之间有多到多关系。 一个用户可以有多个资料[USER，ADMIN，DBA]用户资料信息可以属于多个用户。
     * 
     * 要特别注意fetch = FetchType.LAZY。在这里，我们通知 Hibernate 懒加载用户资料集合。
     * 这也是默认的行为。在此设置中，首先访问仅当查询加载集合将被触发。这是一个很好的方式，以避免加载-这是一个昂贵的操作所有连接的对象。
     * 当在事务/活动会话，并会尝试访问集合，Hibernate会触发不同的选择来获取它们。
     * 
     * 但是，如果您不在活动的会话(会话关闭/无事务：如在JSP)，并试图访问集合，你会遇到错误:org.hibernate.
     * LazyInitializationException – could not initialize proxy – no Session.
     * 为了避免它，需要通过调用 Hibernate.initialize(user.getUserProfiles()); 来初始化对需要的集合;
     * 在有效会话中[在DAO方法，在显示视图之前，可以调用这个初始化方法]。
     * 
     * 如果是*Many* 关联它总是覆盖 hashCode 和 equals 方法，它是通过 Hibernate 持有合并形成集合。
     */
    @JoinTable(name = "APP_USER_USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
	    @JoinColumn(name = "USER_PROFILE_ID") })
    /*
     * 中间表
     * @JoinTable表示它使用两个表的表外键链接来约束自己的主键。这个注解，主要用于关系的拥有方
     * joinColumns是指拥有方(用户ID)的列名，inverseJoinColumns是指关系的反向端(USER_PROFILE的ID)的列。
     * 这个连接表的主键是USER_ID & USER_PROFILE_ID 组合。
     */
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getSsoId() {
	return ssoId;
    }

    public void setSsoId(String ssoId) {
	this.ssoId = ssoId;
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

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Set<UserProfile> getUserProfiles() {
	return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
	this.userProfiles = userProfiles;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof User))
	    return false;
	AppUser other = (AppUser) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (ssoId == null) {
	    if (other.ssoId != null)
		return false;
	} else if (!ssoId.equals(other.ssoId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password + ", firstName=" + firstName
		+ ", lastName=" + lastName + ", email=" + email + "]";
    }

}