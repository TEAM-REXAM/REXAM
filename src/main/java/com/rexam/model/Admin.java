package com.rexam.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "email")
public class Admin extends User {

    /**
     * 
     */
    private static final long serialVersionUID = -625767430528307665L;

}
