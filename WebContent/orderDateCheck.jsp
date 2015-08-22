<!DOCTYPE html>
<!-- <%@page contentType="text/html; charset=UTF-8" %> 避免JSP出現亂碼 -->
<html lang="en">
<head>
<meta charset="UTF-8">
<title>日期檢查 orderDateCheck Ver1508</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/orderDateCheck.js"></script>

</head>
<body>

	<!--  
	session.setAttribute("ddrqCheck", ddrqCheck);
	session.setAttribute("ddjqCheck", ddjqCheck);
	session.setAttribute("chrqCheck", chrqCheck);
	-->	
	
	<table>
		<caption>接單日期修正</caption>
		<tr class="table_title">
			<td>訂單編號</td>
			<td>接單日期</td>
			<td>修正</td>
			<td>Delete ?</td>
		</tr>
		<c:forEach items="${sessionScope.ddrqCheck}" var="ddrqCheck" varStatus="status">
		<tr>
			<td>
				${ddrqCheck.key}  
			</td>
			<td>
				${ddrqCheck.value }
			</td>
			<td><input id="date${ddrqCheck.key}" type="date" /></td>
			<td><input id="Check${ddrqCheck.key}" type="checkbox" onclick=getId(this)></td>
		</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><input id="ddrqUpdate" type="submit" value="Update" /></td>
		</tr>
	</table>
	
	<table>
		<caption>交貨日期修正</caption>
		<tr class="table_title">
			<td>訂單編號</td>
			<td>交貨日期</td>
			<td>修正</td>
			<td>Delete ?</td>
		</tr>
		<c:forEach items="${sessionScope.ddjqCheck}" var="ddjqCheck" varStatus="status">
		<tr>
			<td>
				${ddjqCheck.key}    
			</td>
			<td>
				${ddjqCheck.value }
			</td>
			<td><input type="date" /></td>
			<td><input id="" type="checkbox"></td>
		</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><input type="submit" value="Update" /></td>
		</tr>
	</table>

	<table>
		<caption>生產日期修正</caption>
		<tr class="table_title">
			<td>生產編號</td>
			<td>生產日期</td>
			<td>修正</td>
			<td>Delete ?</td>
		</tr>
		<c:forEach items="${sessionScope.chrqCheck}" var="chrqCheck" varStatus="status">
		<tr>
			<td>
				${chrqCheck.key}    
			</td>
			<td>
				${chrqCheck.value }
			</td>
			<td><input type="date" /></td>
			<td><input type="checkbox"></td>
		</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><input type="submit" value="Update" /></td>
		</tr>
	</table>
</body>
</html>