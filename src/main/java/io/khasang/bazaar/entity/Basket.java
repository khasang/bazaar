package io.khasang.bazaar.entity;

import javax.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "goods_id")
    private Integer goodsid;

    @Column(name = "order_id")
    private Integer orderid;

    @Column(name = "user_id")
    private Integer userid;

    @Column(name = "order_not_issued")
    private Integer ordernotissued;

    @Column(name = "order_issued")
    private Integer orderissued;

    public Basket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        if (id != null ? !id.equals(basket.id) : basket.id != null) return false;
        if (goodsid != null ? !goodsid.equals(basket.goodsid) : basket.goodsid != null) return false;
        if (orderid != null ? !orderid.equals(basket.orderid) : basket.orderid != null) return false;
        if (userid != null ? !userid.equals(basket.userid) : basket.userid != null) return false;
        if (ordernotissued != null ? !ordernotissued.equals(basket.ordernotissued) : basket.ordernotissued != null)
            return false;
        return orderissued != null ? orderissued.equals(basket.orderissued) : basket.orderissued == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (goodsid != null ? goodsid.hashCode() : 0);
        result = 31 * result + (orderid != null ? orderid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (ordernotissued != null ? ordernotissued.hashCode() : 0);
        result = 31 * result + (orderissued != null ? orderissued.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public Integer getOrdernotissued() {
        return ordernotissued;
    }

    public Integer getOrderissued() {
        return orderissued;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setOrdernotissued(Integer ordernotissued) {
        this.ordernotissued = ordernotissued;
    }

    public void setOrderissued(Integer orderissued) {
        this.orderissued = orderissued;
    }
}
