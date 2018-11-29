var infoContainer = document.getElementById("info");
var btn = document.getElementById("btn");
btn.addEventListener("click", function(){
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
        htmlString += "<p>"+ data[i].name +" is a "+ data[i].species + " and the food is " + data[i].food+".</p>";
    }
    infoContainer.insertAdjacentHTML('beforeend',htmlString);
}

