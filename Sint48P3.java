//package p2;
//--------------------------------------------------IMPORTS---------------------------------------------------------------------
import java.io.*;
import java.net.*;
//Servlets
import javax.servlet.*;
import javax.servlet.http.*;
//import javax.servlet.jsp.jstl.*;
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
import Xbean.GC1Resultado;
import Xbean.GC1Canciones;

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
	//Declaración de estructuras de datos
    public static HashMap<String,Document> mapDocs = new HashMap<String,Document>();
    public static LinkedList<String> listaFicheros = new LinkedList<String>();    
    //public  ArrayList<Cancion>Resultado = new ArrayList<Cancion>();
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
        File f= new File(context.getRealPath("iml.xsd"));
        String dir = f.getAbsolutePath();        
        String MY_SCHEMA = dir;      
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
            mensajeError = "Fatal Error: "+e.toString();
            System.out.println("Fatal Error: "+e.toString()); 
            EFatales.put(url,mensajeError);
            error = true;                    
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(Exception ee)
        {
            ee.printStackTrace();
        }        
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
                    mensajeError = "Fatal Error: "+e.toString();
                    System.out.println("Fatal Error: "+e.toString()); 
                    EFatales.put(url,mensajeError);                   
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
        //----------------------------- Objetos para las java beans ---------------------
        //fe = new GetFileError();             
        //req.setAttribute("anioBean",ga); //Esto para los años       
        if((p==null)&&(auto.equals("si")))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doXmlNop.jsp"); //habia sc.
            rd.forward(req,res);                       
        }
        else if((!p.equals("d4r18c392b"))&&(auto.equals("si")))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doXmlIp.jsp"); //habia sc.
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
                    //case "02": doGetFase02(auto,sc,req,res); break;
                    case "11": doGetFase11(auto,sc,req,res,ga); break;
                    case "12": doGetFase12(auto,sc,req,res,anio,gd); break;
                    case "13": doGetFase13(auto,sc,req,res,anio,idd,gc); break;
                    case "14": doGetFase14(auto,sc,req,res,anio,idd,idc,gr); break;
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
        //req.setAttribute("feBean",fe);
        if(auto==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF01.jsp"); //habia sc.
            rd.forward(req,res);
            //doHtmlF01(res); // doHtmlF01.jsp                
        }
        else if(auto.equals("si"))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doXmlF01.jsp"); //habia sc.
            rd.forward(req,res);
            //doXmlF01(res); //doXmlF01.jsp
        }        
    }//doHtmlF01
     

    /*public void doGetFase02(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res)throws IOException
    {        
        if(auto==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF02.jsp");
            rd.forward(req,res);
            //doHtmlF02(res);                
        }
        else if(auto.equals("si"))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doXmlF02.jsp");
            rd.forward(req,res);
            //doXmlF02(res);
        }         
    }//doGetFase02*/

    /*public void doHtmlF02(HttpServletResponse res)throws IOException
    {	               
        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");    
        out.println("<meta charset='utf-8'></meta>");
        out.println("<title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>");
	    out.println("<link rel='stylesheet' type='text/css' href='iml.css'></link>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de canciones</h1>");	    
        out.println("<h2>Se han encontrado "+Warnings.size()+" ficheros con warnings.</h2>");
        Iterator it = Warnings.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<p>"+key+"--------"+Warnings.get(key)+"</p>");
        }               	
        out.println("<h2>Se han encontrado "+Errores.size()+" ficheros con errores</h2>");
        it = Errores.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<p>"+key+"--------"+Errores.get(key)+"</p>");
        }              	              
        out.println("<h2>Se han encontrado "+EFatales.size()+" ficheros con errores fatales</h2>");
        it = EFatales.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<p>"+key+"--------"+EFatales.get(key)+"</p>");
        }               	              
        out.println("<button class = 'buttonAtras'onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=01'\">Atr&aacute;s</button>");
        out.println("</body>");
        out.println("<footer>");
        out.println("<p>sint48. @Diego R&iacute;os Castro.</p>");                
        out.println("</footer>");
        out.println("</html>");
    }//doHtmlF02*/

    /*public void doXmlF02(HttpServletResponse res)throws IOException
    {    
        res.setContentType("text/xml");
        PrintWriter out = res.getWriter();
        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<errores>");
        out.println("<warnings>");
        Iterator it = Warnings.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<warning>");        
            out.println("<file>"+key+"</file>");
            out.println("<cause>"+Warnings.get(key)+"</cause>");
            out.println("</warning>");
        }           
        out.println("</warnings>");
        out.println("<errors>");
        it = Errores.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<error>");
            out.println("<file>"+key+"</file>");
            out.println("<cause>"+Errores.get(key)+"</cause>");
            out.println("</error>");
        }               
        out.println("</errors>");
        out.println("<fatalerrors>");
        it = EFatales.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<fatalerror>");
            out.println("<file>"+key+"</file>");
            out.println("<cause>"+EFatales.get(key)+"</cause>");
            out.println("</fatalerror>");
        }              
        out.println("</fatalerrors>");
        out.println("</errores>");
    }//doXmlF02*/

    public void doGetFase11(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, GC1Anios ga)throws IOException, ServletException
    {
        ga.setAnio(mapDocs);
        req.setAttribute("aniBean",ga);        
        if(auto==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF11.jsp"); //habia sc.
            rd.forward(req,res);                          
        }
        else if(auto.equals("si"))
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/doXmlF11.jsp"); //habia sc.
            rd.forward(req,res);            
        }        
    }//doGetFase11

    public void doGetFase12(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, String anio, GC1Discos gd)throws IOException, ServletException
    {
                
        if(anio==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/wrongReqAnio.jsp");
            rd.forward(req,res);            
        }
        else
        {
            gd.setDisco(mapDocs,anio);
            gd.setAn(anio);
            req.setAttribute("disBean",gd);            
            if(auto==null)
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF12.jsp");
                rd.forward(req,res);                                
            }
            else if(auto.equals("si"))
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/doXmlF12.jsp");
                rd.forward(req,res);                
            }
        }               
    }//doGetFase12

    public void doGetFase13(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, String anio, String idd, GC1Canciones gc)throws IOException, ServletException
    {        
        if(anio==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/wrongReqAnio.jsp");
            rd.forward(req,res);
        }
        else if(idd==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/wrongReqIdd.jsp");
            rd.forward(req,res);            
        }
        else
        {
            gc.setCancion(mapDocs,anio,idd);
            gc.setAn(anio);
            gc.setId(idd);
            req.setAttribute("canBean",gc);                    
            if(auto==null)
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF13.jsp");
                rd.forward(req,res);
                //doHtmlF13(res,anio, idd, listaCanciones);                
            }
            else if(auto.equals("si"))
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/doXmlF13.jsp");
                rd.forward(req,res);
                //doXmlF13(res,idd,listaCanciones);
            }

        }               
    }//doGetFase13

    public void doGetFase14(String auto, ServletContext sc, HttpServletRequest req, HttpServletResponse res, String anio, String idd, String idc, GC1Resultado gr)throws IOException, ServletException
    {           
        if(anio==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/wrongReqAnio.jsp");
            rd.forward(req,res);            
        }
        else if(idd==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/wrongReqIdd.jsp");
            rd.forward(req,res);
        }
        else if(idc==null)
        {
            RequestDispatcher rd = sc.getRequestDispatcher("/wrongReqIdc.jsp");
            rd.forward(req,res);
            //wrongReqIdc(res);
        }
        else
        {
            gr.setResultado(mapDocs,anio,idd,idc);
            gr.setAn(anio);
            gr.setId(idd);
            gr.setIc(idc);
            req.setAttribute("resBean",gr);
            
            /*ArrayList<String> titulos = new ArrayList<String>();            
            for(int i=0;i<Resultado.size();i++)
            {
                Cancion objRes = Resultado.get(i);
                titulos.add(objRes.getTitulo(objRes)); 
            }
            Comparator<String> comparador = Collections.reverseOrder();
            Collections.sort(titulos, comparador);
            for(int j = 0;j<titulos.size();j++)
            {   
                String titlist = titulos.get(j);
                for(int k = 0;k<Resultado.size();k++)
                {
                    Cancion obj = Resultado.get(k);
                    String titu = obj.getTitulo(obj);
                    if(titlist.equals(titu))
                    {
                        Resultado.remove(obj);
                        Resultado.add(obj);
                    }
                }           
            }*/               
            if(auto==null)
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/doHtmlF14.jsp");
                rd.forward(req,res);
                //doHtmlF14(res,anio, idd, idc, Resultado);                
            }
            else if(auto.equals("si"))
            {
                RequestDispatcher rd = sc.getRequestDispatcher("/doXmlF14.jsp");
                rd.forward(req,res);
                //doXmlF14(res,idc,Resultado);
            }
        }               
    }//doGetFase14
    /*public ArrayList<Cancion> getC1Resultado (String anio, String idd, String idc) //Todas las canciones de un interprete que duren menos que una elegida
    {
        System.out.println("anioooooooooooooooooooooo"+anio);
        System.out.println("iddddddddddddddddddd"+idd);
        System.out.println("idcccccccccccccccc"+idc);
        String dur = null;
        String interprete = null;
        String atributoUno = null;
        String atributoDos = null;
        String atributoTres = null;
        String atributoCuatro = null;
        String atributoCinco = null;
        String atributoSeis = "";
        String nodosText = "";
        String atribDisco = "";
        String atribCancion = "";
        Boolean isFirst = false;       
        Document res = null;
        Boolean flag = false;    
        ArrayList<Cancion> listares = new ArrayList<Cancion>();
        for (String key:mapDocs.keySet())
        {
            res = mapDocs.get(key);
            //Document doc = mapDocs.get(value);    
            Element raiz = res.getDocumentElement(); 
            NodeList nodeDiscos = raiz.getElementsByTagName("Disco");
            for(int i = 0;i<nodeDiscos.getLength();i++)
            {
                Node itemDisco = nodeDiscos.item(i);
                atribDisco=itemDisco.getAttributes().getNamedItem("idd").getTextContent();
                if(atribDisco.equals(idd))
                {
                    NodeList childDiscos = itemDisco.getChildNodes();
                    for (int j=0;j<childDiscos.getLength();j++)
                    {                               
                        if(childDiscos.item(j).getNodeName().equals("Interprete"))
                        {
                            interprete = childDiscos.item(j).getTextContent();
                        }
                        else if(childDiscos.item(j).getNodeName().equals("Cancion"))
                        {                            
                            atribCancion=childDiscos.item(j).getAttributes().getNamedItem("idc").getTextContent();
                            if(atribCancion.equals(idc))
                            {
                                Node itemCancion = childDiscos.item(j);
                                NodeList childCancion = itemCancion.getChildNodes();
                                for (int r=0;r<childCancion.getLength();r++)
                                {
                                    if(childCancion.item(r).getNodeName().equals("Duracion"))
                                    {
                                        dur = childCancion.item(r).getTextContent();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }                    
        for (String key:mapDocs.keySet())
        {
            res = mapDocs.get(key);
            //Document doc = mapDocs.get(value);    
            Element raiz = res.getDocumentElement(); 
            NodeList nodeDiscos = raiz.getElementsByTagName("Disco");            
            for(int i = 0;i<nodeDiscos.getLength();i++)
            {
                Node itemDisco = nodeDiscos.item(i);                
                NodeList childDiscos = itemDisco.getChildNodes();            
                for (int j=0;j<childDiscos.getLength();j++)
                {                               
                    if(childDiscos.item(j).getNodeName().equals("Interprete"))
                    {                   
                        String nameInt = childDiscos.item(j).getTextContent();
                        if(nameInt.equals(interprete))
                        {
                            flag = true;
                            Node parentInterprete = childDiscos.item(j).getParentNode();
                            NodeList hijosDisco = parentInterprete.getChildNodes();
                            for (int x = 0; x<hijosDisco.getLength();x++) //hijos:titulo,premios,interprete,cancion
                            {
                                Node hijoPremios = hijosDisco.item(x);
                                if(hijoPremios.getNodeName().equals("Premios"))
                                {                            
                                    NodeList hayPremios = hijoPremios.getChildNodes(); //Node List de premios
                                    for (int m = 0;m<hayPremios.getLength();m++)
                                    {
                                        Node premio = hayPremios.item(m);
                                        if((premio.getNodeName().equals("Premio")) && (m==0))
                                        {
                                            atributoSeis = premio.getTextContent().trim();
                                            //atributoSeis = "blank";
                                        }
                                        else if((premio.getNodeName().equals("Premio")) && (m!=0))
                                        {
                                            atributoSeis = (atributoSeis+" "+premio.getTextContent()).trim();                                            
                                        }                                        
                                    }
                                }                                
                            }                            
                        }
                        else 
                        {
                            flag = false;
                        }                                                                 
                    }
                    else if(childDiscos.item(j).getNodeName().equals("Cancion") && flag)
                    {
                        Node itemcancion = childDiscos.item(j);
                        atributoCuatro=itemcancion.getAttributes().getNamedItem("idc").getTextContent();
                        NodeList childCancion = itemcancion.getChildNodes();                   
                        for(int k = 0;k<childCancion.getLength(); k++)
                        {                                                   
                            if(childCancion.item(k).getNodeName().equals("Titulo"))
                            {
                                atributoUno = childCancion.item(k).getTextContent();
                            }
                            if(childCancion.item(k).getNodeName().equals("Genero"))
                            {
                                atributoDos = childCancion.item(k).getTextContent();
                            }
                            if(childCancion.item(k).getNodeName().equals("Duracion"))
                            {
                                atributoTres = childCancion.item(k).getTextContent();
                            }
                            if(childCancion.item(k).getNodeType() == org.w3c.dom.Node.TEXT_NODE)
                            {                                
                                if(!isFirst)
                                {
                                    nodosText = childCancion.item(k).getNodeValue();
                                    isFirst = true;
                                }
                                else
                                {
                                    nodosText = nodosText.concat(childCancion.item(k).getNodeValue());
                                }                                
                            }                                                                                                                       
                        }
                        atributoCinco = nodosText.trim();
                        listares.add(new Cancion(atributoUno,atributoDos,atributoTres, atributoCuatro, atributoCinco, atributoSeis));
                        atributoUno = null;
                        atributoDos = null;
                        atributoTres = null;
                        atributoCuatro = null;
                        atributoCinco = null;                        
                        isFirst = false;                                         
                    }                                        
                }//Cierra for recorrer hijos Disco
                atributoSeis="";
                flag = false;                                
            }//Cierra for recorrer nodos Disco               
        }//Cierra for recorrer hashmap mapDocs
        //Recorre listares y obtener las duraciones comparando cada una con la elegida. Si es menor, meterlo en la lista resultado
        for (int i = 0;i<listares.size();i++)
        {
            Cancion obj = listares.get(i);
            int durobj = Integer.parseInt(obj.getDuracion(obj));
            if(durobj < Integer.parseInt(dur))
            {
                Resultado.add(obj);                
            }
        }
        System.out.println("Hizo el resultado");
        return Resultado;        
    }//getC1Resultado*/    

    /*public void doHtmlF13(HttpServletResponse res, String anio, String idd, ArrayList<Cancion> listaCanciones)throws IOException
    {                              
        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>");
        out.println("<meta charset='utf-8'></meta>");
	    out.println("<link rel='stylesheet' type='text/css' href='iml.css'></link>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de canciones</h1>");    
        out.println("<h2>Consulta 1: A&ntilde;o="+anio+", Disco="+idd+"</h2>");
        out.println("<form name = 'miformfase13'>");    
        out.println("<h3>Selecciona una canci&oacute;n:</h3>");
	    out.println("<input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>");
        out.println("<input type = 'hidden' name = 'pfase' value = '14'></input>");	
        out.println("<input type = 'hidden' name = 'panio' value = '"+anio+"'></input>");
        out.println("<input type = 'hidden' name = 'pidd' value = '"+idd+"'></input>");        
        for(int i=0;i<listaCanciones.size();i++)
        {
            Cancion c = listaCanciones.get(i);
            if(i==0)
            {
                out.println("<p><input type = 'radio' name = 'pidc' value = "+c.getIdc(c)+" checked>"+Integer.toString(i+1)+".-"+" T&iacute;tulo ='"+c.getTitulo(c)+"' --- IDC ='"+c.getIdc(c)+"' --- G&eacutenero ='"+c.getGenero(c)+"' --- Duraci&oacute;n ='"+c.getDuracion(c)+" seg.'</input></p>");
            }
            else out.println("<p><input type = 'radio' name = 'pidc' value = "+c.getIdc(c)+">"+Integer.toString(i+1)+".-"+" T&iacute;tulo ='"+c.getTitulo(c)+"' --- IDC ='"+c.getIdc(c)+"' --- G&eacute;nero ='"+c.getGenero(c)+"' --- Duraci&oacute;n ='"+c.getDuracion(c)+" seg.'</input></p>");        
        }       
        out.println("<br></br>");    
        out.println("<input type = 'submit' class = 'buttonSubmit'></input>");
        out.println("</form>");
        out.println("<button class = 'buttonAtras' onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=12&panio="+anio+"'\">Atr&aacute;s</button> ");
        out.println("<br></br>");    
        out.println("<button class = 'buttonInicio' onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=01'\">Inicio</button> ");
        out.println("</body>");
        out.println("<footer>");
        out.println("<p>sint48. @Diego R&iacute;os Castro.</p>");                
        out.println("</footer>");
        out.println("</html>");
    }//doHtmlF13    

    public void doXmlF13(HttpServletResponse res,String idd, ArrayList<Cancion> listaCanciones)throws IOException
    {
        res.setContentType("text/xml");
        PrintWriter out = res.getWriter();
        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<canciones>");
        for(int i=0;i<listaCanciones.size();i++)
        {
            Cancion c = listaCanciones.get(i);
            out.println("<cancion idc='"+c.getIdc(c)+"' genero='"+c.getGenero(c)+"' duracion='"+c.getDuracion(c)+"'>"+c.getTitulo(c)+"</cancion>");
        }
        out.println("</canciones>");                   
    }//doXmlF13

    public void doHtmlF14(HttpServletResponse res, String anio, String idd, String idc, ArrayList<Cancion> Resultado)throws IOException
    {                              
        PrintWriter out = res.getWriter();        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>");
        out.println("<meta charset='utf-8'></meta>");
        out.println("<link rel='stylesheet' type='text/css' href='iml.css'></link>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de canciones</h1>");
        out.println("<form name = 'miformfase14'>");
        out.println("<h2>Consulta 1: A&ntilde;o="+anio+", Disco="+idd+", Canci&oacute;n="+idc+"</h2>");    
        out.println("<h3>Este es el resultado:</h3>");
        for(int i=0;i<Resultado.size();i++)
        {
            Cancion obj = Resultado.get(i);
            String premios = obj.getPremios(obj);            
            out.println("<p>"+Integer.toString(i+1)+".-"+" T&iacute;tulo = '"+obj.getTitulo(obj)+"' --- Descripci&oacute;n = '"+obj.getDescripcion(obj)+"' --- Premios = '"+obj.getPremios(obj)+"'</p>");                    
                    
        }        
        out.println("</form>");
        out.println("<button class = 'buttonAtras'  onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=13&panio="+anio+"&pidd="+idd+"'\">Atr&aacute;s</button> ");
        out.println("<br></br>");
        out.println("<button class = 'buttonInicio' onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=01'\">Inicio</button> ");
        out.println("</body>");
        out.println("<footer>");
        out.println("<p>sint48. @Diego R&iacute;os Castro.</p>");                
        out.println("</footer>");
        out.println("</html>"); 
    }//doHtmlF14
    

    public void doXmlF14(HttpServletResponse res,String idc, ArrayList<Cancion> Resultado)throws IOException
    {
        res.setContentType("text/xml");
        PrintWriter out = res.getWriter();
        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<songs>");
        for(int i=0;i<Resultado.size();i++)
        {
            Cancion obj = Resultado.get(i);
            String premios = obj.getPremios(obj);                
            out.println("<song descripcion='"+obj.getDescripcion(obj)+"' premios='"+obj.getPremios(obj)+"'>"+obj.getTitulo(obj)+"</song>");                
        }
        out.println("</songs>");                
    }//doXmlF14*/   
