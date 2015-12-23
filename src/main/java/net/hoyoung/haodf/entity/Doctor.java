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
    private String docTitle;

    private String homeUrl;
    private String infoUrl;
    private Integer status;
    private Date createTime;
    private String hosId;



    private String hosName;
    private String hosUrl;
    private String deptId;

    private String deptName;
    private String deptUrl;
    private Integer totalView;
    private Integer totalArticle;
    private Integer totalPatient;
    private Integer wexinBaodao;
    private Integer totalBaodao;
    private Integer totalVote;
    private Integer totalThankLetter;
    private Integer totalGift;
    private Date startTime;

    @Override
    public String toString() {
        return "Doctor{" +
                "docId='" + docId + '\'' +
                ", docName='" + docName + '\'' +
                ", docTitle='" + docTitle + '\'' +
                ", homeUrl='" + homeUrl + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", hosId='" + hosId + '\'' +
                ", hosName='" + hosName + '\'' +
                ", hosUrl='" + hosUrl + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptUrl='" + deptUrl + '\'' +
                ", totalView=" + totalView +
                ", totalArticle=" + totalArticle +
                ", totalPatient=" + totalPatient +
                ", wexinBaodao=" + wexinBaodao +
                ", totalBaodao=" + totalBaodao +
                ", totalVote=" + totalVote +
                ", totalThankLetter=" + totalThankLetter +
                ", totalGift=" + totalGift +
                ", startTime=" + startTime +
                '}';
    }

    @Basic
    @Column(name = "dept_id")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "doc_title")
    public String getDocTitle() {
        return docTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (docId != null ? !docId.equals(doctor.docId) : doctor.docId != null) return false;
        if (docName != null ? !docName.equals(doctor.docName) : doctor.docName != null) return false;
        if (docTitle != null ? !docTitle.equals(doctor.docTitle) : doctor.docTitle != null) return false;
        if (homeUrl != null ? !homeUrl.equals(doctor.homeUrl) : doctor.homeUrl != null) return false;
        if (infoUrl != null ? !infoUrl.equals(doctor.infoUrl) : doctor.infoUrl != null) return false;
        if (status != null ? !status.equals(doctor.status) : doctor.status != null) return false;
        if (createTime != null ? !createTime.equals(doctor.createTime) : doctor.createTime != null) return false;
        if (hosName != null ? !hosName.equals(doctor.hosName) : doctor.hosName != null) return false;
        if (hosUrl != null ? !hosUrl.equals(doctor.hosUrl) : doctor.hosUrl != null) return false;
        if (deptName != null ? !deptName.equals(doctor.deptName) : doctor.deptName != null) return false;
        if (deptUrl != null ? !deptUrl.equals(doctor.deptUrl) : doctor.deptUrl != null) return false;
        if (totalView != null ? !totalView.equals(doctor.totalView) : doctor.totalView != null) return false;
        if (totalArticle != null ? !totalArticle.equals(doctor.totalArticle) : doctor.totalArticle != null)
            return false;
        if (totalPatient != null ? !totalPatient.equals(doctor.totalPatient) : doctor.totalPatient != null)
            return false;
        if (wexinBaodao != null ? !wexinBaodao.equals(doctor.wexinBaodao) : doctor.wexinBaodao != null) return false;
        if (totalBaodao != null ? !totalBaodao.equals(doctor.totalBaodao) : doctor.totalBaodao != null) return false;
        if (totalVote != null ? !totalVote.equals(doctor.totalVote) : doctor.totalVote != null) return false;
        if (totalThankLetter != null ? !totalThankLetter.equals(doctor.totalThankLetter) : doctor.totalThankLetter != null)
            return false;
        if (totalGift != null ? !totalGift.equals(doctor.totalGift) : doctor.totalGift != null) return false;
        return !(startTime != null ? !startTime.equals(doctor.startTime) : doctor.startTime != null);

    }

    @Override
    public int hashCode() {
        int result = docId != null ? docId.hashCode() : 0;
        result = 31 * result + (docName != null ? docName.hashCode() : 0);
        result = 31 * result + (docTitle != null ? docTitle.hashCode() : 0);
        result = 31 * result + (homeUrl != null ? homeUrl.hashCode() : 0);
        result = 31 * result + (infoUrl != null ? infoUrl.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (hosName != null ? hosName.hashCode() : 0);
        result = 31 * result + (hosUrl != null ? hosUrl.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (deptUrl != null ? deptUrl.hashCode() : 0);
        result = 31 * result + (totalView != null ? totalView.hashCode() : 0);
        result = 31 * result + (totalArticle != null ? totalArticle.hashCode() : 0);
        result = 31 * result + (totalPatient != null ? totalPatient.hashCode() : 0);
        result = 31 * result + (wexinBaodao != null ? wexinBaodao.hashCode() : 0);
        result = 31 * result + (totalBaodao != null ? totalBaodao.hashCode() : 0);
        result = 31 * result + (totalVote != null ? totalVote.hashCode() : 0);
        result = 31 * result + (totalThankLetter != null ? totalThankLetter.hashCode() : 0);
        result = 31 * result + (totalGift != null ? totalGift.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        return result;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }
    @Basic
    @Column(name = "hos_id")
    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }
    @Basic
    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    @Basic
    @Column(name = "dept_url")
    public String getDeptUrl() {
        return deptUrl;
    }

    public void setDeptUrl(String deptUrl) {
        this.deptUrl = deptUrl;
    }
    @Basic
    @Column(name = "total_view")
    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }
    @Basic
    @Column(name = "total_article")
    public Integer getTotalArticle() {
        return totalArticle;
    }

    public void setTotalArticle(Integer totalArticle) {
        this.totalArticle = totalArticle;
    }
    @Basic
    @Column(name = "total_patient")
    public Integer getTotalPatient() {
        return totalPatient;
    }

    public void setTotalPatient(Integer totalPatient) {
        this.totalPatient = totalPatient;
    }
    @Basic
    @Column(name = "wexin_baodao")
    public Integer getWexinBaodao() {
        return wexinBaodao;
    }

    public void setWexinBaodao(Integer wexinBaodao) {
        this.wexinBaodao = wexinBaodao;
    }
    @Basic
    @Column(name = "total_baodao")
    public Integer getTotalBaodao() {
        return totalBaodao;
    }

    public void setTotalBaodao(Integer totalBaodao) {
        this.totalBaodao = totalBaodao;
    }
    @Basic
    @Column(name = "total_vote")
    public Integer getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(Integer totalVote) {
        this.totalVote = totalVote;
    }
    @Basic
    @Column(name = "total_thank_letter")
    public Integer getTotalThankLetter() {
        return totalThankLetter;
    }

    public void setTotalThankLetter(Integer totalThankLetter) {
        this.totalThankLetter = totalThankLetter;
    }
    @Basic
    @Column(name = "total_gift")
    public Integer getTotalGift() {
        return totalGift;
    }

    public void setTotalGift(Integer totalGift) {
        this.totalGift = totalGift;
    }
    @Basic
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "hos_url")
    public String getHosUrl() {
        return hosUrl;
    }

    public void setHosUrl(String hosUrl) {
        this.hosUrl = hosUrl;
    }

    @Basic
    @Column(name = "hos_name")
    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
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

}
