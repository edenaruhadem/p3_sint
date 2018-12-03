var xslStylesheet;
var xsltProcessor = new XSLTProcessor();
var myDOM;

var xmlDoc;

function Init(){
  //document.getElementById("tabla").innerHTML = "a";
  // load the xslt file, example1.xsl
  var myXMLHTTPRequest = new XMLHttpRequest();
  myXMLHTTPRequest.open("GET", "mml-html.xsl", false);
  myXMLHTTPRequest.send(null);

  xslStylesheet = myXMLHTTPRequest.responseXML;
  xsltProcessor.importStylesheet(xslStylesheet);


  // load the xml file, example1.xml
  myXMLHTTPRequest = new XMLHttpRequest();
  myXMLHTTPRequest.open("GET", "iml2001.xml", false);
  myXMLHTTPRequest.send(null);

  xmlDoc = myXMLHTTPRequest.responseXML;

  var fragment = xsltProcessor.transformToFragment(xmlDoc, document);

  document.getElementById("tabla").innerHTML = "";

  myDOM = fragment;
  document.getElementById("tabla").appendChild(fragment);
}