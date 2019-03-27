

var listArray = localStorage.getItem('lists')? JSON.parse(localStorage.getItem('lists')) : [];
var contentArray = localStorage.getItem('contents')? JSON.parse(localStorage.getItem('contents')) : [];
var mydiv = document.getElementById("menu");
var displaydiv = document.getElementById("display");

listArray.forEach(function(value){
    constructList(value);
  } );

function constructList(text)
{
        var li = document.createElement('li');
        li.setAttribute("type","tit");
        li.innerHTML = text;
        mydiv.appendChild(li);
}

function addNewElement() {
  
    var inputValue = document.getElementById("title").value;
    var contentValue = document.getElementById("content").value;

    if (inputValue === '' || contentValue === '') {
        alert("You must write something!");
      } else {

        var li = document.createElement('li');
        li.setAttribute("type","tit");

        li.innerHTML = inputValue;
        mydiv.appendChild(li);

        listArray.push(inputValue);
       
        contentArray.push(contentValue);
        localStorage.setItem('lists', JSON.stringify(listArray));
        localStorage.setItem('contents', JSON.stringify(contentArray));
        document.getElementById("title").value = "";
        document.getElementById("content").value = "";
      }
}

mydiv.addEventListener('click',function(event){

  var elmt =event.target;
  var a = listArray.indexOf(elmt.innerHTML);
  console.log(a);

if(displaydiv.innerHTML == " "){
  
  //var li = document.createElement('li');
  //li.setAttribute("type","cont");
  //li.innerHTML = contentArray[a];
  //displaydiv.appendChild(li);
  displaydiv.innerHTML = contentArray[a]; 
  //displaydiv.value(contentArray[a]);

}
else{
  displaydiv.innerHTML = "";
  // var li = document.createElement('li');
  // li.setAttribute("type","cont");
  // li.innerHTML = contentArray[a];
  // displaydiv.appendChild(li);
  displaydiv.innerHTML = contentArray[a]; 
//  displaydiv.value(contentArray[a]);

}

})

