var baseURL = 'http://localhost:8080/Project1/';
var request = [];


document.getElementById('pending').onclick = () => {
	console.log('clicked');
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/pending-emp");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                resetTable();
                request = [];
                for(let req of resp){
                    console.log(req);
                    request.push(req);
                }
                popTable();
            }
        }
    };
    xhr.send();
};

document.getElementById('approved').onclick = () => {
	console.log('clicked');
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "app/servlet/approved-emp");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4){
        	console.log(xhr.status);
            if(xhr.status === 200){
                resetTable();
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                request = [];
                for(let req of resp){
                    console.log(req);
                    request.push(req);
                }
                popTable();
            }
        }
    };
    xhr.send();
};


function popTable(){
    for(let req of request){
        let newRow = document.createElement('tr');

        let idCol = document.createElement('td');
        idCol.innerText = "Request Id: " + req.reimbursementId + " - ";
        let amountCol = document.createElement('td');
        amountCol.innerText = "$"+req.reimbursementAmount;
        let approvedCol = document.createElement('td');
        if(req.managerId == 0){
            approvedCol.innerText = req.approved ? ".... Status approved" : ".... Status pending";
        } else{
            approvedCol.innerText = req.approved ? ".... Status approved" : ".... Status denied";
        }
        
        newRow.appendChild(idCol);
        newRow.appendChild(amountCol);
        newRow.appendChild(approvedCol);

        document.getElementById('pend-table').appendChild(newRow);
    }
}

function resetTable(){
    let table = document.getElementById('pend-table');
    while(table.firstChild){
        table.removeChild(table.firstChild);
    }
}