package org.zhubao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(length = 128, nullable = false)
    private String title;
    @Column(length = 10000, nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
    @Column(nullable = false)
    private Date dateCreated;
    private Date dateUpdated;

}
