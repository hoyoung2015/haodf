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
public class Disease {
    private String disId;
    private String disName;
    private String url;
    private Date createTime;
    private Integer status;
    private String catId;
    private String catName;
    private Integer disType;

    @Basic
    @Column(name = "dis_type")
    public Integer getDisType() {
        return disType;
    }

    public void setDisType(Integer disType) {
        this.disType = disType;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "disId='" + disId + '\'' +
                ", disName='" + disName + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", catId='" + catId + '\'' +
                ", catName='" + catName + '\'' +
                ", disType=" + disType +
                '}';
    }

    @Id
    @Column(name = "dis_id")
    public String getDisId() {
        return disId;
    }

    public void setDisId(String disId) {
        this.disId = disId;
    }

    @Basic
    @Column(name = "dis_name")
    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
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

    @Basic
    @Column(name = "cat_id")
    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "cat_name")
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        if (disId != null ? !disId.equals(disease.disId) : disease.disId != null) return false;
        if (disName != null ? !disName.equals(disease.disName) : disease.disName != null) return false;
        if (url != null ? !url.equals(disease.url) : disease.url != null) return false;
        if (createTime != null ? !createTime.equals(disease.createTime) : disease.createTime != null) return false;
        if (status != null ? !status.equals(disease.status) : disease.status != null) return false;
        if (catId != null ? !catId.equals(disease.catId) : disease.catId != null) return false;
        if (catName != null ? !catName.equals(disease.catName) : disease.catName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = disId != null ? disId.hashCode() : 0;
        result = 31 * result + (disName != null ? disName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (catId != null ? catId.hashCode() : 0);
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        return result;
    }
}
