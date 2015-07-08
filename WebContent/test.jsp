<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		
		$("#date")
		
		$("#xiexing").change(function(){
			var xiexing=$("#xiexing option:selected").val();
			$.ajax({
				type:"post",
				url:"testConn2.do?xiexing="+encodeURI(encodeURI(xiexing)),
				dataType:"json",
				success:function(data){
					$("#kfqm option").remove();
					$.each(data, function(key, value){
						$("#kfqm").append("<option value='"+key +"'>"+value+"</option");
					});
				},
				error:function(){
					alert("error")
				}
			});
		}); 
		
		
	});
	</script>
	
</head>
<body>

	<input id="date" type="date" />

	<form action="testConn2.do" method="post">
	<select id="xiexing" name="xiexing" style="width:150px" >
	<c:forEach items="${sessionScope.xiexingList}" var="xiexingList" varStatus="status">
		<option value="${xiexingList}">${xiexingList}</option>      
    </c:forEach>
    </select>
    
    <select id="kfqm" style="width:150px">
    	<option value="0">Select</option>
    </select>
    
    <!--  
    <button id="hide" type="button">Hide</button>
    -->
    
    <input type="submit" />
    
    </form>
</body>
</html>


