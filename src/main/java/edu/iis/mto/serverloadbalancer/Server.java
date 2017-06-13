package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

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

}
