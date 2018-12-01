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
import com.google.gson.*;
import java.io.IOException;
import java.io.FileWriter;
import clases.Cancion;

public class GC1Resultadojson
{   
    private Gson resultadojson = new Gson(); 
    //private ArrayList<Cancion> resultadojson = new ArrayList<Cancion>();    
    private String an;
    private String id;
    private String ic;  
    
    public GC1Resultadojson(){}

    public void setResultadojson(HashMap<String,Document> mapDocs, String anio, String idd, String idc)
    {
        //resultadojson.clear();
        ArrayList<Cancion> resultado = new ArrayList<Cancion>();        
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
                resultado.add(obj);                
            }
        }
        
        ArrayList<String> titulos = new ArrayList<String>();            
        for(int i=0;i<resultado.size();i++)
        {
            Cancion objRes = resultado.get(i);
            titulos.add(objRes.getTitulo(objRes)); 
        }
        Comparator<String> comparador = Collections.reverseOrder();
        Collections.sort(titulos, comparador);
        for(int j = 0;j<titulos.size();j++)
        {   
            String titlist = titulos.get(j);
            for(int k = 0;k<resultado.size();k++)
            {
                Cancion obj = resultado.get(k);
                String titu = obj.getTitulo(obj);
                if(titlist.equals(titu))
                {
                    resultado.remove(obj);
                    resultado.add(obj);
                }
            }           
        }
        try (FileWriter writer = new FileWriter("C:\\tomcat9\\apache-tomcat-9.0.12\\webapps\\sint481\\data.json")) {

            resultadojson.toJson(resultado, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //resultadojson = resultadojson.toJson(resultado);
        /*for(int i = 0;i<resultado.size();i++)
        {
            String index = Integer.toString(i+1);
            hresultado.put(index,resultado.get(i));
        }*/                          
    }

    public void setAn(String anio)
    {
        an = anio;
    }
    public void setId(String idd)
    {
        id = idd;
    }
    public void setIc(String idc)
    {
        ic = idc;
    }

    public Gson getResultadojson()
    {
        return(resultadojson);
    }

    public String getAn()
    {
        return (an);
    }
    public String getId()
    {
        return (id);
    }
    public String getIc()
    {
        return (ic);
    }    
}