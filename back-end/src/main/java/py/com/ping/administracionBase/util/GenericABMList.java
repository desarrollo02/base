/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import py.com.ping.utilitarios.interfaces.GenericListValidation;
import py.com.ping.utilitarios.jpa.EntidadABM;

/**
 *
 * @author gdb <dominbla@gmail.com>
 * @param <T>
 */
public class GenericABMList<T extends EntidadABM> {

    protected List<T> objectList;
    private T objectEdit;
    private T objectDelete;
    private T objectView;
    private Class<T> clase;
    private boolean esModificarDetalle;
    private boolean esABMPanel;
    /**
     * @return the objectList
     */
    public List<T> getObjectList() {
        if (objectList == null) {
            objectList = new ArrayList<T>();
        }
        return objectList;
    }

    /**
     * @param objectList the objectList to set
     */
    public void setObjectList(List<T> objectList) {
        this.objectList = objectList;
    }

    /**
     * @return the objectEdit
     */
    public T getObjectEdit() {
        return objectEdit;
    }

    /**
     * @param objectEdit the objectEdit to set
     */
    public void setObjectEdit(T objectEdit) {
        if (objectEdit != null) {
            objectView = objectEdit;
        }
        this.objectEdit = objectEdit;
    }

    /**
     * @return the objectView
     */
    public T getObjectView() {
        if (objectView == null) {
            try {
                objectView = (T) this.getClase().newInstance();
            } catch (InstantiationException ex) {
            } catch (IllegalAccessException ex) {
            }
        }
        return objectView;
    }

    /**
     * @param objectView the objectView to set
     */
    public void setObjectView(T objectView) {
        this.objectView = objectView;
    }

    /**
     * @return the objectDelete
     */
    public T getObjectDelete() {
        return objectDelete;
    }

    public void deleteObject(T objectDelete) {
    }

    /**
     * @param objectDelete the objectDelete to set
     */
    public void setObjectDelete(T objectDelete) {
        this.objectDelete = objectDelete;
    }

    /*validaciones*/
    protected boolean validaciones() {
        try {
             System.err.println("**********llegueV1");
            Set<ConstraintViolation<T>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(getObjectView(), GenericListValidation.class);
             System.err.println("**********llegueV2");
            if (constraintViolations.size() > 0) {
                 System.err.println("**********llegueV3");
                for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                     System.err.println("**********llegueV4"+constraintViolation.getMessage());
                    if (constraintViolation.getMessage() != null) {
                        mensajeAlerta(constraintViolation.getMessage());
                    }

                }
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.err.println("**********llegueError"+e.getMessage());
        }
        return false;
    }

    /**
     * Agregar el objeto seleccionado a la lista
     */
    public void addObject() {
        if (validaciones()) {
            if (esModificarDetalle) {
                List<T> objectAux = new ArrayList<T>();
                for (T edit : getObjectList()) {
                    if (edit.getIndiceLista() == getObjectView().getIndiceLista()) {
                        objectAux.add(objectView);
                    } else {
                        objectAux.add(edit);
                    }
                }
                getObjectList().clear();
                objectList = objectAux;
            } else {
                Integer indice = -1;

                if (getObjectList().size() > 0) {
                    indice = getObjectList().get(getObjectList().size() - 1).getIndiceLista();
                }
                objectView.setIndiceLista(indice + 1);
                getObjectList().add(cargaObjeto(objectView));

            }
            cleanObject();
        }
    }
    
   
    

    public void addObjectReverseOrder() {
        System.err.println("**********llegue1");
        if (validaciones()) {
            System.err.println("**********llegue2");
            if (esModificarDetalle) {
                 System.err.println("**********llegue4");
                List<T> objectAux = new ArrayList<T>();
                for (T edit : getObjectList()) {
                    if (edit.getIndiceLista() == getObjectView().getIndiceLista()) {
                        objectAux.add(objectView);
                    } else {
                        objectAux.add(edit);
                    }
                }
                getObjectList().clear();
                objectList = objectAux;
            } else {
                 System.err.println("**********llegue3");
                Integer indice = -1;

                if (getObjectList().size() > 0) {
                    indice = getObjectList().get(0).getIndiceLista();
                }
                objectView.setIndiceLista(indice + 1);
                T aux = cargaObjeto(objectView);
                getObjectList().add(0, aux);
                 System.err.println("**********llegue5");
            }
            cleanObject();
             System.err.println("**********llegue6");
        }
    }

    private T cargaObjeto(T object) {
        T obj = null;
        try {
            obj = (T) this.getClase().newInstance();
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        obj = object;
        return obj;
    }

    /**
     * @param objectListParam Este metodo es utilizado para los casos en donde
     * la lista se obtiene desde la BD. generalmente esto ocurre en los
     * cabecera/detalle en la seccion de busqueda - carga comprobantes para
     * cargar el detalle. Obs: el @param List<T> objectList debe ya poseer un id
     * de la BD
     */
    public void cargaLista(List<T> objectListParam) {
        Integer indiceA = -1;
        if (objectListParam != null && !objectListParam.isEmpty()) {
            for (T c : objectListParam) {
                indiceA++;
                c.setIndiceLista(indiceA);
            }
        }
        setObjectList(objectListParam);
    }

    public void setDeleteObject(T obj) {
        int i = 0;
        for (T del : getObjectList()) {
            if (del.getIndiceLista() == obj.getIndiceLista()) {
                getObjectList().remove(i);
                break;
            }
            i++;
        }
    }

    /**
     * @return the clase
     */
    public Class<T> getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(Class<T> clase) {
        this.clase = clase;
    }

    public void cleanObject() {
        objectView = null;
        getObjectView();
        setEsModificarDetalle(false);
    }

    public void cleanAll() {
        objectView = null;
        getObjectList().clear();
        getObjectView();

    }
    
    public void cleanObjectViewAndEdit(){
        objectView = null;
        objectEdit = null;
        getObjectView();
    }

    public void cleanAllAndObjectEdit() {
        objectView = null;
        objectEdit = null;
        getObjectList().clear();
        getObjectView();

    }

    /**
     * @return the esModificarDetalle
     */
    public boolean isEsModificarDetalle() {
        return esModificarDetalle;
    }

    /**
     * @param esModificarDetalle the esModificarDetalle to set
     */
    public void setEsModificarDetalle(boolean esModificarDetalle) {
        this.esModificarDetalle = esModificarDetalle;
    }

    
    
    /**
     * @return the esABMPanel
     */
    public boolean isEsABMPanel() {
        return esABMPanel;
    }

    /**
     * @param esABMPanel the esABMPanel to set
     */
    public void setEsABMPanel(boolean esABMPanel) {
        this.esABMPanel = esABMPanel;
    }
    
    public boolean verificarDuplicado(){
        return getObjectList().stream().anyMatch(det->det.equals(getObjectView()));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Mensajes">
    protected FacesMessage facesMsg;

    protected void mensajeExito(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void sortList(final int order) {
        if (objectList != null && !objectList.isEmpty()) {
            Collections.sort(objectList, new Comparator<T>() {

                @Override
                public int compare(T o1, T o2) {
                    if (order > 0) {
                        return o1.getIndiceLista() < o2.getIndiceLista() ? -1
                                : o1.getIndiceLista() > o2.getIndiceLista() ? 1 : 0;
                    } else {
                        return o2.getIndiceLista() < o1.getIndiceLista() ? -1
                                : o2.getIndiceLista() > o1.getIndiceLista() ? 1 : 0;
                    }

                }
            });
        }

    }

    //</editor-fold>
}
