package com.bplow.netconn.aom.dao.entity;

import java.util.Date;

public class TbWebServer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.id
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.host_id
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private String hostId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.server_name
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private String serverName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.web_path
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private String webPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.war_name
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private String warName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.server_status
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private String serverStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.gmt_create
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_web_server.gmt_modify
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    private Date gmtModify;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.id
     *
     * @return the value of tb_web_server.id
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.id
     *
     * @param id the value for tb_web_server.id
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.host_id
     *
     * @return the value of tb_web_server.host_id
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.host_id
     *
     * @param hostId the value for tb_web_server.host_id
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.server_name
     *
     * @return the value of tb_web_server.server_name
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.server_name
     *
     * @param serverName the value for tb_web_server.server_name
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.web_path
     *
     * @return the value of tb_web_server.web_path
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getWebPath() {
        return webPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.web_path
     *
     * @param webPath the value for tb_web_server.web_path
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setWebPath(String webPath) {
        this.webPath = webPath == null ? null : webPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.war_name
     *
     * @return the value of tb_web_server.war_name
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getWarName() {
        return warName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.war_name
     *
     * @param warName the value for tb_web_server.war_name
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setWarName(String warName) {
        this.warName = warName == null ? null : warName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.server_status
     *
     * @return the value of tb_web_server.server_status
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getServerStatus() {
        return serverStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.server_status
     *
     * @param serverStatus the value for tb_web_server.server_status
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus == null ? null : serverStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.gmt_create
     *
     * @return the value of tb_web_server.gmt_create
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.gmt_create
     *
     * @param gmtCreate the value for tb_web_server.gmt_create
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_web_server.gmt_modify
     *
     * @return the value of tb_web_server.gmt_modify
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_web_server.gmt_modify
     *
     * @param gmtModify the value for tb_web_server.gmt_modify
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbWebServer other = (TbWebServer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getHostId() == null ? other.getHostId() == null : this.getHostId().equals(other.getHostId()))
            && (this.getServerName() == null ? other.getServerName() == null : this.getServerName().equals(other.getServerName()))
            && (this.getWebPath() == null ? other.getWebPath() == null : this.getWebPath().equals(other.getWebPath()))
            && (this.getWarName() == null ? other.getWarName() == null : this.getWarName().equals(other.getWarName()))
            && (this.getServerStatus() == null ? other.getServerStatus() == null : this.getServerStatus().equals(other.getServerStatus()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModify() == null ? other.getGmtModify() == null : this.getGmtModify().equals(other.getGmtModify()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getHostId() == null) ? 0 : getHostId().hashCode());
        result = prime * result + ((getServerName() == null) ? 0 : getServerName().hashCode());
        result = prime * result + ((getWebPath() == null) ? 0 : getWebPath().hashCode());
        result = prime * result + ((getWarName() == null) ? 0 : getWarName().hashCode());
        result = prime * result + ((getServerStatus() == null) ? 0 : getServerStatus().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModify() == null) ? 0 : getGmtModify().hashCode());
        return result;
    }
}