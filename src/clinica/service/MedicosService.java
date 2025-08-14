/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.service;

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
public class MedicosService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public MedicosService(){
        
    }
    
     public void buscarMedicos(){
        try {
            Query qryMedicos= em.createNamedQuery("Medicos.findAll", Medicos.class);
            
            List<Medicos> listaMedicos = qryMedicos.getResultList();
            AppContext.getInstance().set("Medicos",   listaMedicos);
        } catch (NonUniqueResultException ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al consultar la lista de médicos.");
        } catch (Exception ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al consultar la lista de médicos.");
        }
    }
    
}
