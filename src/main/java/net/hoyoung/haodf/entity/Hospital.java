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
public class Hospital {
    private String hosId;
    private String hosName;
    private String province;
    private String city;
    private String url;
    private Integer status;
    private Date createTime;
    private String level;

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hosId='" + hosId + '\'' +
                ", hosName='" + hosName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", level='" + level + '\'' +
                '}';
    }

    @Id
    @Column(name = "hos_id")
    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    @Basic
    @Column(name = "hos_name")
    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

        Hospital hospital = (Hospital) o;

        if (hosId != null ? !hosId.equals(hospital.hosId) : hospital.hosId != null) return false;
        if (hosName != null ? !hosName.equals(hospital.hosName) : hospital.hosName != null) return false;
        if (province != null ? !province.equals(hospital.province) : hospital.province != null) return false;
        if (city != null ? !city.equals(hospital.city) : hospital.city != null) return false;
        if (url != null ? !url.equals(hospital.url) : hospital.url != null) return false;
        if (status != null ? !status.equals(hospital.status) : hospital.status != null) return false;
        if (createTime != null ? !createTime.equals(hospital.createTime) : hospital.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hosId != null ? hosId.hashCode() : 0;
        result = 31 * result + (hosName != null ? hosName.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
