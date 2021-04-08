<!-- Import header/navbar -->
<jsp:include page="../../authHeader.jsp"/>

<div class="content">
	<h1>Reimbursement Form:</h1>
	<hr />
	<br />
	<form method="POST" accept-charset=utf-8 enctype="multipart/form-data">
		  <div class="input-group">
		  <span class="input-group-text">Amount: $</span>
		  <input type="number" name="amount" min="0" step=".01" class="form-control" aria-label="Amount" required>
		</div>
		
		<br />
		
		<p>Reimbursement Type:</p>
		<div class="form-check">
		  <input class="form-check-input" value="1" type="radio" name="reimbType" id="flexRadioDefault1" checked>
		  <label class="form-check-label" for="flexRadioDefault1">
		    Lodging
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" value="2" type="radio" name="reimbType" id="flexRadioDefault2">
		  <label class="form-check-label" for="flexRadioDefault2">
		    Travel
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" value="3" type="radio" name="reimbType" id="flexRadioDefault3">
		  <label class="form-check-label" for="flexRadioDefault3">
		    Food
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" value="4" type="radio" name="reimbType" id="flexRadioDefault4">
		  <label class="form-check-label" for="flexRadioDefault4">
		    Other
		  </label>
		</div>
		
		<br />
		  
		  <div class="mb-3">
		  <label for="exampleFormControlTextarea1" class="form-label">Description: </label>
		  <textarea class="form-control" name="description" id="exampleFormControlTextarea1" maxlength="249" rows="3"></textarea>
		</div>
		
		<!-- <br />
		
		  <div>
		  <label for="formFileLg" class="form-label">Upload Receipt (Optional): </label>
		  <input class="form-control form-control-lg" name="file" id="formFileLg" type="file">
		</div> -->
		
		<br />
		
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>

<style type="text/css">
    <%@include file="../Styles/newReimbursement.css" %>
</style>

<!-- Import footer -->
<jsp:include page="../../footer.jsp"/>