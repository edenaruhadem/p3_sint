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
  myXMLHTTPRequestxslt.open("GET", "mml-html.xslt", false);
  myXMLHTTPRequestxslt.send(null);

  xslStylesheet = myXMLHTTPRequestxslt.responseXML;
  xsltProcessor.importStylesheet(xslStylesheet);


  // load the xml file, example1.xml
  myXMLHTTPRequestxml = new XMLHttpRequest();
  var url = 'http://gssi.det.uvigo.es/users/agil/public_html/SINT/18-19/';
  var urlc = url.concat(fichero);
  myXMLHTTPRequestxml.open("GET", urlc, true); //ficheroo. Tenia false
  myXMLHTTPRequestxml.send(); //Tenia null
  xmlDoc = myXMLHTTPRequestxml.responseXML;
  var fragment = xsltProcessor.transformToFragment(xmlDoc, document);
  document.getElementById("tabla").innerHTML = "";
  myDOM = fragment;
  document.getElementById("tabla").appendChild(fragment);
}




  

