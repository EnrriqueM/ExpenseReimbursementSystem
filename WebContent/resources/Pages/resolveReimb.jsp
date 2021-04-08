<!-- Import header/navbar -->
<jsp:include page="../../authHeader.jsp"/>

<%@ page import="models.Reimbursement" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<% Reimbursement reimb = (Reimbursement) request.getSession().getAttribute("reimb");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyy"); %>

<div class="content">
	<table class="table table-light">
		<thead>
		    <tr>
		      <th scope="col">Id</th>
		      <th scope="col">Full Name</th>
		      <th scope="col">Type</th>
		      <th scope="col">Amount</th>
		      <th scope="col">Date Submitted</th>
		      <th scope="col">File Uploaded</th>
		      <th scope="col">Status</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row"><%= reimb.getId()%></th>
		      <td><%= reimb.getAuthor_firstname() + " " + reimb.getAuthor_lastname() %></td>
		      <td><%= reimb.getType() %></td>
		      <td>$<%= reimb.getAmount()%></td>
		      <td><%= reimb.getDateCreated().format(formatter) %></td>
		      <td><em>None</em></td>
		      <td><%= reimb.getStatus() %></td>
		    </tr>
		  </tbody>
	</table>
	
	<table class="table">
		<thead>
		    <tr>
		      <th scope="col">Details</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row" rowspan="0"><%= reimb.getDescription() %></th>
		    </tr>
		  </tbody>
	</table>
    
    <form method="POST" accept-charset=utf-8>
    	<input type="hidden" name="id" value="<%= reimb.getId()%>" />
    	<div class="form-check">
		  <input class="form-check-input" type="radio" name="resolved" id="flexRadioDefault1" value="1">
		  <label class="form-check-label" for="flexRadioDefault1">
		    APPROVED
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="radio" name="resolved" id="flexRadioDefault2" value="0" checked>
		  <label class="form-check-label" for="flexRadioDefault2">
		    DENIED
		  </label>
		</div>
		<button type="submit" class="btn btn-primary">Resolve</button>
    </form>
    
</div>

<!-- Styles -->
<style type="text/css">
    <%@include file="../Styles/viewDetails.css" %>
</style>

<script type="text/javascript">
	document.title = "ERS Resolve";
</script>

<!-- Import footer -->
<jsp:include page="../../footer.jsp"/>