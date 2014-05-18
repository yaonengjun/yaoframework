package org.oursight.study.patterns.visitor.simplevisitor;

public interface IModem {
	
	void dial();
	
	void send();
	
	void hangup();
	
	void recv();
	
	boolean accept(IModemVisitor visitor);

}
