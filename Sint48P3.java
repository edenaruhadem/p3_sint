//package p2;
//--------------------------------------------------IMPORTS---------------------------------------------------------------------
import java.io.*;
import java.net.*;
//Servlets
import javax.servlet.*;
import javax.servlet.http.*;
//Estructuras de datos
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
//import javax.servlet.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.LinkedList;
import java.util.Iterator;
//Parsers, DOM y Exceptions
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.*;
import javax.lang.model.util.ElementScanner6;
import javax.security.sasl.SaslException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import Xbean.GC1Anios;
import Xbean.GC1Discos;
import Xbean.GC1Errores;
import Xbean.GC1Resultado;
import Xbean.GC1Resultadojson;
import Xbean.GC1Canciones;
//import Xbean.GC1Xslt;

//import Xbean.FileError;

import org.xml.sax.helpers.*;

//---------------------------------------------------CLASE SINT48P2--------------------------------------------------------------
public class Sint48P3 extends HttpServlet 
{   
    //------------------------------------------------DECLARACIONES--------------------------------------------------------------- 
	//Atributes Schema
	static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";    
    public static String url;
    public String rutaXml = "http://gssi.det.uvigo.es/users/agil/public_html/SINT/18-19/";
    GC1Anios ga; //Objeto java bean anios
    GC1Discos gd; //Objeto java bean discos
    GC1Canciones gc;
    GC1Resultado gr;
    GC1Errores ge;
    GC1Resultadojson gj;    
	//Declaración de estructuras de datos
    public static HashMap<String,Document> mapDocs = new HashMap<String,Document>();
    public static ArrayList<String> urlDocs = new ArrayList<String>();    
    public static LinkedList<String> listaFicheros = new LinkedList<String>();    
    //public static ArrayList<String>fichErroneos = new ArrayList<String>();
    public Map<String,String>Errores= new HashMap<String,String>();
    public Map<String,String>EFatales = new HashMap<String,String>();
    public Map<String,String>Warnings = new HashMap<String,String>();    
    public boolean error = false;
    public String mensajeError = "";
    //public ArrayList<Document>documentos = new ArrayList<Document>;

//------------------------------------------------------------SERVLET.INIT------------------------------------------------------------------------        
    public void init(ServletConfig config) throws ServletException
    {
        /*ServletContext sc = getServletContext();
        ServletContext context = config.sc;*/
        ServletContext context = config.getServletContext();        
        File f= new File(context.getRealPath("p3/iml.xsd"));
        String dir = f.getAbsolutePath();        
        String MY_SCHEMA = dir;
        ge = new GC1Errores();      
	//----------------Aquí hay que leer los ficheros. Eliminar erróneos para el procesado posterior-------------    	
	//Creada batería de parsers
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //Asignación de atributos
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        dbf.setAttribute(JAXP_SCHEMA_SOURCE, MY_SCHEMA);
        //Declaración de objetos: db y doc(almacén del árbol)       
        DocumentBuilder db = null;
        Document doc = null;	            
        //Estructura dinámica para no repetir archivos 
        ArrayList<String> leidos = new ArrayList<String>();    //Era HashSet            
	    String strAnios = null;        
	    //-------------Objeto clase document builder dbf(conjunto de parsers)------------------
	    try
        {
            //Creacion de un parser de la familia de la batería de antes
            db = dbf.newDocumentBuilder();
            ErrorHandler er = new ErrorHandler(); //Creacion del objeto error
            //Asingación de la clase de control de errores al parser 
            db.setErrorHandler(er);
        }catch(ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }        
        try
        {
            //listaFicheros.add(rutaXml+"iml2001.xml");
            //url = (String) listaFicheros.getFirst();
            doc = db.parse(new URL(rutaXml+"iml2001.xml").openStream());           
            leidos.add(url);            
            buscaIml(doc, mapDocs);
            listaFicheros.removeFirst();
        }catch(SAXException e)
        {            
            e.printStackTrace();
            /*mensajeError = "Fatal Error: "+e.toString();
            System.out.println("Fatal Error: "+e.toString());*/ 
            ge.setErroresf(e,url);
            ge.setTamfer();
            error = true;                    
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(Exception ee)
        {
            ee.printStackTrace();
        }
        //urlDocs.add((rutaXml+"iml2001.xml").substring(7));        
	    while(!listaFicheros.isEmpty())
        {
            url = (String) listaFicheros.getFirst();        
            if(!leidos.contains(url))
            {
                try
                {
                //Generacion del arbol DOM tras el parseo. Generará un error el método parse si el documento no  es well-formed. Una SAXException
                //Saltará la clase de gestión de errores en caso de que los contenga (no válido según el schema xml	definido).            
                    doc = db.parse(new URL(url).openStream());                    
                    leidos.add(url);                    
                }catch(SAXException e)
                {                    
                    e.printStackTrace();
                    /*mensajeError = "Fatal Error: "+e.toString();
                    System.out.println("Fatal Error: "+e.toString());*/ 
                    ge.setErroresf(e,url);
                    ge.setTamfer();
                    error = true;                                      
                }catch(IOException ioe)
                {                    
                    ioe.printStackTrace();
                }catch(Exception ee)
                {                    
                    ee.printStackTrace();
                }                
                if(!error)
                {
                    urlDocs.add(url.substring(7));
                    buscaIml(doc, mapDocs);
                }
                error = false;
            }//if leidos containsURL
            listaFicheros.removeFirst();            
        }///While true		
    }//Init
    
//-------------------------------------------------------------SERVLET.DOGET()--------------------------------------------------------------    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {       
        ServletContext sc = req.getServletContext();
        String p = req.getParameter("p");        
        String fase = req.getParameter("pfase");
        String anio = req.getParameter("panio");
        String idd = req.getParameter("pidd");
        String idc = req.getParameter("pidc");
        String auto = req.getParameter("auto");
        ga = new GC1Anios();
        gd = new GC1Discos();
        gc = new GC1Canciones();
        gr = new GC1Resultado();
        gj = new GC1Resultadojson();        
        //----------------------------- Objetos para las java beans ---------------------
        //fe = new GetFileError();             
        //req.setAttribute("anioBean",ga); //Esto para los años       
        if((p==null)&&(auto.equals("si")))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlNop.jsp"); //habia sc.
            rd.forward(req,res);                       
        }
        else if((!p.equals("d4r18c392b"))&&(auto.equals("si")))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlIp.jsp"); //habia sc.
            rd.forward(req,res);            
        }        
	    else
	    {
            String faseinicial = "01";
            if((fase==null) || (fase.equals(faseinicial)))
            {                
                doGetFase01(auto,sc,req,res);
            }
            else
            {
		        switch(fase)
                {                        
                    //case "01": doGetFase01(auto,sc,req,res); break;                    
                    case "02": doGetFase02(auto,sc,req,res); break;
                    case "03": doGetFase03(sc,req,res,ga);
                    case "11": doGetFase11(auto,sc,req,res,ga); break;
                    case "12": doGetFase12(auto,sc,req,res,anio,gd); break;
                    case "13": doGetFase13(auto,sc,req,res,anio,idd,gc); break;
                    //case "14": doGetFase14(auto,sc,req,res,anio,idd,idc,gr); break;
                    case "14": doGetFase14(auto,sc,req,res,anio,idd,idc,gj); break;
                }
            }
	    }        
    }//doGet
    
