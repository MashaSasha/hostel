
<#import "../general_blocks/common.ftl" as c>
<@c.page>

<br>
<div class="text-center">
    <h1>
        Статисика прибыли отеля и использования промокодов
    </h1>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            общая прибыль за прошлый месяц:<br>
            Колличество броней за предыдущий месяц:
        </div>
    </div>
</div>
<br><br>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">

            <form id="statistic">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="inputState">Год</label>
                        <select id="inputState" class="form-control" name="year">
                            <option selected>2018</option>
                            <option>2019</option>
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputState">Тип отчета</label>
                        <select id="inputState" class="form-control" name="type">
                            <option selected value="promo">Используются промокодов в месяц</option>
                            <option value="profit">Прибыль в месяц для каждого типа номеров</option>
                        </select>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Построить</button>
            </form>

            <!-- График -->
            <div id="chart">

            </div>




        </div>
    </div>
</div>
</@c.page>
<link type="text/css" rel="StyleSheet" href="http://bootstraptema.ru/plugins/2016/shieldui/style.css" />
<script src="http://bootstraptema.ru/plugins/2016/shieldui/script.js"></script>

<script>
    $('#statistic').submit(function (event) {
        let dataToSend = $(this).serialize();

        let request = $.ajax({
            url: '/admin/draw/statistic',
            type: 'GET',
            data: dataToSend,
            dataType: 'json'
        });

        request.done(function (serverData) {
            console.log(serverData);
            if (serverData.status === 'SUCCESS') {
                $(function () {
                    $("#chart").shieldChart({
                        theme: "light",
                        seriesSettings: {
                            line: {
                                dataPointText: {
                                    enabled: true
                                }
                            }
                        },
                        chartLegend: {
                            align: 'center',
                            borderRadius: 2,
                            borderWidth: 2,
                            verticalAlign: 'top'
                        },
                        exportOptions: {
                            image: true,
                            print: true
                        },
                        axisX: {
                            categoricalValues: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
                        },
                        axisY: {
                            title: {
                                text: serverData.titleY
                            }
                        },
                        primaryHeader: {
                            text: serverData.header
                        },
                        dataSeries: serverData.dataSeries
                    });
                });
            } else if (data.status === 'WARNING') {

            } else {
                alert(data.message);
            }
        });
        event.preventDefault();
    });
</script>