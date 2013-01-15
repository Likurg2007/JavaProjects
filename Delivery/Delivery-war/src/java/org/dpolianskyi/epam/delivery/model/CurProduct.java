/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dpolianskyi.epam.delivery.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CURPRODUCT")
public class CurProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURPRODUCT_ID")
    private Long id;
    @Column(name = "CURPRODUCT_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column(name = "CURPRODUCT_NAME")
    private String name;
    @Column(name = "CURPRODUCT_DESCRIPTION")
    private String description;
    @Column(name = "CURPRODUCT_IMPL_YEAR")
    private Integer year;
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODEL")
    private Model model;
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCER")
    private Producer producer;
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY")
    private Category category;
    @OneToMany(mappedBy = "curproduct", cascade = CascadeType.ALL)
    private List<Curpro_Request> curproreq = new LinkedList<Curpro_Request>();

    public CurProduct() {
    }

    public CurProduct(String name, String desc, Model model, StatusEnum status, Producer producer, Category category, Integer year) {
        this.name = name;
        this.status = status;
        this.description = desc;
        this.model = model;
        this.producer = producer;
        this.category = category;
        this.year = year;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Curpro_Request> getCurrentProduct_Request() {
        return curproreq;
    }

    public void setCurrentProduct_Request(List<Curpro_Request> curporeg) {
        this.curproreq = curporeg;
    }

    public void addCurrentProduct_Request(Curpro_Request cpr) {
        curproreq.add(cpr);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 47 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 47 * hash + (this.year != null ? this.year.hashCode() : 0);
        hash = 47 * hash + (this.model != null ? this.model.hashCode() : 0);
        hash = 47 * hash + (this.producer != null ? this.producer.hashCode() : 0);
        hash = 47 * hash + (this.category != null ? this.category.hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof CurProduct)) {
            return false;
        }
        final CurProduct other = (CurProduct) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.status != other.status && (this.status == null || !this.status.equals(other.status))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.year != other.year && (this.year == null || !this.year.equals(other.year))) {
            return false;
        }
        if (this.model != other.model && (this.model == null || !this.model.equals(other.model))) {
            return false;
        }
        if (this.producer != other.producer && (this.producer == null || !this.producer.equals(other.producer))) {
            return false;
        }
        if (this.category != other.category && (this.category == null || !this.category.equals(other.category))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CurProduct{" + "id=" + id  + ", name=" + name + ", description=" + description + ", year=" + year + ", model=" + model + ", producer=" + producer + ", category=" + category + ", curproreq=" + curproreq + '}';
    }
}
