package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoad;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasAVmsCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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
		balancing(aServerListWith(theServer), aListOfVms());
		assertThat(theServer, hasCurrentLoad(0.0d));
	}

	@Test
	public void balanceServerWithOneSlotCapacity_andOneSlotVm_fillServerWithOneVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), aListOfVms(theVm));
		assertThat(theServer, hasCurrentLoad(100.0d));
		assertThat("server should contain one vm", theServer.contain(theVm));

	}

	@Test
	public void balancingOneServerWithTenSlotCapacity_addOneSlotVm_fillsTheServerWithTenPercent() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), aListOfVms(theVm));
		assertThat(theServer, hasCurrentLoad(10.0d));
		assertThat("server should contain one vm", theServer.contain(theVm));
	}

	@Test
	public void balancingServerWithEnoughRoom_fillServerWithAllVms() {
		Server theServer = a(server().withCapacity(100));
		Vm firstVm = a(vm().ofSize(1));
		Vm secondVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), aListOfVms(firstVm, secondVm));
		assertThat(theServer, hasAVmsCountOf(2));
		assertThat("server 1 should contain vm 1", theServer.contain(firstVm));
		assertThat("server 1 should contain vm 2", theServer.contain(secondVm));
	}

	@Test
	public void vmshouldBeBalancedOnLessLoadedServerFirst() {
		Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoad(50.0d));
		Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoad(45.0d));
		Vm theVm = a(vm().ofSize(10));
		
		balancing(aServerListWith(moreLoadedServer, lessLoadedServer), aListOfVms(theVm));
		assertThat("more loaded server should not contain vm 1", !moreLoadedServer.contain(theVm));
		assertThat("less loaded server should contain vm 1", lessLoadedServer.contain(theVm));
	}

	@Test
	public void balancingServerWithNotEnoughRoom_shouldNotBeFilledWithTheVm(){
		Server theServer = a(server().withCapacity(10).withCurrentLoad(90.0d));
		Vm firstVm = a(vm().ofSize(2));
		balancing(aServerListWith(theServer), aListOfVms(firstVm));
		assertThat("server 1 should not contain vm 1", !theServer.contain(firstVm));

	
	}
	
	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] aListOfVms(Vm... vms) {
		return vms;
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}

}
