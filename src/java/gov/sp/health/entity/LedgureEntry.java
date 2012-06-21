/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.entity;

import java.util.Date;

/**
 *
 * @author Buddhika
 */
public class LedgureEntry {

    Date entryDate;
    ItemUnit itemUnit;
    ItemUnitHistory itemUnitHistory;
    BillItem billItem;
    Bill bill;
    String comments;
    Double inQty;
    Double outQty;
    Double monthInQty;
    Double monthOutQty;
    Double yearInQty;
    Double yearOutQty;
    Double beforeStock;
    Double afterStock;
    Institution institution;
    Unit unit;
    Location location;
    Person person;

    public enum EntryType {

        INSTITUTION,
        LOCATION,
        UNIT,
        PERSON,
        OTHER,}
    EntryType entryType;

    public ItemUnitHistory getItemUnitHistory() {
        if (!canProceed()) {
            return null;
        }
        String temJQL;
        switch (entryType) {
            case INSTITUTION:
                break;
            case LOCATION:
                break;
            case UNIT:
                break;
            case PERSON:
                break;
            case OTHER:
            default:


        }
        return itemUnitHistory;
    }

    public void setItemUnitHistory(ItemUnitHistory itemUnitHistory) {
        this.itemUnitHistory = itemUnitHistory;
    }

    public Double getAfterStock() {
        return afterStock;
    }

    private boolean canProceed() {
        if (getEntryDate() == null) {
            return false;
        }
        if (getItemUnit() == null) {
            return false;
        }
        if (getBillItem() == null) {
            return false;
        }
        if (getBill() == null) {
            return false;
        }
        if (getInstitution() != null) {
            entryType = EntryType.INSTITUTION;
            return true;
        } else {
            if (getUnit() != null) {
                entryType = EntryType.UNIT;
                return true;
            } else {
                if (getLocation() != null) {
                    entryType = EntryType.LOCATION;
                    return true;
                } else {
                    if (getPerson() != null) {
                        entryType = EntryType.PERSON;
                        return true;
                    } else {
                        entryType = EntryType.OTHER;
                        return false;
                    }
                }
            }
        }

    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setAfterStock(Double afterStock) {
        this.afterStock = afterStock;
    }

    public Double getBeforeStock() {
        if (!canProceed()) {
            return null;
        }
        return beforeStock;
    }

    public void setBeforeStock(Double beforeStock) {
        this.beforeStock = beforeStock;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public BillItem getBillItem() {
        return billItem;
    }

    public void setBillItem(BillItem billItem) {
        this.billItem = billItem;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Double getInQty() {
        return inQty;
    }

    public void setInQty(Double inQty) {
        this.inQty = inQty;
    }

    public ItemUnit getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(ItemUnit itemUnit) {
        this.itemUnit = itemUnit;
    }

    public Double getMonthInQty() {
        return monthInQty;
    }

    public void setMonthInQty(Double monthInQty) {
        this.monthInQty = monthInQty;
    }

    public Double getMonthOutQty() {
        return monthOutQty;
    }

    public void setMonthOutQty(Double monthOutQty) {
        this.monthOutQty = monthOutQty;
    }

    public Double getOutQty() {
        return outQty;
    }

    public void setOutQty(Double outQty) {
        this.outQty = outQty;
    }

    public Double getYearInQty() {
        return yearInQty;
    }

    public void setYearInQty(Double yearInQty) {
        this.yearInQty = yearInQty;
    }

    public Double getYearOutQty() {
        return yearOutQty;
    }

    public void setYearOutQty(Double yearOutQty) {
        this.yearOutQty = yearOutQty;
    }
}
