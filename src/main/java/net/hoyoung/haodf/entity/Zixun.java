package net.hoyoung.haodf.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/15.
 */
@Entity
public class Zixun {
    private String zixunId;
    private String docId;
    private Date createTime;
    private String patId;
    private Date postTime;
    private String title;
    private String jibing;
    private Integer totalChatNum;
    private Integer docChatNum;
    private Integer patChatNum;
    private Integer giftFlag;
    private Integer hotGiftFlag;
    private Integer payFlag;
    private Integer tellFlag;
    private Integer privateFlag;
    private String ext1;
    private String ext2;
    private String ext3;
    private String url;
    private Integer status;

    @Basic
    @Column(name = "private_flag")
    public Integer getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(Integer privateFlag) {
        this.privateFlag = privateFlag;
    }

    @Basic
    @Column(name = "tell_flag")
    public Integer getTellFlag() {
        return tellFlag;
    }

    public void setTellFlag(Integer tellFlag) {
        this.tellFlag = tellFlag;
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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zixun zixun = (Zixun) o;

        if (zixunId != null ? !zixunId.equals(zixun.zixunId) : zixun.zixunId != null) return false;
        if (docId != null ? !docId.equals(zixun.docId) : zixun.docId != null) return false;
        if (createTime != null ? !createTime.equals(zixun.createTime) : zixun.createTime != null) return false;
        if (patId != null ? !patId.equals(zixun.patId) : zixun.patId != null) return false;
        if (postTime != null ? !postTime.equals(zixun.postTime) : zixun.postTime != null) return false;
        if (title != null ? !title.equals(zixun.title) : zixun.title != null) return false;
        if (jibing != null ? !jibing.equals(zixun.jibing) : zixun.jibing != null) return false;
        if (totalChatNum != null ? !totalChatNum.equals(zixun.totalChatNum) : zixun.totalChatNum != null) return false;
        if (docChatNum != null ? !docChatNum.equals(zixun.docChatNum) : zixun.docChatNum != null) return false;
        if (patChatNum != null ? !patChatNum.equals(zixun.patChatNum) : zixun.patChatNum != null) return false;
        if (giftFlag != null ? !giftFlag.equals(zixun.giftFlag) : zixun.giftFlag != null) return false;
        if (hotGiftFlag != null ? !hotGiftFlag.equals(zixun.hotGiftFlag) : zixun.hotGiftFlag != null) return false;
        if (payFlag != null ? !payFlag.equals(zixun.payFlag) : zixun.payFlag != null) return false;
        if (ext1 != null ? !ext1.equals(zixun.ext1) : zixun.ext1 != null) return false;
        if (ext2 != null ? !ext2.equals(zixun.ext2) : zixun.ext2 != null) return false;
        if (ext3 != null ? !ext3.equals(zixun.ext3) : zixun.ext3 != null) return false;
        return !(url != null ? !url.equals(zixun.url) : zixun.url != null);

    }

    @Override
    public int hashCode() {
        int result = zixunId != null ? zixunId.hashCode() : 0;
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (patId != null ? patId.hashCode() : 0);
        result = 31 * result + (postTime != null ? postTime.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (jibing != null ? jibing.hashCode() : 0);
        result = 31 * result + (totalChatNum != null ? totalChatNum.hashCode() : 0);
        result = 31 * result + (docChatNum != null ? docChatNum.hashCode() : 0);
        result = 31 * result + (patChatNum != null ? patChatNum.hashCode() : 0);
        result = 31 * result + (giftFlag != null ? giftFlag.hashCode() : 0);
        result = 31 * result + (hotGiftFlag != null ? hotGiftFlag.hashCode() : 0);
        result = 31 * result + (payFlag != null ? payFlag.hashCode() : 0);
        result = 31 * result + (ext1 != null ? ext1.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (ext3 != null ? ext3.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Zixun{" +
                "zixunId='" + zixunId + '\'' +
                ", docId='" + docId + '\'' +
                ", createTime=" + createTime +
                ", patId='" + patId + '\'' +
                ", postTime=" + postTime +
                ", title='" + title + '\'' +
                ", jibing='" + jibing + '\'' +
                ", totalChatNum=" + totalChatNum +
                ", docChatNum=" + docChatNum +
                ", patChatNum=" + patChatNum +
                ", gift=" + giftFlag +
                ", hotGift=" + hotGiftFlag +
                ", pay=" + payFlag +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Id
    @Column(name = "zixun_id")
    public String getZixunId() {
        return zixunId;
    }

    public void setZixunId(String zixunId) {
        this.zixunId = zixunId;
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
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    @Column(name = "post_time")
    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
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
    @Column(name = "jibing")
    public String getJibing() {
        return jibing;
    }

    public void setJibing(String jibing) {
        this.jibing = jibing;
    }

    @Basic
    @Column(name = "total_chat_num")
    public Integer getTotalChatNum() {
        return totalChatNum;
    }

    public void setTotalChatNum(Integer totalChatNum) {
        this.totalChatNum = totalChatNum;
    }

    @Basic
    @Column(name = "doc_chat_num")
    public Integer getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(Integer docChatNum) {
        this.docChatNum = docChatNum;
    }

    @Basic
    @Column(name = "pat_chat_num")
    public Integer getPatChatNum() {
        return patChatNum;
    }

    public void setPatChatNum(Integer patChatNum) {
        this.patChatNum = patChatNum;
    }

    @Basic
    @Column(name = "gift_flag")
    public Integer getGiftFlag() {
        return giftFlag;
    }

    public void setGiftFlag(Integer gift) {
        this.giftFlag = gift;
    }

    @Basic
    @Column(name = "hot_gift_flag")
    public Integer getHotGiftFlag() {
        return hotGiftFlag;
    }

    public void setHotGiftFlag(Integer hotGift) {
        this.hotGiftFlag = hotGift;
    }

    @Basic
    @Column(name = "pay_flag")
    public Integer getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(Integer pay) {
        this.payFlag = pay;
    }

    @Basic
    @Column(name = "ext1")
    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    @Basic
    @Column(name = "ext2")
    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Basic
    @Column(name = "ext3")
    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

}
