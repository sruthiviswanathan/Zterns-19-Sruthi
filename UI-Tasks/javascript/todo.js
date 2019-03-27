
var items = localStorage.getItem('list')? JSON.parse(localStorage.getItem('list')) : [];

var n = localStorage.getItem('on_load_counter');
if (n === null) {
    n = 0;
} 
var ul = document.getElementById("list");
            var li = document.createElement("li");

console.log(n);


function listAllItems(items) {

    if(items === []){

        alert("empty");
    }
    else{
        var listt= JSON.parse(localStorage.getItem('list'));
        listt.forEach(function(value){
            var ul = document.getElementById("list");
            var li = document.createElement("li");
            li.appendChild(document.createTextNode(value.name));
            ul.appendChild(li);
            var span = document.createElement("SPAN");
            var strike = document.createElement("BUTTON");
            strike.appendChild(document.createTextNode("Strike"));  
            var del = document.createElement("BUTTON");
            del.appendChild(document.createTextNode("cancel"));
            ul.appendChild(strike);
            ul.appendChild(del);
        });
    }
       
}

function add (a,b) {
    const name = document.getElementById("b");
    items.push({
        'name' : name.value
        })
        localStorage.setItem('list', JSON.stringify(items));
        var ul = document.getElementById("list");
        var li = document.createElement("li");
        li.appendChild(document.createTextNode(name.value));
        ul.appendChild(li);
        var span = document.createElement("SPAN");
        var strike = document.createElement("BUTTON");
        strike.appendChild(document.createTextNode("Strike"));
        strike.setAttribute("type","strike");
        n++;
        strike.setAttribute("id",n);
        var del = document.createElement("BUTTON");
        del.appendChild(document.createTextNode("cancel"));
        del.setAttribute("type","cancel");
        del.setAttribute("id", n);
        del.addEventListener('click',deletefunction);
        ul.appendChild(strike);
        ul.appendChild(del);
        document.getElementById("b").value = "";
        localStorage.setItem("on_load_counter", n);
  }

  ul.addEventListener('click',function(event){
   
    
  });
  
  function storeItems(items){
    localStorage.setItem("isSet","yes");
    localStorage.setItem("list",JSON.stringify(items));
}

function deleteList(items,index){
    items.splice(index,1);
    storeItems(items);
}

 

