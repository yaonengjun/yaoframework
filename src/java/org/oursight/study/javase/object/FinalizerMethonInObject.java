package org.oursight.study.javase.object;

public class FinalizerMethonInObject {
	
	/**
	 * yaonengjun@2010-11-27 
	 * 避免使用finalizer：<br>
	 * 1）该函数并不能保证及时的被执行。对一个对象变得不可到达开始，到它的finalizer函数被执行，这段时间的长度是任意的、不确定的。<br>
	 * 2）这意味着，time-critical的任务不应该由finalizer函数执行。<br>
	 * 3）实际上，[JLS,12.6]不仅保证该函数不会被执行，而且根本就不保证它会被执行。所以，绝不能够使用该函数来释放一个共享资源。<br>
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
