package controller.employer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;

import application.MyJobLeh;
import application.Session;
import controller.Controller;
import dataAccess.sql.JobsDAO_Sql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.event.WorkEvent;
/**
 * Used in FileTransfer.fxml
 * @author NigelChen
 * @version a.2
 * @since a.2
 *
 */

public class FileTransferController extends Controller implements Initializable{
	@FXML
	private JFXButton btnHome;
	@FXML
	private JFXButton btnJobs;
	@FXML
	private JFXButton btnHistory;
	@FXML
	private JFXButton btnProfile;
	@FXML
	private JFXButton btnFeedback;
	@FXML
	private JFXButton btnLogout;
	@FXML
	private TextField fieldSearchBox;
	@FXML
	private Label lbJobName;
	@FXML
	private JFXListView<String> fileListView;
	@FXML
	private JFXButton btnImport;
	@FXML
	private JFXButton delete;
    @FXML
    private JFXButton btnBack;	
    @FXML 
    private JFXButton btnStorage;

    private static WorkEvent work;
	// Event Listener on JFXButton[#btnHome].onAction
	@FXML
	public void handleHome(ActionEvent event) {
		// TODO Autogenerated
	}
    @FXML
    void handleStorage(ActionEvent event) {
    	FileTransferDownloadController.setWork(work);
		try {
			m.swapScene("/employer/FileTransferDownload.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	// Event Listener on JFXButton[#btnJobs].onAction
	@FXML
	public void handleJobs(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnHistory].onAction
	@FXML
	public void handleHistory(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnProfile].onAction
	@FXML
	public void handleProfile(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnFeedback].onAction
	@FXML
	public void handleFeedBack(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnLogout].onAction
	@FXML
	public void handleLogout(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#fieldSearchBox].onAction
	@FXML
	public void handleSearchBox(ActionEvent event) {
		// TODO Autogenerated
	}
    @FXML
    void handleBack(ActionEvent event) {
    	try {
			m.swapScene("/employer/home.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	// Event Listener on JFXButton[#btnImport].onAction
	/**
	 * Select file by opening fileChooser so that user can easily choose the file
	 * Afterwards all processing will be handled by uploadFile method.
	 * @param event
	 */
	@FXML
	public void handleImport(ActionEvent event) {
		FileChooser fc = new FileChooser();
		List<File> selectedFiles = fc.showOpenMultipleDialog(null);
		
		boolean exist;
		
		if(selectedFiles != null){
			for(int i=0 ; i<selectedFiles.size() ; i++){
				exist = dataAccess.FileTransfer.uploadFile(selectedFiles.get(i), work.getJobTitle()+"_"+work.getCompanyName());
//						new JobsDAO_Sql().getEmployerOfJob(work.getEmployeeEmail()).getCompanyName());
				if(exist){
					
					fileListView.getItems().add(selectedFiles.get(i).getAbsolutePath());
					delete.setDisable(false);
					delete.setOnAction(e ->{
						for(int j =0;j<selectedFiles.size();j++){
							fileListView.getSelectionModel().getSelectedItem();
							selectedFiles.get(j).delete();
							fileListView.getItems().add(selectedFiles.get(j).getAbsolutePath());
						}
						
					});
					
					
				}
			}
		}
		else{
			System.out.println("File does not exist");
		}
		
//		dataAccess.FileTransfer.downloadFile("abc123");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lbJobName.setText(work.getJobTitle());
	}
	public static WorkEvent getWork() {
		return work;
	}
	public static void setWork(WorkEvent work) {
		FileTransferController.work = work;
	}
	
	
}