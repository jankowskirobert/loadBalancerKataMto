package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server>{

	
	
	private double expectedProcentageLoad;

	public CurrentLoadPercentageMatcher(double currentLoad) {
		this.expectedProcentageLoad = currentLoad;
		// TODO Auto-generated constructor stub
	}

	public void describeTo(Description description) {
		description.appendText("match server with: ").appendValue(expectedProcentageLoad);
		
	}

	@Override
	protected boolean matchesSafely(Server item) {
		
		return item.currentProcentageLoad == expectedProcentageLoad || Math.abs(item.currentProcentageLoad - expectedProcentageLoad) < 0.01d;
	}

}
