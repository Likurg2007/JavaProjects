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
@Table(name = "MODEL")
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODEL_ID")
    private Long id;
    @Column(name = "MODEL_NAME")
    private String name;
    @Column(name = "MODEL_DESCRIPTION")
    private String desc;
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Set<CurProduct> curproduct = new HashSet<CurProduct>();

    public Model() {
    }

    public Model(String name, String desc) {
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
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.desc != null ? this.desc.hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Model)) {
            return false;
        }
        final Model other = (Model) obj;
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
        return "Model{" + "id=" + id + ", name=" + name + ", desc=" + desc + '}';
    }
}
