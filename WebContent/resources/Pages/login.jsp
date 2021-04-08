<!-- Import header/navbar -->
<jsp:include page="../../header.jsp"/>

<!-- Content -->
<div class="text-center content">
  <form class="form-signin" method="POST" accept-charset=utf-8>
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <label for="inputEmail" class="sr-only">Username</label>
      <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <p class="text-center">Don't have an account? <a href="register">Register</a> </p>  
    </form>
</div>

<style type="text/css">
    <%@include file="../Styles/login.css" %>
</style>

<script type="text/javascript">
	document.title = "ERS Login";
</script>


<!-- Import footer -->
<jsp:include page="../../footer.jsp"/>

