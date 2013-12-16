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
 * Time: 下午4:32
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

        //设置流水信息
        String transRecodeSeq = readPropertiesDao.getTransRecodeSeq();
        TransRecode transRecode = new TransRecode();
        //流水号的格式为yyyyMMddHHmmss+单笔单笔代扣交易码+序列
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
        transRecode.setRespInfo("失败");
        transRecode.setSourceSys("sys001");
        boolean  bl = readPropertiesDao.saveTransRecode(transRecode);
        if (bl){
            logger.info("记流水请求流水成功！");
        } else{
            logger.info("记流水请求流水失败！");
           // return "";
        }
           // reps.parseAll(data);

        //String xmlResponse = new String(data,"GBK");
        //System.out.println("<<<<<<<<<<<<<<<<"+xmlResponse);

        System.err.println("收到服务端消息......");
       // System.err.println("响应码 ......"+reps.getRespCode());

        PrintWriter out = response.getWriter();
        String oper = request.getParameter("oper");
        if ("login".equals(oper)) {
            String user = (String) request.getParameter("user");
            String pwd = (String) request.getParameter("pwd");
            if (true) {
                request.getSession().setAttribute("operLogin", user);
                out.println("<html><head><title>");
                out.println("查询日志");
                out.println("</title></head><body>");
                out.println("<input type='button' value='查询' onclick=\"javascript:window.location='http://127.0.0.1:8080/business/queryLog?oper=query'\"/>");
                out.println("</body></html>");
            }
        }

        if ("query".equals(oper)) {
            if (request.getSession().getAttribute("operLogin") == null) {
                out.println("<html><head><title>");
                out.println("查询日志结果");
                out.println("</title></head><body>未登陆");

                out.println("</body></html>");
            } else {
                ReadPropertiesDao dao = (ReadPropertiesDao) Loader.getContext().getBean("readPropertiesDao");
                List list = dao.getLog();
                out.println("<html><head><title>");
                out.println("查询日志结果");
                out.println("</title></head><body>日志<br><table border=1>");
                out.println("<tr><td>指令</td><td>系统来源</td><td>银行</td><td>帐户</td><td>金额</td><td></td></tr>");
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
