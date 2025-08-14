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
import clinica.util.Mensaje;
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
 * @author ivann --Nuevo
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
    
    /*private boolean validarCampos() {
    String usuarioTxt         = txfUsuario.getText();
    String pass               = txfContrasena.getText();
    String nombre             = txfNombreCompleto.getText();
    String cedula             = txfCedula.getText();
    String anioNacimientoStr  = txfAnioNacimiento.toString();
    String telefono           = txfTelefono.getText();
    String correo             = txtCorreoElectronico.getText();
    String genero             = cbxGenero.getValue();
    String tipoUsuario        = cbxTipoUsuario.getValue();
    
    if (tipoUsuario == null || tipoUsuario.trim().isEmpty()) {//Paciente o Medico
            Mensaje.showAndWait("Validación", "Tipo de usuario", "Debe seleccionar Paciente o Médico");
            return false;
        }
    
    if (usuarioTxt == null || usuarioTxt.trim().isEmpty() || usuarioTxt.length() > 100) {//Validacion del usuario
        Mensaje.showAndWait("Validación", "Usuario inválido",
                "El usuario es obligatorio y no debe superar 100 caracteres");
        return false;
    }
    
    if (pass == null || pass.length() != 8) {//validar la contraseña
        Mensaje.showAndWait("Validación", "Contraseña inválida",
                "La contraseña debe tener exactamente 8 caracteres");
        return false;
    }

    if (nombre == null || nombre.length() < 3 || nombre.length() > 100) {
        Mensaje.showAndWait("Validación", "Nombre inválido", "El nombre debe tener entre 3 y 100 caracteres");
        return false;
    }

    if (cedula == null || !cedula.matches("\\d{9}")) {
        Mensaje.showAndWait("Validación", "Cédula inválida", "La cédula debe contener exactamente 9 dígitos");
        return false;
    }

    int anioActual = LocalDate.now().getYear();
    try {
        int anioNacimiento = Integer.parseInt(anioNacimientoStr);
        if (anioNacimiento < 1900 || anioNacimiento >= anioActual) {
            Mensaje.showAndWait("Validación", "Año de nacimiento inválido", "El año debe estar entre 1900 y " + (anioActual - 1));
            return false;
        }
    } catch (NumberFormatException e) {
        Mensaje.showAndWait("Validación", "Año inválido", "Debe ingresar un año válido");
        return false;
    }

    if (telefono == null || !telefono.matches("\\d{8}")) {
        Mensaje.showAndWait("Validación", "Teléfono inválido", "El teléfono debe contener exactamente 8 dígitos");
        return false;
    }

    if (correo == null || !correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        Mensaje.showAndWait("Validación", "Correo inválido", "Ingrese un correo electrónico válido");
        return false;
    }

    if (genero == null || genero.trim().isEmpty()) {
        Mensaje.showAndWait("Validación", "Género inválido", "Debe seleccionar un género");
        return false;
    }

    return true;
}*/

    @FXML
    private void onAction_Registrar(ActionEvent event) {
        
       // if (!validarCampos()) return;
        usuario = new Usuario();
        persona = new Persona();
  
        usuario.setUsuUsuario(txfUsuario.getText());
        usuario.setUsuContrasena(txfContrasena.getText());
        usuario.setUsuEstado("A");
        usuarioDto = new UsuarioDto(usuario);
        
             
        persona.setCliNombreCompleto(txfNombreCompleto.getText());
        persona.setCliCedula(txfCedula.getText());
        persona.setCliTelefono(txfTelefono.getText());
        /*if(null == cbxGenero.getValue()){
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
        }*/
        persona.setCliCorreoElectronico(txtCorreoElectronico.getText());
        LocalDate fecha =  txfAnioNacimiento.getValue();
        persona.setCliAnioNacimiento(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        persona.setCliIdUsuario(usuario);
        personaDto = new PersonaDto(persona);
        personaService.guardarPersona(personaDto, usuarioDto,cbxTipoUsuario.getValue());
        
        Mensaje.showAndWait("Éxito", "Registro", "Usuario, Persona y " +cbxTipoUsuario.getValue()  + " guardados correctamente.");
 
    }

    //En realidad no se elimina, sino que se le modifica el estado a Inactivo
    @FXML
    private void onAction_eliminarUsuario(ActionEvent event) {
        usuarioService.desactivarUsuario(txfUsuario.getText());
    }
    
     //Metodo para limpiar el formulario (TextFields)
    /*private void limpiarFormulario() {
   
    txfNombreCompleto.clear();
    txfCedula.clear();
    txfAnioNacimiento.setValue(null);
    txfTelefono.clear();
    txtCorreoElectronico.clear();
    txfUsuario.clear();
    txfContrasena.clear();

    // ComboBox
    if (cbxGenero != null) cbxGenero.getSelectionModel().clearSelection();
    if (cbxTipoUsuario != null) cbxTipoUsuario.getSelectionModel().clearSelection();

    // Se coloca en el primer campo para registrar
    txfNombreCompleto.requestFocus();
    }*/

}
