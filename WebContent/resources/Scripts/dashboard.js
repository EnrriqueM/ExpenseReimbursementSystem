

//Get table
const tableEle = document.getElementById("table");
//Table body
let tableBody =  tableEle.getElementsByTagName('tbody')[0];

//Get spinner
const spin = document.getElementById("spinner");

//Get Sidebar
const sidebar = document.getElementById("sidenav");

//Get msg
const msg = document.getElementById("msg");

//Radio button filter add event listeners
document.querySelectorAll('input[name="filterRadios"]').forEach((elem) => {
    elem.addEventListener("change", function(event) {
      var item = event.target.value;
      //Clear table body
      var new_tbody = document.createElement('tbody');
      tableBody.parentNode.replaceChild(new_tbody, tableBody);
      //Resign table var
      tableBody =  tableEle.getElementsByTagName('tbody')[0];
		
		//Filter data
		//None
      if(item == 1)
      {
      	displayReimbs(allReimbsList);
      }
      //Pending
      else if(item == 2)
      {
      	//Filter
      	const filter = (args) => {
      		return args.filter(el => el.status == "PENDING");
      	}
      	
      	//Populate table
      	displayReimbs(filter(allReimbsList));
      }
      //Approved
      else if(item == 3)
      {
      	//Filter
      	const filter = (args) => {
      		return args.filter(el => el.status == "APPROVED");
      	}
      	
      	//Populate table
      	displayReimbs(filter(allReimbsList));
      }
      //Denied
      else
      {
      	//Filter
      	const filter = (args) => {
      		return args.filter(el => el.status == "DENIED");
      	}
      	
      	//Populate table
      	displayReimbs(filter(allReimbsList));
      }
    });
  });

//List of Reimb
let allReimbsList;

//Function to add rows
//Called by AJAX response
function displayReimbs(reimbsList)
{
	//Add each row
	var i;
	for(i = 0; i < reimbsList.length; i++)
	{
		const reimb = reimbsList[i];
		
		// Insert a row at the end of table <tr>
		var newRow = tableBody.insertRow();
		
		//Create <th> element and sent it's attribute (Create row num)
		var th = document.createElement('th');
		
		th.setAttribute('scope', 'row');
		// Append inner text
		const rowNum = i + 1;
		var colNum = document.createTextNode(rowNum);
		th.appendChild(colNum);
		newRow.appendChild(th);
		
		//Create type col
		var typeCell = newRow.insertCell();
		//create inner text and append to cell
		const typeText = document.createTextNode(reimb.type);
		typeCell.appendChild(typeText);
		
		
		//Create amount col
		// Insert a cell at the end of the row <td>
		var amountCell = newRow.insertCell();
		
		// Append a text node to the cell
		var amountText = document.createTextNode("$" + reimb.amount);
		amountCell.appendChild(amountText);
		
		//Get the first 15 char of the descr
		const descr = reimb.description;
		let descrFinal = "";
		if (descr.length < 15)
		{
			descrFinal = descr;
		}
		else
		{
			descrFinal = descr.substring(0,13) + "...";
		}
		
		//Create Descr cell
		const descrCell = newRow.insertCell();
		//Create its inner text and append to cell
		const descrText = document.createTextNode(descrFinal);
		descrCell.appendChild(descrText);
		
		//Create Date submitted
		const dateCreated = newRow.insertCell();
		//Get date created from json
		const date = reimb.dateCreated.dayOfMonth + " " +reimb.dateCreated.month + " " + reimb.dateCreated.year;
		//Create its inner text and append to cell
		const dateText = document.createTextNode(date);
		dateCreated.appendChild(dateText);
		
		//Create File cell
		/*const fileCell = newRow.insertCell();
		//Create innertext and append to cell
		const fileText = document.createTextNode("None");
		fileCell.appendChild(fileText);*/
		
		
		//Create Status Cell
		const statusCell = newRow.insertCell();
		//Create inner text and append to cell
		const statusText = document.createTextNode(reimb.status);
		statusCell.appendChild(statusText);
		
		//Create View Details Cell
		const detailsCell = newRow.insertCell();
		//Create form to view more details
		const form = document.createElement('form');
		form.setAttribute('accept-charset', 'utf-8');
		form.setAttribute('method', 'GET');
		form.setAttribute('action', 'viewDetails');
		
		//Create input type
		const inputType = document.createElement('input');
		inputType.setAttribute('type', 'hidden');
		inputType.setAttribute('name', 'reimbId');
		inputType.setAttribute('value', reimb.id);
		
		//Create submit button
		const viewBtn = document.createElement('button');
		viewBtn.setAttribute('class', "btn btn-light");
		viewBtn.innerText = "Details";
		
		//Append to form
		form.appendChild(inputType);
		form.appendChild(viewBtn);
		
		//Append form to cell
		detailsCell.appendChild(form);
	}
}

function getReimb()
{
	//Start an AJAX request
	const xhr = new XMLHttpRequest();
	
	//Load spinner when preparing request
	xhr.onreadystatechange = function()
	{
		if(this.readyState < 4)
		{
			spin.style.display = "block";
		}
	}
	
	xhr.open('GET', "api/getReimbursements", true);
	
	//When ready state = 4
	xhr.onload = function()
	{
		//Hide spinner
		spin.style.display = "none";
			
		if(this.status == 200)
		{
			allReimbsList = JSON.parse(xhr.responseText);

			
			
			//Display reimb, if any
			if(allReimbsList.length === 0)
			{
				msg.style.display = "block";
				sidebar.style.display = "none";
			}
			else
			{
				//Enable table
				tableEle.style.display = "table";
				sidebar.style.display = "block";
			
				//Add rows
				displayReimbs(allReimbsList);
				
			}
		}
	}
	
	xhr.send();
}

window.onload = function()
{
	document.title = "ERS Dashboard";
	getReimb();
}