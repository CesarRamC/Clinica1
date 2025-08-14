/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivann
 */
@Entity
@Table(name = "CLI_MEDICOS", catalog = "", schema = "SYSTEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicos.findAll", query = "SELECT m FROM Medicos m"),
    @NamedQuery(name = "Medicos.findByMedId", query = "SELECT m FROM Medicos m WHERE m.medId = :medId"),
    @NamedQuery(name = "Medicos.findByMedVersion", query = "SELECT m FROM Medicos m WHERE m.medVersion = :medVersion")})
public class Medicos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="CLI_MEDICOS_GENERATOR",sequenceName = "SYSTEM.SEQ_MEDICOS",allocationSize =1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CLI_MEDICOS_GENERATOR") 
    @Column(name = "MED_ID", nullable = false, precision = 0, scale = -127)
    private Long medId;
    @Version
    @Basic(optional = false)
    @Column(name = "MED_VERSION", nullable = false)
    private Long medVersion;
    @JoinColumn(name = "MED_ID_PERSONA", referencedColumnName = "CLI_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona medIdPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citIdMedico", fetch = FetchType.LAZY)
    private List<Cita> citaList;

    public Medicos() {
    }

    public Medicos(Long medId) {
        this.medId = medId;
    }

    public Medicos(Long medId,Long medVersion) {
        this.medId = medId;
        this.medVersion = medVersion;
    }

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public Long getMedVersion() {
        return medVersion;
    }

    public void setMedVersion(Long medVersion) {
        this.medVersion = medVersion;
    }

    public Persona getMedIdPersona() {
        return medIdPersona;
    }

    public void setMedIdPersona(Persona medIdPersona) {
        this.medIdPersona = medIdPersona;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medId != null ? medId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.medId == null && other.medId != null) || (this.medId != null && !this.medId.equals(other.medId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinica.model.Medicos[ medId=" + medId + " ]";
    }
    
}
