package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server>{

	
	
	private static final double EPSILON = 0.01d;
	private double expectedProcentageLoad;

	public CurrentLoadPercentageMatcher(double currentLoad) {
		this.expectedProcentageLoad = currentLoad;
	}

	public void describeTo(Description description) {
		description.appendText("match server with: ").appendValue(expectedProcentageLoad);
		
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return doubleAreEqual(item.currentProcentageLoad, expectedProcentageLoad);
	}

	private boolean doubleAreEqual(double d1, double d2) {
		return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
	}

}
