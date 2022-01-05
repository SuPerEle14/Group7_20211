package controller;

import entity.dock.Dock;

import java.sql.SQLException;
import java.util.List;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */

public class HomeController extends BaseController{
	
	/**
     * this method gets all Dock in DB and return back to home to display
     * @return List[Dock]
     * @throws SQLException
     */
    public List getAllDock() throws SQLException{
        return Dock.getAllDock();
    }
}
