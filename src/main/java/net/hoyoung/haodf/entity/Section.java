package net.hoyoung.haodf.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/7.
 */
@Entity
public class Section {
    private String secId;
    private String secName;
    private String url;
    private Date createTime;
    private Integer status;

    @Override
    public String toString() {
        return "Section{" +
                "secId='" + secId + '\'' +
                ", secName='" + secName + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    @Id
    @Column(name = "sec_id")
    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    @Basic
    @Column(name = "sec_name")
    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (secId != null ? !secId.equals(section.secId) : section.secId != null) return false;
        if (secName != null ? !secName.equals(section.secName) : section.secName != null) return false;
        if (url != null ? !url.equals(section.url) : section.url != null) return false;
        if (createTime != null ? !createTime.equals(section.createTime) : section.createTime != null) return false;
        if (status != null ? !status.equals(section.status) : section.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = secId != null ? secId.hashCode() : 0;
        result = 31 * result + (secName != null ? secName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
