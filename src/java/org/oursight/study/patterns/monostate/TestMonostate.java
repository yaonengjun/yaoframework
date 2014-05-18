package org.oursight.study.patterns.monostate;

import junit.framework.TestCase;

public class TestMonostate extends TestCase {

	public void testMonostate() {
		Monostate m1 = new Monostate();
		Monostate m2 = new Monostate();
		
		for (int i = 0; i < 10; i++) {
			m1.setNumber(i);
			assertEquals(i, m2.getNumber());
		}
	}
}
