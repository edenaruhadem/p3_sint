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
import clases.Disco;

public class GC1Discos
{   
    //private ArrayList<Disco>disco = new ArrayList<Disco>();
    private HashMap<String,Disco>hdisco = new HashMap<String,Disco>();
    //private ArrayList<String>ls = new ArrayList<String>();
    private String an;  
    
    public GC1Discos(){}

    public void setHdisco(HashMap<String,Document> mapDocs, String anio)
    {
        Document res = null;
        String atributoUno = null;
        String atributoDos = null;
        String atributoTres = null;
        String atributoCuatro = null;
        String idiomaPais = null;
        Integer resComp = 0;
        Integer resCompMem = 0;
        ArrayList<Disco>disco = new ArrayList<Disco>();
        ArrayList<String> interpretes = new ArrayList<String>();
        hdisco.clear();                
        for (String key:mapDocs.keySet())
        {
            if(anio.equals(key))
            {
                res = mapDocs.get(key);
            }
        }
        Element raiz = res.getDocumentElement(); //Obtencion del elemento Songs
        NodeList pais = raiz.getElementsByTagName("Pais");
        NodeList discos = raiz.getElementsByTagName("Disco");
        for(int i = 0; i<pais.getLength();i++)
        {
            Node itemPais = pais.item(i);       
            idiomaPais = itemPais.getAttributes().getNamedItem("lang").getTextContent();                      
            for(int j = 0;j<discos.getLength();j++) //El acceso al texto de IML produce redirecciones a nuevos documentos
            {            
                Node itemDisco = discos.item(j);
                Node parentDisco = itemDisco.getParentNode();
                if(parentDisco.equals(itemPais))
                {
                    NamedNodeMap atributosDisco = itemDisco.getAttributes();
                    if(atributosDisco.getLength()>1)
                    {
                        atributoTres=atributosDisco.getNamedItem("idd").getTextContent();
                        atributoCuatro=atributosDisco.getNamedItem("langs").getTextContent();
                    }
                    else
                    {
                        atributoTres=atributosDisco.getNamedItem("idd").getTextContent();
                        atributoCuatro=idiomaPais;
                    }                                                         
                    NodeList itemDiscoChild = itemDisco.getChildNodes();
                    for(int k = 0;k<itemDiscoChild.getLength(); k++)
                    {
                        if(itemDiscoChild.item(k).getNodeName().equals("Titulo"))
                        {
                            atributoUno = itemDiscoChild.item(k).getTextContent();
                        }
                        if(itemDiscoChild.item(k).getNodeName().equals("Interprete"))
                        {
                            atributoDos = itemDiscoChild.item(k).getTextContent();
                        }                
                    }
                    Disco obj = new Disco(atributoUno,atributoDos,atributoTres, atributoCuatro);
                    disco.add(obj);                                          
                }                                 
            }              
        }
        for(int i=0;i<disco.size();i++)
        {
            Disco objDisco = disco.get(i);
            interpretes.add(objDisco.getInterprete(objDisco)); 
        }
        Collections.sort(interpretes);        
        for(int j = 0;j<interpretes.size();j++)
        {
            String interlist = interpretes.get(j);
            for(int k = 0;k<disco.size();k++)
            {
                Disco obj = disco.get(k);
                String inter = obj.getInterprete(obj);
                if(inter.equals(interlist))
                {
                    disco.remove(obj);
                    disco.add(obj);
                }
            }           
        }
        for(int i = 0; i< disco.size();i++)
        {
            String index = Integer.toString(i+1);
            hdisco.put(index,disco.get(i));
        }      
    }
    public void setAn(String anio)
    {
        an = anio;
    }

    public HashMap<String,Disco> getHdisco()
    {
        return (hdisco);
    }

    public String getAn()
    {
        return (an);
    }    
}