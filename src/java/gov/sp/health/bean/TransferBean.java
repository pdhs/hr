/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.bean;

import gov.sp.health.entity.Bill;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@SessionScoped
public class TransferBean {

    Bill bill;
    /**
     * Creates a new instance of TransferBean
     */
    public TransferBean() {
    }

    public Bill getBill() {

        return bill;
    }

    public void setBill(Bill bill) {

        this.bill = bill;
    }
    
    
    
}
