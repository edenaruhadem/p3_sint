var xslStylesheet;
var xsltProcessor = new XSLTProcessor();
var myDOM;
var xmlDoc;
var fichero;
var count = 0;


function Init(fichero)
{  
  if(count == 0)
  {
    var myXMLHTTPRequestxslt = new XMLHttpRequest();
    myXMLHTTPRequestxslt.open("GET", "p3/mml-html.xslt", false);
    myXMLHTTPRequestxslt.send(null);
    xslStylesheet = myXMLHTTPRequestxslt.responseXML;
    xsltProcessor.importStylesheet(xslStylesheet);
    //carga fichero iml
  
    var myXMLHTTPRequestxml = new XMLHttpRequest();
    //var url = 'http://gssi.det.uvigo.es/users/agil/public_html/SINT/18-19/iml2001.xml';
    //var urlc = url.concat(fichero);
    myXMLHTTPRequestxml.open("GET", fichero, false);
    /*if (!myXMLHTTPRequestxml) {
      alert('CORS not supported');
      return;    
    }
    else{
      alert('CORS supported');
    }*/
    myXMLHTTPRequestxml.send(null);
    xmlDoc = myXMLHTTPRequestxml.responseXML;
    /*if(xmlDoc==null)
    {
      alert('document in null');
      return;
    }*/  
    var fragment = xsltProcessor.transformToFragment(xmlDoc, document);    
    myDOM = fragment;
    document.getElementById("tabla").appendChild(fragment);
  }
  else 
  {
    //carga fichero iml
  
    var myXMLHTTPRequestxml = new XMLHttpRequest();
    //var url = 'http://gssi.det.uvigo.es/users/agil/public_html/SINT/18-19/iml2001.xml';
    //var urlc = url.concat(fichero);
    myXMLHTTPRequestxml.open("GET", fichero, false); //ficheroo. Tenia false
    /*if (!myXMLHTTPRequestxml) {
      alert('CORS not supported');
      return;    
    }
    else{
      alert('CORS supported');
    }*/
    myXMLHTTPRequestxml.send(null); //Tenia null
    xmlDoc = myXMLHTTPRequestxml.responseXML;
    /*if(xmlDoc==null)
    {
      alert('document in null');
      return;
    }*/  
    var fragment = xsltProcessor.transformToFragment(xmlDoc, document);    
    myDOM = fragment;
    document.getElementById("tabla").appendChild(fragment);
  }  
  count = count + 1; 
}

function Delete()
{
  document.getElementById("tabla").innerHTML = "";
}




  

