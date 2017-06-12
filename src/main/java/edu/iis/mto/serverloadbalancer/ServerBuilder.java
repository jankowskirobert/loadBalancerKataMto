package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	
	
	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int i) {
		this.capacity = i;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		if(initialLoad > 0) {
		int initialVmSize = (int) (initialLoad / (double)capacity * server.MAX_LOAD);
		Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();

		server.addVm(initialVm);
		}
		return server;
	}
	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initialLoad) {
		this.initialLoad = initialLoad;
		// TODO Auto-generated method stub
		return this;
	}
}
