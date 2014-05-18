package org.oursight.study.patterns.command.activeobject;

import java.util.LinkedList;

/**
 * 具体用来执行命令的引擎。
 * @author yaonengjun,2011-3-13 下午05:28:29
 *
 */
public class ActiveObjectEngine {
	
	private LinkedList<ICommand> commands = new LinkedList<ICommand>();
	
	public void addCommand(ICommand c) {
		commands.add(c);
	}
	
	/**
	 * 此处是ActiveObject应用模式的关键之处（2 of 2）
	 * @author yaonengjun,2011-3-13 下午05:49:17
	 */
	public void run() {
		while(!commands.isEmpty()) {
			ICommand c = commands.getFirst();
			commands.removeLast();
			try {
				c.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
