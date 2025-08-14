/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clinica.controller;

import clinica.model.Persona;
import clinica.model.PersonaDto;
import clinica.model.Usuario;
import clinica.model.UsuarioDto;
import clinica.service.PersonaService;
import clinica.service.UsuarioService;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ivann
 */
public class RegistroController extends Controller implements Initializable {

    @FXML
    private TextField txfNombreCompleto;
    @FXML
    private TextField txfCedula;
    @FXML
    private DatePicker txfAnioNacimiento;
    @FXML
    private ComboBox<String> cbxGenero;
    @FXML
    private TextField txfTelefono;
    @FXML
    private TextField txtCorreoElectronico;
    @FXML
    private TextField txfUsuario;
    @FXML
    private PasswordField txfContrasena;

    ObservableList<String> listaGenero;

    ObservableList<String> listaTipoUsuario;

    @FXML
    private ComboBox<String> cbxTipoUsuario;

    private Usuario usuario;
    
    private UsuarioDto usuarioDto;
    
    private Persona persona;
    
    private PersonaDto personaDto;

    private UsuarioService usuarioService;
    
    private PersonaService personaService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaGenero = FXCollections.observableArrayList();
        listaGenero.addAll("Masculino", "Femenino", "Otro");
        cbxGenero.setItems(listaGenero);
        listaTipoUsuario = FXCollections.observableArrayList();
        listaTipoUsuario.addAll("Paciente", "Médico");
        cbxTipoUsuario.setItems(listaTipoUsuario);
        //usuarioService = new UsuarioService();
        personaService = new PersonaService();
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onAction_Registrar(ActionEvent event) {
        
        usuario = new Usuario();
        persona = new Persona();
  
        usuario.setUsuUsuario(txfUsuario.getText());
        usuario.setUsuContrasena(txfContrasena.getText());
        usuario.setUsuEstado("A");
        usuarioDto = new UsuarioDto(usuario);
        
        persona.setCliNombreCompleto(txfNombreCompleto.getText());
        persona.setCliCedula(txfCedula.getText());
        persona.setCliTelefono(txfTelefono.getText());
        if(null == cbxGenero.getValue()){
            persona.setCliGenero("O");
        }else switch (cbxGenero.getValue()) {
            case "Masculino":
                persona.setCliGenero("M");
                break;
            case "Femenino":
                persona.setCliGenero("F");
                break;
            default:
                persona.setCliGenero("O");
                break;
        }
        persona.setCliCorreoElectronico(txtCorreoElectronico.getText());
        LocalDate fecha =  txfAnioNacimiento.getValue();
        persona.setCliAnioNacimiento(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        persona.setCliIdUsuario(usuario);
        personaDto = new PersonaDto(persona);
        personaService.guardarPersona(personaDto, usuarioDto,cbxTipoUsuario.getValue());
 
    }

    //En realidad no se elimina, sino que se le modifica el estado a Inactivo
    @FXML
    private void onAction_eliminarUsuario(ActionEvent event) {
        usuarioService.desactivarUsuario(txfUsuario.getText());
    }

}
