function cargarXMLDoc(fichero)
{
if (window.ActiveXObject)
  {
  xhttp = new ActiveXObject("Msxml2.XMLHTTP");
  }
else
  {
  xhttp = new XMLHttpRequest();
  }
xhttp.open("GET", fichero, false);
try {xhttp.responseType = "msxml-document"} catch(err) {} // Helping IE11
xhttp.send("");
return xhttp.responseXML;
}

btn.addEventListener("click", function(){

    document.getElementById("transformacion").innerHTML = "aaaaaaaaaaa";




});
/*function tImlXSLT()
{
    
    xml = cargaXMLDoc("http://gssi.det.uvigo.es/users/agil/public_html/SINT/18-19/iml2001.xml"); //fichero
    xslt = "mml-html.xslt"; //plantilla
    // code for IE
    if (window.ActiveXObject || xhttp.responseType == "msxml-document")
    {
        ex = xml.transformNode(xslt);
        document.getElementById("tranformacion").innerHTML = ex;
    }
    // code for Chrome, Firefox, Opera, etc.
    else if (document.implementation && document.implementation.createDocument)
    {
        xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xslt);
        resultDocument = xsltProcessor.transformToFragment(xml, document);
        //document.getElementById("transformacion").appendChild(resultDocument);
        
    }
}*/