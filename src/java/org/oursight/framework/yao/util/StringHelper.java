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
	 * ��ָ���ַ�����ָ��λ�õ��ַ���byte��ʽ����. �÷����жԷ��ص��ַ���һ��asciiת��, ����ȥһ��'0'��asciiֵ(ʮ���Ƶ�48).
	 * 
	 * @param strHead
	 *            ָ���ַ���
	 * @param index
	 *            ָ��λ��
	 * @return ָ��λ�õ��ַ���byteֵ
	 */
	public static byte byteAt(String strHead, int index) {
		byte result = (byte) strHead.charAt(index);
		result -= '0';
		return result;
	}

	/**
	 * �������ַ���(origin)���ַ���token��Ϊ�ָ������зָ�, �õ�һ���ַ�������. �ú�����������JDK 1.4, ��JDK
	 * 1.4��String.split(String regex)�������ǲ�֧��������ʽ.<br>
	 * �ڲ�������token�ַ���ʱ, ������������ԭ�ַ������ɵ�����.
	 * 
	 * @param origin
	 *            �����ַ���
	 * @param token
	 *            �ָ���
	 * @return �ַ�������
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
	 * �õ������ַ�����������ַ���. <BR>
	 * ����: <code>
	 * <pre>
	 * assertEquals(&quot;/cba&quot;, StringHelper.reverse(&quot;abc/&quot;));
	 * assertEquals(&quot;aabbbccccx&quot;, StringHelper.reverse(&quot;xccccbbbaa&quot;));
	 * assertEquals(&quot;�Բ�&circ;%6cbA����&quot;, StringHelper.reverse(&quot;����Abc6%&circ;����&quot;));
	 * </pre>
	 * </code>
	 */
	public static String reverse(String origin) {
		if (origin == null) {
			throw new NullPointerException("����Ϊnull!");
		}
		return new StringBuffer(origin).reverse().toString();
		/*
		 * int len = origin.length(); char[] origins = origin.toCharArray();
		 * char[] results = new char[len]; for (int i = 0; i < len; i++) {
		 * results[i] = origins[len - i - 1]; } return String.valueOf(results);
		 */
	}

	/**
	 * ��ISO-8859-1�Ը����ַ�������.
	 * 
	 * @param string
	 *            �����ַ���
	 * @return ��������õ��ַ���. ��������ַ���Ϊnull��"", ��ԭ������.
	 */
	public static String encodingByISO8859_1(String string) {
		if ((string != null) && !("".equals(string))) {
			try {
				return new String(string.getBytes(), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.warn("����ISO-8859-1ת��ʱ����", e);
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
	 * ���������ֽ�������ʮ�������ַ�����ʾ.
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
	 * ��������ʮ�������ַ���ת��Ϊ�ֽ�����. <BR>
	 * ��<code>toString(byte[] data)</code>�����෴.
	 * 
	 * @throws RuntimeException
	 *             ������ʮ�������ַ����ĳ���Ϊ����ʱ������ַ���������ʮ�������ַ�.
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
	 * HTMLԪ��valueֵ���˴��������� <code> & &lt; &gt; &quot </code> �������ַ���ת������. <BR>
	 * ����: <code>
	 *    &lt;input type="text" name="Name" value="<%=StringHelper.filterForHTMLValue(sContent)%>"&gt;
	 * </code>
	 * 
	 * @param _sContent
	 *            ָ�����ı�����. ���Ϊnull�򷵻�"".
	 * @return �������ı�����.
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
			case '&': // ת����& -->&amp;why: 2002-3-19
				// caohui@0515
				// ����unicode����
				if ((i + 1) < nLen) {
					cTemp = srcBuff[i + 1];
					if (cTemp == '#')
						retBuff.append("&");
					else
						retBuff.append("&amp;");
				} else
					retBuff.append("&amp;");
				break;
			case '<': // ת����< --> &lt;
				retBuff.append("&lt;");
				break;
			case '>': // ת����> --> &gt;
				retBuff.append("&gt;");
				break;
			case '\"': // ת����" --> &quot;
				retBuff.append("&quot;");
				break;
			default:
				retBuff.append(cTemp);
			}// case
		}// end for

		return retBuff.toString();
	}

	/**
	 * ���ַ���ת��ΪUTF-8��"(\\uXXXX)"����ʽ. <BR>
	 * ʹ���˲�������sun.io.ByteToCharConverter��. ���ֻ����Sun JDK������.
	 * 
	 * @param strSource
	 *            �������ַ���, ����Ϊnull.
	 * @return �ַ�����UTF-8��"(\\uXXXX)"����ʽ.
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
	 * �ȼ���<code>toString(objs, false, ",");</code>.
	 */
	public static String toString(Object[] objs) {
		return toString(objs, false, ", ");
	}

	/**
	 * �ȼ���<code>toString(objs, showOrder, ",");</code>.
	 * 
	 * @see #toString(Object[], boolean, String)
	 */
	public static String toString(Object[] objs, boolean showOrder) {
		return toString(objs, showOrder, ",");
	}

	/**
	 * �����������. �������Ϊnull, ����null. �������ĳԪ��Ϊnull���Ԫ�����Ϊnull.
	 * 
	 * @param objs
	 *            �����������
	 * @param showOrder
	 *            �Ƿ����Ԫ�ص����
	 * @param token
	 *            Ԫ�ؼ�ķָ
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
	// �ַ����滻, (2005-07-21) from CMyString
	/**
	 * �ַ����滻���������ڽ�ָ���ַ�����ָ�����ַ����滻Ϊ�µ��ַ�����
	 * 
	 * @param _strSrc
	 *            Դ�ַ�����
	 * @param _strOld
	 *            ���滻�ľ��ַ���
	 * @param _strNew
	 *            �����滻���ַ��������ַ���
	 * @return �滻�������ַ���
	 */
	public static String replaceStr(String _strSrc, String _strOld, String _strNew) {
		if (_strSrc == null)
			return null;

		// ��ȡԴ�ַ�����Ӧ���ַ�����
		char[] srcBuff = _strSrc.toCharArray();
		int nSrcLen = srcBuff.length;
		if (nSrcLen == 0)
			return "";

		// ��ȡ���ַ�����Ӧ���ַ�����
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

			// �ж��Ƿ�����Ҫ�ҵ��ַ���
			if (srcBuff[i] == oldStrBuff[0]) {
				for (j = 1; j < nOldStrLen; j++) {
					if (i + j >= nSrcLen)
						break;
					if (srcBuff[i + j] != oldStrBuff[j])
						break;
				}
				bIsFound = (j == nOldStrLen);
			}

			// ���ҵ����滻����������
			if (bIsFound) { // �ҵ�
				retBuff.append(_strNew);
				i += nOldStrLen;
			} else { // û���ҵ�
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
	 * �������ָ���Ը����ַ������ָ�, Ȼ����trim����.<BR>
	 * <li>originΪnullʱ����null.
	 * <li>��������token�ַ���ʱ, ������������ԭ�ַ���trim�󹹳ɵ�����.
	 * 
	 * @param origin
	 *            �����ַ���
	 * @param token
	 *            �ָ���. ������Ϊnull.
	 * @return �ַ�������
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
	 * ����ַ���ָ��������ַ���
	 * 
	 * @param originalStr
	 * @param encoding
	 * @return �������ַ���
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
	 * �ж��ַ����Ƿ�Ϊnull���.
	 * 
	 * @return true if <code>(str == null || str.trim().length() == 0)</code>,
	 *         otherwise false.
	 * @since ls@07.0624
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * ���ַ�������Ϊ������maxLength���ȵ��ַ���, ��������Ϊȥ���м���ʵ�����, ��...���ֿ�ͷ�ͽ���.
	 * 
	 * @param maxLength
	 *            ָ���ĳ���.
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
	 * �ж��Ƿ�ΪӢ��
	 * 
	 * @param word
	 * @return
	 */
	public static boolean charIsLetter(String word) {
		boolean sign = true; // ��ʼ����־ΪΪ'true'
		for (int i = 0; i < word.length(); i++) { // ���������ַ�����ÿһ���ַ�
			if (!Character.isLetter(word.charAt(i))) { // �жϸ��ַ��Ƿ�ΪӢ���ַ�
				sign = false; // ����һλ����Ӣ���ַ����򽫱�־λ�޸�Ϊ'false'
			}
		}
		return sign; // ���ر�־λ���
	}

	/**
	 * �ϸ�ƥ���ΪӢ��
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
	 * �ϸ�ƥ���Ϊ����,����δͨ��
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
		System.out.println(isEnglishCharacter("Ҧ�ܿ�"));
		
		System.out.println();
		
		System.out.println(isChineseCharacter("Ҧ�ܿ�"));
		System.out.println(isChineseCharacter("a1a"));
		System.out.println(isChineseCharacter("Ҧ��1a"));
	}*/
}