/*
 * MSc(Biomedical Informatics) Project
 *
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.component.menuitem.MenuItem;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@SessionScoped
public class Menu {

    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    MessageProvider messageProvider;
    
    MenuModel model;
    String temIx = "";

    public MessageProvider getMessageProvider() {
        if(messageProvider==null) messageProvider = new MessageProvider();
        return messageProvider;
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public String getTemIx() {
        return temIx;
    }

    public void setTemIx(String temIx) {
        this.temIx = temIx;
    }
    
    

    private String getLabel(String key) {
        return new MessageProvider().getValue(key);
    }

    public Menu() {
//        createMenu();
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void createMenu() {
        model = new DefaultMenuModel();
//        model.addSubmenu(cadreSubmenu());
        if (sessionController.privilege.isBmeView()) model.addSubmenu(biomedSubmenu());
//        if (sessionController.privilege.isVehicleView()) model.addSubmenu(transportSubmenu());
        if (sessionController.privilege.isDemographyView()) model.addSubmenu(demographySubmenu());
        if (sessionController.privilege.isCaderView()) model.addSubmenu(humanSubmenu());
        if (sessionController.privilege.isMsView()) model.addSubmenu(medicalSubmenu());
        if (sessionController.privilege.isInventoryView()) model.addSubmenu(inventorySubmenu());
        if (sessionController.privilege.isManageAccounts()) model.addSubmenu(adminSubmenu());

    }

    private Submenu cadreSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("hr"));
        
        

        item = new MenuItem();
        item.setValue(messageProvider.getValue("InstitutionTypes"));
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Institutions"));
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("designationsCategory"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu biomedSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("biomedical"));

        item = new MenuItem();
        item.setValue(messageProvider.getValue("InstitutionTypes"));
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Institutions"));
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("designationsCategory"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu transportSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("transport"));

        item = new MenuItem();
        item.setValue("Institution Types");
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("InstitutionTypes"));
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("designationsCategory"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu demographySubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("demography"));


        item = new MenuItem();
        item.setValue(messageProvider.getValue("edit"));
        item.setUrl("demography_edit.xhtml");
        submenu.getChildren().add(item);



        item = new MenuItem();
        item.setValue(messageProvider.getValue("import"));
        item.setUrl("demography_import_excel.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("display"));
        item.setUrl("demography_display_areas.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu humanSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("hr"));

        item = new MenuItem();
        item.setValue(messageProvider.getValue("InstitutionTypes"));
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Institutions"));
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("designationsCategory"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu medicalSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("msd"));

        item = new MenuItem();
        item.setValue(messageProvider.getValue("InstitutionTypes"));
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Institutions"));
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("designationsCategory"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu inventorySubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("inventory"));

        item = new MenuItem();
        item.setValue(messageProvider.getValue("editData"));
        item.setUrl("inventory_edit.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("purchase"));
        item.setUrl("inventory_purchase.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("issue"));
        item.setUrl("inventory_issue.xhtml");
        submenu.getChildren().add(item);

item = new MenuItem();
        item.setValue(messageProvider.getValue("Designations"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(messageProvider.getValue("requests"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("estimates"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(messageProvider.getValue("reports"));
        item.setUrl("inventory_reports.xhtml");
        submenu.getChildren().add(item);

        
        
        return submenu;
    }

    private Submenu adminSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(messageProvider.getValue("admin"));

        item = new MenuItem();
        item.setValue(messageProvider.getValue("activateAccounts"));
        item.setUrl("activate_users.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    //    private Submenu editSubmenu() {
//        Submenu submenu;
//        Submenu childSubmenu;
//        MenuItem item;
//
//        submenu = new Submenu();
//        submenu.setLabel(getLabel("editMenu"));
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("investigationMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("investigationCategoriesMenu"));
//        item.setUrl("/faces/ix_category.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("institutionsMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("institutionMenu"));
//        item.setUrl("/faces/institution.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("financeMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("expenseMenu"));
//        item.setUrl("/faces/expense.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("metadataMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("contactTypeMenu"));
//        item.setUrl("/faces/contact_type.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//        return submenu;
//    }
//
//    private Submenu fileSubmenu() {
//        Submenu submenu;
//        Submenu childSubmenu;
//        MenuItem item;
//
//        submenu = new Submenu();
//        submenu.setLabel(getLabel("fileMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("saveMenu"));
//        item.setUrl(getLabel("#"));
//
//        submenu.getChildren().add(item);
//
//        return submenu;
//
//    }
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
