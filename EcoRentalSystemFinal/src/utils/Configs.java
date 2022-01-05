package utils;

import entity.bike.Bike;
import entity.card.Card;
import entity.payment.RentTransaction;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Namlt Contains the configs for Rent Bike System Project
 */
public class Configs {
	// api constants
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";
	public static final Map<String, String> PAYMENT_RESULT_IMAGE = Map.of(
			"PAYMENT SUCCESSFUL!", "tickgreen.png",
			"PAYMENT FAILED!", "tickerror.png"
	);
	// demo data
	public static final String POST_DATA = "{"
			+ " \"secretKey\": \"BUXj/7/gHHI=\" ,"
			+ " \"transaction\": {"
			+ " \"command\": \"pay\" ,"
			+ " \"cardCode\": \"118609_group1_2020\" ,"
			+ " \"owner\": \"Group 1\" ,"
			+ " \"cvvCode\": \"185\" ,"
			+ " \"dateExpried\": \"1125\" ,"
			+ " \"transactionContent\": \"Pei debt\" ,"
			+ " \"amount\": 50000 "
			+ "}"
		+ "}";
	public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

	// database Configs
	public static final String DB_NAME = "aims";
	public static final String DB_USERNAME = System.getenv("DB_USERNAME");
	public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

	public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;

	// User info
	public static Card card = null;
	// User rental Bike
	public static Bike bike = null;
	// User transaction
	public static RentTransaction rentTransaction = null;
	// static resource
	public static final String IMAGE_PATH = "assets/images";
	public static final String DOCK_BIKE_PATH = "/views/fxml/bike_dock.fxml";
	public static final String DOCK_SCREEN_PATH = "/views/fxml/viewdock.fxml";
	public static final String RESULT_SCREEN_PATH = "/views/fxml/success_payment.fxml";
	public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
	public static final String BIKE_SCREEN_PATH = "/views/fxml/viewbike.fxml";
	public static final String CARD_LOGIN_SCREEN_PATH = "/views/fxml/cardlogin.fxml";
	public static final String CARD_SCREEN_PATH = "/views/fxml/viewcard.fxml";
	public static final String INVOICE_PATH = "/views/fxml/invoice.fxml";
	public static final String RENT_BIKE_PATH = "/views/fxml/rentbike.fxml";
	public static final String PAYMENT_SCREEN = "/views/fxml/payment.fxml";
	public static final String HOME_PATH  = "/views/fxml/home.fxml";
	public static final String HOME_DOCK_PATH = "/views/fxml/dock_home.fxml";
	public static final String POPUP_PATH = "/views/fxml/popup.fxml";
	public static final String VIEW_BIKE_RENT_SCREEN_PATH = "/views/fxml/viewbikerent.fxml";
	public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/returnbike.fxml";
	public static final String INFORMATION_SCREEN_PATH = "/views/fxml/infomation.fxml";

	public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);

}
