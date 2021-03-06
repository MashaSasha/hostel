<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name :</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="username"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-5">
                <input type="password" class="form-control" name="password"/>
            </div>
        </div>
        <#if !isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email: </label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" name="email" placeholder="Some@some.com"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if !isRegisterForm>
            <a href="/registration">Add new User</a>
        </#if>
        <button class="btn btn-primary" type="submit">
            <#if isRegisterForm>Create<#else>Sign In</#if>
        </button>
    </form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button class="btn btn-info" type="submit">Выйти из аккаунта</button>
</form>
</#macro>