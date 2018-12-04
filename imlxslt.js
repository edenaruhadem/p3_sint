var xslStylesheet;
var xsltProcessor = new XSLTProcessor();
var myDOM;
var xmlDoc;
var fichero;

function Init(fichero){ 
  var myXMLHTTPRequest = new XMLHttpRequest();
  myXMLHTTPRequest.open("GET", "mml-html.xslt", false);
  myXMLHTTPRequest.send(null);

  xslStylesheet = myXMLHTTPRequest.responseXML;
  xsltProcessor.importStylesheet(xslStylesheet);


  // load the xml file, example1.xml
  myXMLHTTPRequest = new XMLHttpRequest();
  myXMLHTTPRequest.open("GET", fichero, false);
  myXMLHTTPRequest.send(null);

  xmlDoc = myXMLHTTPRequest.responseXML;

  var fragment = xsltProcessor.transformToFragment(xmlDoc, document);

  document.getElementById("tabla").innerHTML = "";

  myDOM = fragment;
  document.getElementById("tabla").appendChild(fragment);
}




  

