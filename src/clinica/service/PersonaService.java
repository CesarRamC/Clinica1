/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.service;

import clinica.model.Medicos;
import clinica.model.Pacientes;
import clinica.model.Persona;
import clinica.model.PersonaDto;
import clinica.model.Usuario;
import clinica.model.UsuarioDto;
import clinica.util.AppContext;
import clinica.util.EntityManagerHelper;
import clinica.util.FlowController;
import clinica.util.Mensaje;
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
public class PersonaService {

    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    UsuarioService usuarioService;
    PacientesService pacienteService;

    public PersonaService() {

    }

    public void guardarPersona(PersonaDto personaDto, UsuarioDto usuarioDto, String tipo) {
        try {

            usuarioService = new UsuarioService();
            Usuario usuario = new Usuario();
            Persona per = new Persona();
            usuario = usuarioService.guardarUsuario(usuarioDto);
            Usuario usu = null;
            et = em.getTransaction();
            et.begin();
            usuarioService.buscarUsuarioNombre(usuario.getUsuUsuario());
            if (AppContext.getInstance().get("BusquedaUsuarioNombre") != null) {
                usu = (Usuario) AppContext.getInstance().get("BusquedaUsuarioNombre");
            }
            Persona persona = new Persona(personaDto);
            persona.setCliIdUsuario(usu);
            em.persist(persona);
            em.flush();
            et.commit();
            
            buscarPersonaPorUsuario(usu);
            if (AppContext.getInstance().get("Persona") != null) {
                
                per = (Persona) AppContext.getInstance().get("Persona");
               
                Pacientes paciente = new Pacientes();
                paciente.setPacIdPersona(per);
                
                Medicos medico = new Medicos();
                medico.setMedIdPersona(per);
                
                 et = em.getTransaction();
                 et.begin();
                if ("Paciente".equals(tipo)) {
                    em.persist(paciente);
                      Mensaje.show(Alert.AlertType.INFORMATION, "INFORMACIÓN", "El paciente se guardó correctamente.");
                }else{
                    Mensaje.show(Alert.AlertType.INFORMATION, "INFORMACIÓN", "El médico se guardó correctamente.");
                    em.persist(medico);
                }
                 em.flush();
                 et.commit();
                 FlowController.getInstance().goView("InicioSesion");
            }
           
          
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("service:   " + ex);
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al guardar el usuario.");
        }
    }
    
    public void buscarPersonaPorUsuario(Usuario usuario){
        try {
            Query qryPersona= em.createNamedQuery("Persona.findByIdUsuario", Persona.class);
            qryPersona.setParameter("cliIdUsuario", usuario);
            qryPersona.getSingleResult();//Si se necesita obtener una lista sería cambiar getSingleResult por getResultList 
            AppContext.getInstance().set("Persona", qryPersona.getSingleResult());
        } catch (NoResultException ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "No existe un usuario con el nombre o contraseña ingresados.");
        } catch (NonUniqueResultException ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al buscar la persona.");
        } catch (Exception ex) {
            Mensaje.show(Alert.AlertType.ERROR, "ERROR", "Ocurrio un error al buscar la persona");
        }
    }
    

}
