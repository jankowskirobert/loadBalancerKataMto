package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int i;

	public ServerBuilder withCapacity(int i) {
		this.i = i;
		return this;
	}

	public Server build() {
		return new Server();
	}
	

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

}
