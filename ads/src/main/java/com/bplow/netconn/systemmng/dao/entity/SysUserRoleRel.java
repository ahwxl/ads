package com.bplow.netconn.systemmng.dao.entity;

// default package
// Generated 2015-11-29 9:44:02 by Hibernate Tools 3.6.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysUserRoleRel generated by hbm2java
 */
@Entity
@Table(name="sys_user_role_rel"
    ,catalog="ads"
)
public class SysUserRoleRel  implements java.io.Serializable {


     private long id;
     private String userId;
     private String roleId;

    public SysUserRoleRel() {
    }

	
    public SysUserRoleRel(long id) {
        this.id = id;
    }
    public SysUserRoleRel(long id, String userId, String roleId) {
       this.id = id;
       this.userId = userId;
       this.roleId = roleId;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    
    @Column(name="user_id", length=32)
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    @Column(name="role_id", length=32)
    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }




}


