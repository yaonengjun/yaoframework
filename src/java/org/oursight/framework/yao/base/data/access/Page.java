package org.oursight.framework.yao.base.data.access;

import java.util.Map;

/**
 * Title: 分页接口
 * Description: 对列表进行分页
 */
public interface Page {

    /**
     * 生成分页链接的接口方法
     *
     * @param currentPage 当前页
     * @param pageSize    页尺寸
     * @param recordCount 总记录数
     * @param url         表单要提交的url名称
     * @return String
     */
    String getPagination(int currentPage, int pageSize, int recordCount, String url);

    /**
     * 根据查询记录数和每页记录数计算总页数接口方法
     *
     * @param recordCount int
     * @param pageSize    int
     * @return int
     */
    int getPageCount(int recordCount, int pageSize);

    /**
     * 设置站点的path用于图片按钮的url路径
     *
     * @param contextPath
     */
    void setContextPath(String contextPath);

    Map buildPagination(Object object, Page page,  String pageNumber, String url, String[] orderField ,boolean[] isAsc, int pageSize) throws
            Exception;

    Map buildPagination(Object object, Page page,  String pageNumber, String url, String[] orderField ,boolean[] isAsc) throws
            Exception;
}
