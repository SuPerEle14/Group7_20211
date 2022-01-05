package entity.subsystem_;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.InvalidTransactionAmountException;
import common.exception.InvalidVersionException;
import common.exception.NotEnoughBalanceException;
import common.exception.NotEnoughTransactionInfoException;
import common.exception.SuspiciousTransactionException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.InterbankTransaction;
import utils.Utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class InterbankSubsystemController implements Interbank {
	
	private static final Logger LOGGER = Utils.getLogger(InterbankSubsystemController.class.getName());

    /**
     * @param card : instance Information of Card
     * @param cost : transaction price
     * @param command : transaction request {"pay", "refund"}
     * @param content : transaction content
     * @return : eerCode
     */

    @Override
    public String processTransaction(CreditCard card, int cost, String command, String content) {
        reset();
        try {
            // convert to payment transaction
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createdAt = df.format(date);

            InterbankTransaction transaction= new InterbankTransaction();
            transaction.setCardCode(card.getCardCode());
            transaction.setOwner(card.getOwner());
            transaction.setCvvCode(Integer.toString(card.getCvvCode()));
            transaction.setDateExpired(card.getDateExpired());
            transaction.setCommand(command);
            transaction.setTransactionContent(content);
            transaction.setAmount(cost);
            transaction.setCreatedAt(createdAt);
            System.out.println(transaction);

            String transactString = new ObjectMapper().writeValueAsString(transaction);
            JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();

            //convert to request transaction
            JsonObject transToHash = new JsonObject();
            
            transToHash.addProperty("secretKey", "BqtSBMXPNng=");
            transToHash.add("transaction", transactionBody);
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            md.update(transToHash.toString().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            JsonObject sentJson = new JsonObject();
            sentJson.addProperty("version","1.0.1");
            sentJson.add("transaction",transactionBody);
            sentJson.addProperty("appCode", "CSrmgzSlENk=");
            sentJson.addProperty("hashCode", myHash);
            System.out.println("Process Transaction: " + sentJson);
            LOGGER.info("Result" + sentJson);

            InterbankBoundary interbank = new InterbankBoundary();
            String errorCode = interbank.processTransaction(sentJson);
            return errorCode;

        }catch (Exception e) {
            LOGGER.info("False to process Transaction!");
            return "08";
        }
    }

    /**
     * Reset money in Bank account
     */
    @Override
    public void reset(){
        JsonObject body = new JsonObject();
        InterbankBoundary interbank = new InterbankBoundary();
        String errorCode = interbank.reset();
        switch (errorCode) {
            case "00": 
            	LOGGER.info("Reset balance successfull!"); 
            	break;
            case "01":
    			throw new InvalidCardException();
    		case "02":
    			throw new NotEnoughBalanceException();
    		case "03":
    			throw new InternalServerErrorException();
    		case "04":
    			throw new SuspiciousTransactionException();
    		case "05":
    			throw new NotEnoughTransactionInfoException();
    		case "06":
    			throw new InvalidVersionException();
    		case "07":
    			throw new InvalidTransactionAmountException();
    		default:
    			throw new UnrecognizedException();
        }
    }
}
