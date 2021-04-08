<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Expense Reimbursement</title>
	<!-- CSS - Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

</head>
<body>
	
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-5">
  <div class="container-fluid">
    <a class="navbar-brand" href="/ExpenseReimbursementSystem">
      <img src="https://cdn.pixabay.com/photo/2013/07/12/12/14/euro-145386_1280.png" alt="" width="37" height="37" class="d-inline-block align-top">
      ERS
    </a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/ExpenseReimbursementSystem">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="register">Register</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
      </ul>
    </div>
    
  </div>
</nav>

<style type="text/css">
.navbar-nav > li
{
  padding-left:30px;
  padding-right:30px;
  font-size: 1.25em;
}

@media screen and (max-width: 1300px) 
{
  .navbar-nav > li
  {
    padding-left:25px;
  padding-right:25px;
  font-size: 1.12em;
  }
}
</style>