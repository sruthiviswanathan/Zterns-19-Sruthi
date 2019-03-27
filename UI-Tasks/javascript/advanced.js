

// //prototype
// var arr = new Array(1,2,3);
// Array.prototype.iterate= function(fn) {

//     for(var i=0;i<this.length;i++){
//         fn(this[i]);

//     }
// };
// arr.iterate(function(element){

//     console.log(element);
// });



// //bind call and apply
// function sample(a,b,c){
//     console.log(a+b+c);
// }
// sample.apply(null,[1,2,3]);



//rest and spread?
var sample = (...argument) =>{
console.log(argument);
}
sample(...[5,6,7,8,9]);