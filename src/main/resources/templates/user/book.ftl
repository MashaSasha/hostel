<#import "../general_blocks/common.ftl" as c>

<@c.page>
<body class="bg-light">

<style type="text/css">
    .row1 {
        display: flex;
        flex-wrap: wrap;
    }
    .cursor-pointer {
        cursor: pointer;
    }
    .chosen {
        background-color: #D5F8CE;
    }
</style>

<div hidden id="_csrf" name="${_csrf.token}"></div>
<div hidden id="sales" data-days-before="${hotel.daysCountToDiscount}" data-sale-days-before="${hotel.discountOnDays}"></div>

<div class="container-fluid">
    <div class="text-center">
        <h2>Форма бронирования</h2>
        <p class="lead">Вы можете подобрать себе номера исходя из своих финансов и потребностей.</p>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-7">
            <h4 class="mb-3">Подбор номера</h4>
            <form class="needs-validation" id="filterForm" novalidate>
                <div class="row">
                    <div class="col-md-2">
                        <label for="personCount">Человек в номере</label>
                        <input type="number" min="0" max="10" class="form-control" value="1" id="personCount" name="peopleNum" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label for="cost">Макс. цена за ночь</label>
                        <input type="number" min="0" max="10000" class="form-control" value="1000" id="cost" name="maxCost" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cost">Даты бронирования</label>
                        <input type="text" autocomplete="off" class="form-control" name="dates" id="rangeCalendar" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                    <div class="col-md-2 mt-4" style="padding-top: 8px">
                        <button class="btn btn-info" type="reset">Очистить</button>
                    </div>
                </div>
                <hr class="mb-4">
                <button class="btn btn-info btn-lg btn-block" type="submit">Подобрать номера</button>
            </form>

            <#--Сюда вставляются найденые варианты при подборе-->
            <div class="row row1" id="options">
            </div>

        </div>


        <div class="col-md-3 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Корзина</span>
                <span class="badge badge-secondary badge-pill" id="product-count"><#if basket??>${basket?size}<#else>0</#if></span>
            </h4>
            <ul class="list-group mb-3" id="basket-container">

                <#assign  totalTotalCost = 0>
                <#if basket??>
                    <#list basket.entities! as entity>
                        <#assign totalCost = entity.days * entity.roomType.cost>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div id="product-body">
                            <h6 class="my-0" id="product-name">${entity.roomType.title} (${entity.days} ночей)</h6>
                            <#list entity.bonuses as bonus>
                                <#assign totalCost = totalCost + bonus.cost>
                                <small class="text-muted">${bonus.title} - ${bonus.cost} BYN </small><br>
                            </#list>
                        </div>
                        <span class="text-muted" id="product-cost">${totalCost} BYN</span>
                    </li>
                        <#assign totalTotalCost = totalTotalCost + totalCost>
                        <#if entity.sale??>
                        <li class="list-group-item d-flex justify-content-between bg-light">
                            <div class="text-success">
                                <h6 class="my-0">Скидка за раннее бронирование - ${hotel.discountOnDays}%</h6>
                            </div>
                            <span class="text-success">-$${entity.sale}</span>
                            <#assign totalTotalCost = totalTotalCost - entity.sale>
                        </li>
                        </#if>
                    </#list>

                    <#if basket.promocode??>
                        <li class="list-group-item d-flex justify-content-between bg-light">
                            <div class="text-success">
                                <h6 class="my-0">Скидка за промокод - ${hotel.в}%</h6>
                            </div>
                            <span class="text-success">-$${entity.sale}</span>
                            <#assign totalTotalCost = totalTotalCost - entity.sale>
                        </li>
                    </#if>
                </#if>

                <li class="list-group-item d-flex justify-content-between">
                    <span>Total</span>
                    <strong id="total-cost">${totalTotalCost} BYN</strong>
                </li>
            </ul>

            <form class="card p-2">
                <div class="form-row">
                    <div class="col-md-9">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Promo code">
                            <div class="input-group-append">
                                <button type="submit" id="promoInput" class="btn btn-secondary" onclick="addPromo()">Применить</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-secondary" type="button" onclick="clearBasket()">Очистить</button>
                    </div>
                </div>
            </form>
        </div>


        <div class="col-md-1"></div>
    </div>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-2018 Company Name</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>



</@c.page>

<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script>
    $(function() {
        $('input[name="dates"]').daterangepicker({
            opens: 'left'
        }, function(start, end, label) {
            console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        });
    });
</script>
<script src="/static/js/booking.js"></script>


<div class="col-md-4 mt-5" id="roomTypeHiddenDemo" hidden>
    <div class="card">
        <img src="" class="card-img-top" id="demo-img">
        <div class="card-body">
            <h5 class="card-title" id="demo-title"></h5>
            <p class="card-text" id="demo-description"></p>
            <small class="text-muted" id="demo-dates"></small>
        </div>
        <ul class="list-group list-group-flush cursor-pointer" id="demo-bonuses">
        </ul>
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <button type="button" id="basket" class="btn btn-sm btn-outline-secondary basket">В корзину</button>
                </div>
                <small class="text-muted" id="demo-cost"> BYN / ночь</small>
            </div>
        </div>
    </div>
</div>

<li class="list-group-item d-flex justify-content-between lh-condensed" id="basket-example" hidden>
    <div id="product-body">
        <h6 class="my-0" id="product-name"></h6>
    </div>
    <span class="text-muted" id="product-cost"></span>
</li>


<#--<li class="list-group-item d-flex justify-content-between bg-light">-->
<#--<div class="text-success">-->
<#--<h6 class="my-0">Promo code</h6>-->
<#--<small>EXAMPLECODE</small>-->
<#--</div>-->
<#--<span class="text-success">-$5</span>-->
<#--</li>-->