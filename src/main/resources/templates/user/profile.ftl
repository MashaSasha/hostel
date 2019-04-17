<#import "../general_blocks/common.ftl" as c>

<@c.page>

<div class="container-fluid">
    <div class="text-center">
        <h2>Мой Прфоиль</h2>
        <p class="lead">Тут можно редактировать данные о себе а так же добавить фото паспорта, для более быстрого оформления по прибытию</p>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-6">
            <form method="post" action="/user/edit" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${profile.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">

                <div class="form-group row">
                    <label for="passport" class="col-sm-2 col-form-label">Паспорт: </label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="passport" id="passport" placeholder="Паспорт" value="${profile.passport}">
                    </div>
                </div>
                <div class="form-group row">

                    <label for="name" class="col-sm-2 col-form-label">Имя</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Имя" value="${profile.name}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="secondname" class="col-sm-2 col-form-label">Фамилия</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="secondname" name="secondName" placeholder="Фамилия" value="${profile.secondName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" placeholder="E-mail" value="${profile.email}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Новый пароль</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" name="password" placeholder="пароль">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password2" class="col-sm-2 col-form-label">Подтверждение пароля</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password2" name="password2" placeholder="подтвердите пароль">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group custom-file row">
                            <input type="file" class="custom-file-input" id="customFile" name="image">
                            <label class="custom-file-label" for="customFile">Выберете файл</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <button type="submit" class="btn btn-secondary">Сохранить изменения</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-4">
            <h3>Фото паспорта:</h3>
            <img src="/static/img/${profile.passportImage!}" class="img-fluid" alt="Responsive image">
        </div>

        <div class="col-md-1"></div>

    </div>
</div>

<div class="container-fluid mt-5">
    <div class="text-center">
        <h4>Активные брони</h4>
        <p class="lead"></p>
    </div>

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <#if activeReservations??>
                <div class="accordion" id="accordionActiveReservation">
                    <#list activeReservations as reservation>

                        <div class="card">
                            <div class="card-header" id="heading${reservation?index}">
                                <div class="row">
                                    <div class="col-md-6">
                                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${reservation?index}" aria-expanded="true" aria-controls="collapse${reservation?index}">
                                            ${reservation.roomType.title}
                                        </button>
                                    </div>
                                    <div class="col-md-3">
                                        ${reservation.days} Ночей
                                    </div>
                                    <div class="col-md-3">
                                        Дата заселения - ${reservation.startDate}
                                    </div>
                                </div>
                            </div>

                            <div id="collapse${reservation?index}" class="collapse" aria-labelledby="heading${reservation?index}" data-parent="#accordionActiveReservation">
                                <div class="card-body">
                                    <div class="row">

                                        <div class="col-md-4">
                                            <div class="card mb-4 box-shadow">
                                                <img class="card-img-top" src="/static/img/${reservation.roomType.previewImage!}">
                                                <div class="card-body">
                                                    <h5 class="card-title">${reservation.roomType.title}</h5>
                                                    <p class="card-text">${reservation.roomType.description}</p>
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <small class="text-muted">${reservation.roomType.cost} BYN / ночь</small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-8">
                                            <h4>Вы приезжаете ${reservation.startDate} и должны будете освободить номер ${reservation.endDate}</h4>
                                            <h4>Вы оформили эту заявку в этот день : ${reservation.reservationDate}</h4>

                                            <ul class="list-group mb-3 mt-5" id="basket-container">

                                                <#assign totalCost = reservation.days * reservation.roomType.cost>
                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                    <div id="product-body">
                                                        <h6 class="my-0" id="product-name">${reservation.roomType.cost} BYN / ночь (${reservation.days} ночей)</h6>
                                                        <#list reservation.bonuses as bonus>
                                                            <#assign totalCost = totalCost + bonus.cost>
                                                            <small class="text-muted">${bonus.title} - ${bonus.cost} BYN </small><br>
                                                        </#list>
                                                    </div>
                                                    <span class="text-muted" id="product-cost">${totalCost} BYN</span>
                                                </li>
                                                <#if reservation.earlyBookingSale??>
                                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                                        <div class="text-success">
                                                            <h6 class="my-0">Скидка за раннее бронирование</h6>
                                                        </div>
                                                        <span class="text-success">-$${reservation.earlyBookingSale}</span>
                                                    </li>
                                                </#if>

                                                <#if reservation.promoCode??>
                                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                                        <div class="text-success">
                                                            <h6 class="my-0">Скидка за промокод - ${reservation.promoCode.discount}%</h6>
                                                        </div>
                                                        <span class="text-success">-$${totalCost - reservation.earlyBookingSale - reservation.cost}</span>
                                                    </li>
                                                </#if>

                                                <li class="list-group-item d-flex justify-content-between">
                                                    <span>Total</span>
                                                    <strong id="total-cost">${reservation.cost} BYN</strong>
                                                </li>
                                            </ul>

                                            <form id="deleteReservation" name="${reservation.id}">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button type="submit" class="btn btn-info mt-2">Отмена бронирования</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </#list>
                </div>
            </#if>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>


<div class="container-fluid mt-5">
    <div class="text-center">
        <h4>История бронирования</h4>
        <p class="lead"></p>
    </div>

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <#if historyReservations??>
                <div class="accordion" id="accordionActiveReservation">
                    <#list historyReservations as reservation>

                        <div class="card">
                            <div class="card-header" id="heading${reservation?index}">
                                <div class="row">
                                    <div class="col-md-6">
                                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${reservation?index}" aria-expanded="true" aria-controls="collapse${reservation?index}">
                                            ${reservation.roomType.title}
                                        </button>
                                    </div>
                                    <div class="col-md-3">
                                        ${reservation.days} Ночей
                                    </div>
                                    <div class="col-md-3">
                                        Дата заселения - ${reservation.startDate}
                                    </div>
                                </div>
                            </div>

                            <div id="collapse${reservation?index}" class="collapse" aria-labelledby="heading${reservation?index}" data-parent="#accordionActiveReservation">
                                <div class="card-body">
                                    <div class="row">

                                        <div class="col-md-4">
                                            <div class="card mb-4 box-shadow">
                                                <img class="card-img-top" src="/static/img/${reservation.roomType.previewImage!}">
                                                <div class="card-body">
                                                    <h5 class="card-title">${reservation.roomType.title}</h5>
                                                    <p class="card-text">${reservation.roomType.description}</p>
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <small class="text-muted">${reservation.roomType.cost} BYN / ночь</small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-8">
                                            <h4>Вы приезжаете ${reservation.startDate} и должны будете освободить номер ${reservation.endDate}</h4>
                                            <h4>Вы оформили эту заявку в этот день : ${reservation.reservationDate}</h4>

                                            <ul class="list-group mb-3 mt-5" id="basket-container">

                                                <#assign totalCost = reservation.days * reservation.roomType.cost>
                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                    <div id="product-body">
                                                        <h6 class="my-0" id="product-name">${reservation.roomType.cost} BYN / ночь (${reservation.days} ночей)</h6>
                                                        <#list reservation.bonuses as bonus>
                                                            <#assign totalCost = totalCost + bonus.cost>
                                                            <small class="text-muted">${bonus.title} - ${bonus.cost} BYN </small><br>
                                                        </#list>
                                                    </div>
                                                    <span class="text-muted" id="product-cost">${totalCost} BYN</span>
                                                </li>
                                                <#if reservation.earlyBookingSale??>
                                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                                        <div class="text-success">
                                                            <h6 class="my-0">Скидка за раннее бронирование</h6>
                                                        </div>
                                                        <span class="text-success">-$${reservation.earlyBookingSale}</span>
                                                    </li>
                                                </#if>

                                                <#if reservation.promoCode??>
                                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                                        <div class="text-success">
                                                            <h6 class="my-0">Скидка за промокод - ${reservation.promoCode.discount}%</h6>
                                                        </div>
                                                        <span class="text-success">-$${totalCost - reservation.earlyBookingSale - reservation.cost}</span>
                                                    </li>
                                                </#if>

                                                <li class="list-group-item d-flex justify-content-between">
                                                    <span>Total</span>
                                                    <strong id="total-cost">${reservation.cost} BYN</strong>
                                                </li>
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                        </div>

                        </div>

                    </#list>
                </div>
            </#if>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
</@c.page>

<script>
    $('#deleteReservation').submit(function (event) {
        let dataToSend = $(this).serialize();
        let id = $(this).attr('name');
        let url = '/user/booking/delete/' + id;

        let request = $.ajax({
            url: url,
            type: 'POST',
            data: dataToSend,
            dataType: 'json'
        });

        request.done(function (data) {
            if (data.status === 'SUCCESS') {
                document.location.reload(true);
            } else {
                alert(data.message);
            }
        });
        event.preventDefault();
        return false;
    })
</script>