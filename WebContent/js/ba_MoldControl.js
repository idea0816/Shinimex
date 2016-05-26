//ba_MoldControl 模具管理程式  Ver.1605

$(document).ready(function() {
	
	// 設定確認視窗中的按鈕文字
	alertify.set({
		labels : {
			ok : "Accept",
			cancel : "Deny"
		}
	});
	
	//Mold Data頁面取得radio選取值.並送資料到Mold List頁面
	$("input[name='getMold']").click(function(){
		 var mjbh = $("input:radio[name='getMold']:checked").val();
		 alert(mjbh);
		 /*
		 $("#cldh_cldh").val(cldh);
			$.ajax({
				type : "post",
				url : "EditFormula_getcldh.do?cldh="
						+ encodeURI(encodeURI(cldh)),
				dataType : "json",
				success : function(cldhlistData) {
					
					$("#Formula_getcldh td").parent().remove();//加上parent()避免出現空白格
					var sum_amount = 0;//總金額
					var sum_clyl = 0;//總重量
					var sum_z_clyl = 0;//類別Z總重量
					$.each(cldhlistData, function(key, value){
						
						sum_clyl += value.clyl;
						sum_amount += value.amount;
						if(value.lb == "Z"){ sum_z_clyl += value.clyl}
						
						$("#Formula_getcldh").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
								"<td class='td_center'>"+value.lb+"</td>" +
								"<td>"+value.cldhz+"</td>" +
								"<td>"+value.zwpm+"</td>" +
								"<td>"+value.phr+"</td>" +
								"<td>"+value.clyl+"</td>" +
								"<td>"+value.listcldj+"</td>" +
								"<td>"+value.amount+"</td>" +
								"<td class='td_center'><input id='deleteCB' type='checkbox' style=''></td>" +
										"</tr>");
					});
					
					if(sum_clyl == 0 || sum_amount == 0){
						$("#cldh_cldj").val(0);
						$("#cldh_TotKgs").val(0);
					}else{
						$("#cldh_cldj").val(Math.round((sum_amount/sum_clyl)*10000)/10000);//小數點後4位四捨五入簡易設定
						$("#cldh_TotKgs").val(Math.round((sum_clyl-sum_z_clyl)*100)/100);//小數點後2位四捨五入簡易設定
					}
					
					
				},
				beforeSend : function() {
					$.blockUI();
				},
				complete : function() {
					$.unblockUI();
				},
				error : function() {
					alert("error");
				}
			});*/
	});
});