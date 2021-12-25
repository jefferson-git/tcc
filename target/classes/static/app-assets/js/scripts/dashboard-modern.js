// Dashboard - Modern
//----------------------

(function (window, document, $) {
  // //menssagem ao abrir a tela
  //setTimeout(function () {
  //  M.toast({ html: "Olá! seja bem vindo." , classes: 'rounded'})
  //}, 2000)

  // card Saldo Atual

  // -----------
  var CurrentBalanceDonutChart = new Chartist.Pie(
    "#current-balance-donut-chart",
    {
      labels: [1, 2],
      series: [
        { meta: "Concluído", value: 80 },//trazer aqui data inicia do mês
        { meta: "Restante", value: 20 }//trazer aqui data final do mês
      ]
    },

    {
      donut: true,
      donutWidth: 8,
      showLabel: false,
      plugins: [
        Chartist.plugins.tooltip({
          class: "current-balance-tooltip",
          appendToBody: true
        }),
        Chartist.plugins.fillDonut({
          items: [
            {
              content:
                '<p class="small">Balanço</p><h5 class="mt-0 mb-0">R$ 10 mil</h5>'
            }
          ]
        })
      ]
    }
  )

  // Total de Transação
  // -----------------
  var TotalTransactionLine = new Chartist.Line(
    "#total-transaction-line-chart",
    {
      series: [[3, 18, 7, 45, 25, 55, 20]]//trazer aqui a venda de segunda a domingo
    },
    {
      chartPadding: 0,
      axisX: {
        showLabel: true,
        showGrid: false
      },
      axisY: {
        showLabel: true,
        showGrid: true,
        low: 0,
        high: 55, //trazer aqui o maior volume de venda do dia pra ajustar o grafico na tela
        scaleMinSpace: 40
      },
      lineSmooth: Chartist.Interpolation.simple({
        divisor: 2
      }),
      plugins: [
        Chartist.plugins.tooltip({
          class: "total-transaction-tooltip",
          appendToBody: true
        })
      ],
      fullWidth: true
    }
  )

  TotalTransactionLine.on("created", function (data) {
    var defs = data.svg.querySelector("defs") || data.svg.elem("defs")
    defs
      .elem("linearGradient", {
        id: "lineLinearStats",
        x1: 0,
        y1: 0,
        x2: 1,
        y2: 0
      })
      .elem("stop", {
        offset: "0%",
        "stop-color": "rgba(255, 82, 249, 0.1)"
      })
      .parent()
      .elem("stop", {
        offset: "10%",
        "stop-color": "rgba(255, 82, 249, 1)"
      })
      .parent()
      .elem("stop", {
        offset: "30%",
        "stop-color": "rgba(255, 82, 249, 1)"
      })
      .parent()
      .elem("stop", {
        offset: "95%",
        "stop-color": "rgba(133, 3, 168, 1)"
      })
      .parent()
      .elem("stop", {
        offset: "100%",
        "stop-color": "rgba(133, 3, 168, 0.1)"
      })

    return defs
  }).on("draw", function (data) {
    var circleRadius = 5
    if (data.type === "point") {
      var circle = new Chartist.Svg("circle", {
        cx: data.x,
        cy: data.y,
        "ct:value": data.value.y,
        r: circleRadius,
        class:
          data.value.y === 35 //trazer aqui quantidade de vendas evitivadas do dia.
            ? "ct-point ct-point-circle"
            : "ct-point ct-point-circle-transperent"
      })
      data.element.replace(circle)
    }
  })

  // Estatísticas do usuário
  var UserStatisticsBarChart = new Chartist.Bar(
    "#user-statistics-bar-chart",
    {
      labels: ["B1", "B2", "B3", "B4", "B5", "B6"],//sigla de Business
      series: [
        [4000, 8000, 12000, 14000, 12000, 18000],// volume de negocios para comparação
        [5000, 10000, 13000, 12000, 11000, 15000]
      ]
    },
    {
      // Default mobile configuration
      stackBars: true,
      chartPadding: 0,
      axisX: {
        showGrid: false
      },
      axisY: {
        showGrid: false,
        labelInterpolationFnc: function (value) {
          return value / 1000 + "k"
        },
        scaleMinSpace: 50
      },
      plugins: [
        Chartist.plugins.tooltip({
          class: "user-statistics-tooltip",
          appendToBody: true
        })
      ]
    },
    [
      // Substituição de opções para mídia > 800px
      [
        "screen and (min-width: 800px)",
        {
          stackBars: false,
          seriesBarDistance: 10
        }
      ],
      // Substituição de opções para mídia > 1000px
      [
        "screen and (min-width: 1000px)",
        {
          reverseData: false,
          horizontalBars: false,
          seriesBarDistance: 15
        }
      ]
    ]
  )

  UserStatisticsBarChart.on("draw", function (data) {
    if (data.type === "bar") {
      data.element.attr({
        style: "stroke-width: 12px",
        x1: data.x1 + 0.001
      })
      data.group.append(
        new Chartist.Svg(
          "circle",
          {
            cx: data.x2,
            cy: data.y2,
            r: 6
          },
          "ct-slice-pie"
        )
      )
    }
  })

  UserStatisticsBarChart.on("created", function (data) {
    var defs = data.svg.querySelector("defs") || data.svg.elem("defs")
    defs
      .elem("linearGradient", {
        id: "barGradient1",
        x1: 0,
        y1: 0,
        x2: 0,
        y2: 1
      })
      .elem("stop", {
        offset: 0,
        "stop-color": "rgba(255,75,172,1)"
      })
      .parent()
      .elem("stop", {
        offset: 1,
        "stop-color": "rgba(255,75,172, 0.6)"
      })

    defs
      .elem("linearGradient", {
        id: "barGradient2",
        x1: 0,
        y1: 0,
        x2: 0,
        y2: 1
      })
      .elem("stop", {
        offset: 0,
        "stop-color": "rgba(129,51,255,1)"
      })
      .parent()
      .elem("stop", {
        offset: 1,
        "stop-color": "rgba(129,51,255, 0.6)"
      })
    return defs
  })

  // Taxa de conversão
  var ConversionRatioBarChart = new Chartist.Bar(
    "#conversion-ration-bar-chart",
    {
      labels: ["Q1"],
      series: [[55000], [35000], [10000]]// entrada dos resultados convergencia das vendas
    },
    {
      stackBars: true,
      chartPadding: {
        top: 0,
        right: 50,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false
      },
      axisY: {
        showGrid: false,
        labelInterpolationFnc: function (value) {
          return value / 1000 + "%"
        }
      },
      plugins: [
        Chartist.plugins.tooltip({
          class: "user-statistics-tooltip",
          appendToBody: true
        })
      ]
    }
  )
  ConversionRatioBarChart.on("draw", function (data) {
    if (data.type === "bar") {
      data.element.attr({
        style: "stroke-width: 40px",
        x1: data.x1 + 0.05
      })
      data.group.append(
        new Chartist.Svg("circle", {
          cx: data.x2,
          cy: data.y2
        })
      )

    }
  })

  ConversionRatioBarChart.on("created", function (data) {
    var defs = data.svg.querySelector("defs") || data.svg.elem("defs")
    defs
      .elem("linearGradient", {
        id: "barGradient1",
        x1: 0,
        y1: 0,
        x2: 0,
        y2: 1
      })
      .elem("stop", {
        offset: 0,
        "stop-color": "rgba(129,51,255,1)"
      })
      .parent()
      .elem("stop", {
        offset: 1,
        "stop-color": "rgba(129,51,255, 0.6)"
      })

    defs
      .elem("linearGradient", {
        id: "barGradient2",
        x1: 0,
        y1: 0,
        x2: 0,
        y2: 1
      })
      .elem("stop", {
        offset: 0,
        "stop-color": "rgba(255,75,172,1)"
      })
      .parent()
      .elem("stop", {
        offset: 1,
        "stop-color": "rgba(255,75,172, 0.6)"
      })
    return defs
  })

  //Gráfico de receita do mês

  // Options
  var SLOption = {
    responsive: true,
    maintainAspectRatio: true,
    datasetStrokeWidth: 3,
    pointDotStrokeWidth: 4,
    tooltipFillColor: "rgba(0,0,0,0.6)",
    legend: {
      display: false,
      position: "bottom"
    },
    hover: {
      mode: "label"
    },
    scales: {
      xAxes: [
        {
          display: false
        }
      ],
      yAxes: [
        {
          display: false
        }
      ]
    },
    title: {
      display: false,
      fontColor: "#FFF",
      fullWidth: false,
      fontSize: 40,
      text: "82%"
    }
  }
  var SLlabels = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "julho", "agosto", "setembro"]

  var LineSL3ctx = document
    .getElementById("custom-line-chart-sample-three")
    .getContext("2d")

  var gradientStroke = LineSL3ctx.createLinearGradient(500, 0, 0, 200)
  gradientStroke.addColorStop(0, "#8133ff")
  gradientStroke.addColorStop(1, "#ff4bac")

  var gradientFill = LineSL3ctx.createLinearGradient(500, 0, 0, 200)
  gradientFill.addColorStop(0, "#8133ff")
  gradientFill.addColorStop(1, "#ff4bac")

  var SL3Chart = new Chart(LineSL3ctx, {
    type: "line",
    data: {
      labels: SLlabels,
      datasets: [
        {
          label: "Meu segundo conjunto de dados",
          borderColor: gradientStroke,
          pointColor: "#fff",
          pointBorderColor: gradientStroke,
          pointBackgroundColor: "#fff",
          pointHoverBackgroundColor: gradientStroke,
          pointHoverBorderColor: gradientStroke,
          pointRadius: 4,
          pointBorderWidth: 1,
          pointHoverRadius: 4,
          pointHoverBorderWidth: 1,
          fill: true,
          backgroundColor: gradientFill,
          borderWidth: 1,
          data: [4, 8, 20, 30, 20, 33, 55, 35, 49]
        }
      ]
    },
    options: SLOption
  })
  // gráficos atualizados no colapso do sidenav
  $('.logo-wrapper .navbar-toggler').on('click', function () {
    setTimeout(function () {
      CurrentBalanceDonutChart.update();
      TotalTransactionLine.update();
      UserStatisticsBarChart.update();
      ConversionRatioBarChart.update();
    }, 200);
  })
})(window, document, jQuery)