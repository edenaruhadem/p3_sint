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
    
    private ArrayList<String>anio = new ArrayList<String>();
    //private String an;
    
    public GC1Anios(){}

    public void setAnio(HashMap<String,Document> mapDocs /*String a*/)
    {
        anio.clear();
        for(String key:mapDocs.keySet())
        {
            anio.add(key);
        }
        Collections.sort(anio);
        //anio = a;
    }
    public ArrayList<String> getAnio() //ArrayList<String>
    {
        //an = anio.get(2);

        return (anio);
    }
}