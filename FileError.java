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


public class FileError
{
    private Map<String,String>Errores= new HashMap<String,String>();
    private Map<String,String>EFatales= new HashMap<String,String>();
    private Map<String,String>Warnings= new HashMap<String,String>();

    private Integer sizeWarn;
    private Integer sizeErr;
    private Integer sizeEF;
    private String url;
    private String msge;

    private String [] alErr;
    private String [] alEF;
    private String [] alWarn;
    private String [] filew;
    private String [] causew;
    private String [] file;
    private String [] cause;
    private String [] filef;
    private String [] causef;


    
    public FileError(){}
    public void setWar(SAXParseException spe, String url)
    {
        String mensajeError = "Warning: "+spe.toString(); 
        System.out.println("Warning: "+spe.toString());
        Warnings.put(url,mensajeError);

    }
    public void setErr(SAXParseException spe, String url)
    {
        String mensajeError = "Error: "+spe.toString();
        System.out.println("Error: "+spe.toString());        
        Errores.put(url,mensajeError);
    }
    public void setFE(SAXParseException spe, String url)
    {
        String mensajeError = "Fatal Error: "+spe.toString();
        System.out.println("Fatal Error: "+spe.toString()); 
        EFatales.put(url,mensajeError);
    }
    public String [] getWar()
    {
        sizeWarn = Warnings.size();
        Iterator it = Warnings.keySet().iterator();
        Integer i = 0;
        while(it.hasNext())
        {
            String key = (String)it.next();
            alWarn[i] = key+" "+Warnings.get(key);
            //out.println("<p>"+key+"--------"+Warnings.get(key)+"</p>");
            i = i + 1;
        }
        return alWarn;

    }
    public String [] getWarnf(String [] alWarn)
    {
        for(int i = 0; i< getLength(alWarn); i++)
        {
            String [] parts = alWarn(i).trim().split(" ");
            filew[i] = parts[0];            
        }
        return filew;
    }
    public String [] getWarnc(String [] alWarn)
    {
        for(int i = 0; i< getLength(alWarn); i++)
        {
            String [] parts = alWarn(i).trim().split(" ");            
            causew[i] = parts[1];
        }
        return causew;
    }
    public String [] getErr()
    {
        sizeErr = Errores.size();        
        Iterator it = Errores.keySet().iterator();
        Integer i = 0;
        while(it.hasNext())
        {
            String key = (String)it.next();
            alErr[i] = key+" "+Errores.get(key);
            //out.println("<p>"+key+"--------"+Warnings.get(key)+"</p>");
            i = i +1;
        }
        return alErr;        
    }
    public String [] getErrf(String [] alErr)
    {
        for(int i = 0; i< getLength(alErr); i++)
        {
            String [] parts = alErr(i).trim().split(" ");
            file[i] = parts[0];            
        }
        return file;
    }
    public String [] getErrc(String [] alErr)
    {
        for(int i = 0; i< getLength(alErr); i++)
        {
            String [] parts = alErr(i).trim().split(" ");
            cause[i] = parts[1];            
        }
        return cause;
    }
    public String [] getFE()
    {
        sizeEF = EFatales.size();
        Iterator it = EFatales.keySet().iterator();
        Integer i = 0;
        while(it.hasNext())
        {
            String key = (String)it.next();
            alEF[i] = key+" "+EFatales.get(key);
            //out.println("<p>"+key+"--------"+Warnings.get(key)+"</p>");
            i = i + 1;
        }
        return alEF;        
    }
    public String [] getFEf(String [] alFE)
    {
        for(int i = 0; i< getLength(alFE); i++)
        {
            String [] parts = alFE(i).trim().split(" ");
            filef[i] = parts[0];            
        }
        return filef;
    }
    public String [] getFEc(String [] alFE)
    {
        for(int i = 0; i< getLength(alFE); i++)
        {
            String [] parts = alFE(i).trim().split(" ");            
            causef[i] = parts[1];
        }
        return causef;
    }
}