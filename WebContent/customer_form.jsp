<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Form</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body style="color: orange; background-image: url('web.jpg');">

 	<header style="color:white">
		<nav class="navbar navbar-expand-lg navbar-dark bg-info">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Customer Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="table">Customer</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
	</header>	
	
	<br>
	<br>
<div  class="container" style="width: 35%">
	
	<c:if test="${customer == null}">
	
		<form action="add" method="post">          <!-- When data is not coming from Servlet -->
		
		<h2 style="color: red;">Add Customer</h2>
	
	</c:if>
	
	 <c:if test="${ customer != null }">
	 
	 	<form action="edit" method="post">			 <!-- When data is coming from Servlet -->
	 	
	 	<h2 style="color: lime;">Edit Customer</h2>
	 	
	 </c:if>
	 
	   <div class="mb-3" hidden>
		  <input value="<c:out value="${ customer.id }"></c:out>" type="text" class="form-control" id="exampleFormControlInput" name="tbId">
		</div>
		<div class="mb-3">
		  <label for="exampleFormControlInput1" class="form-label">Name</label>
		  <input value="<c:out value="${ customer.name }"></c:out>" type="text" class="form-control" id="exampleFormControlInput1" name="tbName" placeholder="Enter the name" required="required">
		</div>
		
		<div class="mb-3">
		  <label for="exampleFormControlInput2" class="form-label">Email</label>
		  <input value="<c:out value="${ customer.email }"></c:out>" type="email" class="form-control" id="exampleFormControlInput2" name="tbEmail" placeholder="Enter the email" required="required">
		</div>
		
		<div class="mb-3">
		  <label for="exampleFormControlInput3" class="form-label">Mobile</label>
		  <input value="<c:out value="${ customer.mobile }"></c:out>" type="tel" class="form-control" id="exampleFormControlInput3" name="tbMobile" placeholder="Enter the mobile no" required="required">
		</div>
	  	
	  	<div>
	  		<button class="btn btn-success">Save</button>
	  	</div>
	</form>
	</div>
</body>
</html>