//}//Fin SINT48P2

//-----------------------------------------------------------------ERROR CLASS-----------------------------------------------------------------------
class ErrorHandler extends DefaultHandler 
{    
    //public ErrorHandler () {} 

    //Metodos
    public void warning (SAXParseException spe) throws SAXException 
    {        
        error = true;
        //fe.setWar(spe,url);       
    }

    public void error (SAXParseException spe) throws SAXException 
    {         
        error = true;
        //fe.setErr(spe,url);       
    }

    public void fatalerror (SAXParseException spe) throws SAXException 
    {        
        error = true;
        //fe.setFE(spe,url);       
    }    
}//Fin error handler

//---------------------------------------------------------------------CLASE DISCO-----------------------------------------------------------------
/*class Disco
{
    //Atributos
    private String titulo = "";
	private String iDD = "";
    public String interprete = ""; 
    private String idiomas = "";

    //Constructor
    public Disco(String atributoUno, String atributoDos, String atributoTres, String atributoCuatro) 
    {
        titulo = atributoUno;
        interprete = atributoDos;
        iDD = atributoTres;
        idiomas = atributoCuatro;
    }

    //Métodos
    public String getTitulo(Disco d)
    {        
        return d.titulo;
    }
    
    public String getIDD(Disco d)
    {        
        return d.iDD;
    }

    public String getInterprete(Disco d)
    {
        return d.interprete;
    }

    public String getIdiomas(Disco d)
    {        
        return d.idiomas;
    }    
}//Fin clase Disco

//------------------------------------------------------------------------CLASE CANCION-------------------------------------------
class Cancion 
{
    //Atributos
    public String titulo = "";
	private String iDC = "";
    private String genero = ""; 
    public String duracion = "";
    private String desc = "";
    private String premios = "";

    //Constructor
    public Cancion(String atributoUno, String atributoDos, String atributoTres, String atributoCuatro, String atributoCinco, String atributoSeis) 
    {
        titulo = atributoUno;
        genero = atributoDos;
        duracion = atributoTres;
        iDC = atributoCuatro;
        desc = atributoCinco;
        premios = atributoSeis;        
    }

    //Métodos
    public String getTitulo(Cancion c)
    {        
        return c.titulo;
    }    
    public String getGenero(Cancion c)
    {
        return c.genero;
    }
    public String getDuracion(Cancion c)
    {        
        return c.duracion;
    }
    public String getIdc(Cancion c)
    {        
        return c.iDC;
    }
    public String getDescripcion(Cancion c)
    {        
        return c.desc;
    }
    public String getPremios(Cancion c)
    {      
        return c.premios;
    }
}//fin clase Cancion*/
}//Fin SINT48P2
//-------------------------------------------------------------------------------END--------------------------------------------------------------------