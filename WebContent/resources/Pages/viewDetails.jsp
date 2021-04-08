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
	
	<!-- Show table if reimb has been resolved -->
	<% if(reimb.getResolver_id() != null && !(reimb.getResolver_id() <= 0))
	{ %>
		<table class="table">
			<thead>
		    <tr>
		      <th scope="col">Resolver's Name</th>
		      <th scope="col">Date Resolved</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <td scope="row"><%= reimb.getResolver_firstname() + " " + reimb.getResolver_lastname() %></td>
		      <td><%= reimb.getDateResolved().format(formatter) %></td>
		    </tr>
		  </tbody>
		</table>
    	  
    <% }%> 
    
</div>

<!-- Styles -->
<style type="text/css">
    <%@include file="../Styles/viewDetails.css" %>
</style>

<script type="text/javascript">
	document.title = "ERS Details";
</script>

<!-- Import footer -->
<jsp:include page="../../footer.jsp"/>