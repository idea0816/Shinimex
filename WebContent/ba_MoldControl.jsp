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
		<li><a href="#tab3">Last 3Months Records</a></li>
	</ul>

	<div class="clr"></div>

	<section class="block">
		<article id="tab1">
			模具總數量<input id="sum_mjsl" type="text" placeholder="summjsl" value="${summjsl}"/>	
			<input id="mjbh_choice" type="text" list="mjbhlist" placeholder="模具名稱" >
				<datalist id="mjbhlist">
					<c:forEach items="${sessionScope.moldList}" var="moldList" varStatus="status">
					<option>
					${moldList.mjbh}
					</option>
					</c:forEach>	
				</datalist>
			Or	
			<input id="KHDH_choice" type="text" list="KHDHlist" placeholder="客戶名稱" >
				<datalist id="KHDHlist">
					<c:forEach items="${sessionScope.allkfjc}" var="allkfjc" varStatus="status">
					<option>
					${allkfjc}
					</option>
					</c:forEach>
				</datalist>
			<input id="getDatas" type="button" value="Get Datas" >
			
			<table id="moldList">
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
			<div style="width:50%; float:left">
			模具編號<input id="MJZL_mjbh" type="text" placeholder="Not Yet Import Datas"/><br/>
			模具類型<input id="lbzls_zwsm" type="text"/>
			客戶簡稱<input id="kfzl_kfjc" type="text"/><br/>
			鞋廠簡稱<input id="kfzl_kfjc1" type="text"/>
			SIZE國別<input id="MJZL_gbbh" type="text"/><br/>
			<textarea id="MJZL_bz1" placeholder="備註一"></textarea><br/>
			<textarea id="MJZL_bz2" placeholder="備註二"></textarea>
			</div>
			<div style="width:50%; float:right">
				<!-- Size 明細列表 Table -->
				<table id="MoldsizeList" style="overflow:scroll; height: 100px">
					<tr class="table_title">
						<th>選擇</th>
						<th>SIZE</th>
						<th>Amount</th>
					</tr>
					<tbody>
						<tr>
							<td><input type="radio" name="getSize" /></td>
							<td>test</td>
							<td>test</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="clr"></div>
			<!-- 模具進出/維修 Table -->
			<div id="moldInOut" style="display:none">
			<input id="Insert" type="button" value="Insert" />
				<div id="insertInOut" style="display:none">
					<input id="Cancel" type="button" value="Cancel" />
						<div id="InsertMoldInOut" style="width:90%;margin:auto">
							<table id="">
								<tbody>
									<tr id="">
									<td>
									<input id="DGLB" type="text" list="dglglist" placeholder="異動類別" style="width:90%" />
										<datalist id="dglglist">
											<option>模具入庫</option>
											<option>模具出庫</option>
											<option>模具送修</option>
											<option>模具返修</option>
										</datalist>
									</td>
									<td><input id="date_inOut" type="date" style="width:90%" /></td>
									<td>
										<input id="zszl" type="text" list="zszllist" placeholder="廠商名稱" style="width:95%" />
										<datalist id="zszllist"></datalist>
									</td>
									<td><input id="SL" type="number" min="1" placeholder="數量" value="1" style="width:90%" /></td>
									<td>
									<input id="moldCode" type="text" list="moldCodelist" placeholder="模具碼" style="width:90%" />
										<datalist id="moldCodelist">
											<option selected="selected">A</option>
											<option>B</option>
											<option>C</option>
											<option>D</option>
											<option>E</option>
											<option>F</option>
											<option>G</option>
											<option>H</option>
											<option>I</option>
											<option>J</option>
											<option>K</option>
											<option>L</option>
											<option>M</option>
											<option>N</option>
											<option>O</option>
										</datalist>
									</td>
									<td><input id="BZ" type="text" placeholder="備註" style="width:115%" /></td>
									<td><input id="saveInOut" type="button" value="SAVE" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			<table id="moldlist_InOut">
				<tr class="table_title">
					<th style="">異動類別</th>
					<th>異動日期</th>
					<th>廠商</th>
					<th>模具碼</th>
					<th>入廠數量</th>
					<th>出廠數量</th>
					<th>備註</th>
					<th>異動單號</th>
				</tr>
				<tbody>
				<tr id="">
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
				</tr>
				</tbody>
			</table>
			</div>
		</article>
		<article id="tab3">
			<table id="3months_InOut">
				<tr class="table_title">
					<th style="">異動類別</th>
					<th>異動日期</th>
					<th>廠商</th>
					<th>模具編號</th>
					<th>SIZE</th>
					<th>模具碼</th>
					<th>入廠數量</th>
					<th>出廠數量</th>
					<th>備註</th>
					<th>異動單號</th>
				</tr>
				<tbody>
				<tr id="">
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
					<td>test</td>
				</tr>
				</tbody>
			</table>
		</article>
	</section>





</body>
</html>