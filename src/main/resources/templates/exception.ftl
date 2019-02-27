<#import "/spring.ftl"as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="error-template">
                    <h1>Ошибка!!!</h1>
                    ${exception}
                    <a href="/main">домой</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>