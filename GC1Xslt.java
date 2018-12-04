package Xbean;

import java.util.*;
import org.w3c.dom.Document;
import java.util.Map.Entry;
public class GC1Xslt
{   
    private Document xslt;    
    
    public GC1Xslt(){}

    public void setXslt(String fxslt, HashMap<String,Document> mapDocs)
    {
        Entry<String, Document> xsltEntry = null;
        for(Entry<String, Document> entry : mapDocs.entrySet()) 
	    {
            String key = entry.getKey();
            if(key.equals(fxslt)) 
	        {
                xsltEntry = entry;
            }
        }
        xslt = xsltEntry.getValue();        
    }             
    public Document getXslt()
    {
        return (xslt);
    }       
}