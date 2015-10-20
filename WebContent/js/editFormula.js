//editFormula 配方修正程式  Ver.1508

$(document).ready(function() {
	
	// 設定確認視窗中的按鈕文字
	alertify.set({
		labels : {
			ok : "Accept",
			cancel : "Deny"
		}
	});

	//Formula頁面取得radio選取值.並送資料到List頁面
	$("input[name='getFormula']").click(function(){
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
					alert("error");
				}
			});
	});
	
	//Insert Button 取得原物料資料
	$("#Insert").click(function(){
		 var cldh = $("#cldh_cldh").val();
		 $("#getcldhz td").parent().remove();/*加上parent()避免出現空白格*/
		$.ajax({
			type : "post",
			url : "EditFormula_getcldh.do?check="
					+ encodeURI(encodeURI(cldh)),
			dataType : "json",
			success : function(cldhlistData) {
				
				//Get cldh,zwpm,cldj,YYSL For 原物料
				$.each(cldhlistData, function(key, value){
					$("#getcldhz").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
							"<td class='td_center'><input id='addcldhz' value='"+value.cldh+"' type='button' style='width:35%'/></td>" +
							"<td class='td_center'><input id='lb"+value.cldh+"' type='text' list='lb' style='width:40%'/><datalist id='lb'><option>W</option><option>X</option><option>Y</option><option>Z</option></datalist></td>" +
							"<td class='td_center'><input id='amounts"+value.cldh+"' type='text' style='width:40%'/></td>" +
							"<td>"+value.zwpm+"</td>" +
							"<td class='td_center'><input id='cldj"+value.cldh+"' type='text' style='width:40%' value='"+value.cldj+"'/></td>" +
							"<td>"+value.YYSL+"</td>" +
									"</tr>");
				});
			
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
		});
		
		$("#getcldhz").show(1000);
	});
	
	//addCldhz Button 新增原物料到配方表
	$("body").on("click", "#addcldhz", function(){//Dynamically add need to "ON"
		//要新增的5個主要資料-lb.amounts.cldj.cldhz.cldh
		var add = [];
		//判斷取值不為NULL
		var ifNULL = 0;
		
		//從按鈕之處Search同樣TR中的text元素資料
		$(this).closest("tr").find(":text").each(function(i){
			 //alert($(this).val());
			if($(this).val() == ""){
				ifNULL += 1;
			}else{
				add[i] = $(this).val();
			}
			
		});
		
		add[3] = $(this).val();//因為上面用each的關係.所以cldh放在這裡加入
		add[4] = $("#cldh_cldh").val();//取得配方代號
		
		//如果ifNULL !=0, don't do anything
		if(ifNULL !=0){
			alertify.alert("Input Can't NULL!!");
		}else{
			
			// Confirm視窗
			alertify.confirm("Sure to INSERT this item ?", function(e) {
				if (e) {
					// OK! 送出Insert資料
					$.ajax({
						type : "post",
						url : "EditFormula_getcldh.do?addcldhz="
								+ encodeURI(encodeURI(add)),
						dataType : "json",
						success : function(cldhlistData) {
							
							//*****資料寫入結束後、將新的資料再一次送入配方明細頁面以便更新、這段程式碼是拷貝(取得radio選取值.並送資料到List頁面)特別注意*****
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
							alert("error");
						}
					});
					
					$("#getcldhz").hide(1000);
					//新增資料成功提示
					alertify.success("Add Data Success");
				} else {
					// cancel!!
				}
			});
		}
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