package org.dpolianskyi.epam.delivery.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "REQUEST")
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_ID")
    private Long id;
    @Column(name = "REQUEST_CODE")
    private String code;
    @Column(name = "REQUEST_CREDENTIALS")
    private String credent;
    @Column(name = "REQUEST_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @OneToMany(mappedBy = "request", cascade = CascadeType.MERGE)
    private Set<Curpro_Request> curproreq = new HashSet<Curpro_Request>();

    public Request() {
    }

    public Request(String code, String credent, StatusEnum status) {
        this.code = code;
        this.credent = credent;
        this.status = status;
        this.curproreq = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCredentials() {
        return credent;
    }

    public void setCredentials(String credent) {
        this.credent = credent;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Set<Curpro_Request> getCurrentProduct_Request() {
        return curproreq;
    }

    public void setCurrentProduct_Request(Set<Curpro_Request> curproreq) {
        this.curproreq = curproreq;
    }

    public void addCurrentProductRequest(Curpro_Request cpr) {
        curproreq.add(cpr);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 41 * hash + (this.credent != null ? this.credent.hashCode() : 0);
        hash = 41 * hash + (this.status != null ? this.status.hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Request)) {
            return false;
        }
        final Request other = (Request) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        if ((this.credent == null) ? (other.credent != null) : !this.credent.equals(other.credent)) {
            return false;
        }
        if (this.status != other.status && (this.status == null || !this.status.equals(other.status))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", code=" + code + ", credent=" + credent + ", status=" + status + '}';
    }
}
