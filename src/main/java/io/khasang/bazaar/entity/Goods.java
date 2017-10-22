package io.khasang.bazaar.entity;

import javax.persistence.*;

/**
 * This class describes a database entity that represents goods for sale.
 *
 * @author Zulfia Garifullina
 * @date 03.10.2017.
 */
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private GoodsCategory category;

    private String description;
    private Integer price;

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    @Column(name = "quantity_reserved")
    private Integer quantityReserved;

    public Goods() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (id != null ? !id.equals(goods.id) : goods.id != null) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (category != null ? !category.equals(goods.category) : goods.category != null) return false;
        if (price != null ? !price.equals(goods.price) : goods.price != null) return false;
        if (quantityInStock != null ? !quantityInStock.equals(goods.quantityInStock) : goods.quantityInStock != null)
            return false;
        return quantityReserved != null ? quantityReserved.equals(goods.quantityReserved) : goods.quantityReserved == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantityInStock != null ? quantityInStock.hashCode() : 0);
        result = 31 * result + (quantityReserved != null ? quantityReserved.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getQuantityReserved() {
        return quantityReserved;
    }

    public void setQuantityReserved(Integer quantityReserved) {
        this.quantityReserved = quantityReserved;
    }
}
