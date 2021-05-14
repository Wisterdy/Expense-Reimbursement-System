'use strict';

let signinbutton = null;
let signupbutton = null;
let username = null;
let password = null; 
let loginuser = null;
window.addEventListener("load", setup);


function setup()
{
    console.log("Hello friend");
  
    signinbutton = document.getElementById("signin");
    signinbutton.addEventListener("click", ClicksignIn);
    signupbutton = document.getElementById("signup");
    signupbutton.addEventListener("click", ClicksignUP);
    
}



function ClicksignIn() {
    
    let logindto = null;
    username = document.getElementById("LoginUsername").value;
    password = document.getElementById("PasswordLogin").value;
    console.log(username + "password : " + password);
    logindto = { username: username, password: password };
     
    let status = -1;
    if (password.length > 4 && username.length>4)
    {
        fetch("http://localhost:7000/LoginUser",
            {
                method: "POST",
                body: JSON.stringify(logindto),
                headers: { "Content-type": "application/json; charset=UTF-8" },
                credentials: "include"
            }


        ).then(response => response.json() )
        .then(result => {
            loginuser = result
            if (loginuser != null)
            {
                if (loginuser.username != null && loginuser.username!=undefined) {
                    //console.log('response current user ' + status);
                    //console.log("current username  " + loginuser.username);
                    //console.log('current user ' + JSON.stringify(loginuser));
                   
                    window.location.href = "/Html/Home.html";
                }
            }
        }
        
    ).catch(err => console.log())
           
      
      
    }





  

}




function ClicksignUP() {
    window.location.href = "/Html/Signup.html";
}