<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!--  避免JSP出現亂碼 -->

<html lang="en">
<head>
<meta charset="UTF-8">
<title>日期檢查 orderDateCheck Ver1508</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/editFormulaTab.css" />

<script type="text/javascript" src="js/editFormulaTab.js"></script>
<script type="text/javascript" src="js/editFormula.js"></script>

</head>
<body>

	<!--  
	session.setAttribute("clzlList", clzlList);
	-->

	This is editFormula!!!
	<br />

	<ul class="tabs">
		<li><a href="#tab1">Formula</a></li>
		<li><a href="#tab2">List</a></li>
		<li><a href="#tab3">Other</a></li>
	</ul>

	<div class="clr"></div>

	<section class="block">
		<article id="tab1">
			<table id="listFormula">
				<tr class="table_title">
					<th>材料代號</th>
					<th>中文品名</th>
					<th>Description</th>
					<th>Price</th>
				</tr>

				<c:forEach items="${sessionScope.clzlList}" var="clzlList"
					varStatus="status">
					<tr>
					<!--  
					<tr onMouseOut="this.style.backgroundColor=''"
						onMouseOver="this.style.backgroundColor='#B2C67F';" >
					-->
						<td>${clzlList.cldh}</td>
						<td>${clzlList.zwpm }</td>
						<td>${clzlList.ywpm }</td>
						<td>${clzlList.cldj }</td>
					</tr>
				</c:forEach>

			</table>
		</article>
		<article id="tab2">
			配方代號<input type="text"/>USD單價/KG<input type="text"/>一手重量<input type="text"/>
			<br/>
			<table>
				<tr class="table_title">
					<th>類別</th>
					<th>材料代號</th>
					<th>中文品名</th>
					<th>配方量%</th>
					<th>配方用量KG</th>
					<th>USD單價</th>
					<th>金額</th>
				</tr>

				<c:forEach items="${sessionScope.clzlList}" var="clzlList"
					varStatus="status">
					<tr onMouseOut="this.style.backgroundColor=''"
						onMouseOver="this.style.backgroundColor='#B2C67F';">
						<td>${clzlList.cldh}</td>
						<td>${clzlList.zwpm }</td>
						<td>${clzlList.ywpm }</td>
						<td>${clzlList.cldj }</td>
					</tr>
				</c:forEach>
			</table>
			
			<table>
				<tr class="table_title">
					<th>類別</th>
					<th>材料代號</th>
					<th>中文品名</th>
					<th>配方量%</th>
				</tr>

				<c:forEach items="${sessionScope.clzlList}" var="clzlList"
					varStatus="status">
					<tr onMouseOut="this.style.backgroundColor=''"
						onMouseOver="this.style.backgroundColor='#B2C67F';">
						<td>${clzlList.cldh}</td>
						<td>${clzlList.zwpm }</td>
						<td>${clzlList.ywpm }</td>
						<td>${clzlList.cldj }</td>
					</tr>
				</c:forEach>
			</table>
		</article>
		<article id="tab3">
			<p>Morbi interdum mollis sapien. Sed ac risus. Phasellus lacinia,
				magna a ullamcorper laoreet, lectus arcu pulvinar risus, vitae
				facilisis libero dolor a purus. Sed vel lacus. Mauris nibh felis,
				adipiscing varius, adipiscing in, lacinia vel, tellus. Suspendisse
				ac urna. Etiam pellentesque mauris ut lectus. Nunc tellus ante,
				mattis eget, gravida vitae, ultricies ac, leo. Integer leo pede,
				ornare a, lacinia eu, vulputate vel, nisl.</p>
		</article>
	</section>





</body>
</html>