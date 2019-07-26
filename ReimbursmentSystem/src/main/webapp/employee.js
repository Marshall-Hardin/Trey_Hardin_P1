var baseURL = 'http://localhost:8080/Project1/';
var employee;

{
    console.log('loaded');
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/info");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                employee = resp;
                console.log(employee);
                displayInfo();
                //disableUpdate();
            }
        }
    };
    xhr.send();
};

function displayInfo(){
    console.log(employee);

    if(employee.fname){
        document.getElementById('firstname').innerText = "First Name: " + employee.fname;
    }
    if(employee.lname){
        document.getElementById('lastname').innerText = "Last Name: " + employee.lname;
    }
    if(employee.email){
        document.getElementById('email').innerText = "Email: " + employee.email;
    }


}

document.getElementById("editInfo").onclick = () =>{
    console.log("got info click")

    if(document.getElementById("editFields").style.display == "none"){
        document.getElementById("editFields").style.display = "block";
        document.getElementById("editInfo").innerText = "Hide";
        console.log("truthy")
    }else{
        document.getElementById("editFields").style.display = "none";
        document.getElementById("editInfo").innerText = "Edit Information";
        console.log("falsey")
    }
} 

document.getElementById("reimbursment").onclick = () =>{
    console.log("click");
    window.location.href = baseURL + "submitRequest.html";
}

function disableUpdate(){
	document.getElementById('update').setAttribute('disabled', true);
}

function enableUpdate(){
    document.getElementById('update').removeAttribute('disabled');
}