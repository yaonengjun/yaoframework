package org.oursight.framework.yao.tools.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.trs.idm.util.Base64Util;

/**
 * Java文件分析器
 * 
 * @author yaonengjun,2010-12-11 下午05:30:45
 * 
 */
public class FileAnalyzer {

	private boolean inDebugMod = true;

	public FileAnalyzer() {

	}

	public FileAnalyzer(String debugMod) {
		if (debugMod != null && !debugMod.equals(""))
			this.inDebugMod = true;

	}

	public void analyze(String inputFileName, int frequencyNumberToBegin,
			boolean showSuspectedSSOIdOnly) {
		Map ssoIdAppearanceTimesMap = new HashMap();
		Map ssoIdBase64CoSessionIdMap = new HashMap();
		Set base64RedirectedCoSessionSet = new HashSet();

		int lineNum = 1;
		File file = new File(inputFileName);

		long startTime = System.currentTimeMillis();

		String ssoIdStartString = "ssoIdInSession: ";
		String ssoIdEndString = "; ssoIdInUrl:";

		String coSessionIdStartString = "com.trs.idm.interact.agent.AgentUtil.alreadyBindSSOFromSession(AgentUtil.java:66) - HttpSession ID: ";

		String redirectFlag = "redirect to: https://id.51fund.com/ids/LoginServlet";
		String redirectStartString = "&coSessionId=";
		String redirectEndString = "&surl=";

		try {
			BufferedReader in = new BufferedReader(new FileReader(file), 65535);
			String thisLine;
			String preLine = "";
			while ((thisLine = in.readLine()) != null) {
				if (inDebugMod)
					System.out.println("Analyzing line " + (lineNum++) + " ...................  ");

				if (thisLine == null || thisLine.equals("")) {
					continue;
				}

				// 1、如果这行出现了ssoIdInSession，则是一个是否允许匿名访问的判定输出。获取ssoIdInSession中的SSOId，并计算其出现次数
				if (thisLine.indexOf("ssoIdInSession: ") > 0
						&& thisLine.indexOf("; ssoIdInUrl:") > 0) {
					String ssoId = thisLine.substring(thisLine.indexOf(ssoIdStartString)
							+ ssoIdStartString.length(), thisLine.indexOf(ssoIdEndString));

					if (inDebugMod) {
						System.out.println("found: " + ssoId);
						System.out.println();
					}

					if (ssoIdAppearanceTimesMap.get(ssoId) == null) {
						ssoIdAppearanceTimesMap.put(ssoId, new Integer(1));

					} else {
						Integer count = (Integer) ssoIdAppearanceTimesMap.get(ssoId);
						ssoIdAppearanceTimesMap.put(ssoId, new Integer(count.intValue() + 1));
					}

					// 获取前一行中输出的绑定coSessionId，构造 ssoId-base64(coSessionId)的Map
					if (preLine.indexOf(coSessionIdStartString) > 0) {
						String coSessionIdBindedWithSsoId = preLine.substring(preLine
								.indexOf(coSessionIdStartString)
								+ coSessionIdStartString.length());
						ssoIdBase64CoSessionIdMap.put(ssoId, Base64Util
								.encode(coSessionIdBindedWithSsoId));
					}
				}

				// 2、如果这行出现了重定向的内容，记录其base64加密过的coSessionId

				if (thisLine.indexOf(redirectFlag) > 0) {
					String base64CoSessionId = thisLine.substring(thisLine
							.indexOf(redirectStartString)
							+ redirectStartString.length(), thisLine.indexOf(redirectEndString));
					base64RedirectedCoSessionSet.add(base64CoSessionId);
				}

				// 保存本行内容
				preLine = thisLine;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set suspectedSsoIdSet = getAllSuspectedSsoId(ssoIdBase64CoSessionIdMap,
				base64RedirectedCoSessionSet);

		long endTime = System.currentTimeMillis();

		// 输出到文本文件
		try {
			// 如果已经有结果，则删除
			String time = new SimpleDateFormat("_yyyyMMdd_HHmmss").format(new Date(System
					.currentTimeMillis()));
			String outputFileName = inputFileName + "_done" + time + ".txt";

			BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName));
			out.write("Ananalze finish, total lines: " + lineNum + ", total time: "
					+ (endTime - startTime) / 1000 + " seconds(" + (endTime - startTime) / 60000
					+ " minutes) , all ssoId number: " + ssoIdAppearanceTimesMap.size()
					+ ", suspected ssoId number: " + suspectedSsoIdSet.size());
			out.write("\r\n");
			out.write("------------------------");
			out.write(" All targets which frequency number largger than "
					+ (frequencyNumberToBegin - 1) + " ");
			out.write("------------------------");
			out.write("\r\n");
			out.write("\r\n");

			writeMapToTxt(ssoIdAppearanceTimesMap, out, frequencyNumberToBegin,
					ssoIdBase64CoSessionIdMap, suspectedSsoIdSet, showSuspectedSSOIdOnly);

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("------------------------");
		System.out.println("Ananalze finish, total lines: " + lineNum + ", total time: "
				+ (endTime - startTime) / 1000 + " seconds(" + (endTime - startTime) / 60000
				+ " minutes) , all ssoId number: " + ssoIdAppearanceTimesMap.size()
				+ ", suspected ssoId number: " + suspectedSsoIdSet.size());
	}

	/**
	 * 获得清白的SSOID：指其对应的CoSessionId在本文件中曾经有过重定向记录的
	 * 
	 * @param ssoIdBase64CoSessionIdMap
	 * @param base64RedirectedCoSessionSet
	 * @return
	 * @author yaonengjun,Dec 13, 2010 8:55:22 PM
	 */
	private Set getAllSuspectedSsoId(Map ssoIdBase64CoSessionIdMap, Set base64RedirectedCoSessionSet) {
		Set SuspectedSsoIdSet = new HashSet();
		for (Iterator i = ssoIdBase64CoSessionIdMap.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String base64CoSessionId = (String) ssoIdBase64CoSessionIdMap.get(key);
			// 如果coSession曾经重定向过，则清除从ssoIdAppearanceTimesMap中清除掉
			if (base64RedirectedCoSessionSet.contains(base64CoSessionId)) {
				continue;
			}
			SuspectedSsoIdSet.add(key);
		}
		return SuspectedSsoIdSet;
	}

	/**
	 * 将最终的SSOId出现次数的Map输出到文本文件
	 * 
	 * @param map
	 * @param out
	 * @param frequencyNumberToBegin
	 * @throws IOException
	 * @author yaonengjun,Dec 13, 2010 8:47:35 PM
	 */
	private void writeMapToTxt(Map map, BufferedWriter out, int frequencyNumberToBegin,
			Map ssoIdBase64CoSessionIdMap, Set suspectedSsoIdSet, boolean showSuspectedSSOIdOnly)
			throws IOException {
		// 只显示有嫌疑的SSOID
		if (showSuspectedSSOIdOnly) {
			for (Iterator it = suspectedSsoIdSet.iterator(); it.hasNext();) {
				String key = (String) it.next();
				int appearTimes = ((Integer)map.get(key)).intValue();
				if(appearTimes < frequencyNumberToBegin) 
					continue;
				
				out.write(key + "  " + map.get(key));
				out.write("  ");
				out.write("suspected!!!");
				out.write("  ");
				out.write(Base64Util.decode((String) ssoIdBase64CoSessionIdMap.get(key)));
				out.write("\r\n");
			}

		} else {
			// 遍历所有的SSOID
			for (Iterator i = map.keySet().iterator(); i.hasNext();) {
				String key = (String) i.next();
				Integer countObj = (Integer) map.get(key);
				if (countObj.intValue() >= frequencyNumberToBegin) {
					out.write(key + "  " + countObj.intValue());
					out.write("  ");
					if (suspectedSsoIdSet.contains(key)) {
						out.write("suspected!!!");
						out.write("  ");
						out.write(Base64Util.decode((String) ssoIdBase64CoSessionIdMap.get(key)));
					}
					out.write("\r\n");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String inputFileName = null;
		while (inputFileName == null || "".equals(inputFileName)) {
			System.out.print("Please enter the filename you wish to analyze: ");
			inputFileName = br.readLine();
		}

		// 出现频率
		System.out.print("Please enter the frequency number you wish to begin with: ");
		String frequency = br.readLine();
		int frequencyNumberToBegin = 1;
		try {
			frequencyNumberToBegin = new Integer(frequency).intValue();
		} catch (Exception e) {
		}

		// 是否只列出嫌疑的SSOID：
		System.out.print("Show suspected ssoId only? true or false(default): ");
		String showSuspetedOnlyStr = br.readLine();
		boolean showSuspetedOnly = false;
		try {
			showSuspetedOnly = new Boolean(showSuspetedOnlyStr).booleanValue();
		} catch (Exception e) {

		}

		FileAnalyzer fy = new FileAnalyzer();
		fy.analyze(inputFileName.trim(), frequencyNumberToBegin, showSuspetedOnly);
	}

}