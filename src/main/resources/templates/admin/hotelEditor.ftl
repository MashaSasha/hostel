<#import "../general_blocks/common.ftl" as c>
<#import "forms/editHotelInfo.ftl" as editor>

<@c.page>
    <script src="/static/frameworks/ckeditor/ckeditor.js"></script>
    <div class="container-fluid mt-5">
        <div class="row">
            <div class="offset-md-1">
                <div class="alert alert-danger" role="alert" <#if !message??>hidden</#if>>
                    ${message!}
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-1"></div>
            <div class="col-2">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active main-list" id="list-home-list" data-toggle="list"
                       href="#list-home" role="tab" aria-controls="home">
                        Редактировать Информацию об отеле
                    </a>
                    <a class="list-group-item list-group-item-action main-list" id="list-profile-list" data-toggle="list"
                       href="#list-profile" role="tab" aria-controls="profile">
                        Изменение картинок слайдера
                    </a>
                    <a class="list-group-item list-group-item-action main-list" id="list-messages-list" data-toggle="list"
                       href="#list-messages" role="tab" aria-controls="messages">
                        Редактирование типов номеров
                    </a>
                    <a class="list-group-item list-group-item-action main-list" id="list-settings-list" data-toggle="list"
                       href="#list-settings" role="tab" aria-controls="settings">
                        Добавление комнат
                    </a>
                </div>
            </div>

            <div class="col-7">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="list-home" role="tabpanel"
                         aria-labelledby="list-home-list">
                        <@editor.editMainInfo hotel/>
                    </div>
                    <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
                        <@editor.editImages hotel/>
                    </div>
                    <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                        <@editor.editRoomTypes hotel/>
                    </div>
                    <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
                        <@editor.addRooms hotel/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>

<script>
    $('.main-list').click(function () {
       sessionStorage.setItem('page', $(this).attr('id'));
    });

    let activePage = sessionStorage.getItem('page');
    if (activePage === undefined) {
        $('#list-home').trigger('click');
    } else {
        $('#' + activePage).trigger('click');
    }
</script>
