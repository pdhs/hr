/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.bean;

import gov.sp.health.entity.Unit;
import gov.sp.health.entity.AppImage;
import gov.sp.health.facade.UnitFacade;
import gov.sp.health.facade.AppImageFacade;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.UploadedFile;
import sun.awt.image.codec.JPEGImageDecoderImpl;
import sun.awt.image.codec.JPEGImageEncoderImpl;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author buddhika
 */
@ManagedBean
@SessionScoped
public class ImageController {

    StreamedContent scImage;
    StreamedContent scImageById;
    private UploadedFile file;
    @EJB
    AppImageFacade appImageFacade;
    @EJB
    UnitFacade unitFacade;
    Unit unit;
    AppImage appImage;
    List<AppImage> appImages;

    public StreamedContent getScImageById() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getRenderResponse()) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            AppImage temImg = getAppImageFacade().find(Long.valueOf(id));
            return  new DefaultStreamedContent(new ByteArrayInputStream(temImg.getBaImage()), temImg.getFileType());
        }
//        String id = context.getExternalContext().getRequestParameterMap().get("id");
//        try {
//            System.out.println("Getting the id as " + Long.valueOf(id));
//            AppImage temImg = getAppImageFacade().find(Long.valueOf(id));
//            return new DefaultStreamedContent(new ByteArrayInputStream(temImg.getBaImage()), temImg.getFileType());
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return null;
//        }
    }

    public void setScImageById(StreamedContent scImageById) {
        this.scImageById = scImageById;
    }

    public StreamedContent getScImage() {
        return scImage;
    }

    public List<AppImage> getAppImages() {
        return appImages;
    }

    public void setAppImages(List<AppImage> appImages) {
        this.appImages = appImages;
    }

    public void setScImage(StreamedContent scImage) {
        this.scImage = scImage;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public UnitFacade getUnitFacade() {
        return unitFacade;
    }

    public void setUnitFacade(UnitFacade unitFacade) {
        this.unitFacade = unitFacade;
    }

    public AppImage getAppImage() {
        return appImage;
    }

    public void setAppImage(AppImage appImage) {
        this.appImage = appImage;
    }

    public AppImageFacade getAppImageFacade() {
        return appImageFacade;
    }

    public void setAppImageFacade(AppImageFacade appImageFacade) {
        this.appImageFacade = appImageFacade;
    }

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void dispplayUnitImage() {
        try {
            appImage = getAppImageFacade().findFirstBySQL("Select ai from AppImage ai Where ai.unit.id = " + getUnit().getId());
            System.out.println("Diaplaying Image " + appImage.toString());
            scImage = new DefaultStreamedContent(new ByteArrayInputStream(getAppImage().getBaImage()), getAppImage().getFileType());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void dispplayUnitImageById() {
        try {
            appImage = getAppImageFacade().findFirstBySQL("Select ai from AppImage ai Where ai.unit.id = " + getUnit().getId());
            System.out.println("Diaplaying Image " + appImage.toString());
            scImage = new DefaultStreamedContent(new ByteArrayInputStream(getAppImage().getBaImage()), getAppImage().getFileType());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dispplayUnitImages() {
        if (unit == null) {
            appImages = null;
        }
        dispplayUnitImage();
        List<StreamedContent> lstSc = new ArrayList<StreamedContent>();
        List<AppImage> lstImgs = getAppImageFacade().findBySQL("Select ai from AppImage ai Where ai.unit.id = " + getUnit().getId());
        System.out.println("Going to array");
        for (AppImage ai : lstImgs) {
            System.out.println("within array");
            try {
                lstSc.add(new DefaultStreamedContent(new ByteArrayInputStream(ai.getBaImage()), ai.getFileType()));
                System.out.println("completed Try");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        appImages = lstImgs;
    }

    public void saveUnitImage() {
        InputStream in;
        if (unit == null) {
            JsfUtil.addErrorMessage("Please select a unit");
            return;
        }
        if (file == null) {
            JsfUtil.addErrorMessage("Please upload an image");
            return;
        }
        JsfUtil.addSuccessMessage(file.getFileName());
        try {
            appImage = new AppImage();
            appImage.setFileName(file.getFileName());
            appImage.setFileType(file.getContentType());
            appImage.setUnit(unit);
            JsfUtil.addSuccessMessage(file.getFileName());
            in = file.getInputstream();
            appImage.setBaImage(IOUtils.toByteArray(in));
            appImageFacade.create(appImage);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public byte[] PersistentImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new JPEGImageEncoderImpl(out).encode(image);
        return out.toByteArray();
    }

    public Image makeImage(byte[] data) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        return new JPEGImageDecoderImpl(in).decodeAsBufferedImage();
    }
}
