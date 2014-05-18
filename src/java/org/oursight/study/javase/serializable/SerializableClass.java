package org.oursight.study.javase.serializable;

import java.io.Serializable;

public class SerializableClass implements Serializable  {

	
	private String name;
	
	private String usage;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	public String toString() {
		return "[" + super.toString() + ";name=" + name + ";usage=" + usage + "]";
	}
}
