package net.hoyoung.haodf.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/16.
 */
@Entity
@Table(name = "zixun_wenda", schema = "", catalog = "haodf")
public class ZixunWenda {
    private int zwId;
    private String zixunId;
    private String zwType;
    private Date createTime;
    private Date publishTime;
    private String content;
    private String docId;
    private String patId;
    private String ext1;
    private String ext2;
    private String ext3;
    private String html;

    @Override
    public String toString() {
        return "ZixunWenda{" +
                "zwId=" + zwId +
                ", zixunId=" + zixunId +
                ", zwType='" + zwType + '\'' +
                ", createTime=" + createTime +
                ", publishTime=" + publishTime +
                ", content='" + content + '\'' +
                ", docId='" + docId + '\'' +
                ", patId='" + patId + '\'' +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                ", html='" + html + '\'' +
                '}';
    }

    @Basic
    @Column(name = "html")
    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Id
    @GeneratedValue
    @Column(name = "zw_id")
    public int getZwId() {
        return zwId;
    }

    public void setZwId(int zwId) {
        this.zwId = zwId;
    }

    @Basic
    @Column(name = "zixun_id")
    public String getZixunId() {
        return zixunId;
    }

    public void setZixunId(String zixunId) {
        this.zixunId = zixunId;
    }

    @Basic
    @Column(name = "zw_type")
    public String getZwType() {
        return zwType;
    }

    public void setZwType(String zwType) {
        this.zwType = zwType;
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
    @Column(name = "publish_time")
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZixunWenda that = (ZixunWenda) o;

        if (zwId != that.zwId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (ext1 != null ? !ext1.equals(that.ext1) : that.ext1 != null) return false;
        if (ext2 != null ? !ext2.equals(that.ext2) : that.ext2 != null) return false;
        if (ext3 != null ? !ext3.equals(that.ext3) : that.ext3 != null) return false;
        if (patId != null ? !patId.equals(that.patId) : that.patId != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (zixunId != null ? !zixunId.equals(that.zixunId) : that.zixunId != null) return false;
        if (zwType != null ? !zwType.equals(that.zwType) : that.zwType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = zwId;
        result = 31 * result + (zixunId != null ? zixunId.hashCode() : 0);
        result = 31 * result + (zwType != null ? zwType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (patId != null ? patId.hashCode() : 0);
        result = 31 * result + (ext1 != null ? ext1.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (ext3 != null ? ext3.hashCode() : 0);
        return result;
    }
}
