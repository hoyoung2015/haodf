package net.hoyoung.haodf.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/9.
 */
@Entity
public class Wenda {
    private String wenId;
    private String wenName;
    private String url;
    private Integer status;
    private Date createTime;
    private String conCatName;

    @Override
    public String toString() {
        return "Wenda{" +
                "wenId='" + wenId + '\'' +
                ", wenName='" + wenName + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", conCatName='" + conCatName + '\'' +
                ", conCatName2='" + conCatName2 + '\'' +
                ", docId='" + docId + '\'' +
                ", title='" + title + '\'' +
                ", disease='" + disease + '\'' +
                ", description='" + description + '\'' +
                ", wantHelp='" + wantHelp + '\'' +
                ", hospital='" + hospital + '\'' +
                ", hosDept='" + hosDept + '\'' +
                ", drug='" + drug + '\'' +
                ", zhiliao='" + zhiliao + '\'' +
                '}';
    }

    @Basic
    @Column(name = "con_cat_name2")
    public String getConCatName2() {
        return conCatName2;
    }

    public void setConCatName2(String conCatName2) {
        this.conCatName2 = conCatName2;
    }

    private String conCatName2;
    private String docId;
    private String title;
    private String disease;
    private String description;
    private String wantHelp;
    private String hospital;
    private String hosDept;
    private String drug;
    private String zhiliao;

    @Basic
    @Column(name = "zhiliao")
    public String getZhiliao() {
        return zhiliao;
    }

    public void setZhiliao(String zhiliao) {
        this.zhiliao = zhiliao;
    }

    @Basic
    @Column(name = "drug")
    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Basic
    @Column(name = "disease")
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
    @Basic
    @Column(name = "want_help")
    public String getWantHelp() {
        return wantHelp;
    }

    public void setWantHelp(String wantHelp) {
        this.wantHelp = wantHelp;
    }
    @Basic
    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    @Basic
    @Column(name = "hos_dept")
    public String getHosDept() {
        return hosDept;
    }

    public void setHosDept(String hosDept) {
        this.hosDept = hosDept;
    }

    @Basic
    @Column(name = "doc_id")
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    @Id
    @Column(name = "wen_id")
    public String getWenId() {
        return wenId;
    }

    public void setWenId(String wenId) {
        this.wenId = wenId;
    }

    @Basic
    @Column(name = "wen_name")
    public String getWenName() {
        return wenName;
    }

    public void setWenName(String wenName) {
        this.wenName = wenName;
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

    @Basic
    @Column(name = "con_cat_name")
    public String getConCatName() {
        return conCatName;
    }

    public void setConCatName(String conCatName) {
        this.conCatName = conCatName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wenda wenda = (Wenda) o;

        if (wenId != null ? !wenId.equals(wenda.wenId) : wenda.wenId != null) return false;
        if (wenName != null ? !wenName.equals(wenda.wenName) : wenda.wenName != null) return false;
        if (url != null ? !url.equals(wenda.url) : wenda.url != null) return false;
        if (status != null ? !status.equals(wenda.status) : wenda.status != null) return false;
        if (createTime != null ? !createTime.equals(wenda.createTime) : wenda.createTime != null) return false;
        if (conCatName != null ? !conCatName.equals(wenda.conCatName) : wenda.conCatName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wenId != null ? wenId.hashCode() : 0;
        result = 31 * result + (wenName != null ? wenName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (conCatName != null ? conCatName.hashCode() : 0);
        return result;
    }
}
