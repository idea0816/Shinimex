$(document).ready(function() {

	// $("table tr").click(function(){
	// alert(this.rowIndex);
	// });

//	$("table tr").click(function() {
//		$(this).css("background-color", "lightblue")
//	});
	
	
	
	//Formual頁面取得radio選取值.並送資料到List頁面
	$("input[name='getFormula']").click(function(){
//		 alert($("input:radio[name='getFormula']:checked").val());
		 var cldh = $("input:radio[name='getFormula']:checked").val();
		 $("#cldh_cldh").val(cldh);
			$.ajax({
				type : "post",
				url : "EditFormula_getcldh.do?cldh="
						+ encodeURI(encodeURI(cldh)),
				dataType : "json",
				success : function(cldhlistData) {
					
					$("#Formula_getcldh td").parent().remove();/*加上parent()避免出現空白格*/
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
//					$("#Formula_getcldh").append('<tr><td>column 1 value</td><td>column 2 value</td></tr>');
					alert("error");
				}
			});
	});
	
	//Delete Button
	$("#Delete").click(function(){
		if($("#Delete").val() == "Delete"){
			alert($("#Delete").val());
			$("#Delete").val("Confirm");
			$("#deleteCol").show(1000);
			$("#deleteCB").show(1000);
		}else{
			$("#deleteCol").hide(1000);
			$("#deleteCB").hide(1000);
			$("#Delete").val("Delete");
		}
	});
//	$("#Confirm").click(function(){
//		alert($("#Confirm").val());
//		
//	});
	
});