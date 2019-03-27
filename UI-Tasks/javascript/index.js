

var names=["abc","bcd","efg"];

findIndex(names,"efgh",function(index){
    console.log(index);
});

function findIndex(names,key,fn){
var flag=0;
    for(var index=0;index<names.length;index++){
        if(key == names[index]){
            flag=1;
            fn(index);
            break;
        }
     
    }
    if(flag==0){
        fn(-1);
    }
}

var arr1=[40, 100, 1, 5, 25, 10];
sortfunction(arr1,function(a,b){
   return b-a;
});

function sortfunction(arr1,fn){
    for (var i = 0; i < arr1.length-1; i++)       
     {
    for (var j = i+1; j < arr1.length; j++)  
        {
        var n  = fn(arr1[i],arr1[j]);
        if(n>0){
        var temp = arr1[i];
        arr1[i]=arr1[j];
        arr1[j]=temp;
        }
 
    }
   
    }
 console.log(arr1);
}