package controller;

import utils.Configs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import entity.bike.Bike;

/**
 * This {@code ReturnBikeController} class control the Return Bike of user
 * in our Bike Rental project.
 * 
 * @author LamTT
 *
 */

public class ReturnBikeController extends BaseController{
	
	/**
     * This method Validate the Card Number 
     * @param startTime Time to rent Bike
     * @param endTime Time to return Bike
     * @return boolean
     */
	
    public int calculateFee(String startTime, String endTime){
        // Custom date format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();//as given
        long iMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        if(iMinutes==0){
            return 10000;
        }
        if(Configs.bike.getRemainBattery()<0){
        	// price = minute x numOfSeat x Fees/minutes
            return (int) iMinutes*Configs.bike.getNumOfSeat()*Configs.bike.getRentFees();
        }else{
        	// price = minute x numOfSeat x Fees/minutes (penalty time: x2)
            return (int) iMinutes*Configs.bike.getNumOfSeat()*Configs.bike.getRentFees()*2;
        }
    }
}
