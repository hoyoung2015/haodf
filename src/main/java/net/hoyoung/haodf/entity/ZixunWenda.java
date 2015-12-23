package net.hoyoung.haodf.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/16.
 */
@Entity
@Table(name = "zixun_wenda", schema = "", catalog = "haodf")
public class ZixunWenda {
    private String zwId;
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
    private String cureState;
    private Integer privateChat;
    private Integer showBingli;
    private Integer contentSize;
    private Integer replyInter;

    public ZixunWenda(String zwId, String zixunId, Date publishTime, String docId, String patId) {
        this.zwId = zwId;
        this.zixunId = zixunId;
        this.publishTime = publishTime;
        this.docId = docId;
        this.patId = patId;
    }

    @Override
    public String toString() {
        return "ZixunWenda{" +
                "zwId='" + zwId + '\'' +
                ", zixunId='" + zixunId + '\'' +
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
                ", cureState='" + cureState + '\'' +
                ", privateChat=" + privateChat +
                ", showBingli=" + showBingli +
                ", contentSize=" + contentSize +
                ", replyInter=" + replyInter +
                '}';
    }
    @Basic
    @Column(name = "reply_inter")
    public Integer getReplyInter() {
        return replyInter;
    }

    public void setReplyInter(Integer replyInter) {
        this.replyInter = replyInter;
    }

    @Basic
    @Column(name = "content_size")
    public Integer getContentSize() {
        return contentSize;
    }

    public void setContentSize(Integer contentSize) {
        this.contentSize = contentSize;
    }

    @Basic
    @Column(name = "show_bingli")
    public Integer getShowBingli() {
        return showBingli;
    }

    public void setShowBingli(Integer showBingli) {
        this.showBingli = showBingli;
    }

    @Basic
    @Column(name = "private_chat")
    public Integer getPrivateChat() {
        return privateChat;
    }

    public void setPrivateChat(Integer privateChat) {
        this.privateChat = privateChat;
    }

    @Basic
    @Column(name = "cure_state")
    public String getCureState() {
        return cureState;
    }

    public void setCureState(String cureState) {
        this.cureState = cureState;
    }

    public ZixunWenda() {
    }

    public ZixunWenda(String zwId) {
        this.zwId = zwId;
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
    @Column(name = "zw_id")
    public String getZwId() {
        return zwId;
    }

    public void setZwId(String zwId) {
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



}
