//ba_MoldControl 模具管理程式  Ver.1605

$(document).ready(
		function() {

			// 設定確認視窗中的按鈕文字
			alertify.set({
				labels : {
					ok : "Accept",
					cancel : "Deny"
				}
			});
			
			
			// Mold Data頁面取得radio選取值.並送資料到Mold List頁面
			$("input[name='getMold']").click(
					function() {
						var mjbh = $("input:radio[name='getMold']:checked")
								.val();
						$("#MJZL_mjbh").val(mjbh);
						
						//Get Mold Detail & Mold Size
						$.ajax({
							type : "post",
							url : "ba_MoldControl_getData.do?mjbh="
									+ encodeURI(encodeURI(mjbh)),
							dataType : "json",
							success : function(SendData) {
								//Get Mold Detail 
								$.each(SendData[0], function(key, value) {
									$("#lbzls_zwsm").val(value.lbdh);
									$("#kfzl_kfjc").val(value.kfjc);
									$("#kfzl_kfjc1").val(value.kfjc1);
									$("#MJZL_gbbh").val(value.gbbh);
									$("#MJZL_bz1").val(value.bz1);
									$("#MJZL_bz2").val(value.bz2);

								})
								//Get Mold Size
								$("#MoldsizeList td").parent().remove();/*加上parent()避免出現空白格*/
								$.each(SendData[1], function(key, value) {
									$("#MoldsizeList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
											"<td class='td_center'><input id='getSize' name='getSize' value='"+value.lbdh+"' type='radio'></td>" +
											"<td class='td_left'>"+value.lbdh+"</td>" +
											"<td>"+value.mjsl+"</td>" +
													"</tr>");
								})
																
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
			
			//MoldsizeList Radio Select
			$("body").on("click", "#MoldsizeList", function(){//Dynamically add need to "ON"
				$("#moldInOut").show(1000);
				
				var select = [];
				select[0] = $("input:radio[name='getMold']:checked").val();//mjbh
				select[1] = $("input:radio[name='getSize']:checked").val();//size
				
				//Get Mold InOut
				$.ajax({
					type : "post",
					url : "ba_MoldControl_sizeInOut.do?select="+ encodeURI(encodeURI(select)),
					dataType : "json",
					success : function(moldlist_InOut) {
						//Get Mold InOut
						$("#moldlist_InOut td").parent().remove();/*加上parent()避免出現空白格*/
						$.each(moldlist_InOut, function(key, value) {
							$("#moldlist_InOut").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
									"<td class='td_center'>"+value.DGLB+"</td>" +
									"<td class='td_center'>"+value.KSRQ+"</td>" +
									"<td class='td_center'></td>" +
									"<td>"+value.SL+"</td>" +
									"<td>"+value.SL1+"</td>" +
									"<td class='td_center'></td>" +
									"<td class='td_center'>"+value.KSDH+"</td>" +
											"</tr>");
						})
					
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
			
			
			// Insert Button - Add Mold InOut
			$("#Insert").click(function(){
				
				$("#insertInOut").show(1000);
			});
			
			
			// Cancel Button
			$("#Cancel").click(function(){
				$("#insertInOut").hide(1000);
				$("#DGLB").val("");
				$("#date_inOut").val("");
				$("#zszl").val("");
				$("#SL").val("");
				$("#BZ").val("");
			});
			
			
			//Get zszllist 廠商資料
			$("#date_inOut").change(
				function(){
					$.ajax({
						type : "post",
						url : "ba_MoldControl_getzszl.do",
						dataType : "json",
						success : function(data) {	
							$("#zszllist option").remove();
							$.each(data, function(key, value){
								$("#zszllist").append(
										"<option value='"+ value +"'>"
										+ key + "</option>"
								);
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
				}
			);
			
			
			// SAVE Button - add Data to KSYD
			$("#saveInOut").click(function(){
				//Confirm
				alertify.confirm("Sure to INSERT this item ?", function(e){
					if(e){
						//OK! Send Insert Data
						
						var select = [];
						select[0] = $("input:radio[name='getMold']:checked").val();//mjbh
						select[1] = $("input:radio[name='getSize']:checked").val();//size
						select[2] = $("#DGLB").val();
						select[3] = $("#date_inOut").val();
						select[4] = $("#zszl").val();
						select[5] = $("#SL").val();
						select[6] = $("#BZ").val();
						
						//Get Mold InOut
						$.ajax({
							type : "post",
							url : "ba_MoldControl_sizeInOut.do?select="+ encodeURI(encodeURI(select)),
							dataType : "json",
							success : function() {
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
						
						$("#insertInOut").hide(1000);
						$("#DGLB").val("");
						$("#date_inOut").val("");
						$("#zszl").val("");
						$("#SL").val("");
						$("#BZ").val("");
						
						//新增資料成功提示
						alertify.success("Add Data Success");
					}else{
						//cancel!!
					}
				});
			});
		

});