package net.hoyoung.haodf.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/9.
 */
@Entity
public class Duihua {
    private int duiId;
    private String wenId;
    private Date duiTime;
    private Date createTime;
    private String docHomeUrl;
    private Integer status;
    private String docId;
    private String patId;
    private String content;
    private String duiType;
    @Basic
    @Column(name = "dui_type")
    public String getDuiType() {
        return duiType;
    }

    public void setDuiType(String duiType) {
        this.duiType = duiType;
    }

    @Override
    public String toString() {
        return "Duihua{" +
                "duiId=" + duiId +
                ", wenId='" + wenId + '\'' +
                ", duiTime=" + duiTime +
                ", createTime=" + createTime +
                ", docHomeUrl='" + docHomeUrl + '\'' +
                ", status=" + status +
                ", docId='" + docId + '\'' +
                ", patId='" + patId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Id
    @GeneratedValue
    @Column(name = "dui_id")
    public int getDuiId() {
        return duiId;
    }

    public void setDuiId(int duiId) {
        this.duiId = duiId;
    }

    @Basic
    @Column(name = "wen_id")
    public String getWenId() {
        return wenId;
    }

    public void setWenId(String wenId) {
        this.wenId = wenId;
    }

    @Basic
    @Column(name = "dui_time")
    public Date getDuiTime() {
        return duiTime;
    }

    public void setDuiTime(Date duiTime) {
        this.duiTime = duiTime;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "doc_home_url")
    public String getDocHomeUrl() {
        return docHomeUrl;
    }

    public void setDocHomeUrl(String docHomeUrl) {
        this.docHomeUrl = docHomeUrl;
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
    @Column(name = "doc_id")
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "pat_id")
    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duihua duihua = (Duihua) o;

        if (duiId != duihua.duiId) return false;
        if (wenId != null ? !wenId.equals(duihua.wenId) : duihua.wenId != null) return false;
        if (duiTime != null ? !duiTime.equals(duihua.duiTime) : duihua.duiTime != null) return false;
        if (createTime != null ? !createTime.equals(duihua.createTime) : duihua.createTime != null) return false;
        if (docHomeUrl != null ? !docHomeUrl.equals(duihua.docHomeUrl) : duihua.docHomeUrl != null) return false;
        if (status != null ? !status.equals(duihua.status) : duihua.status != null) return false;
        if (docId != null ? !docId.equals(duihua.docId) : duihua.docId != null) return false;
        if (patId != null ? !patId.equals(duihua.patId) : duihua.patId != null) return false;
        if (content != null ? !content.equals(duihua.content) : duihua.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = duiId;
        result = 31 * result + (wenId != null ? wenId.hashCode() : 0);
        result = 31 * result + (duiTime != null ? duiTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (docHomeUrl != null ? docHomeUrl.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (patId != null ? patId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
