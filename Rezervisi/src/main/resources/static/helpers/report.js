const monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
];

function Report(urlApis, dataFields) {
  this.urlApis = urlApis;
  this.dataFields = dataFields;
}


Report.prototype.init = function (type) {
  this.type = type;
  this.initEntityRateReport();
  this.initSubEntityRateReport();
  this.initReport('daily');
  this.initReport('week');
  this.initReport('month');
  $(document).on('submit', "#reportForm", this.submitReportDates.bind(this));
}

Report.prototype.initEntityRateReport = function () {
  ajaxService.GET(this.urlApis[this.type].get, this.showEntityRateCallback.bind(this));
}

Report.prototype.showEntityRateCallback = function (data) {
  $("#entitygrade").html(this.type + " average grade: " + data.averageGrade);
}

Report.prototype.initSubEntityRateReport = function () {
  var source = {
    datatype: "json",
    datafields: this.dataFields[this.type]['rate'],
    url: this.urlApis[this.type].show,
    type: 'GET',
    async: true
  };
  var dataAdapter = new $.jqx.dataAdapter(source, { async: false, autoBind: true, loadError: function (xhr, status, error) { alert('Error loading "' + source.url + '" : ' + error); } });
  var settings = {
    title: "Average grades",
    description: "",
    enableAnimations: true,
    showLegend: true,
    padding: { left: 5, top: 5, right: 5, bottom: 5 },
    titlePadding: { left: 90, top: 0, right: 0, bottom: 10 },
    source: dataAdapter,
    xAxis:
    {
      dataField: this.dataFields[this.type].xAxis,
      showGridLines: true
    },
    colorScheme: 'scheme01',
    seriesGroups:
      [
        {
          type: 'column',
          columnsGapPercent: 50,
          seriesGapPercent: 0,
          valueAxis:
          {
            unitInterval: 10,
            minValue: 0,
            maxValue: 10,
            displayValueAxis: true,
            description: 'Grade',
            axisSize: 'auto',
            tickMarksColor: '#888888'
          },
          series: [
            { dataField: this.dataFields[this.type].yAxis, displayText: this.dataFields[this.type].xAxis.substring(2) + ' Average Grade' }
          ]
        }
      ]
  };

  $('#jqxChartRates').jqxChart(settings);
}

Report.prototype.initReport = function (time) {
  var source = {
    datatype: "json",
    datafields: this.dataFields[this.type][time],
    url: this.urlApis[this.type][time],
    type: 'GET',
    async: true
  };
  var dataAdapter = new $.jqx.dataAdapter(source, { async: false, autoBind: true, loadError: function (xhr, status, error) { alert('Error loading "' + source.url + '" : ' + error); } });
  var settings = {
    title: this.getReportTitle(time),
    description: this.getReportDescription(time),
    enableAnimations: true,
    showLegend: true,
    padding: { left: 5, top: 5, right: 5, bottom: 5 },
    titlePadding: { left: 90, top: 0, right: 0, bottom: 10 },
    source: dataAdapter,
    xAxis:
    {
      dataField: 'created',
      formatFunction: function (value) {
        switch (time) {
          case 'daily':
            return new Date(value).toDateString();
          case 'week':
            console.log(value);
            return value + ". week";
          case 'month':
            return monthNames[new Date(value).getMonth()] + " " + (1900 + new Date(value).getYear());
        }
        return value.getDate();
      },
      type: 'int',
      showGridLines: true
    },
    colorScheme: 'scheme01',
    seriesGroups:
      [
        {
          type: 'column',
          columnsGapPercent: 50,
          seriesGapPercent: 0,
          valueAxis:
          {
            unitInterval: 1000,
            minValue: 0,
            maxValue: 10000,
            displayValueAxis: true,
            description: 'Sales',
            axisSize: 'auto',
            tickMarksColor: '#888888'
          },
          series: [
            { dataField: 'totalPrice', displayText: 'Total Price' }
          ]
        }
      ]
  };
  switch (time) {
    case 'daily':
      $('#jqxChartDaily').jqxChart(settings);
      break;
    case 'week':
      $('#jqxChartWeekly').jqxChart(settings);
      break;
    case 'month':
      $('#jqxChartMonthly').jqxChart(settings);
      break;
  }
}

Report.prototype.getReportTitle = function (time) {
  switch (time) {
    case 'daily':
      return 'Daily sales report';
    case 'week':
      return 'Weekly sales report';
    case 'month':
      return 'Monthly sales report';
  }
}

Report.prototype.submitReportDates = function (e) {
  e.preventDefault();
  var obj = JSON.stringify({
    startDate: Date.parse($('#startDate').val()),
    endDate: Date.parse($('#endDate').val())
  });
  ajaxService.POST(this.urlApis[this.type].submit, obj, this.showDatesReportCallback.bind(this));
}

Report.prototype.showDatesReportCallback = function (data) {
  $("#entitysales").html("Airline sales for this period: " + data);
}

Report.prototype.getReportDescription = function (time) {
  switch (time) {
    case 'daily':
      return 'Sales report grouped by day';
    case 'week':
      return 'Sales report grouped by week';
    case 'month':
      return 'Sales report grouped by month';
  }
}

var report = new Report({
  'Hotels': {
    'get': '/app/hotels/getHotel/' + getUserServiceId(),
    'show': '/app/hotels/' + getUserServiceId() + '/showAllRooms',
    'daily': '/app/report/' + getUserServiceId() + '/dailyRooms',
    'week': '/app/report/' + getUserServiceId() + '/weeklyRooms',
    'month': '/app/report/' + getUserServiceId() + '/monthlyRooms',
    'submit': '/app/report/' + getUserServiceId() + '/roomDates'
  },
  'Rentacar': {
    'get': '/app/rentacar/getRentacar/' + getUserServiceId(),
    'show': '/app/rentacar/' + getUserServiceId() + '/showAllVehicles',
    'daily': '/app/report/' + getUserServiceId() + '/dailyVehicle',
    'week': '/app/report/' + getUserServiceId() + '/weeklyVehicle',
    'month': '/app/report/' + getUserServiceId() + '/monthlyVehicle',
    'submit': '/app/report/' + getUserServiceId() + '/vehicleDates'
  },
  'Airlines': {
    'get': '/app/airlines/getAirline/' + getUserServiceId(),
    'show': '/app/airlines/' + getUserServiceId() + '/showAllFlights',
    'daily': '/app/report/' + getUserServiceId() + '/dailyTicket',
    'week': '/app/report/' + getUserServiceId() + '/weeklyTicket',
    'month': '/app/report/' + getUserServiceId() + '/monthlyTicket',
    'submit': '/app/report/' + getUserServiceId() + '/ticketDates'
  }
}, {
    'Airlines': {
      'xAxis': 'idFlight',
      'yAxis': 'flightAverageGrade',
      'rate': [{ name: 'idFlight', type: 'int' }, { name: 'flightAverageGrade', type: 'float' }],
      'daily': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }],
      'week': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }],
      'month': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }]
    },
    'Hotels': {
      'xAxis': 'roomID',
      'yAxis': 'averageGrade',
      'rate': [{ name: 'roomID', type: 'int' }, { name: 'averageGrade', type: 'float' }],
      'daily': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }],
      'week': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }],
      'month': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }]
    },
    'Rentacar': {
      'xAxis': 'idVehicle',
      'yAxis': 'averageGrade',
      'rate': [{ name: 'idVehicle', type: 'int' }, { name: 'averageGrade', type: 'float' }],
      'daily': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }],
      'week': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }],
      'month': [{ name: 'created', type: 'date' }, { name: 'totalPrice', type: 'float' }]
    }
  });