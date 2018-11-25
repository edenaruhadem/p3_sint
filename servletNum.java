//package beans;
import java.io.*;
//Servlets
import javax.servlet.*;
import javax.servlet.http.*;
import Xbean.AuxBean;
//---------------------------------------------------CLASE servletSuma--------------------------------------------------------------
public class servletNum extends HttpServlet 
{
    AuxBean ab;
    Integer n = 1;   
//-------------------------------------------------------------SERVLET.DOGET()--------------------------------------------------------------    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {        
        ab = new AuxBean();
        //String first = n.toString();
        ab.setNum("1");
        System.out.println("Actualizoooooooo");        
        req.setAttribute("laBean",ab);
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/pag.jsp");
        rd.forward(req,res);
        
    }//doGet
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {   
        System.out.println("Entró en método post");
        n = n+1;     
        String a = n.toString();        
        ab.setNum(a);        
        req.setAttribute("laBean",ab);
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/pag.jsp");
        rd.forward(req,res);
    }//doPost    
//---------------------------------------------------------FUNCTIONS------------------------------------------------------------------------
}//Fin SINT48P2

//-------------------------------------------------------------------------------END--------------------------------------------------------------------
