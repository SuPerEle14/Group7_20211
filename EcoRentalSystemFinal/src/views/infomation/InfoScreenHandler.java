package views.infomation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.HomeController;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class InfoScreenHandler extends BaseScreenHandler implements Initializable {

	public InfoScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		setImage();
		setScreenTitle("Policy");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		
	}
	
}
