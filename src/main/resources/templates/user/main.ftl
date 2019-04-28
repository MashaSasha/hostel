<#import "../general_blocks/common.ftl" as c>
<@c.page>

<style>
    .fixed-image {
        height: 700px;
    }
    .carousel-inner > .item > img {
        margin: 0 auto;
    }
    .fa-heart {
        cursor: pointer
    }
</style>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<main role="main">

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <#list hotel.images as image>
                <li data-target="#myCarousel" data-slide-to="${image_index}" <#if image_index == 0>class="active"</#if>></li>
            </#list>
        </ol>
        <div class="carousel-inner">
            <#list hotel.images as image>
                <div class="carousel-item <#if image_index == 0>active</#if>">
                    <div class="text-center">
                        <img class="fixed-image" src="/static/img/${image}">
                    </div>
                    <div class="container">
                        <div class="carousel-caption text-left">
                            <h1>${hotel.hotelName}.</h1>
                            <p>${hotel.description}</p>
                            <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing mt-5">


        <div class="row">
        <#list hotel.roomTypes! as roomType>
            <div class="col-md-4">
                <div class="card mb-4 box-shadow">
                    <img class="card-img-top" src="/static/img/${roomType.previewImage!}">
                    <div class="card-body">
                        <h5 class="card-title">${roomType.title}</h5>
                        <p class="card-text">${roomType.description}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#rtModal${roomType.id}">Подробности</button>
                            </div>
                            <small class="text-muted">${roomType.cost} BYN / ночь</small>
                        </div>
                    </div>
                </div>
            </div>



            <div class="modal fade" id="rtModal${roomType.id}" tabindex="-1" role="dialog" aria-labelledby="centerTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">${roomType.title}</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <h5>Дополнительные услуги:</h5>
                            <div class="row">
                                <#list roomType.bonuses! as bonus>
                                <div class="col-sm-8">
                                    - ${bonus.title}
                                </div>
                                <div class="col-sm-4">
                                    : ${bonus.cost} BYN
                                </div>
                                </#list>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        </div>

        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <#if !alreadyHaveReview>
            <form action="/user/review/add" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-row">
                    <div class="col-md-9">
                        <label for="titleReview">Заголовок отзыва</label>
                        <input type="text" name="title" id="titleReview" class="form-control" placeholder="Заголовок отзыва">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="typeReview">Тип отзыва: </label>
                        <select id="typeReview" class="form-control ml-2" name="type">
                            <option selected value="positive">Позитивный</option>
                            <option value="negative">Отрциательный</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="hotelDescription" class="col-sm-2 col-form-label">Содержание отзыва:</label>
                    <textarea class="form-control" name="text" placeholder="Текст поста" id="reviewText" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-secondary offset-4 col-md-4">Добавить</button>
                </div>
            </form>
            <hr>
        </#if>


        <div class="mb-2">
            Отфильтровать используя текущие параметры:
        </div>
        <form>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <select id="inputState" class="form-control">
                        <option selected>Позитивные</option>
                        <option>Отрицательные</option>
                        <option>Любые</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <select id="inputState" class="form-control">
                        <option selected>По дате</option>
                        <option>По популярности</option>
                    </select>
                </div>
            </div>
        </form>
        <hr class="featurette-divider">
        <#list reviews! as review>
            <div class="row featurette">
                <div class="col-md-12">
                    <h2 class="featurette-heading">${review.title}</h2>
                    <p class="lead">${review.text}</p>
                    <div class="text-right">
                        <a id="${review.id}" class="like-href">
                            <i class="far fa-heart" aria-hidden="true"></i>
                        </a>
                        5
                        <b>${review.author.name} ${review.author.secondName}</b> <span class="date">${review.date}</span>
                    </div>

                </div>
            </div>

            <div hidden>
                <form id="likeForm${review.id}" action="/user/review/like" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" value="${review.id}" name="id">
                </form>
            </div>

            <hr class="featurette-divider">
        </#list>

    </div><!-- /.container -->

    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>&copy; 2017-2018 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>
</main>
</@c.page>


<script>
    $(document).ready(function() {
        $(".like-href").click(function() {
            $('#likeForm' + this.id).submit()
        });
    });
</script>