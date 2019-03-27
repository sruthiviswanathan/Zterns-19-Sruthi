var add=document.getElementById("note");
var notetext=document.getElementById("txtarea");
var list=document.getElementById("menu");
var data;
var prevdata;
var item_id=0;
var cur_id;
var userToken=sessionStorage.getItem('token');
console.log("User Token is "+userToken);
var notes={

}

add.addEventListener('click',function(){
    add_note();
    
});

list.addEventListener('click',function(){
    var element=event.target;
    if(element.getAttribute("class")=="fa fa-trash-o"){
        var parent=element.parentElement;
        var index=parent.getAttribute("id");
        list.removeChild(parent);
        if(index==cur_id){
            notetext.value="";
        }
        deleteItem(index);
    }
    else if(element.getAttribute("class")=="note-list"){
        console.log(element.getAttribute("class"));
        var index=element.getAttribute("id");
        notetext.value=notes[index]['data'];
        cur_id=index;

    }
    else{

    }

});

notetext.addEventListener('keyup',function(){
    if(cur_id!=null){
        data=notetext.value;
        write_note(cur_id,data);
        notes[cur_id]={
            "data":data
        }
        let item=document.getElementById(cur_id);
        item.children[1].innerHTML = data.slice(0,20);
        console.log(item.children[1]);
        console.log(notes);
    }

});

function createBlankList(my_id){
       list.innerHTML="<li id="+my_id+" class='note-list'><i class='fa fa-trash-o'></i><div class='title-holder'></div></li>"+list.innerHTML;
}

async function deleteItem(index){

    let url="http://192.168.100.162:3000/notes/"+index;
    let deleteNoteRequest=await fetch(url,{
        method:"delete",
        headers:{
            "Authorization":"Bearer "+sessionStorage.getItem('token'),
            "Content-Type":"application/json"
        },
        }
    ); 
    let result = await deleteNoteRequest.json();
    console.log(result);


    //delete notes[index];
    console.log(notes);
}

async function add_note(){
    let url="http://192.168.100.162:3000/notes";
    let newNoteRequest=await fetch(url,{
        method:"post",
        headers:{
            "Authorization":"Bearer "+sessionStorage.getItem('token'),
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            "description":"",
        })
        }
    ); 
    let result = await newNoteRequest.json();
    console.log(result);
    if(result['isSuccess']){
        var id=result['responseBody']['id'];
        console.log(id);
        notetext.value="";
            if(id!=null){
            createBlankList(id);
        cur_id=id;
        notes[cur_id]={
            "data":""
        }
    }
    
    }
    else{
        window.alert("Invalid credentials");
    }
}

async function write_note(id,description){
    let url="http://192.168.100.162:3000/notes/"+id;
    let updateNoteRequest=await fetch(url,{
        method:"put",
        headers:{
            "Authorization":"Bearer "+sessionStorage.getItem('token'),
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            "description":description,
        })
        }
    ); 
    let result = await updateNoteRequest.json();
    console.log(result);
    if(result['isSuccess']){
        var id=result['responseBody']['id'];
        console.log("Updated "+id);
        return id;
    }
}

async function fetchallnotes() {
    
    let url="http://192.168.100.162:3000/notes";
    let fetchallRequest = await fetch(url,{
        method:"get",
        headers: {
            "Authorization":"Bearer "+sessionStorage.getItem('token'),
            "Content-Type":"application/json"
        }
       
    })
    let result = await fetchallRequest.json();
    resultb=result['responseBody'];
     console.log(result);
    if(result['isSuccess']){
        for(let i=0;i<resultb.length;i++){
            console.log("adding notes to array");
            var id=resultb[i]['id'];
            var description = resultb[i]['description'];
            notes[id]={
                'data':description
            }
        }
        console.log(notes);
        addallnotes(notes);
    
    }else{
        console.log("error");
    }
}

function addallnotes(mynotes){
    console.log(mynotes);
    for(var note in mynotes){
        console.log(note,mynotes[note]['data']);
        list.innerHTML="<li id="+note+" class='note-list'><i class='fa fa-trash-o'></i><div class='title-holder'>"+mynotes[note]['data']+"</div></li>"+list.innerHTML;
    }

}

