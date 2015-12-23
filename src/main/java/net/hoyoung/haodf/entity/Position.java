package net.hoyoung.haodf.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Position {
    private int score;
    private String formatCreateTime;
    private Date createTime;
    private int positionId;
    private int deliverCount;
    private String positionName;
    private String positionType;
    private String workYear;
    private String education;
    private String jobNature;
    private String companyName;
    private int companyId;
    private int orderBy;
    private long showOrder;
    private int adWord;
    private boolean randomScore;
    private boolean countAdjusted;
    private int relScore;
    private int hrScore;
    private int flowScore;
    private int showCount;
    private int totalCount;
    private int searchScore;
    private double pvScore;
    private long createTimeSort;
    private boolean haveDeliver;
    private boolean calcScore;
    private String city;
    private String imstate;
    private String plus;
    private String positonTypesMap;
    private String companyLogo;
    private String industryField;
    private String positionAdvantage;
    private String salary;
    private String positionFirstType;
    private String leaderName;
    private String companySize;
    private String financeStage;
    private String companyShortName;
    private String description;
    private List<String> companyLabelList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(int deliverCount) {
        this.deliverCount = deliverCount;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(long showOrder) {
        this.showOrder = showOrder;
    }

    public int getAdWord() {
        return adWord;
    }

    public void setAdWord(int adWord) {
        this.adWord = adWord;
    }

    public boolean isRandomScore() {
        return randomScore;
    }

    public void setRandomScore(boolean randomScore) {
        this.randomScore = randomScore;
    }

    public boolean isCountAdjusted() {
        return countAdjusted;
    }

    public void setCountAdjusted(boolean countAdjusted) {
        this.countAdjusted = countAdjusted;
    }

    public int getRelScore() {
        return relScore;
    }

    public void setRelScore(int relScore) {
        this.relScore = relScore;
    }

    public int getHrScore() {
        return hrScore;
    }

    public void setHrScore(int hrScore) {
        this.hrScore = hrScore;
    }

    public int getFlowScore() {
        return flowScore;
    }

    public void setFlowScore(int flowScore) {
        this.flowScore = flowScore;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSearchScore() {
        return searchScore;
    }

    public void setSearchScore(int searchScore) {
        this.searchScore = searchScore;
    }

    public double getPvScore() {
        return pvScore;
    }

    public void setPvScore(double pvScore) {
        this.pvScore = pvScore;
    }

    public long getCreateTimeSort() {
        return createTimeSort;
    }

    public void setCreateTimeSort(long createTimeSort) {
        this.createTimeSort = createTimeSort;
    }

    public boolean isHaveDeliver() {
        return haveDeliver;
    }

    public void setHaveDeliver(boolean haveDeliver) {
        this.haveDeliver = haveDeliver;
    }

    public boolean isCalcScore() {
        return calcScore;
    }

    public void setCalcScore(boolean calcScore) {
        this.calcScore = calcScore;
    }

    @Override
    public String toString() {
        return "Position{" +
                "score=" + score +
                ", formatCreateTime='" + formatCreateTime + '\'' +
                ", createTime=" + createTime +
                ", positionId=" + positionId +
                ", deliverCount=" + deliverCount +
                ", positionName='" + positionName + '\'' +
                ", positionType='" + positionType + '\'' +
                ", workYear='" + workYear + '\'' +
                ", education='" + education + '\'' +
                ", jobNature='" + jobNature + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyId=" + companyId +
                ", orderBy=" + orderBy +
                ", showOrder=" + showOrder +
                ", adWord=" + adWord +
                ", randomScore=" + randomScore +
                ", countAdjusted=" + countAdjusted +
                ", relScore=" + relScore +
                ", hrScore=" + hrScore +
                ", flowScore=" + flowScore +
                ", showCount=" + showCount +
                ", totalCount=" + totalCount +
                ", searchScore=" + searchScore +
                ", pvScore=" + pvScore +
                ", createTimeSort=" + createTimeSort +
                ", haveDeliver=" + haveDeliver +
                ", calcScore=" + calcScore +
                ", city='" + city + '\'' +
                ", imstate='" + imstate + '\'' +
                ", plus='" + plus + '\'' +
                ", positonTypesMap='" + positonTypesMap + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", industryField='" + industryField + '\'' +
                ", positionAdvantage='" + positionAdvantage + '\'' +
                ", salary='" + salary + '\'' +
                ", positionFirstType='" + positionFirstType + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", companySize='" + companySize + '\'' +
                ", financeStage='" + financeStage + '\'' +
                ", companyShortName='" + companyShortName + '\'' +
                ", companyLabelList=" + companyLabelList +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImstate() {
        return imstate;
    }

    public void setImstate(String imstate) {
        this.imstate = imstate;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getPositonTypesMap() {
        return positonTypesMap;
    }

    public void setPositonTypesMap(String positonTypesMap) {
        this.positonTypesMap = positonTypesMap;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getPositionAdvantage() {
        return positionAdvantage;
    }

    public void setPositionAdvantage(String positionAdvantage) {
        this.positionAdvantage = positionAdvantage;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPositionFirstType() {
        return positionFirstType;
    }

    public void setPositionFirstType(String positionFirstType) {
        this.positionFirstType = positionFirstType;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public List<String> getCompanyLabelList() {
        return companyLabelList;
    }

    public void setCompanyLabelList(List<String> companyLabelList) {
        this.companyLabelList = companyLabelList;
    }
}
