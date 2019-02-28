<#macro editMainInfo hotel>
    <div class="text-center">
        <h5>Редактирование информации об отеле</h5>
    </div>

    <form method="post" action="/admin/hotel/edit" id="editHotel" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${hotel.id!}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <div class="form-group row text-right">
            <label for="editHotelName" class="col-sm-2 col-form-label">Hotel Name:</label>
            <div class="col-sm-10">
                <input value="${hotel.hotelName!}" class="form-control" id="editHotelName" type="text" name="hotelName"
                       placeholder="hotel name">
            </div>
        </div>

        <div class="form-group row text-right">
            <label for="editHotelName" class="col-sm-2 col-form-label">Hotel Address:</label>
            <div class="col-sm-10">
                <input value="${hotel.address!}" class="form-control" id="editHotelName" type="text" name="address"
                       placeholder="hotel address">
            </div>
        </div>

        <div class="form-group row text-right">
            <label for="hotelDescription" class="col-sm-2 col-form-label">Hotel description:</label>
            <div class="col-sm-10">
            <textarea class="form-control" name="description" placeholder="Hotel description" rows="8">
                ${hotel.description!}
            </textarea>
                <script>
                    CKEDITOR.replace('description');
                </script>
            </div>
        </div>

        <div class="text-center mt-5">
            <h4>Общие положения</h4>
        </div>
        <div class="row">
        <pre>
        Скидка <input type="text" value="${hotel.discountOnRoomCount!}" name="discountOnRoomCount" style="width: 40px">% при бронировании сразу <input
                    type="text"
                    value="${hotel.roomCountToDiscount!}"
                    name="roomCountToDiscount"
                    style="width: 40px"> номеров

        Скидка <input type="text" value="${hotel.discountOnDays!}" name="discountOnDays" style="width: 40px">% при бронировании за <input
                    type="text"
                    value="${hotel.daysCountToDiscount!}"
                    name="daysCountToDiscount"
                    style="width: 40px"> дней до заселения
        </pre>
        </div>
    </form>
    <div class="text-center">
        <button class="btn btn-info text-center mt-2" type="submit"
                onclick="document.getElementById('editHotel').submit()"
                style="width: 200px">
            Сохранить изменения
        </button>
    </div>
</#macro>

<#macro editImages hotel>
    <div class="text-center mt-3">
        <h4>Картинки слайдера</h4>
    </div>
    <form action="/admin/hotel/add/image" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <div class="row">
            <div class="col-md-2 mt-3 text-right">
                Добавить картинку:
            </div>
            <div class="col-md-4 ml-3">
                <div class="custom-file my-2 row">
                    <input type="file" class="custom-file-input" id="customFile" name="image">
                    <label class="custom-file-label" for="customFile">Выберете файл</label>
                </div>
            </div>
            <div class="col-md-2 mt-2">
                <button type="submit" class="btn btn-info">Добавить</button>
            </div>
        </div>
    </form>

    <div class="card-columns mt-5">
        <#if hotel.images??>
            <#list hotel.images as image>
                <div class="card text-center bg-light">
                    <img class="card-img-top" src="/static/img/${image}">
                    <div class="m-2 card-body">
                        <#--<a href="#" class="btn btn-primary">Go somewhere</a>-->
                        <form action="/admin/hotel/delete/img" method="post">
                            <input type="hidden" name="image" value="${image}">
                            <input type="hidden" name="id" value="${hotel.id!}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-info" type="submit">Delete</button>
                        </form>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</#macro>

