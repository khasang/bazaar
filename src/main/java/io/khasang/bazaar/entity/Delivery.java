package io.khasang.bazaar.entity;


import javax.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery {

    public Delivery() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String sender;
    private String product;
    private String address;
    private String dateRegistration;
    private String dateSend;
    private String dateReceive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        if (id != null ? !id.equals(delivery.id) : delivery.id != null) return false;
        if (recipient != null ? !recipient.equals(delivery.recipient) : delivery.recipient != null) return false;
        if (sender != null ? !sender.equals(delivery.sender) : delivery.sender != null) return false;
        if (product != null ? !product.equals(delivery.product) : delivery.product != null) return false;
        if (address != null ? !address.equals(delivery.address) : delivery.address != null) return false;
        if (dateRegistration != null ? !dateRegistration.equals(delivery.dateRegistration) : delivery.dateRegistration != null)
            return false;
        if (dateSend != null ? !dateSend.equals(delivery.dateSend) : delivery.dateSend != null) return false;
        return dateReceive != null ? dateReceive.equals(delivery.dateReceive) : delivery.dateReceive == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (dateRegistration != null ? dateRegistration.hashCode() : 0);
        result = 31 * result + (dateSend != null ? dateSend.hashCode() : 0);
        result = 31 * result + (dateReceive != null ? dateReceive.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(String dateReceive) {
        this.dateReceive = dateReceive;
    }
}
