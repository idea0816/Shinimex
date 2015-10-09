<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%><!--  避免JSP出現亂碼 -->

<html lang="en">
<head>
<meta charset="UTF-8">
<title>業務各項資料查詢列表 sales_Datalist Ver1510</title>


<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="css/forTab.css" />

<script type="text/javascript" src="js/forTab.js"></script>
<!--  
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
-->

</head>
<body>
	<br />

	<ul class="tabs">
		<li><a href="#tab1">品牌分析</a></li>
		<li><a href="#tab2">客戶分析</a></li>
		<li><a href="#tab3">型體分析</a></li>
	</ul>

	<div class="clr"></div>

	<section class="block">
		<article id="tab1">
			<br />
			<table style="width:50%">
				<tr class="table_title">
					<th>品牌</th>
					<th>訂單數量</th>
					<th>金額</th>
				</tr>


			</table>
		</article>
		<article id="tab2">
			<br />
			<table style="width:50%">
				<tr class="table_title">
					<th>客戶名稱</th>
					<th>訂單數量</th>
					<!--  
					<th>金額</th>
					-->
				</tr>
				<tr>
					<td>中菲VSV</td>
					<td>2,471,013</td>
				</tr>
					<!--  
adidas樂億	2296179
adidas億春	1970194
億雄	1871805
adidas 樂億二	1639105
中菲VRF	1501229
億誠	1479990
慧春-平陽廠	1363372
慧春-西寧廠	1114283
寶元(VN)	523852
慧春柬埔寨	450781
新富 DUC THANH	339455
鞋美(EVC)	336060
JX(佳新）	300333
鞋美(EVM)	183435
DELTA	171188
GIA DINH宏凱	142776
劭駿	104600
ART-阿根廷	94250
中國鑽石-CDV	88090
翔鑫堡(東興）	86173
OSCO 歐集	80814
VAGABOND	80525
SUNG HYUN VINA	76095
志雄柬埔寨	72850
中菲-DAI LOC	71375
吉毅 THUONG THANG	69778
越立	63904
安鼎	55869
印尼	53147
盟藝	46939
越南鐕石	45417
宏福	42477
建溢	41457
印度	38745
GARSPORT S.R.L	37441
春日	33820
UNISOL S.A(阿根廷)	21600
勝百吉	20862
SAMIL TONGSANG	19620
BRASIL-巴西	17360
東興	13020
Sumber Citra Persada	11208
麒勝(VN）	7935
毅豐	7584
信國	5327
台灣新北海	4086
麒勝-印尼	3705
EIZO	3632
怡昌－凱暘	2520
嘉名	1605
HWA SEUNG VINA	1300
MOLAND CO.,LTD	1000
PT.CIPTA KARYA BUANA	909
福群	600
南鑫	360
印尼寶成	244
麥斯 TU MACH	97
YJ(義哲）	84

-->
			</table>
		</article>
		<article id="tab3">
			<br />
			<table style="width:50%">
				<tr class="table_title">
					<th>型體名稱</th>
					<th>訂單數量</th>
					<th>金額</th>
				</tr>
			</table>
		</article>
	</section>



</body>
</html>