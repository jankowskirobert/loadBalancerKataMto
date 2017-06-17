package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	public double currentPercentageLoad = 0.0d;
	public double capacity;

	private List<Vm> vms = new ArrayList<Vm>();
	
	public Server(double capacity) {
		super();
		this.capacity = capacity;
	}

	public boolean contain(Vm theVm) {
		// TODO Auto-generated method stub
		return vms.contains(theVm);
	}

	public void addVm(Vm vm) {
		this.currentPercentageLoad = loadOfVm(vm);
		this.vms.add(vm);
	}

	private double loadOfVm(Vm vm) {
		return (double)vm.size / (double)this.capacity * MAX_LOAD;
	}

	public int countVms() {
		return vms.size();
	}

	public boolean canFit(Vm vm) {
		
		return currentPercentageLoad + loadOfVm(vm) <= MAX_LOAD;
	}

}
