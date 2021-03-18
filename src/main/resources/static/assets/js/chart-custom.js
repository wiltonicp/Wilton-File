(function(jQuery) {

    "use strict";

// for apexchart
function apexChartUpdate(chart, detail) {
    let color = getComputedStyle(document.documentElement).getPropertyValue('--dark');
    if (detail.dark) {
      color = getComputedStyle(document.documentElement).getPropertyValue('--white');
    }
    chart.updateOptions({
      chart: {
        foreColor: color
      }
    })
  }
  
// for am chart
function amChartUpdate(chart, detail) {
  // let color = getComputedStyle(document.documentElement).getPropertyValue('--dark');
  if (detail.dark) {
    // color = getComputedStyle(document.documentElement).getPropertyValue('--white');
    chart.stroke = am4core.color(getComputedStyle(document.documentElement).getPropertyValue('--white'));
  }
  chart.validateData();
}


/*-----------------------------------
Layout 1 Charts
-------------------------------------*/
jQuery("#layout-1-chart").length && am4core.ready(function() {

	am4core.useTheme(am4themes_animated);
// Themes end

// create chart
var chart = am4core.create("layout-1-chart", am4charts.GaugeChart);
chart.innerRadius = am4core.percent(82);

/**
 * Normal axis
 */



/**
 * Axis for ranges
 */

var colorSet = new am4core.ColorSet();

var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
axis2.min = 0;
axis2.max = 100;
axis2.strictMinMax = true;
axis2.renderer.labels.template.disabled = true;
axis2.renderer.ticks.template.disabled = true;
axis2.renderer.grid.template.disabled = true;

var range0 = axis2.axisRanges.create();
range0.value = 0;
range0.endValue = 50;
range0.axisFill.fillOpacity = 1;
range0.axisFill.fill = colorSet.getIndex(0);

range0.axisFill.fill = am4core.color("#8f93f6");

var range1 = axis2.axisRanges.create();
range1.value = 50;
range1.endValue = 100;
range1.axisFill.fillOpacity = 1;
range1.axisFill.fill = colorSet.getIndex(2);

  range1.axisFill.fill = am4core.color("#FAFAFA");

/**
 * Label
 */

var label = chart.radarContainer.createChild(am4core.Label);
label.isMeasured = false;
label.fontSize = 0;
label.x = am4core.percent(50);
label.y = am4core.percent(100);
label.horizontalCenter = "middle";
label.verticalCenter = "bottom";
label.text = "50%";


/**
 * Hand
 */

var hand = chart.hands.push(new am4charts.ClockHand());
hand.axis = axis2;
hand.innerRadius = am4core.percent(20);
hand.startWidth = 10;
hand.pin.disabled = true;
hand.value = 50;

hand.events.on("propertychanged", function(ev) {
  range0.endValue = ev.target.value;
  range1.value = ev.target.value;
  label.text = axis2.positionToValue(hand.currentPosition).toFixed(1);
  axis2.invalidate();
});

setInterval(function() {
  var value = Math.round(Math.random() * 100);
  var animation = new am4core.Animation(hand, {
    property: "value",
    to: value
  }, 1000, am4core.ease.cubicOut).start();
}, 2000);

const body = document.querySelector('body')
if (body.classList.contains('dark')) {
  amChartUpdate(chart, {
    dark: true
  })
}

document.addEventListener('ChangeColorMode', function (e) {
  amChartUpdate(chart, e.detail)
})

}); // end am4core.ready()

if (jQuery("#layout-1-chart2").length) {
  var options = {
    series: [{
      name: "Desktops",
      data: [10, 35, 25, 60, 59, 25, 55, 20, 35]
  }],
  colors: ["#8f93f6"],
    chart: {
      toolbar: {
        show: false
      },
    height: 225,
    type: 'line',
    zoom: {
      enabled: false
    }
  },
  dataLabels: {
    enabled: false
  },
  stroke: {
    curve: 'straight'
  },
  title: {
    text: '',
    align: 'left'
  },
  grid: {
    row: {
      colors: ['#f3f3f3', 'transparent'], 
      opacity: 0.5
    },
  },
  xaxis: {
    categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
    labels: {
      minHeight: 20,
      maxHeight: 20
    }
  },
  yaxis: {
      show: true,
      labels: {
        minWidth: 15,
        maxWidth: 15
      }
    }
  };
  if(typeof ApexCharts !== typeof undefined){
        var chart = new ApexCharts(document.querySelector("#layout-1-chart2"), options);
        chart.render();
          const body = document.querySelector('body')
          if (body.classList.contains('dark')) {
            apexChartUpdate(chart, {
              dark: true
            })
          }
      
          document.addEventListener('ChangeColorMode', function (e) {
            apexChartUpdate(chart, e.detail)
          })
        }  
}

/*---------------------------------------------------------------------
   Profile Image Edit
-----------------------------------------------------------------------*/

$(document).ready(function() {


  var readURL = function(input) {
      if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function (e) {
              $('.profile-pic').attr('src', e.target.result);
          }

          reader.readAsDataURL(input.files[0]);
      }
  }


  $(".file-upload").on('change', function(){
      readURL(this);
  });

  $(".upload-button").on('click', function() {
     $(".file-upload").click();
  });
});


})(jQuery);

