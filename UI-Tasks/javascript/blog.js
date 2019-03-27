// var description = localStorage.getItem('desc')? JSON.parse(localStorage.getItem('desc')) : [];
// var titleArray = localStorage.getItem('title')? JSON.parse(localStorage.getItem('title')) : [];

var parent = document.getElementById('txt');
var ul = document.getElementById('menu');
var descValue = document.getElementById('txtarea');
var emptyspan;
var textarea;

var setTitle = function (el) {

  emptyspan.innerHTML = ellipsify(el.value);
}

function ellipsify(str) {
  if (str.length > 10) {
    return (str.substring(0, 10) + "...");
  }
  else {
    return str;
  }
}

//   titleArray.forEach(function(title){
//     constructli(title);
//   });


descValue.addEventListener('keyup',function(){

  var li = document.createElement('li');
  var span = document.createElement('span');
  span.innerHTML = title;
  li.appendChild(span);
  li.setAttribute("type", "show");
  ul.appendChild(li);
})

// var constructli = function (title) {
//   var li = document.createElement('li');
//   var span = document.createElement('span');
//   span.innerHTML = title;
//   li.appendChild(span);
//   li.setAttribute("type", "show");
//   ul.appendChild(li);
//   return span;
// }

var description;

function addNewElement() {
 
  
  
  //emptyspan = constructli("");
  // textarea = document.getElementById('txtarea');
  // textarea.value = "";
  // textarea.focus();
  // description = descValue.value;
    writenote("aaa");
  
  
  // writenote(description);
 
 

  
}

async function writenote(description) {

  let token = sessionStorage.getItem('token');
  let flag = sessionStorage.getItem('flag');
  console.log(description);  
  //console.log(token);   

   
    console.log(flag);  
    console.log(description); 
  let result= await fetch("http://192.168.100.162:3000/notes",{
  method:"post",
  headers:{
  "Authorization":'Bearer ' + token,
  "Content-Type":"application/json"
  },
  body:JSON.stringify({
  "description": "aaaaaa",
  })

  }); 
  let body = await result.json();
  console.log(body);
}



