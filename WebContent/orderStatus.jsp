<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!-- 避免JSP出現亂碼 -->
<%@ page import="java.util.*,java.text.*"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>訂單產能狀況表-OrderStatus Ver1509</title>
<!-- 2016-03-04 增加其他年份查詢功能 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/table.css" />

<script type="text/javascript" src="js/orderStatus.js"></script>
<!-- 
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/pc_Progress.js"></script>
-->

</head>
<body>

	<!--  
	session.setAttribute("year", year);
	session.setAttribute("outsoles", outsoles);
	session.setAttribute("pieces", pieces);
	session.setAttribute("subtotal", subtotal);
	session.setAttribute("delivery", delivery);
	session.setAttribute("capacity", capacity);
	session.setAttribute("forchoiceyears", forchoiceyears);
	session.setAttribute("brands", brands);
	session.setAttribute("XieXing", XieXing);
	-->

	<table border="1">
		<caption><label class="label_title">Shinimex 訂單產能狀況表 - <%= session.getAttribute("year") %></label></caption>
		<tr class="table_title">
			<th colspan="2"></th>
			<th>Jan</th>
			<th>Feb</th>
			<th>Mar</th>
			<th>Apr</th>
			<th>May</th>
			<th>Jun</th>
			<th>Jul</th>
			<th>Aug</th>
			<th>Sep</th>
			<th>Oct</th>
			<th>Nov</th>
			<th>Dec</th>
			<th>Sum</th>
		</tr>
		<tr>
			<th rowspan="3">月份<br/>接單量</th>
			<th>大底</th>
			<c:forEach items="${sessionScope.outsoles}" var="outsoles"
				varStatus="status">
				<td>${outsoles}</td>
			</c:forEach>
		</tr>
		<tr>
			<th>飾片</th>
			<c:forEach items="${sessionScope.pieces}" var="pieces"
				varStatus="status">
				<td>${pieces}</td>
			</c:forEach>
		</tr>
		<tr>
			<th>小計</th>
			<c:forEach items="${sessionScope.subtotal}" var="subtotal"
				varStatus="status">
				<td>${subtotal}</td>
			</c:forEach>
		</tr>
		<tr>
			<th colspan="2">預計交貨單量</th>
			<c:forEach items="${sessionScope.delivery}" var="delivery"
				varStatus="status">
				<td>${delivery}</td>
			</c:forEach>
		</tr>
		<tr>
			<th colspan="2">實際產能</th>
			<c:forEach items="${sessionScope.capacity}" var="capacity"
				varStatus="status">
				<td>${capacity}</td>
			</c:forEach>
		</tr>
		<tr>
			<td class="td_center" colspan="15">機器配置產能:960(孔) - 38(sample孔) = 922 * 1(困難系數) *
				105(回轉數) * D(296工作天) = 28655760 * 80%(人員) = 22,924,608(雙)</td>
		</tr>
	</table>
	<label class="label_usual">
	<br /> 說明：
	<br /> 一、飾片指非大底的總合(ex.半叉、飾帶...等)。
	<br /> 二、預計交貨單量及實際產能均含飾片數量。
	<br /> 資料擷取日期：
	<%
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		out.print(dateFormat.format(new Date()));
	%><br />
	</label>
	<br />
	<br />
	<input id="choiceyear" list="yearlist" type="text" placeholder="Back to the...?" />
	<datalist id="yearlist">
		<c:forEach items="${sessionScope.forchoiceyears}" var="forchoiceyears" varStatus="status">
				<option>${forchoiceyears}</option>
		</c:forEach>
	</datalist>
	<input id="getchoiceyear" type="button" value="Jump" />
	<input type="button" value="PRINT" />
	<input type="button" value="Export to Excel" />
	<input type="button" value="Export to PDF" />
	<br />
	<br />
	<div>
				<table border="1" style="width:40%;margin:auto;float:left">
					<caption><label class="label_title">品牌分析明細 - <%= session.getAttribute("year") %></label></caption>
					<tr class="table_title">
						<th>品牌名稱</th>
						<th>訂單數量</th>
					</tr>
					<c:forEach items="${sessionScope.brands}" var="brands" varStatus="status">
					<tr>
						<td>${brands.key}</td>
						<td>${brands.value}</td>
					</tr>
					</c:forEach>
				</table>
				<table border="1" style="width:40%;margin:auto">
					<caption><label class="label_title">鞋型分析明細 - <%= session.getAttribute("year") %></label></caption>
					<tr class="table_title">
						<th>鞋型名稱</th>
						<th>訂單數量</th>
					</tr>
					<c:forEach items="${sessionScope.XieXing}" var="XieXing" varStatus="status">
					<tr>
						<td>${XieXing.key}</td>
						<td>${XieXing.value}</td>
					</tr>
					</c:forEach>
				</table>
	
	
	</div>

</body>
</html>