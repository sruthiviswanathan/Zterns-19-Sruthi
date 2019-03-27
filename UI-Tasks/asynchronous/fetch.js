
//var ul = document.getElementById('list');

async function fetchfunction() {
    let result= await fetch("https://jsonplaceholder.typicode.com/posts");
    let body = await result.json();
    console.log(body);
    for(var i=0;i<body.length;i++){
        
        var ul = document.getElementById("list");
        var li = document.createElement("li");
        li.appendChild(document.createTextNode(body[i].id +" "+ body[i].title));
        ul.appendChild(li);
    }
}



fetchfunction();