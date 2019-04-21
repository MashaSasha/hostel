<#import "../general_blocks/common.ftl" as c>
<@c.page>

<style>
    .fixed-image {
        height: 700px;
    }
    .carousel-inner > .item > img {
        margin: 0 auto;
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

        <form>
            <div class="form-row">
                <div class="col-md-9">
                    <label for="titleReview">Заголовок отзыва</label>
                    <input type="text" id="titleReview" class="form-control" placeholder="Заголовок отзыва">
                </div>
                <div class="form-group col-md-3">
                    <label for="typeReview">Тип отзыва: </label>
                    <select id="typeReview" class="form-control ml-2" name="year">
                        <option selected value="positive">Позитивный</option>
                        <option value="negative">Отрциательный</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                    <label for="hotelDescription" class="col-sm-2 col-form-label">Описание комнаты:</label>
                    <div class="col-sm-10">
                                <textarea class="form-control" name="description" placeholder="Текст поста"
                                          id="reviewText" rows="5">
                                </textarea>
                        <script>
                            CKEDITOR.replace('reviewText');
                        </script>
                    </div>
            </div>
        </form>


        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Отель плохой - не ездите сюда пожалуйста</h2>
                <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-fluid mx-auto" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
                <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
            </div>
            <div class="col-md-5 order-md-1">
                <img class="featurette-image img-fluid mx-auto" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
                <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-fluid mx-auto" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->

    </div><!-- /.container -->


    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>&copy; 2017-2018 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>
</main>
</@c.page>