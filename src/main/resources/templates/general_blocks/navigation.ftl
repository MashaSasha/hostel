<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Отель</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#--<#if isAdmin>-->
            <li class="nav-item">
                <a class="nav-link" href="/">Домашняя</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/hotel/editor">Редактирование данных</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/booking">Бронирование</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Профиль</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/statistic">Статистика</a>
            </li>
            <#--</#if>-->
        </ul>
        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
    </div>
</nav>