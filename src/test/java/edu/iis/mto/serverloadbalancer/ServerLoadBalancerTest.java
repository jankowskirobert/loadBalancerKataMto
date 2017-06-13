package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serversStayEmpty() {
		Server theServer = a(server().withCapacity(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoad(0.0d));
	}

	private CurrentLoadPercentageMatcher hasCurrentLoad(double currentLoad) {
		return new CurrentLoadPercentageMatcher(currentLoad);
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms(Vm... vms) {
		return vms;
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

}
