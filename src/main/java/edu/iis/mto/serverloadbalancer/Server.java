package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	public double currentProcentageLoad = 0.0d;
	public double capacity;

	public Server(double capacity) {
		super();
		this.capacity = capacity;
	}

	public boolean contain(Vm theVm) {
		// TODO Auto-generated method stub
		return true;
	}

	public void addVm(Vm vm) {
		this.currentProcentageLoad = (double)vm.size / (double)this.capacity * MAX_LOAD;
		
	}

}
