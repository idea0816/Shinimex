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
				alert("test")
				/*
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
							});
							
							$("#getcldhz").hide(1000);
							//新增資料成功提示
							alertify.success("Add Data Success");
						} else {
							// cancel!!
						}
					});
				}*/
				
			});

		});