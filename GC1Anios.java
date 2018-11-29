package Xbean;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
//import javax.servlet.annotation.*;
import java.util.Map;
import org.w3c.dom.Document;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.LinkedList;
import java.util.Iterator;

public class GC1Anios
{   
    private HashMap<String,String>hanio = new HashMap<String,String>();
    //private String tam;  
    
    public GC1Anios(){}

    public void setHanio(HashMap<String,Document> mapDocs)
    {
        hanio.clear();
        ArrayList<String>anio = new ArrayList<String>();        
        for(String key:mapDocs.keySet())
        {
            anio.add(key);
        }
        Collections.sort(anio);
        for (int i = 0; i<anio.size();i++)
        {
            String index = Integer.toString(i+1);
            hanio.put(index, anio.get(i));
        }
    }
    /*public void setTam()
    {
        String tama = Integer.toString(anio.size());
        tam = tama;              
    }*/         
    public HashMap<String,String> getHanio()
    {
        return (hanio);
    }
    /*public String getTam()
    {        
        return (tam);
    }*/    
}