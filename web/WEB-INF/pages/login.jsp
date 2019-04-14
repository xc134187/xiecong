<!DOCTYPE html>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@page isELIgnored="false" %>
    <%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
    <html lang="zh" class="no-js">
    <head>
        <title>${Title}</title>
        <meta charset="UTF-8" />
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="/css/style.css" />
        <link rel="stylesheet" type="text/css" href="/css/animate-custom.css" />
        <script>
            function loadXmlDoc() {
                xhr = new XMLHttpRequest();
                xhr.open("GET", "/Notify/Top?top=2", true);
                xhr.send();
                xhr.onreadystatechange = function (ev) {
                    if(xhr.readyState==4 && xhr.status==200){
                        var data = JSON.parse(xhr.responseText);
                        var n = document.getElementById("notifications");
                        for(var i =0; i <2;i++){
                            var a = document.createElement("a");
                            a.setAttribute("href", '/Notify/Notify/'+data[i].notificationId);
                            a.innerText=data[i].title + '-' + new Date(data[i].time).toLocaleString();
                            n.appendChild(a);
                            n.appendChild(document.createElement("br"));
                        }
                    }
                }
            }
        </script>
    </head>
    <body onload="loadXmlDoc()">
        <div class="container">
            <header>
                <h1>攀枝花学院课程设计管理系统</h1>
				<nav class="codrops-demos">
					<span>点击 <strong>"news"</strong> 查看全部通知</span>
					<a href="#">news</a>
                </nav>
                <div id="notifications">
                    <%--<c:forEach items="${news}" var="news">--%>
                        <%--<a href="news.link">news.name</a>--%>
                        <%--<br/><br/>--%>
                    <%--</c:forEach>--%>
                </div>
            </header>
            <section>				
                <div id="container_demo" >
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form method="post" action="/User/Login" autocomplete="on">
                                <h1>Log in</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > username </label>
                                    <input id="username" name="username" required="required" type="text" autocomplete="off"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p">password </label>
                                    <input id="password" name="password" required="required" type="password" />
                                </p>
                                <p class="login button"> 
                                    <input type="submit" value="Login" /> 
								</p>
                                <p class="change_link">
								</p>
                            </form>
                        </div>
                    </div>
                </div>  
            </section>
        </div>
    </body>
</html>