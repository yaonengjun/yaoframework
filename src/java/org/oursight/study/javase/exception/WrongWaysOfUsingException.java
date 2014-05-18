package org.oursight.study.javase.exception;


/**
 * 列举了一些对异常使用不当的方法。
 * 
 * @author yaonengjun,2010-12-19 下午05:07:46
 *
 */
public class WrongWaysOfUsingException {
	
	/**
	 * 每次循环时进行异常检测，靠抛出异常来中断程序的操作。
	 * @param strArray 用于遍历循环的数组
	 * @author yaonengjun,2010-12-19 下午05:16:09
	 */
	private void wrongWay1(String[] strArray) {
		try {
			int i = 0;
			while(true) {
				strArray[i++] = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	/**
	 * 与{@linkplain #wrongWay1()}对应的正确的用法
	 * @param strArray 用于遍历循环的数组
	 * @author yaonengjun,2010-12-19 下午05:09:53
	 */
	private void rightWay1(String[] strArray) {
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
	}
	
	/**
	 * 演示了第一种错误使用情况下的性能差别（与正确使用情况下的性能差别）。<br><br>
	 * 
	 * <b>测试结果如下：</b><br>
	 * 	Total time use of wrongWay1:30 ms<br>
	 *  Total time use of rightWay1:60 ms<br>
	 * <b><font color="red">结论居然是每次检测异常比正常的循环效率还要高？这个结论与《Effiective Java》中第39条的结论不符，待进一步验证。</font></b>
	 * @author yaonengjun,2010-12-19 下午05:14:42
	 */
	public void showEfficiencyOfWrongWay1() {
		// 先准备数据
		String[] strArray = new String[100000];
		System.out.println("Comparing the efficiency between wrongWay1 and rightWay1.......");
		
		long startTime = System.currentTimeMillis();
		wrongWay1(strArray);
		long endTIme = System.currentTimeMillis();
		System.out.println("Total time use of wrongWay1:" + (endTIme - startTime) + " ms");
		
		startTime = System.currentTimeMillis();
		rightWay1(strArray);
		endTIme = System.currentTimeMillis();
		System.out.println("Total time use of rightWay1:" + (endTIme - startTime) + " ms");
		
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		WrongWaysOfUsingException wrongWaysOfUsingException = new WrongWaysOfUsingException();
		wrongWaysOfUsingException.showEfficiencyOfWrongWay1();
	}
}
