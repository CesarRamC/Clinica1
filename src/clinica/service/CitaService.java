/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.service;

import clinica.model.Cita;
import clinica.model.Medicos;
import clinica.util.AppContext;
import clinica.util.EntityManagerHelper;
import clinica.util.Mensaje;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author ivann
 */
public class CitaService {
     EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public CitaService(){
        
    }
    
     public Cita guardarCita(Cita cita) {
        try { 
            et = em.getTransaction();
            et.begin();
            em.persist(cita);
            et.commit();
            em.refresh(cita);
            Mensaje.show(Alert.AlertType.INFORMATION, "INFORMACIÓN", "La cita se registró correctamente.");
            return cita;
        } catch (Exception ex) {
            if (et != null && et.isActive()) et.rollback();
            System.out.println("CitaService.guardarCita: " + ex);
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrió un error al registrar la cita.");
            return null;
        }
    }
    
      public void buscarCitas(){
        try {
            Query qryCitas= em.createNamedQuery("Cita.findAll", Cita.class);
            
            List<Cita> listaCitas = qryCitas.getResultList();
            AppContext.getInstance().set("Citas",   listaCitas);
        } catch (NonUniqueResultException ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al consultar la lista de citas.");
        } catch (Exception ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al consultar la lista de citas.");
        }
    }
      
      /*public boolean eliminarCita(Long id) {
        try {
            Cita c = em.find(Cita.class, id);
            if (c == null) {
                Mensaje.show(Alert.AlertType.WARNING, "ATENCIÓN", "No se encontró la cita.");
                return false;
            }
            et = em.getTransaction();
            et.begin();
            em.remove(c);
            et.commit();
            Mensaje.show(Alert.AlertType.INFORMATION, "INFORMACIÓN", "La cita se eliminó correctamente.");
            return true;
        } catch (Exception ex) {
            if (et != null && et.isActive()) et.rollback();
            System.out.println("CitaService.eliminarCita: " + ex);
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrió un error al eliminar la cita.");
            return false;
        }
    }*/
    
}
