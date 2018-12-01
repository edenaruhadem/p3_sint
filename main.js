var infoContainer = document.getElementById("resultado");
var btna = document.getElementById("btna");
var btnd = document.getElementById("btnd");

btna.addEventListener("click", function(){
    //var ourData = "ascendente";
    //renderHTML(ourData);
    var ourRequest = new XMLHttpRequest();
    ourRequest.open('GET','data.json'); //Para recibir. POST para enviar
    ourRequest.onload = function(){
        renderHTML(ourData);

        if(ourRequest.status >= 200 && ourRequest.status < 400){
            var ourData = JSON.parse(ourRequest.responseText);
            renderHTML(ourData);
        }else{
            console.log("We connected to the server but it performed an error")
        }    
    };
    ourRequest.onerror = function(){
        console.log("Connection Error"); //por ejemplo
    }
    ourRequest.send();
});
btnd.addEventListener("click", function(){
    //var ourData = "descendente";
    //renderHTML(ourData);
    var ourRequest = new XMLHttpRequest();
    ourRequest.open('GET','data.json'); //Para recibir. POST para enviar
    ourRequest.onload = function(){

        if(ourRequest.status >= 200 && ourRequest.status < 400){
            var ourData = JSON.parse(ourRequest.responseText);
            renderHTML(ourData);
        }else{
            console.log("We connected to the server but it performed an error")
        }    
    };
    ourRequest.onerror = function(){
        console.log("Connection Error"); //por ejemplo
    }
    ourRequest.send();
});

function renderHTML(data){
    var htmlString="";
    for(i = 0;i<data.length;i++)
    {        
        htmlString += "<p>"+ data[i].titulo +" --- "+ data[i].desc + " --- " + data[i].premios+".</p>";                
    }
    infoContainer.insertAdjacentHTML('beforeend',htmlString);
    //infoContainer.insertAdjacentHTML('beforeend',data);
}

