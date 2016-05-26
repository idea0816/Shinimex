<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!--  避免JSP出現亂碼 -->

<html lang="en">
<head>
<meta charset="UTF-8">
<title>模具資料管理 ba_MoldControl Ver1605</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="css/forTab.css" />

<script type="text/javascript" src="js/ba_MoldControl.js"></script>
<script type="text/javascript" src="js/forTab.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

</head>
<body>

	<!--  
	session.setAttribute("moldList", moldList);
	session.setAttribute("summjsl", summjsl);
	-->

	<br />

	<ul class="tabs">
		<li><a href="#tab1">Mold Data</a></li>
		<li><a href="#tab2">Mold List</a></li>
		<li><a href="#tab3">Other</a></li>
	</ul>

	<div class="clr"></div>

	<section class="block">
		<article id="tab1">
			模具總數量<input id="sum_mjsl" type="text" placeholder="summjsl" value="${summjsl}"/>		
			<input id="KHDH" type="text" list="KHDHlist" placeholder="客戶名稱" >
				<datalist id="KHDHlist"></datalist>
				
			<input id="getDatas" type="button" value="Get Datas" >
			<table >
				<tr class="table_title">
					<th style="width:5%">選擇</th>
					<th>模具編號</th>
					<th>模具類型</th>
					<th>客戶簡稱</th>
					<th>鞋廠簡稱</th>
					<th>模具數量</th>
					<th>國別</th>
				</tr>
				<c:forEach items="${sessionScope.moldList}" var="moldList"
					varStatus="status">
					<tr onMouseOut="this.style.backgroundColor=''"
						onMouseOver="this.style.backgroundColor='#B2C67F';" >
						<td class="td_center"><input type="radio" name="getMold" value="${moldList.mjbh}" /></td>
						<td class="td_center">${moldList.mjbh}</td>
						<td>${moldList.lbdh}</td>
						<td>${moldList.kfjc}</td>
						<td>${moldList.kfjc1}</td>
						<td>${moldList.mjsl}</td>
						<td>${moldList.gbbh}</td>
					</tr>
				</c:forEach>
			</table>
		</article>
		<article id="tab2">
			模具編號<input id="" type="text" placeholder="Not Yet Import Datas"/><br/>
			模具類型<input id="" type="text"/>
			客戶簡稱<input id="" type="text"/><br/>
			鞋廠簡稱<input id="" type="text"/>
			SIZE國別<input id="" type="text"/><br/>
			<textarea placeholder="備註一"></textarea><br/>
			<textarea placeholder="備註二"></textarea>
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