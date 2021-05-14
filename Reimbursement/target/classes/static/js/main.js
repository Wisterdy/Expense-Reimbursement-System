'use strict';
let loginuser = null;
let submitUserButton = null;
let allimagebutton = null;
let table = null;
let selectionfilter = null;
let imagecontainer = null; 
let closebutton = null;
let tableview = true;
let allreimb = [];
let receiptimage = null; 


window.addEventListener("load", loadhomepage);


function loadhomepage()
{
    //let re = { firstname: " wiz", lastname: "alcida", type: "food", amount: " 2092", date: " 12/35/9309", status: "pending", resolver: "terry", image: " https://homepages.cae.wisc.edu/~ece533/images/airplane.png" };
    //let re1= { firstname: " wiz", lastname: "alcida", type: "food", amount: " 2092", date: " 12/35/9309", status: "pending", resolver: "terry", image: " https://homepages.cae.wisc.edu/~ece533/images/boat.png" };
    //let re2 = { firstname: " wiz", lastname: "alcida", type: "food", amount: " 2092", date: " 12/35/9309", status: "pending", resolver: "terry", image: "https://homepages.cae.wisc.edu/~ece533/images/watch.png" };
    //let re3= { firstname: " wiz", lastname: "alcida", type: "food",  amount : " 2092" ,date: " 12/35/9309", status: "pending", resolver: "terry", image: null};
    //allreimb.push(re);
    //allreimb.push(re1);
    //allreimb.push(re2);
    //allreimb.push(re3);
    
    receiptimage = document.getElementById("recieptimage");
    imagecontainer = document.getElementById("imageholder");
    closebutton = document.getElementById("closeview");
    closebutton.addEventListener("click", Closereceipt);
    selectionfilter = document.getElementById("Selectionbar");
    table = document.getElementById("reimb_table");
    allimagebutton = document.querySelectorAll("td button");
  
    
    Getuser();
   
    
   /* for (let i = 0; i < allimagebutton.length; i++)
    {
        allimagebutton[i].addEventListener("click" ,GetReimb)
    }*/

    
}

function CreateNewUser()
{

    console.log("creating new user yeah");
    
}


function Getuser() {
    
    fetch("http://localhost:7000/LoginUser",
    {
        method: "GET",
        //body: JSON.stringify(logindto),
        headers: { "Content-type": "application/json; charset=UTF-8" },
        credentials: "include"
    }


).then(response => response.json() )
.then(result => {
    
    if (result != null)
    {
        
        if (result.user_name != null || result.user_name!=undefined)
        {
            loginuser = result;
            
            if(loginuser.role.userRoleID=="1")
              getTheReimb(loginuser.user_id);
            if (loginuser.role.userRoleID == "2")
                getTheReimbFinance();
        }

        if (loginuser != null)
        {
            userinfosetup(result);
        }
    }
   // console.log("inside home" + JSON.stringify(result));
}

    ).catch(err => console.log(err))
    

    

}



function userinfosetup(data) {
    
  // let Storage = window.sessionStorage;
    sessionStorage.setItem('user', 'ehwoeo');
   // let test = document.getElementById("user");
   // test.innerText = JSON.stringify(loginuser);

}


function GetReimb(event) {
    
    console.log("getting image");
    
    let id = this.getAttribute('id');
    console.log("button id is " + id);
    receiptimage.src = "data:image/png;base64,"  + allreimb[id].image || "data:image/jpg;base64,"  + allreimb[id].image;
   table.style.display = "none"
   selectionfilter.style.display = "none";
    imagecontainer.style.display = "flex";
    

}

function Closereceipt( )
{

    
        imagecontainer.style.display = "none";
        table.style.display = "table";
        selectionfilter.style.display = "flex";
       
    
    
}


