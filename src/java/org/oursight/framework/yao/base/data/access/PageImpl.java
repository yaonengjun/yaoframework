package org.oursight.framework.yao.base.data.access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.oursight.framework.yao.base.service.DataService;
import org.oursight.framework.yao.util.StringHelper;


public class PageImpl implements Page{
	
	private static final Logger LOG = Logger.getLogger(PageImpl.class);

	private String contextPath;

    public PageImpl() {
    }

    /**
     * ���ݲ�ѯ��¼����ÿҳ��¼��������ҳ��
     *
     * @param recordCount int
     * @param pageSize    int
     * @return int
     */
    public int getPageCount(int recordCount, int pageSize) {
        int pageCount = 0;
        if (recordCount > 0) {
            if (recordCount % pageSize == 0) {
                pageCount = recordCount / pageSize;
            } else {
                pageCount = recordCount / pageSize + 1;
            }
        }
        return pageCount;
    }

    /**
     * ���ɷ�ҳ���ӵĽӿڷ���
     *
     * @param currentPage ��ǰҳ
     * @param pageSize    ҳ�ߴ�
     * @param recordCount �ܼ�¼��
     * @param url         ��Ҫ�ύ��url����
     * @return String
     */
    public String getPagination(int currentPage, int pageSize, int recordCount,
                                String url) {
        int pageCount = 0;
        if (pageSize <= 0)
            pageSize = recordCount;
        String valign = "middle";
//    String objForm = "";
//    String objPageNum = "";
        String inputClass = " class='mask' maskType='int' positive='true' ";

        if (recordCount > 0) {
            if (recordCount % pageSize == 0) {
                pageCount = recordCount / pageSize;
            } else {
                pageCount = recordCount / pageSize + 1;
            }
            if (currentPage < 1) {
                currentPage = 1;
            }
            if (currentPage > pageCount) {
                currentPage = pageCount;
            }
        } else {
            pageCount = 0;
            currentPage = 0;
        }

        //�ر����ӵ�html�ַ���

        String disabledFirst = "<td valign='" + valign + "'>\n<button class='btn' disabled>��ҳ</button>\n</td>\n";
        String disabledPrior = "<td valign='" + valign + "'>\n<button class='btn' disabled>��ҳ</button>\n</td>\n";
        String disabledNext = "<td valign='" + valign + "'>\n<button class='btn' disabled>��ҳ</button>\n</td>\n";
        String disabledLast = "<td valign='" + valign + "'>\n<button class='btn' disabled>βҳ</button>\n</td>\n";
        String disabledInput = "<td valign='" + valign + "'>\n&nbsp;\n</td>\n"
                + "<td valign='" + valign + "'>\n<input type='text' name='pageNumber' value='"
                + currentPage + "'" + inputClass + " style='width:30' readonly='true'>\n</td>\n"
                + "<td valign='" + valign + "'>\n<button class='btn' disabled>GO</button>\n</td>\n"
                + "<td valign='" + valign + "'>\n&nbsp;\n</td>\n";

        //�򿪵����ӵ�html�ַ���
        String enabledFirst = "<td valign='" + valign + "'>\n"
                + "<button class='btn' onClick='pageTo(1);'>��ҳ</button>\n</td>\n";

        String enabledPrior = "<td valign='" + valign + "'>\n"
                + "<button class='btn' onClick='pageTo(" + (currentPage - 1) + ");'>��ҳ</button>\n</td>\n";


        String enabledNext = "<td valign='" + valign + "'>\n"
                + "<button class='btn' onClick='pageTo(" + (currentPage + 1) + ");'>��ҳ</button>\n</td>\n";

        String enabledLast = "<td valign='" + valign + "'>\n"
                + "<button class='btn' onClick='pageTo(" + pageCount + ");'>βҳ</button>\n</td>\n";

        String enabledInput = "<td valign='" + valign + "'>\n&nbsp;\n</td>\n<td align=\"right\" valign='" + valign + "'>\n"
                + "<input type='text' name='pageNumber' value='" + currentPage + "' "
                + inputClass + " style='width:30;'>\n</td>\n"
                + "<td valign='" + valign + "'>\n"
                + "<button class='btn' onClick='pageGo();'>GO</button>\n</td>\n"
                + "<td valign='" + valign + "'>\n&nbsp;\n</td>\n";

        //��ҳ��Ϣ
        String pageInfo = "<td valign='" + valign + "'>\n<a class='labelframe'>"
                + "��" + currentPage + "/" + pageCount + "ҳ&nbsp;" + pageSize + "��/ҳ&nbsp;��"
                + recordCount + "����¼</a>\n</td>";
        String script = "\n<script>"
                + "\nfunction pageTo(pageNum){"
                + "\nvar obj = window.event.srcElement;"
                + "\nvar oForm = obj.form;"
                + "\nif(window.dialogArguments==null){"
                + "\noForm.target ='_self';"
                + "\n}"
                + "\noForm.action = '" + url + "';"
                + "\noForm.pageNumber.value = pageNum;"
                + "\noForm.submit();"
                + "\n}"
                + "\nfunction pageGo(){"
                + "\nvar obj = window.event.srcElement;"
                + "\nvar oForm = obj.form;"
                + "\nvar pageNum = oForm.pageNumber.value;"
                + "\nif (pageNum < 1 || pageNum >" + pageCount + "){"
                + "\nalert('��������ȷ��ҳ����');"
                + "\noForm.pageNumber.focus();"
                + "\n}"
                + "\nelse{"
                + "\nif(window.dialogArguments==null){"
                + "\noForm.target ='_self';"
                + "\n}"
                + "\noForm.action = '" + url + "';"
                + "\noForm.submit();"
                + "\n}"
                + "\n}"
                + "\n</script>";
        String html = "";

        //�����ҳ��С�ڵ���1��ر�����
        if (pageCount <= 1) {
            html = disabledFirst + disabledPrior + disabledNext
                    + disabledLast + disabledInput + pageInfo;
        } else {
            //��ǰλ�ڵ�һҳ��ر���ҳ����һҳ����,����һҳ��βҳ����
            if (currentPage == 1) {
                html = disabledFirst + disabledPrior + enabledNext + enabledLast
                        + enabledInput + pageInfo;
            }
            //��ǰλ�ڵ�һҳ�����һҳ֮��,�򿪴���������
            if ((currentPage > 1) && (currentPage < pageCount)) {
                html = enabledFirst + enabledPrior + enabledNext + enabledLast
                        + enabledInput + pageInfo;
            }
            //��ǰλ��βҳ�����ҳ����һҳ����,�ر���һҳ��βҳ����
            if (currentPage == pageCount) {
                html = enabledFirst + enabledPrior + disabledNext + disabledLast
                        + enabledInput + pageInfo;
            }
        }
        html = script + "\n<table>\n<tr>\n" + html + "\n</tr>\n</table>";
        return html;

    }


