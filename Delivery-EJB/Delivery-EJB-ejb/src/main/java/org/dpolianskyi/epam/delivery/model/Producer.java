package org.dpolianskyi.epam.delivery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCER")
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCER_ID")
    private Long id;
    @Column(name = "PRODUCER_NAME")
    private String name;
    @Column(name = "PRODUCER_DESCRIPTION")
    private String desc;
    @OneToMany(mappedBy = "producer")
    private List<CurProduct> curproduct = new ArrayList<CurProduct>();

    public Producer() {
    }

    public Producer(String name, String desc) {
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

    public List<CurProduct> getCurProductList() {
        return curproduct;
    }

    public void setCurProductList(List<CurProduct> curproduct) {
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
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 71 * hash + (this.desc != null ? this.desc.hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Producer)) {
            return false;
        }
        final Producer other = (Producer) obj;
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
        return "Producer{" + "id=" + id + ", name=" + name + ", desc=" + desc + '}';
    }
}
