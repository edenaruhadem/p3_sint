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
    
    public GC1Anios(){}

    public void setAnio(HashMap<String,Document> mapDocs)
    {
        anio.clear();
        for(String key:mapDocs.keySet())
        {
            anio.add(key);
        }
        Collections.sort(anio);        
    }        
    public ArrayList<String> getAnio()
    {
        return (anio);
    }    
}