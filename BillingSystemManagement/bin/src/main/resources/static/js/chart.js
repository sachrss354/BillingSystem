  const ctx = document.getElementById('myChart').getContext("2d");
  var myChart = new Chart(ctx,{
	type:"bar",
	data:{
		labels:["Java","Python","Javascript","PHP","C#","C++"],
		datasets:[
			{
				data:[13,15,5,10,9,10],
				label:"programming langage",
				backgroundColor:["red","blue","pink","green","gold","lime"],
			},
		],
	},
	options:{
		plugins:{
			color:"lightGreen",
		},
		
		responsive:false,
		layout:{
			padding:{
				paddingLeft:4,
			},
		},
		tooltips :{
			 showTooltips: false,
			enabled:false,
			backgroundColor:"blue",
			titleFontSize:30,
		},
		title:{
			display :false,
			text:"custom chart Title",
		},
	},
  });

