<!-- Import header/navbar -->
<jsp:include page="../../authHeader.jsp"/>

<!-- Side navigation -->
<div class="sidenav" id="sidenav">
  <h3>Filter</h3>
  <div class="form-check">
	  <input class="form-check-input" type="radio" name="filterRadios" id="noneRadios" value="1" checked>
	  <label class="form-check-label" for="noneRadios">
	    None
	  </label>
	</div>
	<div class="form-check">
	  <input class="form-check-input" type="radio" name="filterRadios" id="pendingRadios" value="2">
	  <label class="form-check-label" for="pendingRadios">
	    Pending
	  </label>
	</div>
	<div class="form-check">
	  <input class="form-check-input" type="radio" name="filterRadios" id="ApprovedRadio" value="3">
	  <label class="form-check-label" for="ApprovedRadio">
	    Approved
	  </label>
	</div>
	<div class="form-check">
	  <input class="form-check-input" type="radio" name="filterRadios" id="deniedRadio" value="4">
	  <label class="form-check-label" for="deniedRadio">
	    Denied
	  </label>
	</div>
</div>

<div class="content">
	<h2>Recent Reimbursements</h2>
	<p id="msg">No reimbursements submit :(</p>
	
	<hr />
	
	<table class="table table-striped" id="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Full Name</th>
	      <th scope="col">Type</th>
	      <th scope="col">Amount</th>
	      <th scope="col">Description</th>
	      <th scope="col">Date Submitted</th>
<!-- 	      <th scope="col">File</th> -->
	      <th scope="col">Status</th>
	    </tr>
	  </thead>
	  <tbody>
	
	  </tbody>
	</table>
</div>

<div class="text-center" id="spinner">
	<div class="spinner-border text-primary" role="status">
	  <span class="visually-hidden">Loading...</span>
	</div>
</div>


<!-- Styles -->
<style type="text/css">
    <%@include file="../Styles/dashboard.css" %>
</style>

<!-- Import script -->
<script type="text/javascript" >
	<%@include file="../Scripts/fmDashboard.js" %>
</script>

<!-- Import footer -->
<jsp:include page="../../footer.jsp"/>