    /**
     * ����վ���path����ͼƬ��ť��url·��
     *
     * @param contextPath
     */
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Map buildPagination(Object object, Page page, String pageNumber, String url, String[] orderField ,boolean[] isAsc, int pageSize) throws
            Exception {
        Map mapInfo = new HashMap();
        List lisRecord = null;
        int count = 0;
        int pageNum = 1;
        if (!StringHelper.isEmpty(pageNumber)) {
            pageNum = Integer.parseInt(pageNumber);
        }
        try {
            count = DataService.getCommonDao().queryCount(object,orderField,isAsc);

            if (count > 0) {
                if (pageNum > page.getPageCount(count, pageSize)) {
                    pageNum = page.getPageCount(count, pageSize);
                }
                int pageNumber1 = (pageNum - 1) * pageSize;
                lisRecord = DataService.getCommonDao().queryList(object,pageNumber1,pageSize,orderField,isAsc);
            }
        }catch(Exception e){
        	LOG.error("PageImpl error", e);
        	e.printStackTrace();
        }
        mapInfo.put("partList", lisRecord);
        mapInfo.put("page", page.getPagination(pageNum, pageSize, count, url));
        return mapInfo;
    }

    public Map buildPagination(Object object, Page page, String pageNumber, String url, String[] orderField ,boolean[] isAsc) throws
            Exception {
        Map mapInfo = new HashMap();
        List lisRecord = null;
        int count = 0;
        int pageNum = 1;
        if (!StringHelper.isEmpty(pageNumber)) {
            pageNum = Integer.parseInt(pageNumber);
            LOG.info("pageNum�� " + pageNum);
        }
        try {
        	count = DataService.getCommonDao().queryCount(object,orderField,isAsc);

            if (count > 0) {
                if (pageNum > page.getPageCount(count, 10)) {
                    pageNum = page.getPageCount(count, 10);
                }
                int pageNumber1 = (pageNum - 1) * 10;
                lisRecord = DataService.getCommonDao().queryList(object,pageNumber1,10,orderField,isAsc);
            }
        }catch(Exception e){
        	LOG.error("PageImpl error", e);
        	e.printStackTrace();
        }
        mapInfo.put("partList", lisRecord);
        mapInfo.put("page", page.getPagination(pageNum, 10, count, url));
        return mapInfo;
    }

}
