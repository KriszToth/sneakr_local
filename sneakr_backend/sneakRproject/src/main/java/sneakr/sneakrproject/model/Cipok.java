/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static sneakr.sneakrproject.model.Userek.emf;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "cipok")
@NamedQueries({
    @NamedQuery(name = "Cipok.findAll", query = "SELECT c FROM Cipok c"),
    @NamedQuery(name = "Cipok.findById", query = "SELECT c FROM Cipok c WHERE c.id = :id"),
    @NamedQuery(name = "Cipok.findByNev", query = "SELECT c FROM Cipok c WHERE c.nev = :nev"),
    @NamedQuery(name = "Cipok.findByAllapot", query = "SELECT c FROM Cipok c WHERE c.allapot = :allapot"),
    @NamedQuery(name = "Cipok.findByAr", query = "SELECT c FROM Cipok c WHERE c.ar = :ar")})
public class Cipok implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nev")
    private String nev;
    @Size(max = 8)
    @Column(name = "allapot")
    private String allapot;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ar")
    private Float ar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "img")
    private String img;
    @JoinColumn(name = "akcio_id", referencedColumnName = "id")
    @ManyToOne
    private Akciok akcioId;
    @JoinColumn(name = "exkluziv_id", referencedColumnName = "id")
    @ManyToOne
    private Exkluzivok exkluzivId;
    @JoinColumn(name = "marka_id", referencedColumnName = "id")
    @ManyToOne
    private Markak markaId;
    @JoinColumn(name = "meret_id", referencedColumnName = "id")
    @ManyToOne
    private Meretek meretId;
    @JoinColumn(name = "nem_id", referencedColumnName = "id")
    @ManyToOne
    private Nemek nemId;
    @JoinColumn(name = "ujdonsag_id", referencedColumnName = "id")
    @ManyToOne
    private Ujdonsagok ujdonsagId;
    @OneToMany(mappedBy = "cipokId")
    private Collection<RendelesTetelek> rendelesTetelekCollection;
    @OneToMany(mappedBy = "cipokId")
    private Collection<ShoppingSession> shoppingSessionCollection;

    public Cipok() {
    }
    public Cipok(String nev,Float ar, String img) {
      this.nev = nev;
      this.ar = ar;
      this.img = img;
    }

    public Cipok(Integer id, String img) {
        this.id = id;
        this.img = img;
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

    public String getAllapot() {
        return allapot;
    }

    public void setAllapot(String allapot) {
        this.allapot = allapot;
    }

    public Float getAr() {
        return ar;
    }

    public void setAr(Float ar) {
        this.ar = ar;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Akciok getAkcioId() {
        return akcioId;
    }

    public void setAkcioId(Akciok akcioId) {
        this.akcioId = akcioId;
    }

    public Exkluzivok getExkluzivId() {
        return exkluzivId;
    }

    public void setExkluzivId(Exkluzivok exkluzivId) {
        this.exkluzivId = exkluzivId;
    }

    public Markak getMarkaId() {
        return markaId;
    }

    public void setMarkaId(Markak markaId) {
        this.markaId = markaId;
    }

    public Meretek getMeretId() {
        return meretId;
    }

    public void setMeretId(Meretek meretId) {
        this.meretId = meretId;
    }

    public Nemek getNemId() {
        return nemId;
    }

    public void setNemId(Nemek nemId) {
        this.nemId = nemId;
    }

    public Ujdonsagok getUjdonsagId() {
        return ujdonsagId;
    }

    public void setUjdonsagId(Ujdonsagok ujdonsagId) {
        this.ujdonsagId = ujdonsagId;
    }

    public Collection<RendelesTetelek> getRendelesTetelekCollection() {
        return rendelesTetelekCollection;
    }

    public void setRendelesTetelekCollection(Collection<RendelesTetelek> rendelesTetelekCollection) {
        this.rendelesTetelekCollection = rendelesTetelekCollection;
    }

    public Collection<ShoppingSession> getShoppingSessionCollection() {
        return shoppingSessionCollection;
    }

    public void setShoppingSessionCollection(Collection<ShoppingSession> shoppingSessionCollection) {
        this.shoppingSessionCollection = shoppingSessionCollection;
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
        if (!(object instanceof Cipok)) {
            return false;
        }
        Cipok other = (Cipok) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static ArrayList<Cipok> getAllShoes() {
    EntityManager em = emf.createEntityManager();
    ArrayList<Cipok> shoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllShoes", Cipok.class);
        spq.execute();
        shoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return shoesList;
}
    public static ArrayList<Cipok> getShoesNamePrice() {
    EntityManager em = emf.createEntityManager();
    ArrayList<Cipok> shoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getShoesNamePrice", Cipok.class);
        spq.execute();
        shoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return shoesList;
}
    public static ArrayList<Cipok> getShoesByAir(Cipok u) {
    EntityManager em = emf.createEntityManager();
    ArrayList<Cipok> shoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getShoesByAir", Cipok.class);
        spq.execute();
        shoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return shoesList;
}

    public ArrayList<Cipok> getShoesByAir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
