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
            <form>
                <div class="form-group row">
                    <label for="passport" class="col-sm-2 col-form-label">Паспорт: </label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="passport" placeholder="Паспорт">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Имя</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" placeholder="Имя">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="secondname" class="col-sm-2 col-form-label">Фамилия</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="secondname" placeholder="Фамилия">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Новый пароль</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" placeholder="пароль">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password2" class="col-sm-2 col-form-label">Подтверждение пароля</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password2" placeholder="подтвердите пароль">
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
            <img src="https://2019-god.com/wp-content/uploads/2017/11/petuh-horo.jpg" class="img-fluid" alt="Responsive image">
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
            <#if profile.reservations??>
                <#list profile.reservations as reservation>
                    ${reservation.startDate}
                </#list>
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

        </div>
        <div class="col-md-1"></div>
    </div>
</div>
</@c.page>