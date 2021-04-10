package cn.edu.nbut.jerry.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 上传除申报书以为的多个文件的功能
 * @author cong
 * <action name="UploadMoreAction" class="com.museum.structs.declare.UploadMoreAction">
 *	    <result name="success">/page/resultNotify.jsp</result>
 *	</action>
 */
@WebServlet(urlPatterns = "/test")
public class testServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        HttpSession session = request.getSession();
        session.setAttribute("test", "test");
        response.sendRedirect("/test_1");
    }
}