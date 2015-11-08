package net.hoyoung.haodf.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/8.
 */
@Entity
public class Doctor {
    private String docId;
    private String docName;
    private String homeUrl;
    private String infoUrl;
    private Integer status;
    private Date createTime;

    @Override
    public String toString() {
        return "Doctor{" +
                "docId='" + docId + '\'' +
                ", docName='" + docName + '\'' +
                ", homeUrl='" + homeUrl + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    @Id
    @Column(name = "doc_id")
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "doc_name")
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Basic
    @Column(name = "home_url")
    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    @Basic
    @Column(name = "info_url")
    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (docId != null ? !docId.equals(doctor.docId) : doctor.docId != null) return false;
        if (docName != null ? !docName.equals(doctor.docName) : doctor.docName != null) return false;
        if (homeUrl != null ? !homeUrl.equals(doctor.homeUrl) : doctor.homeUrl != null) return false;
        if (infoUrl != null ? !infoUrl.equals(doctor.infoUrl) : doctor.infoUrl != null) return false;
        if (status != null ? !status.equals(doctor.status) : doctor.status != null) return false;
        if (createTime != null ? !createTime.equals(doctor.createTime) : doctor.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = docId != null ? docId.hashCode() : 0;
        result = 31 * result + (docName != null ? docName.hashCode() : 0);
        result = 31 * result + (homeUrl != null ? homeUrl.hashCode() : 0);
        result = 31 * result + (infoUrl != null ? infoUrl.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
