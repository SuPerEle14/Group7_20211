package controller;

import entity.card.Card;
import utils.Configs;

import java.sql.SQLException;

public class CardLoginController extends BaseController{
	
	/**
	 * This {@code CardLoginController} class control the Login by card
	 * in our Eco Bike Rental project.
	 * 
	 * @author LamTT
	 *
	 */
	
	/**
     * This method Validate the Card Information to Login
     * @param card
     * @return boolean
     */	
    public boolean login(String cardCode, String password) throws SQLException {
        Card card = Card.login(cardCode, password);
        if(card!=null){
            Configs.card = card;
            return true;
        }
        return false;
    }
    
    
    /**
     * This method Validate the Card Information of User
     * @param card
     * @return boolean
     */
    public boolean validateCardInfo(String cardCode, String password){
        return true;
    }
    
    
    /**
     * This method Validate the Card Username of User
     * @param card
     * @return boolean
     */
    public boolean validateUsernameCard(String userName){
        if(userName.length() < 8) return false;
        if(userName.contains(" ")) return false;
        if(userName.contains("<")) return false;
        if(userName.contains("/")) return false;
        if(userName.contains("=")) return false;
        return true;
    }
    
    
    /**
     * This method Validate the Card Password of User
     * @param card
     * @return boolean
     */
    public boolean validatePasswordCard(String passWord){
        if(passWord.length() < 8) return false;
        if(passWord.contains(" ")) return false;
        if(passWord.contains("<")) return false;
        if(passWord.contains("/")) return false;
        if(passWord.contains("=")) return false;
        return true;
    }
}
