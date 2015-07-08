<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8" %> <!-- 避免JSP出現亂碼 -->
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>海關需求配方明細 f_list_for_Customs Ver1505</title>

	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/pc_Progress.js"></script>

</head>
<body>
	
	<input id="xiexing" type="text" list="xiexinglist" placeholder="鞋型" >
		<datalist id="xiexinglist"></datalist>
		
	<input id="KHDH" type="text" list="KHDHlist" placeholder="客戶名稱" >
		<datalist id="KHDHlist"></datalist>
		
	<input id="getOrders" type="button" value="Get Orders" >
				
	<table>
		<tr class="table_title">
					<td>接單日期</td>
					<td>BBB</td>
					<td>CCC</td>
					<td>Select</td>
					<td>DDD</td>
					<td>E</td>
					<td>F</td>
					<td>G</td>
					<td>H</td>
				</tr>
				<tr>
					<td>111</td>
					<td>222</td>
					<td>333333333333333333333333333333333333333333</td>
					<td><input type="date" /></td>
					<td>444</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>111</td>
					<td>222</td>
					<td>333333333333333333333333333333333333333333</td>
					<td><input type="date" /></td>
					<td>444</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>111</td>
					<td>222</td>
					<td>333333333333333333333333333333333333333333</td>
					<td><input type="file" /></td>
					<td>444</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
			</table>

</body>
</html>