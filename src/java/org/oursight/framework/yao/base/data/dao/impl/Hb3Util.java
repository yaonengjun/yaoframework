/*
 * Title: 	  TRS ��ݷ�����
 * Copyright: Copyright (c) 2004-2005, TRS��Ϣ�������޹�˾. All rights reserved.
 * License:   see the license file.
 * Company:   TRS��Ϣ�������޹�˾(www.trs.com.cn)
 * 
 * Created on 2005-6-6
 */
package org.oursight.framework.yao.base.data.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * Hibernate 3������, ͳһ����Hibernate Session�Ļ�ȡ�͹ر�. ��������ʹ��. <BR>
 * @author TRS��Ϣ�������޹�˾
 */
class Hb3Util {

    private static final Logger LOG = Logger.getLogger(Hb3Util.class);

    /**
     * �ر�һ��Hibernate Session, �ͷ�����Դ.
     * @param session ָ����Hibernate Session����
     */
    static void closeSession(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException e) {
                LOG.warn("fail to close session!" + e, e);
            }
        }
    }

    /**
     * ��ȡ��ʹ�õ�JDBC�������.<BR>
     * @param hbCfg The Hibernate Configuration Object
     * @return ��ʹ�õ�JDBC�������
     */
    static String getDBDriver(Configuration hbCfg) {
        return hbCfg.getProperty(Environment.DRIVER);
    }

    /**
     * �ع�����.
     * @param tx ��ع������
     */
    static void rollback(Transaction tx) {
        if (tx != null) {
        	tx.rollback();
        }
    }

    /**
     * Convenience method to return the int value that matches the query.
     * @param qry the Hibernate Query Object. must not be null.
     * @return the int value or <tt>-1</tt> if the query returns no results.
     * @throws HibernateException if there is more than one matching result
     * @see Query#uniqueResult()
     */
    public static int uniqueResultAsInt(Query qry) {
        /*
        final Object uniqueResult = qry.uniqueResult();
        if (uniqueResult instanceof Integer) {
            return ((Integer) uniqueResult).intValue();
        }
        return -1;
        */
        List results = qry.list();
        if (results == null || results.size() == 0) {
            return 0;
        }
        int result = 0;
        Object obj;
        for (Iterator iter = results.iterator(); iter.hasNext();) {
            obj = iter.next();
            // TODO ls@07-1118 ��32λmysql 5/JDK6@Win2k3�ϣ�hibernate 3.2.5ִ��select count(*) from ĳ���(ֻ�м����¼)�ķ��ؽ��Ȼ��Long
            // TODO ls@07-1216 ��32λmysql 5/JDK5@Mac OS X�ϣ�hibernate 3.2.5ִ��select count(*) from ĳ���ķ��ؽ��Ҳ��Long
            if (obj instanceof Number) {
                result += ((Number) obj).intValue();
            }
        }
        return result;

    }

}