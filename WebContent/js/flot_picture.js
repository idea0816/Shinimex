$(document).ready(function() {
	
	
	
	
	var data = [[1, 130], [2, 40], [3, 80], [4, 160], [5, 450], [6, 550], [7, 330] ];
	var dataset = [{label:"st", data: data}];
	var options = {
			series:{
				lines:{show:true}, 
				points:{
					radius:3, 
					fill:true,
					show:true
				},
				grid:{
					hoverable:true,
					borderWidth:2,
					borderColor:"#633200",
					backgroundColor:{colors:["#ffffff", "#EDF5FF"]}
				}
			}
	};
	$.plot($("#flot-placeholder"), dataset, options);
	
	
});