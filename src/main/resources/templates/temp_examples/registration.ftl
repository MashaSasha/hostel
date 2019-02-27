<#import "../general_blocks/common.ftl" as c>
<#import "../general_blocks/login.ftl" as l>

<@c.page>
<div class="mb-1">Add new user</div>
${message!}
<@l.login "/registration" true/>
</@c.page>