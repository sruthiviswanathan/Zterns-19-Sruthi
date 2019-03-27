var items =[{id:1,name:"aaa"},{id:2,name:"bbb"},{id:3,name:"ccc"},{id:4,name:"ddd"},{id:5,name:"eee"}];

var random = Math.floor((Math.random() * 10) + 1); 
console.log(random);
function checkid(random){

    return new Promise((resolve,reject) => {
        setTimeout(() => {
            for(var i=0; i< items.length; i++){
                if(random === items[i].id){
                    resolve(random);
                }
            }
            reject("id not exists");
        },1000);
    });
}

function modifydesc(random) {

    return new Promise((resolve,reject) =>{
        setTimeout(() => {
             items[random - 1].name+= "abcdef";
             resolve(items[random - 1].name);
        },1000);
    });
}

// checkid(random).then(random => {
//     console.log(random);
//     return modifydesc(random);
// }).then(flag => {
//     console.log(flag);
// }).catch(err => {
//     console.error(err);
// });

async function modify() {
    
    try {
    let id = await checkid(random);
    console.log(id);
    let name = await modifydesc(id);
    console.log(name);
}catch(error) {
    console.log(error);
}
}
modify();