package org.dpolianskyi.epam.delivery.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CURPRODUCT_REQUEST")
public class Curpro_Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURPRO_REQUEST_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "REQUEST")
    private Request request;
    @ManyToOne
    @JoinColumn(name = "CURPRODUCT")
    private CurProduct curproduct;
    @Column(name = "CURPRO_QUANTITY")
    private Integer cpquant;

    public Curpro_Request() {
    }

    public Curpro_Request(Integer cpquant, Request request, CurProduct curproduct) {
        this.cpquant = cpquant;
        this.request = request;
        this.curproduct = curproduct;
    }

    public CurProduct getCurrentProduct() {
        return curproduct;
    }

    public void setCurrentProduct(CurProduct curproduct) {
        this.curproduct = curproduct;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentProductQuantity(Integer cpquant) {
        this.cpquant = cpquant;
    }

    public Integer getCurrentProductQuantity() {
        return cpquant;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.request != null ? this.request.hashCode() : 0);
        hash = 13 * hash + (this.curproduct != null ? this.curproduct.hashCode() : 0);
        hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 13 * hash + (this.cpquant != null ? this.cpquant.hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Curpro_Request)) {
            return false;
        }
        final Curpro_Request other = (Curpro_Request) obj;
        if (this.request != other.request && (this.request == null || !this.request.equals(other.request))) {
            return false;
        }
        if (this.curproduct != other.curproduct && (this.curproduct == null || !this.curproduct.equals(other.curproduct))) {
            return false;
        }
        if (this.cpquant != other.cpquant && (this.cpquant == null || !this.cpquant.equals(other.cpquant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curpro_Order{" + "order=" + request + ", curproduct=" + curproduct + ", id=" + id + ", cpquant=" + cpquant + '}';
    }
}
