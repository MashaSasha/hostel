<#import "../general_blocks/common.ftl" as c>

<@c.page>
<link href="form-validation.css" rel="stylesheet">

<body class="bg-light">

<div class="container-fluid">
    <div class="text-center">
        <h2>Форма бронирования</h2>
        <p class="lead">Вы можете подобрать себе номера исходя из своих финансов и потребностей.</p>
    </div>

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-7">
            <h4 class="mb-3">Billing address</h4>
            <form class="needs-validation" id="filterForm" novalidate>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="row">
                    <div class="col-md-2">
                        <label for="personCount">Человек в номере</label>
                        <input type="number" min="0" max="10" class="form-control" id="personCount" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label for="cost">Макс. цена за ночь</label>
                        <input type="number" min="0" max="10000" class="form-control" id="cost" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cost">Даты бронирования</label>
                        <input type="text" class="form-control" name="rangeCalendar" id="rangeCalendar" placeholder="" value="" required>
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
        </div>
        <div class="col-md-3 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Корзина</span>
                <span class="badge badge-secondary badge-pill">3</span>
            </h4>
            <ul class="list-group mb-3">
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">Product name</h6>
                        <small class="text-muted">Brief description</small>
                    </div>
                    <span class="text-muted">$12</span>
                </li>
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">Second product</h6>
                        <small class="text-muted">Brief description</small>
                    </div>
                    <span class="text-muted">$8</span>
                </li>
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">Third item</h6>
                        <small class="text-muted">Brief description</small>
                    </div>
                    <span class="text-muted">$5</span>
                </li>
                <li class="list-group-item d-flex justify-content-between bg-light">
                    <div class="text-success">
                        <h6 class="my-0">Promo code</h6>
                        <small>EXAMPLECODE</small>
                    </div>
                    <span class="text-success">-$5</span>
                </li>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Total (USD)</span>
                    <strong>$20</strong>
                </li>
            </ul>

            <form class="card p-2">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Promo code">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary">Redeem</button>
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
        $('input[name="rangeCalendar"]').daterangepicker({
            opens: 'left'
        }, function(start, end, label) {
            console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        });
    });
</script>
<script>
    $('#filterForm').submit(function (event) {
        let isFormValid = true;

        $('#login-form input[type="text"], #login-form input[type="number"]').each(function () {
            if (this.checkValidity()) {
                $(this).removeClass('invalid').next().fadeOut(100);
            } else {
                $(this).addClass('invalid').next().fadeIn(100);
                isFormValid = false
            }
        });
        if (isFormValid) {
            sendForm(this);
        } else {
            event.preventDefault();
        }
    });

    function sendForm(form) {
        let dataToSend;
        dataToSend = $(form).serialize();

        $.post('/user/booking/filter', dataToSend).done(function (data) {
            if (data.success !== undefined) {
                window.location.replace(data.redirect);
            }
            if (data.error !== undefined) {
                $('#error-message').fadeIn(500).text(data.error);
            }
        });
        event.preventDefault();
    }
</script>