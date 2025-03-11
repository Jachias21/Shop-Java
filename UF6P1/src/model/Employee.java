package model;

import main.Logable;

import java.sql.SQLException;

import dao.DaoImplJDBC;
import dao.DaoImplMongoDb;
import dao.dao;

public class Employee extends Person implements Logable {
	
    public Employee(String name) {
		super(name);
		// this.dao = new DaoImplJDBC(); // Asignar a atributo dao un objeto del constructor DaoImplJDBC
		this.dao = new DaoImplMongoDb();

	}
    private dao dao; 
	private int employeeId;
   // private static final int USER = 123;
   // private static final String PASSWORD = "test";

    public boolean login(int employeeId, String password) throws SQLException {
        //return employeeId == USER && password.equals(PASSWORD);
    	boolean isAuthenticed = false;
    	
    	try {
    		dao.connect();
    		Employee employee = dao.getEmployee(employeeId, password);
    		if(employee != null) {
    			isAuthenticed = true;
    		} 
    		dao.disconnect();
    	}
    catch (Exception e) {
    	e.printStackTrace();
    }
    	return isAuthenticed;
    }
    
    //Get & Set dao
    public dao getDao() {
        return dao;
    }
    
    public void setDao(dao dao) {
        this.dao = dao;
    }


}
