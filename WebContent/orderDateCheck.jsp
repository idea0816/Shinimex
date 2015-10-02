<!DOCTYPE html>
<!-- <%@page contentType="text/html; charset=UTF-8" %> 避免JSP出現亂碼 -->
<html lang="en">
<head>
<meta charset="UTF-8">
<title>日期檢查 orderDateCheck Ver1508</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
-->
<script type="text/javascript" src="js/orderDateCheck.js"></script>

</head>
<body>

	<!--  
	session.setAttribute("ddrqCheck", ddrqCheck);
	session.setAttribute("ddjqCheck", ddjqCheck);
	session.setAttribute("chrqCheck", chrqCheck);
	-->

	<table>
		<caption><label class="label_title">接單日期修正</label></caption>
		<tr class="table_title">
			<th style="width:5%"><input id="checkAll" type="checkbox"></th>
			<th>訂單編號</th>
			<th>接單日期</th>
			<th>修正</th>
		</tr>
		<c:forEach items="${sessionScope.ddrqCheck}" var="ddrqCheck"
			varStatus="status">
			<tr onMouseOut="this.style.backgroundColor=''"
				onMouseOver="this.style.backgroundColor='#B2C67F';">
				<td class="td_center"><input name="checkBox" value="ddrq${ddrqCheck.key}"
					type="checkbox"></td>
				<td>${ddrqCheck.key}</td>
				<td>${ddrqCheck.value }</td>
				<td><input name="updateDate" id="ddrq${ddrqCheck.key}"
					type="date" /></td>
			</tr>
		</c:forEach>
	</table>

	<table>
		<caption><label class="label_title">交貨日期修正</label></caption>
		<tr class="table_title">
			<th style="width:5%"></th>
			<th>訂單編號</th>
			<th>交貨日期</th>
			<th>修正</th>
		</tr>
		<c:forEach items="${sessionScope.ddjqCheck}" var="ddjqCheck"
			varStatus="status">
			<tr onMouseOut="this.style.backgroundColor=''"
				onMouseOver="this.style.backgroundColor='#B2C67F';">
				<td class="td_center"><input name="checkBox" value="ddjq${ddjqCheck.key}"
					type="checkbox"></td>
				<td>${ddjqCheck.key}</td>
				<td>${ddjqCheck.value }</td>
				<td><input name="updateDate" id="ddjq${ddjqCheck.key}"
					type="date" /></td>
			</tr>
		</c:forEach>
	</table>

	<table>
		<caption><label class="label_title">生產日期修正</label></caption>
		<tr class="table_title">
			<th style="width:5%"></th>
			<th>生產編號</th>
			<th>生產日期</th>
			<th>修正</th>
		</tr>
		<c:forEach items="${sessionScope.chrqCheck}" var="chrqCheck"
			varStatus="status">
			<tr onMouseOut="this.style.backgroundColor=''"
				onMouseOver="this.style.backgroundColor='#B2C67F';">
				<td class="td_center"><input name="checkBox" value="chrq${chrqCheck.key}"
					type="checkbox"></td>
				<td>${chrqCheck.key}</td>
				<td>${chrqCheck.value }</td>
				<td><input name="updateDate" id="chrq${chrqCheck.key}"
					type="date" /></td>
			</tr>
		</c:forEach>
	</table>

	<input id="delete" type="submit" value="DELETE Select" />
	<input id="update" type="submit" value="UPDATE Date" />

</body>
</html>