package net.hoyoung.haodf.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/8.
 */
@Entity
@Table(name = "disease_cat", schema = "", catalog = "haodf")
public class DiseaseCat {
    private String catId;
    private String parentId;
    private String catName;
    private String url;
    private Date createTime;
    private Integer status;

    @Override
    public String toString() {
        return "DiseaseCat{" +
                "catId='" + catId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", catName='" + catName + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    @Id
    @Column(name = "cat_id")
    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "cat_name")
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
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

        DiseaseCat that = (DiseaseCat) o;

        if (catId != null ? !catId.equals(that.catId) : that.catId != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (catName != null ? !catName.equals(that.catName) : that.catName != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catId != null ? catId.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
