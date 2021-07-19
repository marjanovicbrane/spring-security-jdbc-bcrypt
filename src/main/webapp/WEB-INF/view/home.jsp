<html>
<!-- We are adding "form" taglib for support for SPRING MVC FORM TAG so we can using model,data binding...-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- we are adding now SPRING SECURITY TAG LIB, WHICH WE ADDED IN OUR .POM FILE,
SO WE CAN SHOW USERNAME AND ROLES, WHEN WE ARE LOGGED IN SYSTEM. -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!-- This is page which will show up for this mapping showHome(/) from controller class -->

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>

	<p>
	Welcome to the luv2code company home page!
	</p>
	
	
	<!-- Here we wil show USERNAME AND ROLES with SPRING SECURITY TAG LIB -->
	<hr>
	<p>
		User: <security:authentication property="principal.username"/>

		<br><br>
		
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	

	<!-- NOW WE WILLUSE ALSO SECURITY TAG, BECAUSE WE WANT TO SHOW ONLY THIS LINK DOWN FOR
	USERS WITH ROLE MANAGER, BECAUSE OF THATWE ARE USING access="hasRole('MANAGER')" -->
	<security:authorize access="hasRole('MANAGER')">
	<p>
	<!-- WE ARE NOW COLLING THIS REQURST MAPING FROM DEMO CONTROLLER CLASS, WHICH CALLING
	THIS .JSP PAGE leaders.jsp -->
	<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>(Only for Manager)
	</p>
	</security:authorize>
	
	
	<security:authorize access="hasRole('ADMIN')">
	<p>
	<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>(Only for Admin)
	</p>
	</security:authorize>
	
	<hr>
	<!-- NOW WE ARE ADDING LOGOUT BUTTON.LIKE BEFORE BY DEFAULT WHEN APP ADDING ERROR PARAMETER
	TO THE LINK, SO WE CAN TAKE THAT PARAMETER AND CHECK IT OUT WITH IF STATEMENT.
	 THE SAME APPROACH WE ARE USING HEREAND WE CAN USE THIS PARAMETER logout to show some message:
	 You have been logged out. wE ARE USING postmethod because we sending data to server,
	 because we want to logout the user from the system and we also removing HTTP SESSION,COOKIES,ITD... -->
	<form:form action="${pageContext.request.contextPath}/logout" metho="POST">
	
	<input type="submit" value="Logout" />
	
	</form:form>
</body>

</html>