<#macro editRoomTypes hotel>
    <h3>
        Добавить тип номеров
    </h3>
    <div class="mt-5">
        <form method="post" action="/admin/hotel/add/roomType">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="${hotel.id!}">

            <div class="form-group row">
                <label for="roomTypeTitle" class="col-sm-2 col-form-label">Добавить новый тип
                    номеров</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="roomTypeTitle" name="roomTypeTitle"
                           placeholder="Название типа комнат">
                </div>
            </div>

            <div class="form-group row">
                <label for="roomTypeCost" class="col-sm-2 col-form-label">Добавить цену</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="roomTypeCost" name="roomTypeCost" placeholder="Цена">
                </div>
            </div>

            <button type="submit" class="btn btn-info">Добавить</button>
        </form>
    </div>

    <div class="text-center mt-5">
        <h1>Типы номеров</h1>
    </div>
    <div class="accordion" id="accordion">
        <#list hotel.roomTypes! as roomType>
            <div class="card">
                <div class="card-header" id="heading${roomType.id}">
                    <h2 class="mb-0">
                        <button class="btn btn-link" type="button" data-toggle="collapse"
                                data-target="#roomType${roomType.id}" aria-expanded="true"
                                aria-controls="roomType${roomType.id}">
                            ${roomType.title!}
                        </button>
                    </h2>
                </div>
                <div id="roomType${roomType.id}" class="collapse" aria-labelledby="heading${roomType.id}"
                     data-parent="#accordion">
                    <div class="card-body">

                        <form action="/admin/hotel/edit/roomType" method="post" id="editRoomType${roomType.id}"
                              enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${roomType.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">

                            <div class="form-group row">
                                <label for="roomName${roomType.id!}" class="col-sm-2 col-form-label">Название
                                    номеров</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="cardName${roomType.id!}"
                                           value="${roomType.title!}"
                                           name="title" placeholder="Название типа комнат">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="roomCost${roomType.id!}" class="col-sm-2 col-form-label">
                                    Цена за номер
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="roomName${roomType.id!}"
                                           value="${roomType.cost!}"
                                           name="cost" placeholder="Цена за номер">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="roomAmount${roomType.id!}" class="col-sm-2 col-form-label">
                                    Спальных мест в номере
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="roomAmount${roomType.id!}"
                                           value="${roomType.sleepPlacesAmount!}"
                                           name="sleepPlacesAmount" placeholder="Введите колличество спальных мест">
                                </div>
                            </div>

                            <div class="form-group row text-right">
                                <label for="hotelDescription" class="col-sm-2 col-form-label">Описание комнаты:</label>
                                <div class="col-sm-10">
                                <textarea class="form-control" name="description" placeholder="Описание комнаты"
                                          id="roomTypeDesc${roomType.id}" rows="5">
                                    ${roomType.description!}
                                </textarea>
                                    <script>
                                        CKEDITOR.replace('roomTypeDesc${roomType.id}');
                                    </script>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-2 mt-3 text-right">
                                    Добавить картинку:
                                </div>
                                <div class="col-md-4 ml-3">
                                    <div class="custom-file my-2 row">
                                        <input type="file" class="custom-file-input" id="customFile" name="image">
                                        <label class="custom-file-label" for="customFile">Выберете файл</label>
                                    </div>
                                </div>
                                <div class="col-md-2"></div>
                                <div class="col-md-4">
                                    <img src="/static/img/${roomType.previewImage!}" style="height: 200px">
                                </div>
                            </div>
                        </form>
                    </div>


                    <h4>Дополнительные услуги</h4>
                    <#list roomType.bonuses as bonus>
                        <div>
                            <ul>
                                <li>
                                    <div class="row">
                                        <div class="col-sm-9">${bonus.title}</div>
                                        <div class="col-sm-3">${bonus.cost}</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    <#else>
                        В данный момент нет дополнительных услуг.
                    </#list>
                    <form action="/admin/hotel/add/bonus" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="roomTypeId" value="${roomType.id}">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input type="text" class="form-control" name="title" placeholder="Название бонуса"/>
                            </div>
                            <div class="form-group col-md-3">
                                <input type="number" class="form-control" name="cost" placeholder="Стоимость услуги"/>
                            </div>
                            <div class="form-group col-md-3">
                                <button class="btn btn-info" type="submit">Добавить</button>
                            </div>
                        </div>
                    </form>
                    <div class="row mt-2">
                        <div class="col-sm-6 text-center">
                            <button class="btn btn-info" style="width: 200px"
                                    onclick="document.getElementById('editRoomType${roomType.id}').submit()">
                                Save
                            </button>
                        </div>
                        <div class="col-sm-6 text-center">
                            <button class="btn btn-danger" style="width: 200px">Canсel</button>
                        </div>
                    </div>
                </div>

            </div>
        <#else>
            <h5>Пока нет ни одного Номера</h5>
        </#list>
    </div>
</#macro>

<#macro addRooms hotel>
    <h4>
        Добавление Комнат
    </h4>
    <div class="row">
        <div class="col-md-6">
            <div class="list-group" id="list-tab" role="tablist">
                <#list hotel.roomTypes! as roomType>
                    <a class="list-group-item list-group-item-action<#if roomType_index == 0> active</#if>"
                       id="list-home-list" data-toggle="list" href="#addRoom${roomType.id}" role="tab"
                       aria-controls="home">
                        ${roomType.title}
                    </a>
                </#list>
            </div>
        </div>
        <div class="col-md-6">
            <div class="tab-content" id="nav-tabContent">
                <#list hotel.roomTypes! as roomType>
                    <div class="tab-pane fade <#if roomType_index == 0> show active</#if>" id="addRoom${roomType.id}"
                         role="tabpanel" aria-labelledby="list-home-list">
                        <form action="/admin/hotel/add/room" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="roomTypeId" value="${roomType.id}">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <input type="number" class="form-control" name="roomNumber"
                                           placeholder="Номер новой комнаты"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <button class="btn btn-info" type="submit">Добавить</button>
                                </div>
                            </div>
                        </form>
                        <br>
                        <ul class="list-group">
                            <#list roomType.rooms! as room>
                                <li class="list-group-item ">${room.roomNumber!}
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                        Изменить
                                    </button>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </#list>
            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Удадить</button>
                </div>
            </div>
        </div>
    </div>
</#macro>