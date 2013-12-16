package com.zihexin.business_interface.log;

import com.zihexin.business_interface.common.Loader;
import com.zihexin.business_interface.common.UUIDGenerator;
import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.domain.TransRecode;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-16
 * Time: ����4:32
 * To change this template use File | Settings | File Templates.
 */
public class LogQueryServlet extends HttpServlet {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LogQueryServlet.class);
    @Override
    public void init() throws ServletException {
        // Put your code here
    }
    private ReadPropertiesDao readPropertiesDao = (ReadPropertiesDao) Loader.getContext().getBean("readPropertiesDao");
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
        Field field = new Field();

        //������ˮ��Ϣ
        String transRecodeSeq = readPropertiesDao.getTransRecodeSeq();
        TransRecode transRecode = new TransRecode();
        //��ˮ�ŵĸ�ʽΪyyyyMMddHHmmss+���ʵ��ʴ��۽�����+����
        String transRecodeNo = UUIDGenerator.getSysDate()+transRecodeSeq;
        transRecode.setTransRecodeNo(transRecodeNo);
        transRecode.setBankCode("ABC");
        transRecode.setAccountNo("123");
        transRecode.setDbCur("1");
        //transRecode.setAmt(Long.parseLong(field.getValue()));
       // transRecode.setAmt(10L);
        //transRecode.setReqDate();
        //transRecode.setRespDate();
        transRecode.setRespCode("9999");
        transRecode.setRespInfo("ʧ��");
        transRecode.setSourceSys("sys001");
        boolean  bl = readPropertiesDao.saveTransRecode(transRecode);
        if (bl){
            logger.info("����ˮ������ˮ�ɹ���");
        } else{
            logger.info("����ˮ������ˮʧ�ܣ�");
           // return "";
        }
           // reps.parseAll(data);

        //String xmlResponse = new String(data,"GBK");
        //System.out.println("<<<<<<<<<<<<<<<<"+xmlResponse);

        System.err.println("�յ��������Ϣ......");
       // System.err.println("��Ӧ�� ......"+reps.getRespCode());

        PrintWriter out = response.getWriter();
        String oper = request.getParameter("oper");
        if ("login".equals(oper)) {
            String user = (String) request.getParameter("user");
            String pwd = (String) request.getParameter("pwd");
            if (true) {
                request.getSession().setAttribute("operLogin", user);
                out.println("<html><head><title>");
                out.println("��ѯ��־");
                out.println("</title></head><body>");
                out.println("<input type='button' value='��ѯ' onclick=\"javascript:window.location='http://127.0.0.1:8080/business/queryLog?oper=query'\"/>");
                out.println("</body></html>");
            }
        }

        if ("query".equals(oper)) {
            if (request.getSession().getAttribute("operLogin") == null) {
                out.println("<html><head><title>");
                out.println("��ѯ��־���");
                out.println("</title></head><body>δ��½");

                out.println("</body></html>");
            } else {
                ReadPropertiesDao dao = (ReadPropertiesDao) Loader.getContext().getBean("readPropertiesDao");
                List list = dao.getLog();
                out.println("<html><head><title>");
                out.println("��ѯ��־���");
                out.println("</title></head><body>��־<br><table border=1>");
                out.println("<tr><td>ָ��</td><td>ϵͳ��Դ</td><td>����</td><td>�ʻ�</td><td>���</td><td></td></tr>");
                for (int i = 0; i < list.size(); i++) {
                    HashMap map = (HashMap) list.get(i);
                    out.println("<tr><td>" + map.get("COMMAND_ID") + "</td><td>" + map.get("SRC") + "</td><td>" + map.get("BANK") + "</td><td>" + map.get("ACCOUNT") + "</td><td>" + map.get("AMT") == null ? "" : map.get("AMT") +
                            "</td><td></td>");
                    out.println("</tr>");
                }


                out.println("</table></body></html>");
                request.getSession().removeAttribute("operLogin");
            }

        }

    }


}
