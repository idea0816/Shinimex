//訂單年度直條圖_Ver.1510 

//SELECT DATENAME(MONTH, DDRQ),SUM(Pairs) 
//FROM DDZL WHERE DATEPART(YEAR, DDRQ) = '2015' 
//GROUP BY DATENAME(MONTH, DDRQ),DATEPART(MONTH, DDRQ) 
//ORDER BY DATEPART(MONTH, DDRQ)

$(document)
		.ready(
				function() {

					// ******* 2015 Order Amount - BAR CHART
					var data = [
					            [ 0, 2540505 ], 
					            [ 1, 2377896 ], 
					            [ 2, 2475850 ],
					            [ 3, 1286180 ], 
					            [ 4, 2156102 ], 
					            [ 5, 737459 ],
					            [ 6, 2096865 ],
					            [ 7, 3209075 ],
					            [ 8, 2235451 ],
					            [ 9, 462764 ],
					            [ 10, 0 ],
					            [ 11, 0 ],

					];

					var dataset = [ {
						label : "2015 Order Amount",
						data : data,
						color : "#5482FF"
					} ];

					var ticks = [ 
					              [ 0, "Jan" ], 
					              [ 1, "Feb" ],
					              [ 2, "Mar" ], 
					              [ 3, "Apr" ],
					              [ 4, "May" ], 
					              [ 5, "Jun" ], 
					              [ 6, "Jul" ],
					              [ 7, "Aug" ],
					              [ 8, "Sep" ],
					              [ 9, "Oct" ],
					              [ 10, "Nov" ],
					              [ 11, "Dec" ]
					             ];

					var options = {
						series : {
							bars : {
								show : true
							}
						},
						bars : {
							align : "center",
							barWidth : 0.4
						},
						xaxis : {
							axisLabel : "Months",
							axisLabelUseCanvas : true,
//							axisLabelFontSizePixels : 12,
//							axisLabelFontFamily : 'Verdana, Arial',
//							axisLabelPadding : 10,
							ticks : ticks

						},
						yaxis : {
							axisLabel : "Amounts",
							axisLabelUseCanvas : true,
//							axisLabelFontSizePixels : 12,
//							axisLabelFontFamily : 'Verdana, Arial',
//							axisLabelPadding : 3,
//							tickFormatter : function(v, axis) {
//								return v + "°C";
//							}
						},
						legend : {
							noColumns : 0,
							labelBoxBorderColor : "#000000",
							position : "nw"
						},
						grid : {
							hoverable : true,
							borderWidth : 2,
							backgroundColor : {
								colors : [ "#ffffff", "#EDF5FF" ]
							}
						}
					};

					$(document).ready(function() {
						$.plot($("#orderData_yearpicture"), dataset, options);
						$("#orderData_yearpicture").UseTooltip();
					});

//					function gd(year, month, day) {
//						return new Date(year, month, day).getTime();
//					}

					var previousPoint = null, previousLabel = null;

					$.fn.UseTooltip = function() {
						$(this)
								.bind(
										"plothover",
										function(event, pos, item) {
											if (item) {
												if ((previousLabel != item.series.label)
														|| (previousPoint != item.dataIndex)) {
													previousPoint = item.dataIndex;
													previousLabel = item.series.label;
													$("#tooltip").remove();

													var x = item.datapoint[0];
													var y = item.datapoint[1];

													var color = item.series.color;

													// console.log(item.series.xaxis.ticks[x].label);

													showTooltip(
															item.pageX,
															item.pageY,
															color,
															"<strong>"
																	+ item.series.label
																	+ "</strong><br>"
																	+ item.series.xaxis.ticks[x].label
																	+ " : <strong>"
																	+ y
																	+ "</strong> ");
												}
											} else {
												$("#tooltip").remove();
												previousPoint = null;
											}
										});
					};

					function showTooltip(x, y, color, contents) {
						$('<div id="tooltip">' + contents + '</div>')
								.css(
										{
											position : 'absolute',
											display : 'none',
											top : y - 40,
											left : x - 120,
											border : '2px solid ' + color,
											padding : '3px',
											'font-size' : '9px',
											'border-radius' : '5px',
											'background-color' : '#fff',
											'font-family' : 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
											opacity : 0.9
										}).appendTo("body").fadeIn(200);
					}

					/*
					 * //Order Values var data = [ [gd(2015, 0, 1), 2540505],
					 * [gd(2015, 1, 1), 2377896], [gd(2015, 2, 1), 2475850],
					 * [gd(2015, 3, 1), 1286180], [gd(2015, 4, 1), 2156102],
					 * [gd(2015, 5, 1), 737459], [gd(2015, 6, 1), 2096559],
					 * [gd(2015, 7, 1), 3211788], [gd(2015, 8, 1), 503712],
					 * [gd(2015, 9, 1), 0], [gd(2015, 10, 1), 0], [gd(2015, 11,
					 * 1), 0], ];
					 * 
					 * 
					 * $.plot($("#orderData_yearpicture"), [data], { series: {
					 * bars: { show: true, barWidth:0.6 } } });
					 *  /* var dataset = [ { label:"訂單資料", data: data } ]; var
					 * options = {
					 * 
					 * series:{ bars:{ show:true, barWidth:0.6 } }, bars:{
					 * show:true, barWidth:2 }, //X軸設定 xaxis:{ mode:"time",
					 * tickSize:[1, "month"] } }
					 * 
					 * 
					 * //show picture $.plot($("#orderData_yearpicture"),
					 * dataset, options);
					 * 
					 * //GetDate Function function gd(year, month, day){ return
					 * new Date(year, month, day).getTime(); }
					 */
				});