function createtable() {
    let headingarray = null;
    
    if (loginuser.role.userRoleID == "1") {
       headingarray = ["Author",
            "Type",
            "Amount",
            "Date Submit",
            "status",
            "Resolver",
            "Date Resolve",
            "Receipt"
        ]
    }

    if (loginuser.role.userRoleID == "2") {
         headingarray = ["Author",
            "Type",
            "Amount",
            "Date Submit",
            "status",
            "Resolver",
            "Date Resolve",
            "Receipt",
            "Deny",
            "Approve",
        ]
    }
    let headingarraytd;
    
    let trheading = document.createElement("tr");
     
    for (let i = 0; i < headingarray.length; i++)
    {
        let th = document.createElement("th");
        th.innerText = headingarray[i];
        trheading.appendChild(th);
       
    }
    table.appendChild(trheading);

    for (let i = 0; i < allreimb.length; i++)
    {
        let tr = document.createElement("tr");
        for (let j = 0; j < headingarray.length+1; j++)
        {
            
          
            if (j ==0)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].firstname +" "+ allreimb[i].lastname;
                tr.appendChild(td);
            }
            if (j == 2)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].type;
                tr.appendChild(td);
            }
            if (j == 3)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].amount;
                tr.appendChild(td);
            }

            if (j == 4)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].date;
                tr.appendChild(td);
            }

            if (j == 5)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].status;
                tr.appendChild(td);
            }
            if (j == 6)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].resolver;
                tr.appendChild(td);
            }
            if (j == 7)
            {
                let td = document.createElement("td");
                td.innerText = allreimb[i].dateresolve;
                tr.appendChild(td);
            }

            if (j == 8)
            {
                let td = document.createElement("td");
                let buttonclickable = document.createElement("button");
                buttonclickable.innerText= "View";
                buttonclickable.id = i;
                buttonclickable.addEventListener("click", GetReimb);
               if (allreimb[i].image===null)
                {
                   buttonclickable.disabled = true; 
                }
                td.appendChild(buttonclickable);
                tr.appendChild(td);

                if (loginuser.role.userRoleID == "2") {
                    let td = document.createElement("td");
                    let buttondeny = document.createElement("button");
                    buttondeny.innerText = "Deny";
                    var currentindex = i;
                    buttondeny.id =  currentindex.toString() + "deny";
                    buttondeny.addEventListener("click", Deny);
                    
                    if (allreimb[i].dateresolve.length > 1)
                    {
                        buttondeny.disabled = true;
                    }
                    
                   
                    td.appendChild( buttondeny);
                    tr.appendChild(td);
                   

                }

                if (loginuser.role.userRoleID == "2") {
                    let td = document.createElement("td");
                    let buttonapprove = document.createElement("button");
                    buttonapprove.innerText= "Approve";
                    buttonapprove.id = currentindex.toString()+ "approve";
                    buttonapprove.addEventListener("click", Approve);
                   
                    if (allreimb[i].dateresolve.length > 1)
                    {
                        buttonapprove.disabled = true;
                        
                    }
                    
                   
                    td.appendChild( buttonapprove);
                    tr.appendChild(td);
                   

                }
            }
        }
        table.appendChild(tr);
    }
    
   
   

}






function getTheReimb(id) {
    

    fetch("http://localhost:7000/GetReimb" +"/" + id + " ",
        {
            method: "GET",
            headers: { "Content-type":   "multipart/form-data" },
            credentials: "include"
        }


    ).then(response => response.json() )
        .then(result => {
            //let reader = new FileReader()
          
         // let blobresult = new Blob([result.message] , {type: "multipart/form-data"});
          
         //  let url = URL.createObjectURL(blobresult);
         
           // imagetesting.src = "data:image/png;base64," + result.message || "data:image/jpg;base64," + result.message;
           //console.log("wait in result" + JSON.stringify(result));
            for (let i = 0; i <result.length; i++)
            {
                let temp = result[i];

                
                let imageurl = null;
                if (temp.imageurl != null && temp.imageurl != "")
                    imageurl = temp.imageurl;
                
                let resolvername = "";
                
                if (temp.resolvername != null)
                {
                    resolvername = temp.resolvername;
                    }
                
                   // let re = { firstname: " wiz", lastname: "alcida", type: "food", amount: " 2092", date: " 12/35/9309", status: "pending", resolver: "terry", image: " https://homepages.cae.wisc.edu/~ece533/images/airplane.png" };
                let reimb = { firstname:temp.firstname, lastname: temp.lastname, type: temp.type, amount: temp.amount, date: temp.datesubmit, status: temp.status, resolver:  resolvername, dateresolve: temp.dateresolve, image: imageurl , id: temp.id};
                allreimb.push(reimb);
               // console.log("my reimb " + JSON.stringify(reimb));
                console.log("hello world form the other side")
            }
            createtable();
            //window.location.href = "/index.html";
        })
        .catch(err => console.log( "error " + JSON.stringify(err)))

}


