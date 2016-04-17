package com.bplow.netconn.systemmng.dao.entity;

// default package
// Generated 2015-11-29 9:44:02 by Hibernate Tools 3.6.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysOrganizeUserRel generated by hbm2java
 */
@Entity
@Table(name="sys_organize_user_rel"
    ,catalog="ads"
)
public class SysOrganizeUserRel  implements java.io.Serializable {


     private Integer id;
     private String userId;
     private String organizeId;

    public SysOrganizeUserRel() {
    }

    public SysOrganizeUserRel(String userId, String organizeId) {
       this.userId = userId;
       this.organizeId = organizeId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="user_id", length=32)
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    @Column(name="organize_id", length=32)
    public String getOrganizeId() {
        return this.organizeId;
    }
    
    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }




}

