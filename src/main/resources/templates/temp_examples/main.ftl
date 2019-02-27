<#import "../general_blocks/common.ftl" as c>


<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" value="${filter!}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>
  <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
     aria-controls="collapseExample">
      Add new message
  </a>
<div class="collapse" id="collapseExample">
    <div class="form-group ">
        <form method="post" enctype="multipart/form-data">
            <input type="text" class="form-control my-2" name="text" placeholder="Введите сообщение">
            <input type="text" class="form-control my-2" name="tag" placeholder="Тэг">
            <div class="custom-file my-2">
                <input type="file" name="file" id="customFile">
                <label for="customFile" class="custom-file-label">Choose file</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary my-2">Add</button>
        </form>
    </div>
</div>
<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
            </div>
            <div class="m-2">
                <span>${message.text}</span>
                <i>${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                ${message.authorName}
            </div>
        </div>
    <#else>
    No Messages
    </#list>
</div>
</@c.page>