function Deny( )
{
    let buttonid = this.getAttribute("id");
    console.log("the current index deny " + this.getAttribute("id"));


    let index = parseInt(buttonid, 10)
    console.log("the index" + index);
    processReimbUpdate(index, "deny");
}

function Approve()
{
    console.log("the current index approve " + this.getAttribute("id"));
    let buttonid = this.getAttribute("id");
    let index =parseInt( buttonid, 10)
    processReimbUpdate(index, "approve");

}

function getTheReimbFinance() {
    

    fetch("http://localhost:7000/GetReimbFinance",
        {
            method: "GET",
            headers: { "Content-type":   "multipart/form-data" },
            credentials: "include"
        }


    ).then(response => response.json() )
        .then(result => {
            //let reader = new FileReader()
          
         // let blobresult = new Blob([result.message] , {type: "multipart/form-data"});
          
         //  let url = URL.createObjectURL(blobresult);
         
           // imagetesting.src = "data:image/png;base64," + result.message || "data:image/jpg;base64," + result.message;
            //console.log("wait in result" + JSON.stringify(result));
            for (let i = 0; i <result.length; i++)
            {
                let temp = result[i];

                
                let imageurl = null;
                if (temp.imageurl != null && temp.imageurl != "")
                    imageurl = temp.imageurl;
                
                   // let re = { firstname: " wiz", lastname: "alcida", type: "food", amount: " 2092", date: " 12/35/9309", status: "pending", resolver: "terry", image: " https://homepages.cae.wisc.edu/~ece533/images/airplane.png" };
                let reimb = { firstname:temp.firstname, lastname: temp.lastname, type: temp.type, amount: temp.amount, date: temp.datesubmit, status: temp.status, resolver: temp.resolvername, dateresolve: temp.dateresolve, image: imageurl, id: temp.id};
                allreimb.push(reimb);
                //console.log("my reimb " + (reimb));
                console.log("hello world form the other side")
            }
            createtable();
            //window.location.href = "/index.html";
        })
        .catch(err => console.log( "error " + JSON.stringify(err)))

}


function processReimbUpdate(index, accept) {
    let approvedto = null;
    if (allreimb != null) {
        console.log("index " + index + " approve " + accept);
        if (accept == "deny") {
            //status whether its approve or not id of reimbursment;
            approvedto = { status: "1", approve: allreimb[index].id,  resolveid: loginuser.user_id};
        
        }

        if (accept == "approve") {
            //status whether its approve or not id of reimbursment;
            approvedto = { status: "2", approve: allreimb[index].id, resolveid: loginuser.user_id};
        
        }
        console.log(JSON.stringify(approvedto));
    }

    fetch("http://localhost:7000/UpdateReimbFinance",
        {
            method: "PUT",
            body: JSON.stringify(approvedto),
            headers: { "Content-type": "multipart/form-data" },
            credentials: "include"
        }


    ).then(response => response.json())
        .then(result => {
     
           // console.log("wait in result" + JSON.stringify(result));
       
        })
        .catch(err => console.log("error " + error))
    
}


