<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!--  避免JSP出現亂碼 -->

<html lang="en">
<head>
<meta charset="UTF-8">
<title>訂單生產入庫建檔 pc_Produce Ver1510</title>

<link rel="stylesheet" type="text/css" href="css/table.css" />

<script type="text/javascript" src="js/pc_Produce.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

</head>
<body>
	<p />
	<label class="label_title">Shinimex Produce & Storage</label>
	<p />
	<input type="text" list="inputtype" placeholder="How To Input?" />
	<datalist id="inputtype">
		<option>By ScanCode</option>
		<option>By KHPO</option>
	</datalist>
	<input id="enterData" type="text" value=""/>
	
	<p />
	
	<table id="produceTable" style="width:90%;margin:auto;display:none">
		<tr class="table_title">
			<th>XieXing</th>
			<th>SheHao</th>
			<th>Size</th>
			<th>Amount</th>
		</tr>
		<tbody>
		<tr>
			<td>test</td>
			<td>test</td>
			<td>test</td>
			<td>test</td>
		</tr>
		</tbody>
	</table>


</body>
</html>