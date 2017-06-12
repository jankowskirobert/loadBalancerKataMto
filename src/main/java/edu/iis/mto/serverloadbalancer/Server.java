package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	public double currentLoadPercentage;
	public int capacity;
	public List<Vm> vms = new ArrayList<Vm>();

	public boolean contains(Vm theVm) {
		return vms.contains(theVm);
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public void addVm(Vm vm) {
		currentLoadPercentage = (double) vm.size / (double) this.capacity * MAX_LOAD;
		this.vms.add(vm);
	}

	public int countVms() {
		// TODO Auto-generated method stub
		return vms.size();
	}

}
