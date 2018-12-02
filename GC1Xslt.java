package Xbean;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class GC1Xslt
{   
    private ArrayList<String> xslt = new ArrayList<String>();    
    
    public GC1Xslt(){}

    public void setXslt(ArrayList<String> urlDocs)
    {
        Integer indexstr = 7;
        xslt.clear();
        urlDocs.remove(urlDocs.size()-1);        
        xslt = urlDocs;
    }             
    public ArrayList<String> getXslt()
    {
        return (xslt);
    }       
}