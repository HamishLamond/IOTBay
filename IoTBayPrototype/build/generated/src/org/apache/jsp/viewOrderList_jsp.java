package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.HashMap;
import uts.isd.group30.model.Customer;

public final class viewOrderList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/IoTBayCSS.css\">\n");
      out.write("        <title>Customer Order List</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            Customer customer = (Customer)session.getAttribute("customer");
            HashMap<String, Integer> cart = (HashMap<String, Integer>)session.getAttribute("cart");
         
      out.write("\n");
      out.write("        <h1>IoTBay</h1>\n");
      out.write("        <hr>\n");
      out.write("        <div class=\"top_right_link_div\">\n");
      out.write("            <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            ");
 if (cart != null){ 
      out.write("\n");
      out.write("            <a href=\"currentOrder.jsp\">View Order [");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cart.size()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("]</a>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("        ");
 if (customer.getName() != null){ 
      out.write("\n");
      out.write("            <h2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("'s Order List</h2>\n");
      out.write("            <table class=\"order_table\">\n");
      out.write("                <tr>\n");
      out.write("                    <th>Transaction Number</th>\n");
      out.write("                    <th>Date Last Modified</th>\n");
      out.write("                    <th>Status</th>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    if (cart.size() > 0){
                        for (int i = 0; i < cart.size(); i++){
                            
                    
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td><a>Transaction ID</a></td>\n");
      out.write("                    <td>Transaction LMD</td>\n");
      out.write("                    <td>Transaction Stat</td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                        }
                    }
                    
      out.write("\n");
      out.write("            </table>\n");
      out.write("        ");
 } else { 
      out.write("\n");
      out.write("            <h2>Order List</h2>\n");
      out.write("            <p>Please log in to load your transaction list</p>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
