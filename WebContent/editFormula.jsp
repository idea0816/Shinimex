<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!--  避免JSP出現亂碼 -->

<html lang="en">
<head>
<meta charset="UTF-8">
<title>日期檢查 orderDateCheck Ver1508</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="css/editFormula.css" />
<link rel="stylesheet" type="text/css" href="css/forTab.css" />

<script type="text/javascript" src="js/editFormula.js"></script>
<script type="text/javascript" src="js/forTab.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

</head>
<body>

	<!--  
	session.setAttribute("clzlList", clzlList);
	-->

	<br />

	<ul class="tabs">
		<li><a href="#tab1">Formula</a></li>
		<li><a href="#tab2">List</a></li>
		<li><a href="#tab3">Other</a></li>
	</ul>

	<div class="clr"></div>

	<section class="block">
		<article id="tab1">
			<table >
				<tr class="table_title">
					<th style="width:5%">選擇</th>
					<th>材料代號</th>
					<th>中文品名</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
				
				<c:forEach items="${sessionScope.clzlList}" var="clzlList"
					varStatus="status">
					<tr onMouseOut="this.style.backgroundColor=''"
						onMouseOver="this.style.backgroundColor='#B2C67F';" >
						<td class="td_center"><input type="radio" name="getFormula" value="${clzlList.cldh}" /></td>
						<td class="td_center">${clzlList.cldh}</td>
						<td>${clzlList.zwpm}</td>
						<td>${clzlList.ywpm}</td>
						<td>${clzlList.cldj}</td>
					</tr>
				</c:forEach>
			</table>
		</article>
		<article id="tab2">
			配方代號<input id="cldh_cldh" type="text" value="Not Yet Import Datas"/>
			USD單價/KG<input id="cldh_cldj" type="text"/>
			一手重量<input id="cldh_TotKgs" type="text"/>
			<input id="Insert" type="button" value="Insert" />
			<input id="Delete" type="button" value="Delete" />
			<input type="button" value="Update" />
			<br/>
			<!-- 原物料列表 Table //Get cldh,zwpm,cldj,YYSL For 原物料-->
			<table id="getcldhz" style="width:70%;margin:auto;display:none">
				<tr class="table_title">
					<th style="width:10%">Add</th>
					<th style="width:10%">LB</th>
					<th style="width:10%">Amount</th>
					<th>原物料名稱</th>
					<th style="width:15%">Price</th>
					<th style="width:15%">庫存</th>
				</tr>
				<tr>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
				</tr>
			</table>
			
			
			<!-- 配方明細列表 Table -->
			<table id="Formula_getcldh">
				<tr class="table_title">
					<th style="width:5%">類別</th>
					<th>材料代號</th>
					<th>中文品名</th>
					<th>PHR</th>
					<th>配方用量KG</th>
					<th>USD單價</th>
					<th>金額</th>
					<th id="deleteCol" style="width:5%">Delete</th>
				</tr>
				<tbody>
				<tr id="edidList">
					<!-- <td><input id="deleteCB" type="checkbox" style="display:none"></td>-->
					<td>test</td>
					<td>test</td>
					<td>test</td>
				</tr>
				</tbody>
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