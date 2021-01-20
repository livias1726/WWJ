google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawAverageChart);
google.charts.setOnLoadCallback(drawPopularityChart);
google.charts.setOnLoadCallback(drawCompetitorsChart);

function drawPopularityChart() {
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'X');
	data.addColumn('number', 'Popularity');
	
	var pop = document.getElementById("pop_list");  
	var str = pop.getAttribute("value");
	var tok = str.split("$$");
	
	data.addRows([
	    ["2016", parseInt(tok[0])],
		["2017", parseInt(tok[1])],  
		["2018", parseInt(tok[2])],  
		["2019", parseInt(tok[3])],  
		["2020", parseInt(tok[4])]
	]);
	
	var options = {
	    hAxis: {
	      title: 'Years'
	    },
	    vAxis: {
	      title: 'Popularity (%)'
	    },
	    backgroundColor: '#f1f8e9'
	};
	
	var chart = new google.visualization.LineChart(document.getElementById('chart_pop'));
	chart.draw(data, options);
}

function drawCompetitorsChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'X');
    data.addColumn('number', 'Competitors');

	var comp = document.getElementById("comp_list");  
	var str = comp.getAttribute("value");
	var tok = str.split("$$");
	
    data.addRows([
        ["2016", parseInt(tok[0])],
		["2017", parseInt(tok[1])],  
		["2018", parseInt(tok[2])],  
		["2019", parseInt(tok[3])],  
		["2020", parseInt(tok[4])]
    ]);

    var options = {
        hAxis: {
          title: 'Years'
        },
        vAxis: {
          title: 'Competitors'
        },
        backgroundColor: '#f1f8e9'
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_comp'));
    chart.draw(data, options);
}

function drawAverageChart() {

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'X');
    data.addColumn('number', 'Amount');
	
	var avg = document.getElementById("avg_list");  
	var str = avg.getAttribute("value");
	var tok = str.split("$$");
	
    data.addRows([
        [{v: 'Average earnings'}, parseInt(tok[0])],
        [{v: 'Average cost'}, parseInt(tok[1])]
    ]);

      var options = {
        vAxis: {
          title: tok[3]
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_avg'));

      chart.draw(data, options);
}