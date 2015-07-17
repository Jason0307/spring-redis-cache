package org.zhubao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int userId;
    @Column(length = 32, nullable = false)
    private String username;
    @Column(length = 32, nullable = false)
    private String emailAddress;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 128, nullable = false)
    private String avatar;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateLastLogin;
}
