/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-05-08 15:45:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class YZMLlogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-3.6.0.js\"></script>\r\n");
      out.write("    <title>Document</title>\r\n");
      out.write("\r\n");
      out.write("    <style>\r\n");
      out.write("        input{\r\n");
      out.write("            width: 200px;\r\n");
      out.write("            height: 32px;\r\n");
      out.write("            border: 1px solid #000;\r\n");
      out.write("            box-sizing: border-box;\r\n");
      out.write("            margin-top: 2px;\r\n");
      out.write("        }\r\n");
      out.write("        #c1{\r\n");
      out.write("            vertical-align: middle;\r\n");
      out.write("            box-sizing: border-box;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("        #btn{\r\n");
      out.write("            display: block;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("            height: 32px;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"code\">\r\n");
      out.write("    ç¨æ·å:<input type=\"text\"><br>\r\n");
      out.write("    å¯&nbsp;&nbsp;&nbsp;ç :<input type=\"text\"><br>\r\n");
      out.write("    éªè¯ç :<input type=\"text\" value=\"\" id=\"inputValue\" placeholder=\"è¯·è¾å¥éªè¯ç ï¼ä¸åºåå¤§å°åï¼\">\r\n");
      out.write("    <canvas id=\"c1\" width=\"100\" height=\"30\" style=\"border:1px solid black\"></canvas>\r\n");
      out.write("    <br>\r\n");
      out.write("    <button id=\"btn\">ç»å½</button>\r\n");
      out.write("</div><br>\r\n");
      out.write("<!--éªè¯ç  kaptcha-->\r\n");
      out.write("<li>\r\n");
      out.write("    <div class=\"item-content\">\r\n");
      out.write("        <div class=\"item-inner\">\r\n");
      out.write("            <div class=\"item-title label\">éªè¯ç </div>\r\n");
      out.write("            <input type=\"text\" id=\"j_captcha\" placeholder=\"éªè¯ç \">\r\n");
      out.write("            <div class=\"item-input\">\r\n");
      out.write("                <img id=\"captcha_img\" alt=\"ç¹å»æ´æ¢\" title=\"ç¹å»æ´æ¢\"\r\n");
      out.write("                     onclick=\"changeVerifyCode(this)\" src=\"../Kaptcha\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</li>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("    $(function(){\r\n");
      out.write("        // å­æ¾éæºçéªè¯ç \r\n");
      out.write("        var showNum = []\r\n");
      out.write("\r\n");
      out.write("        draw(showNum)\r\n");
      out.write("\r\n");
      out.write("        $(\"#c1\").click(function(){\r\n");
      out.write("            draw(showNum)\r\n");
      out.write("        })\r\n");
      out.write("        $(\"#btn\").click(function(){\r\n");
      out.write("            var s = $(\"#inputValue\").val().toLowerCase()\r\n");
      out.write("            var s1 = showNum.join(\"\")\r\n");
      out.write("            if (s==s1) {\r\n");
      out.write("                alert(\"æäº¤æå\")\r\n");
      out.write("            }else{\r\n");
      out.write("                alert(\"éªè¯ç éè¯¯\")\r\n");
      out.write("            }\r\n");
      out.write("            draw(showNum)\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        // å°è£ä¸ä¸ªæéæºéªè¯ç æ¾å¨ç»å¸ä¸\r\n");
      out.write("        function draw(showNum){\r\n");
      out.write("            // è·åcanvas\r\n");
      out.write("            var canvas = $(\"#c1\")\r\n");
      out.write("            var ctx = canvas[0].getContext(\"2d\")\r\n");
      out.write("            // è·åç»å¸çå®½é«\r\n");
      out.write("            var canvas_width = canvas.width()\r\n");
      out.write("            var canvas_height = canvas.height()\r\n");
      out.write("            //  æ¸ç©ºä¹åç»å¶çåå®¹\r\n");
      out.write("            // 0,0æ¸ç©ºçèµ·å§åæ \r\n");
      out.write("            // ç©å½¢çå®½é«\r\n");
      out.write("            ctx.clearRect(0,0,canvas_width,canvas_height)\r\n");
      out.write("            // å¼å§ç»å¶\r\n");
      out.write("            var scode = \"a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,1,2,3,4,5,6,7,8,9,\"\r\n");
      out.write("            var arrCode = scode.split(\",\")\r\n");
      out.write("            var arrLength = arrCode.length\r\n");
      out.write("            for(var i = 0;i<4;i++){\r\n");
      out.write("                var index = Math.floor(Math.random()*arrCode.length)\r\n");
      out.write("                var txt = arrCode[index]//éæºä¸ä¸ªå­ç¬¦\r\n");
      out.write("                showNum[i] = txt.toLowerCase()//è½¬åä¸ºå°åå­å¥éªè¯ç æ°ç»\r\n");
      out.write("                // å¼å§æ§å¶å­ç¬¦çç»å¶ä½ç½®\r\n");
      out.write("                var x = 10 +20*i //æ¯ä¸ä¸ªéªè¯ç ç»å¶çèµ·å§ç¹xåæ \r\n");
      out.write("                var y = 20 + Math.random()*8// èµ·å§ç¹yåæ \r\n");
      out.write("\r\n");
      out.write("                ctx.font = \"bold 20px å¾®è½¯éé»\"\r\n");
      out.write("                // å¼å§æè½¬å­ç¬¦\r\n");
      out.write("                var deg = Math.random*-0.5\r\n");
      out.write("                // canvas è¦å®ç°ç»å¶åå®¹å·æå¾æçææï¼å¿é¡»åå¹³ç§»ï¼ç®çæ¯ææè½¬ç¹ç§»å¨å°ç»å¶åå®¹çå°æ¹\r\n");
      out.write("                ctx.translate(x,y)\r\n");
      out.write("                ctx.rotate(deg)\r\n");
      out.write("                // è®¾ç½®ç»å¶çéæºé¢è²\r\n");
      out.write("                ctx.fillStyle = randomColor()\r\n");
      out.write("                ctx.fillText(txt,0,0)\r\n");
      out.write("\r\n");
      out.write("                // æcanvaså¤å\r\n");
      out.write("                ctx.rotate(-deg)\r\n");
      out.write("                ctx.translate(-x,-y)\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("            for(var i = 0;i<30;i++){\r\n");
      out.write("                if (i<5) {\r\n");
      out.write("                    // ç»å¶çº¿\r\n");
      out.write("                    ctx.strokeStyle = randomColor()\r\n");
      out.write("                    ctx.beginPath()\r\n");
      out.write("                    ctx.moveTo(Math.random()*canvas_width,Math.random()*canvas_height)\r\n");
      out.write("                    ctx.lineTo(Math.random()*canvas_width,Math.random()*canvas_height)\r\n");
      out.write("                    ctx.stroke()\r\n");
      out.write("                }\r\n");
      out.write("                // ç»å¶ç¹\r\n");
      out.write("                ctx.strokeStyle = randomColor()\r\n");
      out.write("                ctx.beginPath()\r\n");
      out.write("                var x = Math.random()*canvas_width\r\n");
      out.write("                var y = Math.random()*canvas_height\r\n");
      out.write("                ctx.moveTo(x,y)\r\n");
      out.write("                ctx.lineTo(x+1,y+1)\r\n");
      out.write("                ctx.stroke()\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        // éæºé¢è²\r\n");
      out.write("        function randomColor(){\r\n");
      out.write("            var r = Math.floor(Math.random()*256)\r\n");
      out.write("            var g = Math.floor(Math.random()*256)\r\n");
      out.write("            var b = Math.floor(Math.random()*256)\r\n");
      out.write("            return `rgb(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(',');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${g}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(',');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(")`\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    })\r\n");
      out.write("</script>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
