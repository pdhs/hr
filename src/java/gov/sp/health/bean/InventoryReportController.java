/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.bean;

import gov.sp.health.entity.*;
import gov.sp.health.facade.BillFacade;
import gov.sp.health.facade.BillItemFacade;
import gov.sp.health.facade.CountryFacade;
import gov.sp.health.facade.InstitutionFacade;
import gov.sp.health.facade.ItemFacade;
import gov.sp.health.facade.ItemUnitFacade;
import gov.sp.health.facade.ItemUnitHistoryFacade;
import gov.sp.health.facade.LocationFacade;
import gov.sp.health.facade.MakeFacade;
import gov.sp.health.facade.ManufacturerFacade;
import gov.sp.health.facade.ModalFacade;
import gov.sp.health.facade.PersonFacade;
import gov.sp.health.facade.SupplierFacade;
import gov.sp.health.facade.UnitFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@ViewScoped
public class InventoryReportController {

    /**
     *
     * Enterprise Java Beans
     *
     *
     */
    @EJB
    private ItemFacade itemFacade;
    @EJB
    private ModalFacade modalFacade;
    @EJB
    private MakeFacade makeFacade;
    @EJB
    private BillFacade billFacade;
    @EJB
    private BillItemFacade billItemFacade;
    @EJB
    InstitutionFacade institutionFacade;
    @EJB
    UnitFacade unitFacade;
    @EJB
    LocationFacade locationFacade;
    @EJB
    PersonFacade personFacade;
    @EJB
    CountryFacade countryFacade;
    @EJB
    ManufacturerFacade manufacturerFacade;
    @EJB
    SupplierFacade supplierFacade;
    @EJB
    ItemUnitFacade itemUnitFacade;
    @EJB
    ItemUnitHistoryFacade itemUnitHistoryFacade;
    /**
     * Managed Properties
     */
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    /**
     * Collections
     */
    DataModel<Item> items;
    DataModel<Make> makes;
    //
    DataModel<LedgureEntry> ledgureEntrys;
    List<LedgureEntry> lstLedgureEntrys;
    //
    DataModel<Institution> fromInstitutions;
    DataModel<Unit> fromUnits;
    DataModel<Location> fromLocations;
    DataModel<Person> fromPersons;
    //
    DataModel<Institution> toInstitutions;
    DataModel<Unit> toUnits;
    DataModel<Location> toLocations;
    DataModel<Person> toPersons;
    //
    DataModel<Country> countries;
    DataModel<Supplier> suppliers;
    DataModel<Manufacturer> manufacturers;
    //
    /*
     * Current Objects
     *
     */
    Date fromDate;
    Date toDate;
    LedgureEntry ledgureEntry;

    /**
     *
     * Methods
     *
     */
   
    public Double calculateStock(Item item) {
        if (item != null) {
            return calculateStock("SELECT SUM(i.quentity) FROM ItemUnit i WHERE i.retired=false AND i.item.id = " + item.getId() + "");
        } else {
            return 0.00;
        }
    }

    public Double calculateStock(Item item, Institution institution) {
        if (item != null && institution != null) {
            return calculateStock("SELECT SUM(i.quentity) FROM ItemUnit i WHERE i.retired=false AND i.item.id = " + item.getId() + " AND i.institution.id = " + institution.getId());
        } else {
            return 0.0;
        }
    }

    public Double calculateStock(Item item, Location location) {
        if (item != item && location != null) {
            return calculateStock("SELECT SUM(i.quentity) FROM ItemUnit i WHERE i.retired=false AND i.item.id = " + item.getId() + " AND i.location.id = " + location.getId());
        } else {
            return 0.00;
        }
    }

    public Double calculateStock(Item item, Unit unit) {
        if (item != null && unit != null) {
            return calculateStock("SELECT SUM(i.quentity) FROM ItemUnit i WHERE i.retired=false AND i.item.id = " + item.getId() + " AND i.unit.id = " + unit.getId());
        } else {
            return 0.00;
        }
    }

    public Double calculateStock(Item item, Person person) {
        if (item != null && person != null) {
            return calculateStock("SELECT SUM(i.quentity) FROM ItemUnit i WHERE i.retired=false AND i.item.id = " + item.getId() + " AND i.person.id = " + person.getId());
        } else {
            return 0.00;
        }
    }

    
    
    
    public Double calculateStock(String strJQL) {
        System.out.println(strJQL);
        System.out.println(getBillFacade().toString());
        System.out.println(getBillFacade().findAll().toString());
        return getBillFacade().findAggregateDbl(strJQL);
    }

    
           

    
    
