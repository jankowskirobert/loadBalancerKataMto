package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double currentLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		if (currentLoad > 0) {
			int initialVmSize = (int) (currentLoad / (double) capacity * 100.0d);
			Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();
			server.addVm(initialVm);
		}
		return server;
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoad(double currentLoad) {
		this.currentLoad = currentLoad;
		return this;
	}

}
