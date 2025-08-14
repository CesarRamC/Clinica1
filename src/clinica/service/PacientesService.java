/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.service;

import clinica.model.Pacientes;
import clinica.model.Usuario;
import clinica.util.AppContext;
import clinica.util.EntityManagerHelper;
import clinica.util.Mensaje;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author ivann
 */
public class PacientesService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public PacientesService(){
        
    }
    
    public void buscarPacientes(){
        try {
            Query qryPacientes= em.createNamedQuery("Pacientes.findAll", Pacientes.class);
            
            List<Pacientes> listaPacientes = qryPacientes.getResultList();
            AppContext.getInstance().set("Pacientes",   listaPacientes);
        } catch (NonUniqueResultException ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al consultar la lista de pacientes.");
        } catch (Exception ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al consultar la lista de pacientes.");
        }
    }
    


}
