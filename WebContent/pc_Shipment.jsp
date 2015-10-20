<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!--  避免JSP出現亂碼 -->

<html lang="en">
<head>
<meta charset="UTF-8">
<title>訂單出貨建檔 pc_Shipment Ver1509</title>

<link rel="stylesheet" type="text/css" href="css/table.css" />

<script type="text/javascript" src="js/pc_Shipment.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

</head>
<body>
	<p />
	<label class="label_title">Shinimex Shipment</label>
	<p />
	<input type="text" list="inputtype" placeholder="How To Input?" />
	<datalist id="inputtype">
		<option>By ScanCode</option>
		<option>By KHPO</option>
	</datalist>
	<input id="enterData2" type="text" value="" />
	<input id="checkout" type="button" value="Check Out" style="display:none"/>
	<p />
	
	<table id="shipmentTable" style="width:90%;margin:auto;display:none">
		<tr class="table_title">
			<th>XieXing</th>
			<th>SheHao</th>
			<th>Size</th>
			<th>Amount</th>
		</tr>
		<tbody>
		<tr>
		</tr>
		</tbody>
	</table>
	


</body>
</html>