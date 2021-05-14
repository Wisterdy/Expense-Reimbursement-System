'use strict';
let submitUserButton = null;
let form = null;
let firstname = null;
let lastname = null;
let email = null;
let username = null;
let password = null;
let role = null;


window.addEventListener("load", onloadsetup);

function onloadsetup()
{
    console.log("setting up registration");
    submitUserButton = document.getElementById("signupconfirm");
    submitUserButton.addEventListener("click", CreateNewUser);
    form = document.getElementsByClassName("loginsection");
    firstname = document.getElementById("Firstname");
    lastname = document.getElementById("LastName");
    email = document.getElementById("email");
    username = document.getElementById("SignupUsername");
    password = document.getElementById("SignupPassword")
    role = document.getElementById("role");
}


function CreateNewUser() {
    console.log("Creating new user");
  
    if (firstname.checkValidity() == true && lastname.checkValidity() == true && email.checkValidity() == true && username.checkValidity() == true && password.checkValidity() == true) {
        
        let url = "";

        if (role.value == "employee") {
            console.log("calling empoyee");
            url = "http://localhost:7000/CreateUser";
        }
        
        if (role.value == "finance") {
            console.log("calling finance");
            url = "http://localhost:7000/CreateUserFinance";
        }

        let name = firstname.value;
        let lastnam = lastname.value;
        let uname = username.value;
        let emai = email.value;
        let pas = password.value;



        let user = {
            username: uname,
            password: pas,
            firstname: name,
            lastname: lastnam,
            email: emai
        };
        console.log(JSON.stringify(user));
    
      
        
        
        fetch(url,
            {
                method: "POST",
                body: JSON.stringify(user),
                headers: { "Content-type": "application/json; charset=UTF-8" },
                credentials: "include"
            }


        ).then(response => response.json() )
            .then(result => {
                console.log("wait in path directory");
                window.location.href = "/index.html";
            })
            .catch(err => console.log())
        
    }
       
}