package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	public double currentPercentageLoad = 0.0d;
	private int capacity;

	public Server(int capacity) {
		this.capacity = capacity;
		
	}

	public boolean contains(Vm theVm) {
		return true;
	}

}
