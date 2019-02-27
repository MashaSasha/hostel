<#import "general_blocks/common.ftl" as c>
<#import "general_blocks/login.ftl" as l>

<@c.page>
<link href="/static/css/login.css" rel="stylesheet"/>

<div class="container my-login-container">
    <hr class="my-hr" style="margin-bottom: 25px">
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
            <div class="panel panel-login">

                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm" id="login-form-link-div">
                            <a class="active" id="login-form-link">Вход</a>
                        </div>
                        <div class="col-sm dis-active" id="register-form-link-div">
                            <a style="padding-top: 10px" id="register-form-link">Регистрация</a>
                        </div>
                    </div>
                    <hr>
                    <div class="alert alert-danger" id="error-message" style="display: none">
                    </div>
                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">

                            <form id="login-form" method="post" action="/login" style="display: block;">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <div class="form-group">
                                    <input type="email" name="username" class="form-control" id="passport-login" required placeholder="Email">
                                    <div class="invalid-my-feedback" style="display: none">Неверный формат паспортного поля </div>
                                </div>

                                <div class="form-group">
                                    <input type="password" pattern="^[^\s]*$" name="password" class="form-control" minlength="5" placeholder="Пароль" required>
                                    <div class="invalid-my-feedback" style="display: none">Пароль должен быть от 5 до 15 символов</div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 offset-sm-3">
                                            <input type="submit" name="login-submit" id="login-submit" class="btn btn-outline-danger btn-login" value="Войти">
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <form id="register-form" method="post" action="/registration" style="display: none;">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <div class="form-group">
                                    <input type="text" name="name" required maxlength="30" minlength="2" id="firstName" tabindex="1" class="form-control" placeholder="Имя" value="">
                                    <div class="invalid-my-feedback" style="display: none">Имя должно содержать от 2 до 30 не пробельных символов</div>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="secondName" required maxlength="30" minlength="2" id="secondName" tabindex="1" class="form-control" placeholder="Фамилия" value="">
                                    <div class="invalid-my-feedback" style="display: none">Фамилия должна содержать от 2 до 30 не пробельных символов</div>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="passport" required pattern="^[A-Z]{2}[0-9]{7}$" id="passport" tabindex="1" class="form-control" placeholder="Номер пасспорта" value="">
                                    <div class="invalid-my-feedback" style="display: none">Неверный формат пасспортного номера</div>
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" required id="email" tabindex="1" class="form-control" placeholder="E-mail" value="">
                                    <div class="invalid-my-feedback" style="display: none">Неверный формат email</div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password-register" class="form-control" pattern="^[^\s]*$" required maxlength="15" minlength="5" placeholder="Пароль">
                                    <div class="invalid-my-feedback" style="display: none">Пароль должен содержать от 5 до 15 символов без пробелов</div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="repeatPassport" id="repeatPassport" class="form-control" required placeholder="Подтвердите пароль">
                                    <div class="invalid-my-feedback" style="display: none">Пароль не совпадает</div>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 offset-sm-3">
                                            <input type="submit" name="register-submit" id="register-submit" class="btn-outline-danger btn btn-register" value="Зарегестрироваться">
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-2">
        </div>
    </div>
    <hr class="my-hr">
</div>

</@c.page>
<script src="/static/js/loginForm.js"></script>