//---------------------------------------------------------FUNCTIONS------------------------------------------------------------------------
    public void buscaIml(Document doc, HashMap<String,Document> mapDocs)
    {
        Element raiz = doc.getDocumentElement(); //Obtencion del elemento Songs
        NodeList anios = raiz.getElementsByTagName("Anio"); //Recoge todos los elementos anios del xml. Solo hay uno
        NodeList urls = raiz.getElementsByTagName("IML"); //Recoge todos los elementos IML del xml
        Node itemAnio =anios.item(0);                    
		String strAnios = itemAnio.getTextContent();
		mapDocs.put(strAnios,doc);                    
        for(int i = 0;i<urls.getLength();i++) //El acceso al texto de IML produce redirecciones a nuevos documentos. 
        {
            Node itemUrl = urls.item(i);                        
            listaFicheros.add(rutaXml+itemUrl.getTextContent().trim());                        
        }        
    }

    public void doGetFase01(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
    {        
        if(auto==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF01.jsp"); //habia sc.
            rd.forward(req,res);
            //doHtmlF01(res); // doHtmlF01.jsp                
        }
        else if(auto.equals("si"))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlF01.jsp"); //habia sc.
            rd.forward(req,res);
            //doXmlF01(res); //doXmlF01.jsp
        }        
    }//doHtmlF01
     

    public void doGetFase02(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
    {
        req.setAttribute("errBean",ge);        
        if(auto==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF02.jsp");
            rd.forward(req,res);                            
        }
        else if(auto.equals("si"))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlF02.jsp");
            rd.forward(req,res);            
        }         
    }//doGetFase02
    
    public void doGetFase03(ServletContext sc, HttpServletRequest req, HttpServletResponse res, GC1Anios ga)throws IOException, ServletException
    {
        ga.setHanio(mapDocs);
        req.setAttribute("aniBean",ga);
        RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF03.jsp");
        rd.forward(req,res);                     
    }//doGetFase03 

    public void doGetFase11(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, GC1Anios ga)throws IOException, ServletException
    {
        ga.setHanio(mapDocs);
        //ga.setTam();
        req.setAttribute("aniBean",ga);        
        if(auto==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF11.jsp"); //habia sc.
            rd.forward(req,res);                          
        }
        else if(auto.equals("si"))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlF11.jsp"); //habia sc.
            rd.forward(req,res);            
        }        
    }//doGetFase11

    public void doGetFase12(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, String anio, GC1Discos gd)throws IOException, ServletException
    {
                
        if(anio==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/wrongReqAnio.jsp");
            rd.forward(req,res);            
        }
        else
        {
            gd.setHdisco(mapDocs,anio);
            gd.setAn(anio);
            req.setAttribute("disBean",gd);            
            if(auto==null)
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF12.jsp");
                rd.forward(req,res);                                
            }
            else if(auto.equals("si"))
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlF12.jsp");
                rd.forward(req,res);                
            }
        }               
    }//doGetFase12

    public void doGetFase13(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, String anio, String idd, GC1Canciones gc)throws IOException, ServletException
    {        
        if(anio==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/wrongReqAnio.jsp");
            rd.forward(req,res);
        }
        else if(idd==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/wrongReqIdd.jsp");
            rd.forward(req,res);            
        }
        else
        {
            gc.setHcancion(mapDocs,anio,idd);
            gc.setAn(anio);
            gc.setId(idd);
            req.setAttribute("canBean",gc);                    
            if(auto==null)
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF13.jsp");
                rd.forward(req,res);                             
            }
            else if(auto.equals("si"))
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlF13.jsp");
                rd.forward(req,res);                
            }

        }               
    }//doGetFase13

    public void doGetFase14(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, String anio, String idd, String idc, /*GC1Resultado gr,*/GC1Resultadojson gj)throws IOException, ServletException
    {           
        if(anio==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/wrongReqAnio.jsp");
            rd.forward(req,res);            
        }
        else if(idd==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/wrongReqIdd.jsp");
            rd.forward(req,res);
        }
        else if(idc==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/p3/wrongReqIdc.jsp");
            rd.forward(req,res);            
        }
        else
        {
            //boolean isAsc = true;            
            gj.setResultadojsona(mapDocs, anio, idd, idc);
            gj.setResultadojsond(mapDocs, anio, idd, idc);
            gj.setAn(anio);
            gj.setId(idd);
            gj.setIc(idc);
            //isAsc = false;
            //gj.setResultadojson(mapDocs, anio, idd, idc, isAsc);
            /*gr.setHresultado(mapDocs,anio,idd,idc);
            gr.setAn(anio);
            gr.setId(idd);
            gr.setIc(idc);
            req.setAttribute("resBean",gr);*/
            req.setAttribute("resBean",gj);                       
            if(auto==null)
            {
                /*RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF14.jsp");
                rd.forward(req,res);*/
                RequestDispatcher rd = sc.getRequestDispatcher("/p3/doHtmlF14Json.jsp");
                rd.forward(req,res);                               
            }
            else if(auto.equals("si"))
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/p3/doXmlF14.jsp");
                rd.forward(req,res);                
            }
        }               
    }//doGetFase14       
//}//Fin SINT48P2

//-----------------------------------------------------------------ERROR CLASS-----------------------------------------------------------------------
class ErrorHandler extends DefaultHandler 
{    
    //public ErrorHandler () {} 

    //Metodos
    public void warning (SAXParseException spe) throws SAXException 
    {        
        error = true;
        ge.setWarnings(spe,url);
        ge.setTamwar();
        //fe.setWar(spe,url);       
    }

    public void error (SAXParseException spe) throws SAXException 
    {         
        error = true;
        ge.setErrores(spe,url);
        ge.setTamerr();
        //fe.setErr(spe,url);       
    }

    public void fatalerror (SAXParseException spe) throws SAXException 
    {        
        error = true;
        ge.setErroresf(spe,url);
        ge.setTamfer();
        //fe.setFE(spe,url);       
    }    
}//Fin error handler
}//Fin SINT48P2
//-------------------------------------------------------------------------------END--------------------------------------------------------------------