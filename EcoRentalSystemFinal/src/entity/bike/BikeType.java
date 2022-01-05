package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.db.AIMSDB;
import utils.Utils;

public class BikeType {
	private static final Logger LOGGER = Utils.getLogger(BikeType.class.getName());
	
	protected Statement stm;
	
	private int ID;
	private String type;
	private int rentFees;
	
	public BikeType() throws SQLException{
        stm = AIMSDB.getConnection().createStatement();
    }
	
	public BikeType(int ID, String type, int rentFees) {
		this.ID = ID;
		this.type = type;
		this.rentFees = rentFees;
	}
	
	public BikeType getBikeTypeById(int id) throws SQLException{
        String sql = "SELECT * FROM biketype where ID=" + id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            return new BikeType(res.getInt("ID"), res.getString("type"), res.getInt("rentFees"));
        }
        return new BikeType(99, "UnType", 500);
    }
	
	public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
	
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getRentFees() {
        return rentFees;
    }

    public void setRentFees(int rentFees) {
        this.rentFees = rentFees;
    }
}
