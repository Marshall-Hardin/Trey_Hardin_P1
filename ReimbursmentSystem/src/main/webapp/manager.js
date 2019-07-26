var baseURL = 'http://localhost:8080/Project1/';
var request = [];


document.getElementById('view-pending').onclick = () => {
    console.log('clicked');
    document.getElementById('accept-block').style.display = 'block';
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/all-pending-manager");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                resetTable(false);
                request = [];
                for(let req of resp){
                    console.log(req);
                    request.push(req);
                }
                popTable(false);
            }
        }
    };
    xhr.send();
};

//Needs work getting error
document.getElementById('filter-emps').onclick = () => {
    console.log('submit');
    document.getElementById('accept-block').style.display = 'block';
    let empId = document.getElementById('emp-id').value;
    let url = baseURL + "app/servlet/all-pending-manager/filtered?empId=" + empId;
    console.log(url);
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/all-pending-manager/filtered?empId=" + empId);
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                resetTable(false);
                request = [];
                for(let req of resp){
                    console.log(req);
                    request.push(req);
                }
                popTable(false);
            }
        }
    };
    xhr.send();
};

document.getElementById('view-approved').onclick = () => {
	console.log('clicked');
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/all-approved-manager");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                resetTable(true);
                request = [];
                for(let req of resp){
                    console.log(req);
                    request.push(req);
                }
                popTable(true);
            }
        }
    };
    xhr.send();
};

//for employee needs some work
document.getElementById('view-employees').onclick = () => {
	console.log('clicked');
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/all-employees-manager");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                resetTableEmployee();
                request = [];
                for(let req of resp){
                    console.log(req);
                    request.push(req);
                }
                popTableEmployee();
            }
        }
    };
    xhr.send();
};

document.getElementById('filter').onclick = () => {
    if(document.getElementById("emp-id").style.display == "none"){
        document.getElementById("filter-emps").style.display = "block";
        document.getElementById("emp-id").style.display = "block";
        document.getElementById("filter").innerText = "Hide";
    }else{
        document.getElementById("filter-emps").style.display = "none";
        document.getElementById("emp-id").style.display = "none";
        document.getElementById("filter").innerText = "filter results";
        console.log("falsey")
    }
}



function popTable(isApproved){
    let tablename;
    if(isApproved){
        tablename = "approved";
        console.log(tablename);
    } else{
        tablename = "pending";
        console.log(tablename);
    }

    for(let req of request){
        let newRow = document.createElement('tr');

        let idCol = document.createElement('td');
        idCol.innerText = "Request Id: " + req.reimbursementId + " - ";
        let empIdCol = document.createElement('td');
        empIdCol.innerText = "Employee Id: " + req.employeeId
        let amountCol = document.createElement('td');
        amountCol.innerText = "$"+req.reimbursementAmount;
        let mngCol = document.createElement('td');
        mngCol.innerText = "Manager Id: " + req.managerId;
        
        if(!req.approved){
            newRow.appendChild(empIdCol);
            newRow.appendChild(idCol);
            newRow.appendChild(amountCol);
        } else{
            newRow.appendChild(empIdCol);
            newRow.appendChild(amountCol);
            newRow.appendChild(mngCol);
        }

        document.getElementById(tablename).appendChild(newRow);
    }
}

function popTableEmployee(){
    for(let req of request){
        let newRow = document.createElement('tr');

        let idCol = document.createElement('td');
        idCol.innerText = "Employee Id: " + req.id + " ";
        let empName = document.createElement('td');
        empName.innerText = "Employee name: " + req.fname + " " + req.lname;
        let emailCol = document.createElement('td');
        emailCol.innerText = " Email: "+req.email;
        
        newRow.appendChild(idCol);
        newRow.appendChild(empName);
        newRow.appendChild(emailCol);
        
        document.getElementById('employees').appendChild(newRow);
    }
}

function resetTable(isApproved){
    let tablename;
    if(isApproved){
        tablename = "approved";
    } else{
        tablename = "pending";
    }

    let table = document.getElementById(tablename);
    while(table.firstChild){
        table.removeChild(table.firstChild);
    }
}

function resetTableEmployee(){
    let table = document.getElementById('employees');
    while(table.firstChild){
        table.removeChild(table.firstChild);
    }
}