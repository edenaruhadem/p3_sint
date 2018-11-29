package Xbean;

import java.util.ArrayList;
import java.util.*;
import java.lang.reflect.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class GC1Errores
{
    private Map<String,String>errores= new HashMap<String,String>();
    private Map<String,String>erroresf= new HashMap<String,String>();
    private Map<String,String>warnings= new HashMap<String,String>();
    private String tamwar;
    private String tamerr;
    private String tamfer;

    
    public GC1Errores(){}

    public void setWarnings(SAXException spe, String url)
    {
        String mensajeError = "Warning: "+spe.toString(); 
        System.out.println("Warning: "+spe.toString());
        warnings.put(url,mensajeError);

    }
    public void setTamwar()
    {
        if(warnings.isEmpty())
        {
            tamwar = "0";
        }
        else
        {
            String tam = Integer.toString(warnings.size()); 
            tamwar=tam;
        }
    }
    public void setErrores(SAXException spe, String url)
    {
        String mensajeError = "Error: "+spe.toString();
        System.out.println("Error: "+spe.toString());        
        errores.put(url,mensajeError);
    }
    public void setTamerr()
    {
        String tam = Integer.toString(errores.size()); 
        tamerr=tam;

    }
    public void setErroresf(SAXException spe, String url)
    {
        String mensajeError = "Fatal Error: "+spe.toString();
        System.out.println("Fatal Error: "+spe.toString()); 
        erroresf.put(url,mensajeError);
    }
    public void setTamfer()
    {
        String tam = Integer.toString(erroresf.size()); 
        tamfer=tam;
    }
    public Map<String,String> getWarnings()
    {
        return(warnings);
    }
    public String getTamwar()
    {
        return(tamwar);
    }
    
    public Map<String,String> getErrores()
    {
        return (errores);        
    }
    public String getTamerr()
    {
        return(tamerr);
    }
    
    public Map<String,String> getErroresf()
    {        
        return (erroresf);        
    }
    public String getTamfer()
    {
        return(tamfer);
    }    
}