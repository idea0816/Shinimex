<!DOCTYPE html>
<!-- <%@page contentType="text/html; charset=UTF-8" %> 避免JSP出現亂碼 -->
<html lang="en">
<head>
<meta charset="UTF-8">
<title>日期檢查 orderDateCheck Ver1508</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/editFormulaTab.css" />

<script type="text/javascript" src="js/editFormulaTab.js"></script>
<!-- 
<script type="text/javascript" src="js/orderDateCheck.js"></script>
-->
</head>
<body>

	This is editFormula!!!
	<br />

	<ul class="tabs">
		<li><a href="#tab1">Formula Page</a></li>
		<li><a href="#tab2">List</a></li>
		<li><a href="#tab3">Other</a></li>
	</ul>

	<div class="clr"></div>

	<section class="block">
		<article id="tab1">
			<table>
				<tr class="table_title">
					<th>材料代號</th>
					<th>中文品名</th>
					<th>Description</th>
					<th>Price</th>
					<th></th>
				</tr>
			</table>
		</article>
		<article id="tab2">
			<p>Sed egestas, ante et vulputate volutpat, eros pede semper est,
				vitae luctus metus libero eu augue. Morbi purus libero, faucibus
				adipiscing, commodo quis, gravida id, est. Sed lectus. Praesent
				elementum hendrerit tortor. Sed semper lorem at felis. Vestibulum
				volutpat, lacus a ultrices sagittis, mi neque euismod dui, eu
				pulvinar nunc sapien ornare nisl. Phasellus pede arcu, dapibus eu,
				fermentum et, dapibus sed, urna.</p>
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