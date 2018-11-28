package Xbean;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
//import javax.servlet.annotation.*;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.LinkedList;
import java.util.Iterator;
import clases.Cancion;

public class GC1Canciones
{   
    private ArrayList<Cancion>cancion = new ArrayList<Cancion>();    
    private String an;
    private String id;  
    
    public GC1Canciones(){}

    public void setCancion(HashMap<String,Document> mapDocs, String anio, String idd)
    {
        cancion.clear();
        Document res = null;
        String atributoUno = null;
        String atributoDos = null;
        String atributoTres = null;
        String atributoCuatro = null;
        String atributoCinco = null;
        String atributoSeis = "";    
        for (String key:mapDocs.keySet())
        {
            if(anio.equals(key))
            {
                res = mapDocs.get(key);
            }
        }
        Element raiz = res.getDocumentElement(); //Obtencion del elemento Songs
        NodeList canciones = raiz.getElementsByTagName("Cancion");
        for(int i = 0;i<canciones.getLength();i++) //El acceso al texto de IML produce redirecciones a nuevos documentos
        {
            Node itemcancion = canciones.item(i);
            String stridc=itemcancion.getAttributes().getNamedItem("idc").getTextContent();
            atributoCuatro = stridc;
            String substridd = idd.substring(0,12);
            String substridc = stridc.substring(0,12);
            if (substridc.equals(substridd))
            {
                NodeList itemCancionChild = itemcancion.getChildNodes();
                for(int j = 0;j<itemCancionChild.getLength(); j++)
                {
                    if(itemCancionChild.item(j).getNodeName().equals("Titulo"))
                    {
                        atributoUno = itemCancionChild.item(j).getTextContent();
                    }
                    if(itemCancionChild.item(j).getNodeName().equals("Genero"))
                    {
                        atributoDos = itemCancionChild.item(j).getTextContent();
                    }
                    if(itemCancionChild.item(j).getNodeName().equals("Duracion"))
                    {
                        atributoTres = itemCancionChild.item(j).getTextContent();
                    }                
                }
                cancion.add(new Cancion(atributoUno,atributoDos,atributoTres, atributoCuatro, atributoCinco, atributoSeis));
            }
        }

        ArrayList<Integer> dur = new ArrayList<Integer>();        
        for(int i=0;i<cancion.size();i++)
        {
            Cancion objCancion = cancion.get(i);
            dur.add(Integer.parseInt(objCancion.getDuracion(objCancion))); 
        }
        Collections.sort(dur);
        for(int j = 0;j<dur.size();j++)
        {
            Integer durlist = dur.get(j);
            for(int k = 0;k<cancion.size();k++)
            {
                Cancion obj = cancion.get(k);
                String dura = obj.getDuracion(obj);
                if(Integer.toString(durlist).equals(dura))
                {
                    cancion.remove(obj);
                    cancion.add(obj);
                }
            }           
        }            
    }

    public void setAn(String anio)
    {
        an = anio;
    }
    public void setId(String idd)
    {
        id = idd;
    }

    public ArrayList<Cancion> getCancion()
    {
        return(cancion);
    }

    public String getAn()
    {
        return (an);
    }
    public String getId()
    {
        return (id);
    }    
}