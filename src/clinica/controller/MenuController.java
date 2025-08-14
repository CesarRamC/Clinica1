/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clinica.controller;

import clinica.model.Cita;
import clinica.model.Medicos;
import clinica.model.Pacientes;
import clinica.service.CitaService;
import clinica.service.MedicosService;
import clinica.service.PacientesService;
import clinica.util.AppContext;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ivann
 */
public class MenuController extends Controller implements Initializable {

    @FXML
    private TextField txfCodigo;
    @FXML
    private Tab tabRegistrarCita;
    @FXML
    private ComboBox<Pacientes> cbxPacientes;
    @FXML
    private ComboBox<Medicos> cbxMedicos;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextArea txaDescripcion;
    @FXML
    private Tab tabModificarCita;

    int contador;

    PacientesService pacientesService;
    MedicosService medicosService;
    CitaService citasService;

    @FXML
    private ListView<Cita> lv_listaCitas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contador = 0;
        txfCodigo.setText(generarCodigoCita());
        medicosService = new MedicosService();
        pacientesService = new PacientesService();
        citasService = new CitaService();
        
        pacientesService.buscarPacientes();
        medicosService.buscarMedicos();
        citasService.buscarCitas();
        
        //pacientes = new ArrayList<>();

        // Obtener la lista de pacientes
        if (AppContext.getInstance().get("Pacientes") != null) {
            List<Pacientes> pacientes = (List<Pacientes>) AppContext.getInstance().get("Pacientes");

            // Asignar los pacientes al ComboBox
            // ¡Esta es la parte que falta en tu código!
            cbxPacientes.setItems(FXCollections.observableArrayList(pacientes));

            // Configurar la CellFactory para mostrar el nombre completo en la lista desplegable
            cbxPacientes.setCellFactory(cb -> new ListCell<Pacientes>() {
                @Override
                protected void updateItem(Pacientes paciente, boolean empty) {
                    super.updateItem(paciente, empty);
                    setText(empty ? null : paciente.getPacIdPersona().getCliNombreCompleto());
                }
            });

            // Configurar la ButtonCell para mostrar el nombre completo en el ComboBox cuando está cerrado
            cbxPacientes.setButtonCell(new ListCell<Pacientes>() {
                @Override
                protected void updateItem(Pacientes paciente, boolean empty) {
                    super.updateItem(paciente, empty);
                    setText(empty ? null : paciente.getPacIdPersona().getCliNombreCompleto());
                }
            });

            if (!cbxPacientes.getItems().isEmpty()) {
                cbxPacientes.getSelectionModel().selectFirst();
            }
        }

        // Obtener la lista de medicos
        if (AppContext.getInstance().get("Medicos") != null) {
            List<Medicos> medicos = (List<Medicos>) AppContext.getInstance().get("Medicos");

            // Asignar los pacientes al ComboBox
            // ¡Esta es la parte que falta en tu código!
            cbxMedicos.setItems(FXCollections.observableArrayList(medicos));

            // Configurar la CellFactory para mostrar el nombre completo en la lista desplegable
            cbxMedicos.setCellFactory(cb -> new ListCell<Medicos>() {
                @Override
                protected void updateItem(Medicos medico, boolean empty) {
                    super.updateItem(medico, empty);
                    setText(empty ? null : medico.getMedIdPersona().getCliNombreCompleto());
                }
            });

            // Configurar la ButtonCell para mostrar el nombre completo en el ComboBox cuando está cerrado
            cbxMedicos.setButtonCell(new ListCell<Medicos>() {
                @Override
                protected void updateItem(Medicos medico, boolean empty) {
                    super.updateItem(medico, empty);
                    setText(empty ? null : medico.getMedIdPersona().getCliNombreCompleto());
                }
            });

            if (!cbxMedicos.getItems().isEmpty()) {
                cbxMedicos.getSelectionModel().selectFirst();
            }
        }
        
           if (AppContext.getInstance().get("Citas") != null) {
                List<Cita> citas = (List<Cita>) AppContext.getInstance().get("Citas");
                lv_listaCitas.setItems(FXCollections.observableArrayList(citas));
                
                lv_listaCitas.setCellFactory(lv->new ListCell<Cita>(){
                    @Override
                    protected void updateItem(Cita cita, boolean empty){
                        super.updateItem(cita, empty);
                        
                        if(empty || cita == null){
                            setText("");
                        }else{
                            setText(cita.getCitCodigo()+"-"+cita.getCitHora());
                        }
                    }
                
                });
           }

    }

    @Override
    public void initialize() {

    }

    public String generarCodigoCita() {
        String prefijo = "CITA-";
        String numeroSecuencial = String.format("%03d", contador);
        contador++;
        return prefijo + numeroSecuencial;
    }

    public void limpiar() {
        txaDescripcion.setText("");
        txfCodigo.setText(generarCodigoCita());
        cbxPacientes.getSelectionModel().clearSelection();
        cbxMedicos.getSelectionModel().clearSelection();
    }

    @FXML
    private void onAction_agregarCita(ActionEvent event) {

        limpiar();
    }

    @FXML
    private void onAction_buscarCita(ActionEvent event) {
    }

}
