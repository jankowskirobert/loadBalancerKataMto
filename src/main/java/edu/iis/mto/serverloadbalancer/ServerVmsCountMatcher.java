package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server>{

	private int expectedVmsCount;

	public ServerVmsCountMatcher(int expectedVmsCount) {
		this.expectedVmsCount = expectedVmsCount;
		// TODO Auto-generated constructor stub
	}

	public void describeTo(Description description) {
		description.appendText("a server with vms count ").appendValue(expectedVmsCount);
		
	}
@Override
protected void describeMismatchSafely(Server item, Description description) {
	// TODO Auto-generated method stub
	description.appendText("a server with vms count ").appendValue(item.countVms());
}
	@Override
	protected boolean matchesSafely(Server server) {
		return expectedVmsCount == server.countVms();
	}

}
