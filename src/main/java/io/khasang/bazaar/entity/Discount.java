package io.khasang.bazaar.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate_in_percents", nullable = false)
    private Integer rate;
    private String description;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public Discount() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (id != null ? !id.equals(discount.id) : discount.id != null) return false;
        if (rate != null ? !rate.equals(discount.rate) : discount.rate != null) return false;
        if (startDate != null ? !startDate.equals(discount.startDate) : discount.startDate != null) return false;
        if (endDate != null ? !endDate.equals(discount.endDate) : discount.endDate != null) return false;
        if (promoCode != null ? !promoCode.equals(discount.promoCode) : discount.promoCode != null) return false;
        return description != null ? description.equals(discount.description) : discount.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (promoCode != null ? promoCode.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRate() {return rate;
    }

    public void setRate(Integer rate) throws IllegalArgumentException{
        if (rate > 100 || rate < 0) throw new IllegalArgumentException("Discount rate is out of range [0...100]%");
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}