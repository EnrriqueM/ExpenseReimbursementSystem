<!-- Check for any sess User data -->
<%@ page import="models.User" %>

<!-- Check Navbar display -->
<% User sessUser = (User) request.getSession().getAttribute("sessUser");%>
<% if(sessUser == null)
	{ %>
		<jsp:include page="header.jsp"/>
    	  
    <% }
    else
    {  
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	%>
    	<jsp:include page="authHeader.jsp"/>
   	<% } %>

<!-- content -->
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="https://cdn.pixabay.com/photo/2018/03/27/12/16/analytics-3265840_1280.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
      	<% if(sessUser == null)
			{ %>
				<h5 style="color: red;">WELCOME</h5>
        		<p style="color: #ff5349;">Create an account and request a reimbursement.</p>
		    	  
		    <% }
		    else
		    { %> 
		    	<h5 style="color: red;">Welcome back <%= sessUser.getFirstName() %> </h5>
        		<p style="color: #ff5349;">View your dashboard NOW!!</p>
		   	<% } %>
      </div>
    </div>
    <div class="carousel-item">
      <img src="https://cdn.pixabay.com/photo/2016/04/19/20/08/time-is-money-1339781_1280.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Time is money</h5>
        <p>Get money back for purchases made out of pocket.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="https://cdn.pixabay.com/photo/2017/03/13/17/26/ecommerce-2140604_1280.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Reimbursements made simple</h5>
        <p>Once approved, spend it however you like.</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"  data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"  data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<!-- Import footer -->
<jsp:include page="footer.jsp"/>
