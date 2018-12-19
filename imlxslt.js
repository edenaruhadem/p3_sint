var xslStylesheet;
var xsltProcessor = new XSLTProcessor();
var myDOM;
var xmlDoc;
var fichero;

/*function Load(){

  var myXMLHTTPRequestxslt = new XMLHttpRequest();
  myXMLHTTPRequestxslt.open("GET", "mml-html.xslt", false);
  myXMLHTTPRequestxslt.send(null);
  xslStylesheet = myXMLHTTPRequestxslt.responseXML;
  xsltProcessor.importStylesheet(xslStylesheet);

}*/


function Init(fichero){ 
  var myXMLHTTPRequestxslt = new XMLHttpRequest();
  myXMLHTTPRequestxslt.open("GET", "p3/mml-html.xslt", false);
  myXMLHTTPRequestxslt.send(null);

  xslStylesheet = myXMLHTTPRequestxslt.responseXML;
  xsltProcessor.importStylesheet(xslStylesheet);


  // load the xml file, example1.xml
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
  document.getElementById("tabla").innerHTML = "";
  myDOM = fragment;
  document.getElementById("tabla").appendChild(fragment);
}




  

