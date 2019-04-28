<%@ page import="se.model.Notification" %>
<!DOCTYPE html>
<html lang="zh-cn">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="../../js/jquery-3.3.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <style>
        .new-container {
            width: 100%;
            min-height: 400px;
            display: flex;
            justify-content: center;
        }

        .new-content {
            width: 70%;
            height: 200px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>消息界面</h1>
        </div>
        <div class="new-container">
            <div class="new-content">
                <div class="panel-group" id="accordion" role='tablist' aria-multiselectable="true">
                    <c:forEach items="${notifications}" var="notification">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading-${notification.notificationId}">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse-${notification.notificationId}"
                                       aria-expanded="true" aria-controls="collapseOne">
                                        ${notification.title}-${notification.time.toLocaleString()}
                                    </a>
                                </h4>
                            </div>

                            <div id="collapse-${notification.notificationId}" class="panel-collapse collapse " role="tabpanel"
                                 aria-labelledby="heading-${notification.notificationId}">
                                <div class="panel-body">
                                    ${notification.context}
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <%--<div class="panel panel-default">--%>
                        <%--<div class="panel-heading" role="tab" id="headingTwo">--%>
                            <%--<h4 class="panel-title">--%>
                                <%--<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"--%>
                                    <%--aria-expanded="true" aria-controls="collapseTwo">--%>
                                    <%--消息2--%>
                                <%--</a>--%>
                            <%--</h4>--%>
                        <%--</div>--%>
                        <%--<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"--%>
                            <%--aria-labelledby="headingTwo">--%>
                            <%--<div class="panel-body">--%>
                                <%--消息内容2--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>

</body>
</html>