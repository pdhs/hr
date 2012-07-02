/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

/**
 *
 * @author Buddhika
 */
@Entity
@Inheritance
public class Amp extends PharmaceuticalItem implements Serializable {
    @ManyToOne
    Vmp vmp;

    

    public Vmp getVmp() {
        return vmp;
    }

    public void setVmp(Vmp vmp) {
        this.vmp = vmp;
    }
    
    
}
