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
    
}
