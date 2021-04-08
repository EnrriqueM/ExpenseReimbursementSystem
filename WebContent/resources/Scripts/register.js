/**
 * 
 */

//Textfileds needing validations
let isUsernameUnique = false;
const usernameEle = document.getElementById('usernameId');
let isEmailUnique = false;
const emailEle = document.getElementById('emailId');

//Get password fields
const pwd = document.getElementById('pwd');
const repeatPwd = document.getElementById('repeatPwd');
const pwdWarning = document.getElementById('pwdWarning');

repeatPwd.addEventListener('input', checkPwd);

//Get spinners
const emailSpinner = document.getElementById("emailSpinner");
const unSpinner = document.getElementById("unSpinner");

//Submit button
const submitBtn = document.getElementById("submitBtn");

checkUsername();
checkEmail();
enableBtn(isEmailUnique, isUsernameUnique);

//Event listeners
usernameEle.addEventListener('change', checkUsername);
emailEle.addEventListener('change', checkEmail);

//Set title
document.title = "ERS Register";

//Checks to see if pwd matches
function checkPwd(e)
{
	if (repeatPwd.value == pwd.value)
	{
		pwdWarning.style.color = "white";
		enableBtn(isEmailUnique, isUsernameUnique);
	}
	else
	{
		pwdWarning.style.color = "red";
		submitBtn.disabled = true;
	}
}

//AJAX Function to validate username
function checkUsername()
{
	//Prevent AJAX if field is empty
	if(usernameEle.value == " " || usernameEle.value == null || usernameEle.value == "")
	{
		return;
	}
	
	//console.log(usernameEle.value);
	const un = usernameEle.value;
	const xhr = new XMLHttpRequest();

	//Load spinner when preparing request
	xhr.onreadystatechange = function()
	{
		if(this.readyState < 4)
		{
			unSpinner.style.display = "flex";
		}
	}

	xhr.open('GET', "api/validateUsername?username=" + un, true);
	
	xhr.onload = function()
	{
		if(this.status == 202)
		{
			console.log("Username is availabe");
			isUsernameUnique = true;
		}
		else
		{
			console.log("Not available");
			isUsernameUnique = false;
		}
		
		unSpinner.style.display = "none";
		enableBtn(isEmailUnique, isUsernameUnique);
	}
	
	xhr.send();
}

//AJAX Function to validate email
function checkEmail()
{
	//Prevent AJAX if field is empty
	if(emailEle.value == " " || emailEle.value == null || emailEle.value == "")
	{
		return;
	}
	
	const email = emailEle.value;
	const xhr = new XMLHttpRequest();
	
	//Load spinner when preparing request
	xhr.onreadystatechange = function()
	{
		if(this.readyState < 4)
		{
			emailSpinner.style.display = "flex";
		}
	}
	
	xhr.open('GET', "api/validateEmail?email=" + email, true);
	
	xhr.onload = function()
	{
		if(this.status == 202)
		{
			console.log("Email is availabe");
			isEmailUnique = true;
		}
		else
		{
			console.log("Not available");
			isEmailUnique = false;
		}
		
		emailSpinner.style.display = "none";
		enableBtn(isEmailUnique, isUsernameUnique);
	}
	
	xhr.send();
}

//Function to enable submit button
function enableBtn(emailVal, unVal)
{
	//Enable submit btn if email and username is valid
	//Disable otherwise
	if(emailVal && unVal)
	{
		submitBtn.disabled = false;
	}
	else
	{
		submitBtn.disabled = true;
	}
}
	
