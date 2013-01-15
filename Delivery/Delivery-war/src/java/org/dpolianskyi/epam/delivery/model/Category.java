package org.dpolianskyi.epam.delivery.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;
    @Column(name = "CATEGORY_NAME")
    private String name;
    @Column(name = "CATEGORY_DESCRIPTION")
    private String desc;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<CurProduct> curproduct = new HashSet<CurProduct>();

    public Category() {
    }

    public Category(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public Set<CurProduct> getCurProductList() {
        return curproduct;
    }

    public void setCurProductList(Set<CurProduct> curproduct) {
        this.curproduct = curproduct;
    }

    public void addCurProductList(CurProduct cp) {
        curproduct.add(cp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 11 * hash + (this.desc != null ? this.desc.hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Category)) {
            return false;
        }
        final Category other = (Category) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.desc == null) ? (other.desc != null) : !this.desc.equals(other.desc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", desc=" + desc + ", curproduct=" + curproduct + '}';
    }
}
