package entity.subsystem_;

import java.util.logging.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entity.dock.Dock;
import lombok.extern.slf4j.Slf4j;
import utils.Utils;



public class InterbankBoundary {
	private static final Logger LOGGER = Utils.getLogger(InterbankBoundary.class.getName());
    private final String base_url = "https://ecopark-system-api.herokuapp.com/";

    public String reset(){

        try {
            JsonObject body = new JsonObject();
            body.addProperty("cardCode", "kscq2_group7_2021");
            body.addProperty("owner", "Group 7");
            body.addProperty("cvvCode", "125");
            body.addProperty("dateExpired", "1125");
            String reset_path = "api/card/reset-balance";
            String response = new HttpConnector().sendPatch(base_url + reset_path, body.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                System.out.println(responseJson);
                return responseJson.get("errorCode").getAsString();
            }
        } catch (Exception e){
        }
        return "08";
    }

    public String processTransaction(JsonObject sentJson) {
        // response from api
        try {
            String transaction_path = "api/card/processTransaction";
            String response =  new HttpConnector().sendPatch(base_url + transaction_path,sentJson.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                LOGGER.info("Transaction:" + responseJson);
                return responseJson.get("errorCode").getAsString();
            }
        } catch (Exception e) {
        	LOGGER.info("False to process Transaction");
        }
        return "08";
    }
}
