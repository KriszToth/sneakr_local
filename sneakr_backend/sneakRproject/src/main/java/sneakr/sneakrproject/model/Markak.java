/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "markak")
@NamedQueries({
    @NamedQuery(name = "Markak.findAll", query = "SELECT m FROM Markak m"),
    @NamedQuery(name = "Markak.findById", query = "SELECT m FROM Markak m WHERE m.id = :id"),
    @NamedQuery(name = "Markak.findByNev", query = "SELECT m FROM Markak m WHERE m.nev = :nev")})
public class Markak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nev")
    private String nev;
    @OneToMany(mappedBy = "markaId")
    private Collection<Cipok> cipokCollection;

    public Markak() {
    }

    public Markak(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Collection<Cipok> getCipokCollection() {
        return cipokCollection;
    }

    public void setCipokCollection(Collection<Cipok> cipokCollection) {
        this.cipokCollection = cipokCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Markak)) {
            return false;
        }
        Markak other = (Markak) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.Markak[ id=" + id + " ]";
    }
    
}
