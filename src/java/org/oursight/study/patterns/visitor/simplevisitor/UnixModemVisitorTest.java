package org.oursight.study.patterns.visitor.simplevisitor;

import junit.framework.TestCase;

import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.AdslModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.CableModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.LightModem;

public class UnixModemVisitorTest extends TestCase {
	
	private UnixModemVisitor visitor;
	
	private AdslModem adslModem;
	
	private CableModem cableModem;
	
	private LightModem lightModem;
	
	@Override
	protected void setUp() throws Exception {
		visitor = new UnixModemVisitor();
		lightModem = new LightModem();
		cableModem = new CableModem();
		adslModem = new AdslModem();
	}
	
	public void testVisitAdslModem() {
		adslModem.accept(visitor);
		assertEquals("Config AdslModem in Unix! ", visitor.visit(adslModem));
	}


}
