<%@ page isErrorPage="true" %>

<!-- Check for any sess User data -->
<%@ page import="models.User" %>
<!-- Check Navbar display -->
<% User sessUser = (User) request.getSession().getAttribute("sessUser");%>
<% if(sessUser == null)
	{ %>
		<jsp:include page="header.jsp"/>
    	  
    <% }
    else
    { %> 
    	<jsp:include page="authHeader.jsp"/>
   	<% } %>

<h1>Uhhh, this doesn't exist</h1>

<!-- Import footer -->
<jsp:include page="footer.jsp"/>