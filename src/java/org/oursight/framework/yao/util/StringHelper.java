package org.oursight.framework.yao.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class StringHelper {

	private static final Logger logger = Logger.getLogger(StringHelper.class);

	/**
	 * 将指定字符串的指定位置的字符以byte方式返回. 该方法中对返回的字符有一次ascii转换, 即减去一个'0'的ascii值(十进制的48).
	 * 
	 * @param strHead
	 *            指定字符串
	 * @param index
	 *            指定位置
	 * @return 指定位置的字符的byte值
	 */
	public static byte byteAt(String strHead, int index) {
		byte result = (byte) strHead.charAt(index);
		result -= '0';
		return result;
	}

	/**
	 * 将给定字符串(origin)以字符串token作为分隔符进行分隔, 得到一个字符串数组. 该函数不依赖于JDK 1.4, 和JDK
	 * 1.4中String.split(String regex)的区别是不支持正则表达式.<br>
	 * 在不包含有token字符串时, 本函数返回以原字符串构成的数组.
	 * 
	 * @param origin
	 *            给定字符串
	 * @param token
	 *            分隔符
	 * @return 字符串数组
	 */
	public static String[] split(String origin, String token) {
		if (origin == null) {
			return null;
		}
		final StringTokenizer st = new StringTokenizer(origin, token);
		final int countTokens = st.countTokens();
		if (countTokens <= 0) {
			return new String[] { origin };
		}
		String[] results = new String[countTokens];
		for (int i = 0; i < countTokens; i++) {
			results[i] = st.nextToken();
		}
		return results;
	}

	/**
	 * 得到给定字符串的逆序的字符串. <BR>
	 * 用例: <code>
	 * <pre>
	 * assertEquals(&quot;/cba&quot;, StringHelper.reverse(&quot;abc/&quot;));
	 * assertEquals(&quot;aabbbccccx&quot;, StringHelper.reverse(&quot;xccccbbbaa&quot;));
	 * assertEquals(&quot;试测&circ;%6cbA数参&quot;, StringHelper.reverse(&quot;参数Abc6%&circ;测试&quot;));
	 * </pre>
	 * </code>
	 */
	public static String reverse(String origin) {
		if (origin == null) {
			throw new NullPointerException("参数为null!");
		}
		return new StringBuffer(origin).reverse().toString();
		/*
		 * int len = origin.length(); char[] origins = origin.toCharArray();
		 * char[] results = new char[len]; for (int i = 0; i < len; i++) {
		 * results[i] = origins[len - i - 1]; } return String.valueOf(results);
		 */
	}

	/**
	 * 用ISO-8859-1对给定字符串编码.
	 * 
	 * @param string
	 *            给定字符串
	 * @return 编码后所得的字符串. 如果给定字符串为null或"", 则原样返回.
	 */
	public static String encodingByISO8859_1(String string) {
		if ((string != null) && !("".equals(string))) {
			try {
				return new String(string.getBytes(), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.warn("进行ISO-8859-1转码时出错", e);
			}
		}
		return string;
	}

	/**
	 * The method is identical with {@link #split(String, String)}, and will be
	 * deleted.
	 */
	public static String[] splitAlways(String origin, String token) {
		return split(origin, token);
	}

	// from commons-codec: char[] Hex.encodeHex(byte[])
	/**
	 * 将给定的字节数组用十六进制字符串表示.
	 */
	public static String toString(byte[] data) {
		if (data == null) {
			return "null!";
		}
		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return new String(out);
	}

	// from commons-codec: byte[] Hex.decodeHex(char[])
	/**
	 * 将给定的十六进制字符串转化为字节数组. <BR>
	 * 与<code>toString(byte[] data)</code>作用相反.
	 * 
	 * @throws RuntimeException
	 *             当给定十六进制字符串的长度为奇数时或给定字符串包含非十六进制字符.
	 * @see #toString(byte[])
	 */
	public static byte[] toBytes(String str) {
		if (str == null) {
			return null;
		}
		char[] data = str.toCharArray();
		int len = data.length;

		if ((len & 0x01) != 0) {
			throw new RuntimeException("Odd number of characters!");
		}

		byte[] out = new byte[len >> 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f = f | toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	// [liushen@2005-04-21] from caohui
	/**
	 * HTML元素value值过滤处理函数：将 <code> & &lt; &gt; &quot </code> 等特殊字符作转化处理. <BR>
	 * 用例: <code>
	 *    &lt;input type="text" name="Name" value="<%=StringHelper.filterForHTMLValue(sContent)%>"&gt;
	 * </code>
	 * 
	 * @param _sContent
	 *            指定的文本内容. 如果为null则返回"".
	 * @return 处理后的文本内容.
	 */
	public static String filterForHTMLValue(String _sContent) {
		if (_sContent == null)
			return "";

		// _sContent = replaceStr(_sContent,"</script>","<//script>");
		// _sContent = replaceStr(_sContent,"</SCRIPT>","<//SCRIPT>");

		char[] srcBuff = _sContent.toCharArray();
		int nLen = srcBuff.length;
		if (nLen == 0)
			return "";

		StringBuffer retBuff = new StringBuffer((int) (nLen * 1.8));

		for (int i = 0; i < nLen; i++) {
			char cTemp = srcBuff[i];
			switch (cTemp) {
			case '&': // 转化：& -->&amp;why: 2002-3-19
				// caohui@0515
				// 处理unicode代码
				if ((i + 1) < nLen) {
					cTemp = srcBuff[i + 1];
					if (cTemp == '#')
						retBuff.append("&");
					else
						retBuff.append("&amp;");
				} else
					retBuff.append("&amp;");
				break;
			case '<': // 转化：< --> &lt;
				retBuff.append("&lt;");
				break;
			case '>': // 转化：> --> &gt;
				retBuff.append("&gt;");
				break;
			case '\"': // 转化：" --> &quot;
				retBuff.append("&quot;");
				break;
			default:
				retBuff.append(cTemp);
			}// case
		}// end for

		return retBuff.toString();
	}

	/**
	 * 将字符串转化为UTF-8的"(\\uXXXX)"的形式. <BR>
	 * 使用了不公开的sun.io.ByteToCharConverter类. 因此只能在Sun JDK下运行.
	 * 
	 * @param strSource
	 *            给定的字符串, 不能为null.
	 * @return 字符串的UTF-8的"(\\uXXXX)"的形式.
	 * @throws sun.io.MalformedInputException
	 * @throws UnsupportedEncodingException
	 *             public static String convert(String strSource) throws
	 *             sun.io.MalformedInputException, UnsupportedEncodingException {
	 *             sun.io.ByteToCharConverter b2c = sun.io.ByteToCharConverter
	 *             .getConverter("UTF-16BE"); char[] chars =
	 *             b2c.convertAll(strSource.getBytes("UTF-16BE")); StringBuffer
	 *             sb = new StringBuffer(); for (int i = 0; i < chars.length;
	 *             i++) { sb.append("\\u");
	 *             sb.append(Integer.toHexString(chars[i])); } return
	 *             sb.toString(); }
	 */

	/**
	 * 等价于<code>toString(objs, false, ",");</code>.
	 */
	public static String toString(Object[] objs) {
		return toString(objs, false, ", ");
	}

	/**
	 * 等价于<code>toString(objs, showOrder, ",");</code>.
	 * 
	 * @see #toString(Object[], boolean, String)
	 */
	public static String toString(Object[] objs, boolean showOrder) {
		return toString(objs, showOrder, ",");
	}

	/**
	 * 输出数组内容. 如果数组为null, 返回null. 如果数组某元素为null则该元素输出为null.
	 * 
	 * @param objs
	 *            待输出的数组
	 * @param showOrder
	 *            是否输出元素的序号
	 * @param token
	 *            元素间的分割串
	 */
	public static String toString(Object[] objs, boolean showOrder, String token) {
		if (objs == null) {
			return "null";
		}
		int len = objs.length;
		StringBuffer sb = new StringBuffer(10 * len);
		for (int i = 0; i < len; i++) {
			if (showOrder) {
				sb.append(i).append(':');
			}
			sb.append(objs[i]);
			if (i < len - 1) {
				sb.append(token);
			}
		}
		return sb.toString();
	}

	public static String avoidNull(String str) {
		return (str == null) ? "" : str;
	}

	public static String trim(String str) {
		return (str == null) ? "" : str.trim();
	}

	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * Converts a hexadecimal character to an integer.
	 * 
	 * @param ch
	 *            A character to convert to an integer digit
	 * @param index
	 *            The index of the character in the source
	 * @return An integer
	 * @throws RuntimeException
	 *             Thrown if ch is an illegal hex character
	 */
	static int toDigit(char ch, int index) {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new RuntimeException("Illegal hexadecimal charcter " + ch + " at index " + index);
		}
		return digit;
	}

	// =============================================================================
	// 字符串替换, (2005-07-21) from CMyString
	/**
	 * 字符串替换函数：用于将指定字符串中指定的字符串替换为新的字符串。
	 * 
	 * @param _strSrc
	 *            源字符串。
	 * @param _strOld
	 *            被替换的旧字符串
	 * @param _strNew
	 *            用来替换旧字符串的新字符串
	 * @return 替换处理后的字符串
	 */
	public static String replaceStr(String _strSrc, String _strOld, String _strNew) {
		if (_strSrc == null)
			return null;

		// 提取源字符串对应的字符数组
		char[] srcBuff = _strSrc.toCharArray();
		int nSrcLen = srcBuff.length;
		if (nSrcLen == 0)
			return "";

		// 提取旧字符串对应的字符数组
		char[] oldStrBuff = _strOld.toCharArray();
		int nOldStrLen = oldStrBuff.length;
		if (nOldStrLen == 0 || nOldStrLen > nSrcLen)
			return _strSrc;

		StringBuffer retBuff = new StringBuffer((nSrcLen * (1 + _strNew.length() / nOldStrLen)));

		int i, j, nSkipTo;
		boolean bIsFound = false;

		i = 0;
		while (i < nSrcLen) {
			bIsFound = false;

			// 判断是否遇到要找的字符串
			if (srcBuff[i] == oldStrBuff[0]) {
				for (j = 1; j < nOldStrLen; j++) {
					if (i + j >= nSrcLen)
						break;
					if (srcBuff[i + j] != oldStrBuff[j])
						break;
				}
				bIsFound = (j == nOldStrLen);
			}

			// 若找到则替换，否则跳过
			if (bIsFound) { // 找到
				retBuff.append(_strNew);
				i += nOldStrLen;
			} else { // 没有找到
				if (i + nOldStrLen >= nSrcLen) {
					nSkipTo = nSrcLen - 1;
				} else {
					nSkipTo = i;
				}
				for (; i <= nSkipTo; i++) {
					retBuff.append(srcBuff[i]);
				}
			}
		}// end while
		srcBuff = null;
		oldStrBuff = null;
		return retBuff.toString();
	}

	/**
	 * 按给定分割符对给定字符串作分割, 然后作trim处理.<BR>
	 * <li>origin为null时返回null.
	 * <li>不包含有token字符串时, 本函数返回以原字符串trim后构成的数组.
	 * 
	 * @param origin
	 *            给定字符串
	 * @param token
	 *            分隔符. 不允许为null.
	 * @return 字符串数组
	 */
	public static String[] splitAndTrim(String origin, String token) {
		if (origin == null) {
			return null;
		}
		origin = origin.trim();
		final StringTokenizer st = new StringTokenizer(origin, token);
		final int countTokens = st.countTokens();
		if (countTokens <= 0) {
			return new String[] { origin };
		}
		List strs = new ArrayList(countTokens);
		String str;
		for (int i = 0; i < countTokens; i++) {
			str = st.nextToken().trim();
			if (str.length() > 0) {
				strs.add(str);
			}
		}
		return (String[]) strs.toArray(new String[0]);
	}

	public static String hexToStr(String hex) {
		return new String(toBytes(hex));
	}

	/**
	 * see {@link StringHelperTest#testTruncateAndTrim()}.
	 */
	public static String truncateAndTrim(String str, String delim) {
		if (str == null || delim == null) {
			return str;
		}
		int nStart = str.indexOf(delim);
		if (nStart < 0) {
			return str;
		}
		return str.substring(nStart + delim.length()).trim();
	}

	/**
	 * 获得字符串指定编码的字符串
	 * 
	 * @param originalStr
	 * @param encoding
	 * @return 编码后的字符串
	 */
	public static String getStringByEncoding(String originalStr, String encoding) {

		String encodingStr = originalStr;
		try {
			encodingStr = new String(originalStr.getBytes(), encoding);
		} catch (UnsupportedEncodingException e) {
			return originalStr;
		}
		return encodingStr;

	}

	/**
	 * 判断字符串是否为null或空.
	 * 
	 * @return true if <code>(str == null || str.trim().length() == 0)</code>,
	 *         otherwise false.
	 * @since ls@07.0624
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 将字符串调整为不超过maxLength长度的字符串, 调整方法为去掉中间的适当长度, 以...区分开头和结束.
	 * 
	 * @param maxLength
	 *            指定的长度.
	 * @since ls@07.1227
	 * @see StringHelperTest#testAdjustLength()
	 */
	public static String adjustLength(String str, int maxLength) {
		if (str == null) {
			return str;
		}
		if (maxLength <= 0) {
			throw new IllegalArgumentException("Illegal value of maxLength: " + maxLength
					+ "! It must be a positive integer.");
		}
		int strLength = str.length();
		if (maxLength > strLength) {
			return str;
		}
		final String DELIT = "...";
		StringBuffer sb = new StringBuffer(maxLength);
		int splitPos = (maxLength - DELIT.length()) / 2;
		sb.append(str.substring(0, splitPos));
		sb.append(DELIT);
		sb.append(str.substring(strLength - splitPos));
		return sb.toString();
	}

	/**
	 * 判断是否为英文
	 * 
	 * @param word
	 * @return
	 */
	public static boolean charIsLetter(String word) {
		boolean sign = true; // 初始化标志为为'true'
		for (int i = 0; i < word.length(); i++) { // 遍历输入字符串的每一个字符
			if (!Character.isLetter(word.charAt(i))) { // 判断该字符是否为英文字符
				sign = false; // 若有一位不是英文字符，则将标志位修改为'false'
			}
		}
		return sign; // 返回标志位结果
	}

	/**
	 * 严格匹配仅为英文
	 * @param word
	 * @return
	 */
	public static boolean isEnglishCharacter(String word) {
		String patternRegex = "^[A-Za-z]+$";// #{xxx}

		Pattern pattern = Pattern.compile(patternRegex);
		Matcher matcher = pattern.matcher(word);
		return matcher.find();
		

	}
	
	/**
	 * 严格匹配仅为中文,测试未通过
	 * @param word
	 * @return
	 */
	public static boolean isChineseCharacter(String word) {
		String patternRegex = "[\u4e00-\u9fa5]";// #{xxx}
//		String patternRegex = "[^\x00-\xff]";// #{xxx}

		Pattern pattern = Pattern.compile(patternRegex);
		Matcher matcher = pattern.matcher(word);
		return matcher.find();
		

	}

/*	public static void main(String[] args) {
		System.out.println(isEnglishCharacter("aaa"));
		System.out.println(isEnglishCharacter("a1a"));
		System.out.println(isEnglishCharacter("姚能俊"));
		
		System.out.println();
		
		System.out.println(isChineseCharacter("姚能俊"));
		System.out.println(isChineseCharacter("a1a"));
		System.out.println(isChineseCharacter("姚能1a"));
	}*/
}