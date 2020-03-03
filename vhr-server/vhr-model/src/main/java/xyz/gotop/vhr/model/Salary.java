package xyz.gotop.vhr.model;

import java.io.Serializable;
import java.util.Date;

/**
 * salary
 * @author 
 */
public class Salary implements Serializable {
    private Integer id;

    /**
     * 基本工资
     */
    private Integer basicsalary;

    /**
     * 奖金
     */
    private Integer bonus;

    /**
     * 午餐补助
     */
    private Integer lunchsalary;

    /**
     * 交通补助
     */
    private Integer trafficsalary;

    /**
     * 应发工资
     */
    private Integer allsalary;

    /**
     * 养老金基数
     */
    private Integer pensionbase;

    /**
     * 养老金比率
     */
    private Float pensionper;

    /**
     * 启用时间
     */
    private Date createdate;

    /**
     * 医疗基数
     */
    private Integer medicalbase;

    /**
     * 医疗保险比率
     */
    private Float medicalper;

    /**
     * 公积金基数
     */
    private Integer accumulationfundbase;

    /**
     * 公积金比率
     */
    private Float accumulationfundper;

    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(Integer basicsalary) {
        this.basicsalary = basicsalary;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getLunchsalary() {
        return lunchsalary;
    }

    public void setLunchsalary(Integer lunchsalary) {
        this.lunchsalary = lunchsalary;
    }

    public Integer getTrafficsalary() {
        return trafficsalary;
    }

    public void setTrafficsalary(Integer trafficsalary) {
        this.trafficsalary = trafficsalary;
    }

    public Integer getAllsalary() {
        return allsalary;
    }

    public void setAllsalary(Integer allsalary) {
        this.allsalary = allsalary;
    }

    public Integer getPensionbase() {
        return pensionbase;
    }

    public void setPensionbase(Integer pensionbase) {
        this.pensionbase = pensionbase;
    }

    public Float getPensionper() {
        return pensionper;
    }

    public void setPensionper(Float pensionper) {
        this.pensionper = pensionper;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getMedicalbase() {
        return medicalbase;
    }

    public void setMedicalbase(Integer medicalbase) {
        this.medicalbase = medicalbase;
    }

    public Float getMedicalper() {
        return medicalper;
    }

    public void setMedicalper(Float medicalper) {
        this.medicalper = medicalper;
    }

    public Integer getAccumulationfundbase() {
        return accumulationfundbase;
    }

    public void setAccumulationfundbase(Integer accumulationfundbase) {
        this.accumulationfundbase = accumulationfundbase;
    }

    public Float getAccumulationfundper() {
        return accumulationfundper;
    }

    public void setAccumulationfundper(Float accumulationfundper) {
        this.accumulationfundper = accumulationfundper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Salary other = (Salary) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBasicsalary() == null ? other.getBasicsalary() == null : this.getBasicsalary().equals(other.getBasicsalary()))
            && (this.getBonus() == null ? other.getBonus() == null : this.getBonus().equals(other.getBonus()))
            && (this.getLunchsalary() == null ? other.getLunchsalary() == null : this.getLunchsalary().equals(other.getLunchsalary()))
            && (this.getTrafficsalary() == null ? other.getTrafficsalary() == null : this.getTrafficsalary().equals(other.getTrafficsalary()))
            && (this.getAllsalary() == null ? other.getAllsalary() == null : this.getAllsalary().equals(other.getAllsalary()))
            && (this.getPensionbase() == null ? other.getPensionbase() == null : this.getPensionbase().equals(other.getPensionbase()))
            && (this.getPensionper() == null ? other.getPensionper() == null : this.getPensionper().equals(other.getPensionper()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getMedicalbase() == null ? other.getMedicalbase() == null : this.getMedicalbase().equals(other.getMedicalbase()))
            && (this.getMedicalper() == null ? other.getMedicalper() == null : this.getMedicalper().equals(other.getMedicalper()))
            && (this.getAccumulationfundbase() == null ? other.getAccumulationfundbase() == null : this.getAccumulationfundbase().equals(other.getAccumulationfundbase()))
            && (this.getAccumulationfundper() == null ? other.getAccumulationfundper() == null : this.getAccumulationfundper().equals(other.getAccumulationfundper()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBasicsalary() == null) ? 0 : getBasicsalary().hashCode());
        result = prime * result + ((getBonus() == null) ? 0 : getBonus().hashCode());
        result = prime * result + ((getLunchsalary() == null) ? 0 : getLunchsalary().hashCode());
        result = prime * result + ((getTrafficsalary() == null) ? 0 : getTrafficsalary().hashCode());
        result = prime * result + ((getAllsalary() == null) ? 0 : getAllsalary().hashCode());
        result = prime * result + ((getPensionbase() == null) ? 0 : getPensionbase().hashCode());
        result = prime * result + ((getPensionper() == null) ? 0 : getPensionper().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getMedicalbase() == null) ? 0 : getMedicalbase().hashCode());
        result = prime * result + ((getMedicalper() == null) ? 0 : getMedicalper().hashCode());
        result = prime * result + ((getAccumulationfundbase() == null) ? 0 : getAccumulationfundbase().hashCode());
        result = prime * result + ((getAccumulationfundper() == null) ? 0 : getAccumulationfundper().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", basicsalary=").append(basicsalary);
        sb.append(", bonus=").append(bonus);
        sb.append(", lunchsalary=").append(lunchsalary);
        sb.append(", trafficsalary=").append(trafficsalary);
        sb.append(", allsalary=").append(allsalary);
        sb.append(", pensionbase=").append(pensionbase);
        sb.append(", pensionper=").append(pensionper);
        sb.append(", createdate=").append(createdate);
        sb.append(", medicalbase=").append(medicalbase);
        sb.append(", medicalper=").append(medicalper);
        sb.append(", accumulationfundbase=").append(accumulationfundbase);
        sb.append(", accumulationfundper=").append(accumulationfundper);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}