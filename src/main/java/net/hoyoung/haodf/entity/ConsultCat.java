package net.hoyoung.haodf.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/9.
 */
@Entity
@Table(name = "consult_cat", schema = "", catalog = "haodf")
public class ConsultCat {
    private String conCatId;
    private String conCatName;
    private String url;
    private Date createTime;
    private Integer status;
    private String superCatName;

    @Id
    @Column(name = "con_cat_id")
    public String getConCatId() {
        return conCatId;
    }

    public void setConCatId(String conCatId) {
        this.conCatId = conCatId;
    }

    @Override
    public String toString() {
        return "ConsultCat{" +
                "conCatId='" + conCatId + '\'' +
                ", conCatName='" + conCatName + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", superCatName='" + superCatName + '\'' +
                '}';
    }

    @Basic
    @Column(name = "con_cat_name")
    public String getConCatName() {
        return conCatName;
    }

    public void setConCatName(String conCatName) {
        this.conCatName = conCatName;
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

        ConsultCat that = (ConsultCat) o;

        if (conCatId != null ? !conCatId.equals(that.conCatId) : that.conCatId != null) return false;
        if (conCatName != null ? !conCatName.equals(that.conCatName) : that.conCatName != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conCatId != null ? conCatId.hashCode() : 0;
        result = 31 * result + (conCatName != null ? conCatName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "super_cat_name")
    public String getSuperCatName() {
        return superCatName;
    }

    public void setSuperCatName(String superCatName) {
        this.superCatName = superCatName;
    }
}
