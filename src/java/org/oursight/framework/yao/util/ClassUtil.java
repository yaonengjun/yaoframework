package org.oursight.framework.yao.util;

import java.io.File;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import org.apache.log4j.Logger;

import com.trs.idm.exception.IdMException;
import com.trs.idm.system.ClientConst;


/**
 * 创建类实例的工具类, 辅助完成各接口的实现类的注入过程. <BR>
 * @author TRS信息技术有限公司
 * @since JDK1.4
 */
public class ClassUtil {

    private static final Logger LOG = Logger.getLogger(ClassUtil.class);

    /**
     * 根据指定类名得到其实例. 调用时需要保证className是有效的类名, 否则会返回null.
     * @param className 指定类名
     * @return 成功得到实例返回该实例, 否则返回null.
     */
    public static Object getInstanceByClassName(String className)
			throws IdMException {
		if (className == null) {
			throw new IllegalArgumentException("className is null!");
		}
		className = className.trim();
		if (className.length() == 0) {
			throw new IllegalArgumentException("className is empty!");
		}
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new IdMException("fail to initialize class, className:"
					+ className);
		}
	}

    /**
     * 根据指定类名得到其类对象(class对象). 调用时需要保证className是有效的类名, 否则会返回null.
     * @param className 指定类名
     * @return 成功得到实例返回该实例, 否则返回null.
     */
    public static Class getClassByName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOG.error("fail to find class: " + className, e);
        }

        return null;
    }

    /**
     * 根据指定类对象(class对象)得到其实例.
     * @param clazz 指定类对象
     * @return 成功得到实例返回该实例, 否则返回null.
     */
    public static Object getInstanceByClass(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            LOG.error("fail to create instance! class=" + clazz, e);
        }
        return null;
    }
    
    /**
     * 获取指定类的源位置.
     * @return 该clazz的源位置.
     * @since JDK1.4
     */
    public static String getSourceLocation(Class clazz) {
        if (clazz != null) {
            try {
                ProtectionDomain pd = clazz.getProtectionDomain();
                CodeSource cs = pd.getCodeSource();
                if (cs != null) {
                    return cs.getLocation().toString();
                } else {
                    return ClientConst.Agent_INFO_UNKOWN;
                }
            } catch (RuntimeException e) {
                return e.toString();
            }
        }
        return ClientConst.Agent_INFO_UNKOWN;
    }
        /*
	 * 取得某一类所在包的所有类名 不含迭代
	 */
	public static String[] getPackageAllClassName(String classLocation, String packageName) {
		
		if (StringHelper.isEmpty(classLocation)) {
			return null;
		}
		// 将packageName分解
		String[] packagePathSplit = packageName.split("[.]");
		String realClassLocation = classLocation;
		int packageLength = packagePathSplit.length;
		for (int i = 0; i < packageLength; i++) {
			realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
		}
		// String realClassLocation1 = StringHelper.substringByFirstOccurance(realClassLocation, "file:/", null);
		String realClassLocation1 = realClassLocation.substring(8);
		if (StringHelper.isEmpty(realClassLocation1)) {
			return null;
		}
		realClassLocation = realClassLocation1;
		File packeageDir = new File(realClassLocation);
		if (packeageDir.isDirectory()) {
			String[] allClassName = packeageDir.list();
			return allClassName;
		}
		return null;
	}

	 public static void main(String[] args) {

		String location = getSourceLocation(ClassUtil.class);
		String[] classNames = getPackageAllClassName(location, "org.oursight.framework.yao.util");
		for (int i = 0; i < classNames.length; i++) {
			String string = classNames[i];
			System.out.println(string);
		}

	}
    
    
}