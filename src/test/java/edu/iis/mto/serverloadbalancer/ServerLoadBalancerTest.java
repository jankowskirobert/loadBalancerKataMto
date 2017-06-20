package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasCurrentLoadOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balanceServerWithNoVMs() {
		Server theServer = a(server().withCapacity(1));
		balance(serverListWith(theServer), emptyListOfVMs());
		MatcherAssert.assertThat("server has no vms", theServer, hasCurrentLoadOf(0.0d));
	}
	
	@Test
	public void balanceOneServerWithCapacityOfOne_oneVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balance(serverListWith(theServer), vmsListOf(theVm));
		MatcherAssert.assertThat("server has no vms", theServer, hasCurrentLoadOf(0.0d));
		MatcherAssert.assertThat("server has one vms", theServer.contains(theVm));
	}

	private Vm[] vmsListOf(Vm... vms) {
		return vms;
	}

	private Vm a(VmBuilder builder) {
		return builder.build();
	}

	private VmBuilder vm() {
		return new VmBuilder();
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}


	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] emptyListOfVMs() {
		return new Vm[]{};
	}

	private Server[] serverListWith(Server... servers) {
		return servers;
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
