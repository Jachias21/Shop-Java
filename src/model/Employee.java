package model;

import main.Logable;

public class Employee extends Person implements Logable {
	
    public Employee(String name) {
		super(name);

	}

	private int employeeId;
    private static final int USER = 123;
    private static final String PASSWORD = "test";

    public boolean login(int employeeId, String password) {
        return employeeId == USER && password.equals(PASSWORD);
    }


}
