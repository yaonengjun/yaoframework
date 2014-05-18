package org.oursight.framework.yao.base.data.access;

import java.util.Map;

/**
 * Title: ��ҳ�ӿ�
 * Description: ���б���з�ҳ
 */
public interface Page {

    /**
     * ���ɷ�ҳ���ӵĽӿڷ���
     *
     * @param currentPage ��ǰҳ
     * @param pageSize    ҳ�ߴ�
     * @param recordCount �ܼ�¼��
     * @param url         ��Ҫ�ύ��url����
     * @return String
     */
    String getPagination(int currentPage, int pageSize, int recordCount, String url);

    /**
     * ���ݲ�ѯ��¼����ÿҳ��¼��������ҳ���ӿڷ���
     *
     * @param recordCount int
     * @param pageSize    int
     * @return int
     */
    int getPageCount(int recordCount, int pageSize);

    /**
     * ����վ���path����ͼƬ��ť��url·��
     *
     * @param contextPath
     */
    void setContextPath(String contextPath);

    Map buildPagination(Object object, Page page,  String pageNumber, String url, String[] orderField ,boolean[] isAsc, int pageSize) throws
            Exception;

    Map buildPagination(Object object, Page page,  String pageNumber, String url, String[] orderField ,boolean[] isAsc) throws
            Exception;
}
