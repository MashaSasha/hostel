<#import "../parts/common.ftl" as c>
<#import "forms/editHotelInfo.ftl" as editor>

<@c.page>
<script src="/static/ckeditor/ckeditor.js"></script>
<div class="container-fluid mt-5">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">
                    Редактировать Информацию об отеле
                </a>
                <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">
                    Изменение картинок слайдера
                </a>
                <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">
                    Редактирование типов номеров
                </a>
            </div>
        </div>
        <div class="col-7">
            <div class="tab-content" id="v-pills-tabContent">

                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                    <@editor.editMainInfo hotel/>
                </div>

                <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                    <@editor.editImages hotel/>
                </div>

                <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                    <@editor.editRoomTypes hotel/>
                </div>
            </div>
        </div>
    </div>
</div>

</@c.page>
