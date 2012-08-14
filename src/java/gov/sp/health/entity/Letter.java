/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author buddhika
 */
@Entity
public class Letter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Main Properties
    String name;
    String code;
    String description;
    @Lob
    byte[] letterImage;
    @Lob
    String letterContent;
    //Created Properties
    @ManyToOne
    WebUser creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createdAt;
    //Retairing properties
    boolean retired;
    @ManyToOne
    WebUser retirer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    String retireComments;
    @ManyToOne
    Institution fromInstitution;
    @ManyToOne
    Unit fromUnit;
    @ManyToOne
    Location fromLocation;
    @ManyToOne
    Person fromPerson;
    @ManyToOne
    Institution toInstitution;
    @ManyToOne
    Unit toUnit;
    @ManyToOne
    Location toLocation;
    @ManyToOne
    Person toPerson;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date lettterDate;
    @Temporal(javax.persistence.TemporalType.TIME)
    Date letterTime;
    @Lob
    String letterComments;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date receivedDate;
    @Temporal(javax.persistence.TemporalType.TIME)
    Date receivedTime;
    @Lob
    String receiveComments;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date sentDate;
    @Temporal(javax.persistence.TemporalType.TIME)
    Date sentTime;
    @Lob
    String sentComments;
    @ManyToOne
    Letter previousLetter;
    @ManyToOne
    Letter nextLetter;
    @ManyToOne
    Category category;
    @ManyToOne
    Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Letter)) {
            return false;
        }
        Letter other = (Letter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.sp.health.entity.Letter[ id=" + id + " ]";
    }
}
