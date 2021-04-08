<!-- Import header/navbar -->
<jsp:include page="../../header.jsp"/>

<!-- Load in any temporary data -->
<%@ page import="models.User" %>
<% User role= (User) request.getSession().getAttribute("tempUser");%>

<div class="container">

<div class="card bg-light">
<article class="card-body mx-auto" style="max-width: 400px;">
	<h4 class="card-title mt-3 text-center">Create Account</h4>
	<p class="text-center">Get started with your free account</p>
	
	<p class="divider-text"> <hr /> </p>
	
	<form method="POST" accept-charset=utf-8>
	<div class="form-group input-group">
        <input id="firstname" name="fn" value="<%= role==null? "" : role.getFirstName()%>" class="form-control" placeholder="First name" type="text" required>
    </div> <!-- form-group// -->
    
    <div class="form-group input-group">
        <input name="ln" class="form-control" value="<%= role==null? "" : role.getLastName() %>" placeholder="Last name" type="text" required>
    </div> <!-- form-group// -->
    
    
	
    <div class="form-group input-group">
        <input id="emailId" name="email" value="<%= role==null? "" : role.getEmail() %>" class="form-control" placeholder="Email address" type="email" required>
	    <div class="clearfix" id="emailSpinner">
		  <div class="spinner-border text-warning float-end" role="status">
		    <span class="visually-hidden">Loading...</span>
		  </div>
		</div>
    </div>
    
    
    <div class="form-check">
	  <input class="form-check-input" type="radio" name="userRadio" value="fm" id="flexRadioDefault1" required>
	  <label class="form-check-label" for="flexRadioDefault1">
	    Finance Manager
	  </label>
	</div>
	<div class="form-check">
	  <input class="form-check-input" type="radio" name="userRadio" value="employee" id="flexRadioDefault2" checked>
	  <label class="form-check-label" for="flexRadioDefault2">
	    Employee
	  </label>
	</div>
	
	<p class="divider-text"> <hr > </p>
	
	<div class="form-group input-group">
        <input id="usernameId" name="username" value="<%= role==null? "" : role.getUsername() %>" class="form-control" placeholder="Username" type="text" required>
    	<div class="clearfix" id="unSpinner">
		  <div class="spinner-border text-warning float-end" role="status">
		    <span class="visually-hidden">Loading...</span>
		  </div>
		</div>
    </div> 
	
    <div class="form-group input-group">
        <input class="form-control" id="pwd" name="password" placeholder="Create password" type="password" required>
    </div> <!-- form-group// -->
    
    <div class="form-group input-group">
        <input class="form-control" id="repeatPwd" placeholder="Repeat password" type="password" required>
    </div>
    <p id="pwdWarning" style="color:white;">Passwords to not match</p>
     
                                     
    <div class="form-group text-center">
        <button type="submit" id="submitBtn" class="btn btn-primary btn-block "> Create Account  </button>
    </div> <!-- form-group// -->      
    <p class="text-center">Have an account? <a href="login">Log In</a> </p>                                                                 
</form>
</article>
</div> <!-- card.// -->

</div> 

<style type="text/css">
    <%@include file="../Styles/register.css" %>
</style>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Styles/register.css" /> --%>

<!-- DOES NOT WORK -->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/register.js"></script> -->

<!-- Import script -->
<script type="text/javascript" >
	<%@include file="../Scripts/register.js" %>
</script>


<!-- Import footer -->
<jsp:include page="../../footer.jsp"/>

