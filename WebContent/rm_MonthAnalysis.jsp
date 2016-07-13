<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!-- 避免JSP出現亂碼 -->
<%@ page import="java.util.*,java.text.*"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>原料、配方用量分析-rmMonthAnalysis Ver1607</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/table.css" />

</head>
<body>

	<!--
		session.setAttribute("year", year);
		session.setAttribute("material_top1", material_top1);
		session.setAttribute("material_top2", material_top2);
		session.setAttribute("material_top3", material_top3);
		session.setAttribute("material_top4", material_top4);
		session.setAttribute("material_top5", material_top5);
		session.setAttribute("formula_top1", formula_top1);
		session.setAttribute("formula_top2", formula_top2);
		session.setAttribute("formula_top3", formula_top3);
		session.setAttribute("formula_top4", formula_top4);
		session.setAttribute("formula_top5", formula_top5);
	-->

	<table border="1">
		<caption><label class="label_title">Shinimex 原料用量分析  Top5 - <%= session.getAttribute("year") %></label></caption>
		<tr class="table_title">
			<th style="width:150px">原料名稱</th>
			<th>總用量(Kg)</th>
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
			<th>平均單價</th>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.material_top1}" var="material_top1" varStatus="status">
				<td>${material_top1}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.material_top2}" var="material_top2" varStatus="status">
				<td>${material_top2}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.material_top3}" var="material_top3" varStatus="status">
				<td>${material_top3}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.material_top4}" var="material_top4" varStatus="status">
				<td>${material_top4}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.material_top5}" var="material_top5" varStatus="status">
				<td>${material_top5}</td>
			</c:forEach>
		</tr>
		
	</table>
	<br>
	<table border="1">
		<caption><label class="label_title">Shinimex 配方用量分析  Top5 - <%= session.getAttribute("year") %></label></caption>
		<tr class="table_title">
			<th style="width:150px">配方名稱</th>
			<th>總用量(Kg)</th>
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
			<th>平均單價</th>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.formula_top1}" var="formula_top1" varStatus="status">
				<td>${formula_top1}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.formula_top2}" var="formula_top2" varStatus="status">
				<td>${formula_top2}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.formula_top3}" var="formula_top3" varStatus="status">
				<td>${formula_top3}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.formula_top4}" var="formula_top4" varStatus="status">
				<td>${formula_top4}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${sessionScope.formula_top5}" var="formula_top5" varStatus="status">
				<td>${formula_top5}</td>
			</c:forEach>
		</tr>
		
	</table>
	<label class="label_usual">
	<br /> 說明：
	<br /> 一、因更改計算方法、故資料由2016年2月啟用為主。
	<br /> 資料擷取日期：
	<%
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		out.print(dateFormat.format(new Date()));
	%><br />
	</label>
	<br />
	<br />
	<div style="display:none">
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
	</div>
</body>
</html>