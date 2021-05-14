let loginuser = null; 
let submitrequest = null;
let buttongetimage = null;
let imagetesting = null;
window.addEventListener("load", createpage);


 function createpage(){
    
    console.log("creating ")
    //let data = sessionStorage.getItem('key');
     let user = sessionStorage.getItem('user');
     submitrequest = document.getElementById("newreimb");
     submitrequest.addEventListener("click", createrequest);
     buttongetimage = document.getElementById("getimage");
    // buttongetimage.addEventListener("click", getTheReimb);
     imagetesting=document.getElementById("imagetesting");
    Getuser();
   
    
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
        }

       
    }
    console.log("inside home" + JSON.stringify(result));
}

    ).catch(err => console.log(err))
    

    

}

function createrequest() {
    let amount = document.getElementById("amount");
    let reciept = document.getElementById("reciept");
    let type = document.getElementById("typecreate");
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth();
    let year = date.getFullYear();
    //let newdate = new Date(month, date, year);

    //let formdata = new FormData();

    //if (loginuser != null)
    let reimbobject = { id: loginuser.user_id, amount: amount.value, url: null, type: type.value, }
    
    
    //Sformdata.append('new', "HI");
    
    let myblob = new Blob([reciept.files[0]], { type: " multipart/form-data" });
    //console.log("my blob ", myblob);
//formdata.append("file",URL.createObjectURL(myblob));
    
   // let myobject = formdata.get("new");
    if (reciept.files != null && reciept.files[0] != null) {
        console.log("url man " + URL.createObjectURL(reciept.files[0]));
        console.log("okay " + JSON.stringify(reciept.files[0]));
       
        reimbobject.url = URL.createObjectURL(myblob);
            //URL.createObjectURL(reciept.files[0]).blob;

    }
    
    
    if (amount.checkValidity() == true) {
        console.log("receipt" + reciept.files[0]);
      //  console.log(JSON.stringify(reimbobject));
       // console.log(URL.createObjectURL(myblob));
        let url = "/Reimb";
       // console.log();
        
        
           fetch("http://localhost:7000/CreateReimb"+ "/" + reimbobject.id + "/" + reimbobject.amount + "/" + reimbobject.type + " ",
                {
                    method: "POST",
                    body: reciept.files[0], //blob
                    headers: { "Content-type":"multipart/form-data" },
                    credentials: "include"
                }
    
    
            ).then(response => response.json() )
                .then(result => {
                    console.log(" response wait in path directory" + JSON.stringify(result));
                    //window.location.href = "/index.html";
                })
                .catch(err => console.log())
            
        }
    

}


function getTheReimb() {
    
 
    fetch("http://localhost:7000/GetReimb" +"/" + loginuser.user_id + " ",
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
            console.log("wait in result" + JSON.stringify(result.length));
            //window.location.href = "/index.html";
        })
        .catch(err => console.log( "error " + JSON.stringify(result[0])))

}