    private boolean canProceed() {
//        if (getEntryDate() == null) {
//            return false;
//        }
//        if (getItemUnit() == null) {
//            return false;
//        }
//        if (getBillItem() == null) {
//            return false;
//        }
//        if (getBill() == null) {
//            return false;
//        }
//        if (getInstitution() != null) {
//            entryType = LedgureEntry.EntryType.INSTITUTION;
//            return true;
//        } else {
//            if (getUnit() != null) {
//                entryType = LedgureEntry.EntryType.UNIT;
//                return true;
//            } else {
//                if (getLocation() != null) {
//                    entryType = LedgureEntry.EntryType.LOCATION;
//                    return true;
//                } else {
//                    if (getPerson() != null) {
//                        entryType = LedgureEntry.EntryType.PERSON;
//                        return true;
//                    } else {
//                        entryType = LedgureEntry.EntryType.OTHER;
//                        return false;
//                    }
//                }
//            }
//        }
        return false;

    }
    
    
    
    
    
    /**
     * Creates a new instance of PurchaseBillController
     */
    public InventoryReportController() {
    }

    /**
     * Getters and Setters
     */

    public BillFacade getBillFacade() {
        return billFacade;
    }

    public void setBillFacade(BillFacade billFacade) {
        this.billFacade = billFacade;
    }

    public BillItemFacade getBillItemFacade() {
        return billItemFacade;
    }

    public void setBillItemFacade(BillItemFacade billItemFacade) {
        this.billItemFacade = billItemFacade;
    }

    public ItemFacade getItemFacade() {
        return itemFacade;
    }

