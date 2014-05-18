package org.oursight.study.javase.runtime;


public class ExcuteOutterCmd {
	
	public static void main(String[] args) {
		runNotepad();
		// runWord();
	}

	private static void runNotepad() {
		Process process;
		try {
			process = Runtime.getRuntime().exec("notepad.exe");
			System.out.println("ExcuteOutterCmd.runNotepad()");
			System.out.println(process.toString());
			System.out.println(process);
			Thread.sleep(10000);
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runWord() {
		Process process;
		try {
			process = Runtime.getRuntime().exec("cmd /D:ON/c start MyDoc.docx");
			System.out.println("ExcuteOutterCmd.runWord()");
			System.out.println(process.toString());
			System.out.println(process);
			Thread.sleep(10000);
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
