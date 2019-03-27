

function viewlogin() {
    var x = document.getElementById("signup");
    var y = document.getElementById("login");
    //console.log(x);
    x.style.display = "none";
    y.style.display = "block";
    y.classList.add('button');
  }
  
  function viewsignup() {
    var y = document.getElementById("signup");
    var x = document.getElementById("login");
    y.style.display = "block";
    x.style.display = "none";

  }

  async function fetchlogin(){
    var uname = document.getElementById('uname').value;
    var pwd = document.getElementById('pwd').value;
    let result= await fetch("http://192.168.100.162:3000/auth/login",{
      method: "post",
      headers: {
        "Content-Type" : "application/json"
      },
      body:JSON.stringify({
        "username": uname,
        "password":pwd
      })

    });

    let body = await result.json();
    console.log(body);
    if(body['isSuccess']){
      sessionStorage.setItem('flag',body['isSuccess']);
      sessionStorage.setItem('token',body['responseBody']['token']);
      window.location="blog.html"
    }
    else{
      alert(body['responseBody']['errorMessage']);
    }

  }
  async function fetchregister() {

    var uname = document.getElementById('uname').value;
    var pwd = document.getElementById('pwd').value;
    let result= await fetch("http://192.168.100.162:3000/auth/register",{
      method: "post",
      headers: {
        "Content-Type" : "application/json"
      },
      body:JSON.stringify({
        "username": uname,
        "password":pwd
      })

    });

    let json = await result.json();
    console.log(json);
    // sessionStorage.setItem('flag',body['isSuccess']);
    sessionStorage.setItem('token',json['responseBody']['token']);
  }