    public void setItemFacade(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    public DataModel<Item> getItems() {
        return new ListDataModel<Item>(getItemFacade().findBySQL("SELECT i FROM Item i WHERE i.retired=false ORDER By i.name"));
    }

    public void setItems(DataModel<Item> items) {
        this.items = items;
    }

    public MakeFacade getMakeFacade() {
        return makeFacade;
    }

    public void setMakeFacade(MakeFacade makeFacade) {
        this.makeFacade = makeFacade;
    }

    public DataModel<Make> getMakes() {
        return new ListDataModel(getMakeFacade().findBySQL("SELECT m FROM Make m WHERE m.retired=false ORDER BY m.name"));
    }

    public void setMakes(DataModel<Make> makes) {
        this.makes = makes;
    }

    public ModalFacade getModalFacade() {
        return modalFacade;
    }

    public void setModalFacade(ModalFacade modalFacade) {
        this.modalFacade = modalFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }



    public InstitutionFacade getInstitutionFacade() {
        return institutionFacade;
    }

    public void setInstitutionFacade(InstitutionFacade institutionFacade) {
        this.institutionFacade = institutionFacade;
    }

    public DataModel<Institution> getToInstitutions() {
        return new ListDataModel<Institution>(getInstitutionFacade().findBySQL("SELECT i FROM Institution i WHERE i.retired=false ORDER by i.name"));
    }

    public void setToInstitutions(DataModel<Institution> institutions) {
        this.toInstitutions = institutions;
    }

    public DataModel<Institution> getFromInstitutions() {
        return new ListDataModel<Institution>(getInstitutionFacade().findBySQL("SELECT i FROM Institution i WHERE i.retired=false ORDER by i.name"));
    }

    public void setFromInstitutions(DataModel<Institution> institutions) {
        this.fromInstitutions = institutions;
    }

    public DataModel<Unit> getFromUnits() {
//        return new ListDataModel<Unit>(getUnitFacade().findBySQL("SELECT u FROM Unit u WHERE u.retired=false AND u.institution.id = " + getBill().getFromInstitution().getId()));
        return null;
    }

    public void setFromUnits(DataModel<Unit> fromUnits) {
        this.fromUnits = fromUnits;
    }

    public DataModel<Location> getFromLocations() {
//        if (getBill().getFromUnit() != null) {
//            return new ListDataModel<Location>(getLocationFacade().findBySQL("SELECT l FROM Location l WHERE l.retired=false AND l.unit.id = " + getBill().getFromUnit().getId() + " ORDER BY l.name"));
//        }
        return null;
    }

    public void setFromLocations(DataModel<Location> locations) {
        this.fromLocations = locations;
    }

    public DataModel<Location> getToLocations() {
//        System.out.println("Getting ToLocations");
//        if (getBill().getToUnit() != null) {
//            System.out.println("Got Null while getting toLocations");
//            return new ListDataModel<Location>(getLocationFacade().findBySQL("SELECT l FROM Location l WHERE l.retired=false AND l.unit.id = " + getBill().getToUnit().getId() + " ORDER BY l.name"));
//        }
//        System.out.println("Got Null while getting toLocations");
        return null;
    }

    public void setToLocations(DataModel<Location> locations) {
        this.toLocations = locations;
    }

    public DataModel<Unit> getToUnits() {
//        if (getBill().getToInstitution() != null) {
//            return new ListDataModel<Unit>(getUnitFacade().findBySQL("SELECT u FROM Unit u WHERE u.retired=false AND u.institution.id=" + getBill().getToInstitution().getId() + " ORDER BY u.name"));
//        }
        return null;
    }

    public void setToUnits(DataModel<Unit> toUnits) {
        this.toUnits = toUnits;
    }

    public UnitFacade getUnitFacade() {
        return unitFacade;
    }

    public void setUnitFacade(UnitFacade unitFacade) {
        this.unitFacade = unitFacade;
    }

    public LocationFacade getLocationFacade() {
        return locationFacade;
    }

    public void setLocationFacade(LocationFacade locationFacade) {
        this.locationFacade = locationFacade;
    }

    public DataModel<Person> getFromPersons() {
//        if (getBill().getFromInstitution() != null) {
//            return new ListDataModel<Person>(getPersonFacade().findBySQL("SELECT p FROM Person p WHERE p.retired=false AND p.institution.id=" + getBill().getFromInstitution().getId() + " ORDER BY p.name"));
//        }
        return null;
    }

    public void setFromPersons(DataModel<Person> fromPersons) {
        this.fromPersons = fromPersons;
    }

    public PersonFacade getPersonFacade() {
        return personFacade;
    }

    public void setPersonFacade(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    public DataModel<Person> getToPersons() {
//        if (getBill().getToInstitution() != null) {
//            return new ListDataModel<Person>(getPersonFacade().findBySQL("SELECT p FROM Person p WHERE p.retired=false AND p.institution.id=" + getBill().getToInstitution().getId() + " ORDER BY p.name"));
//        }
        return null;
    }

    public void setToPersons(DataModel<Person> toPersons) {
        this.toPersons = toPersons;
    }

    public DataModel<Country> getCountries() {
        return new ListDataModel<Country>(getCountryFacade().findBySQL("SELECT c FROM Country c WHERE c.retired=false ORDER BY c.name"));
    }

    public void setCountries(DataModel<Country> countries) {
        this.countries = countries;
    }

    public CountryFacade getCountryFacade() {
        return countryFacade;
    }

    public void setCountryFacade(CountryFacade countryFacade) {
        this.countryFacade = countryFacade;
    }

    public ManufacturerFacade getManufacturerFacade() {
        return manufacturerFacade;
    }

    public void setManufacturerFacade(ManufacturerFacade manufacturerFacade) {
        this.manufacturerFacade = manufacturerFacade;
    }

    public DataModel<Manufacturer> getManufacturers() {
        return new ListDataModel<Manufacturer>(getManufacturerFacade().findBySQL("SELECT m FROM Manufacturer m WHERE m.retired=false ORDER BY m.name"));
    }

    public void setManufacturers(DataModel<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public SupplierFacade getSupplierFacade() {
        return supplierFacade;
    }

    public void setSupplierFacade(SupplierFacade supplierFacade) {
        this.supplierFacade = supplierFacade;
    }

    public DataModel<Supplier> getSuppliers() {
        return new ListDataModel<Supplier>(getSupplierFacade().findBySQL("SELECT s FROM Supplier s WHERE s.retired=false ORDER BY s.name"));
    }

    public void setSuppliers(DataModel<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public ItemUnitFacade getItemUnitFacade() {
        return itemUnitFacade;
    }

    public void setItemUnitFacade(ItemUnitFacade itemUnitFacade) {
        this.itemUnitFacade = itemUnitFacade;
    }

    public ItemUnitHistoryFacade getItemUnitHistoryFacade() {
        return itemUnitHistoryFacade;
    }

    public void setItemUnitHistoryFacade(ItemUnitHistoryFacade itemUnitHistoryFacade) {
        this.itemUnitHistoryFacade = itemUnitHistoryFacade;
    }

    public LedgureEntry getLedgureEntry() {
        return ledgureEntry;
    }

    public void setLedgureEntry(LedgureEntry ledgureEntry) {
        this.ledgureEntry = ledgureEntry;
    }

    public DataModel<LedgureEntry> getLedgureEntrys() {
        return ledgureEntrys;
    }

    public void setLedgureEntrys(DataModel<LedgureEntry> ledgureEntrys) {
        this.ledgureEntrys = ledgureEntrys;
    }

    public List<LedgureEntry> getLstLedgureEntrys() {
        return lstLedgureEntrys;
    }

    public void setLstLedgureEntrys(List<LedgureEntry> lstLedgureEntrys) {
        this.lstLedgureEntrys = lstLedgureEntrys;
    }
    
    
}
