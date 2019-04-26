<%--
  Created by IntelliJ IDEA.
  User: weibo
  Date: 2019/4/25
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${Title}</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        body {
            padding: 0px 50px;
        }

        .no-click {
            /*cursor: not-allowed;*/
            pointer-events: none;
        }

        .student-container {
            display: flex;
            height: 450px;
            width: 100%;
        }

        .left {
            width: 30%;
            height: 100%;
        }

        .right {
            width: 70%;
            height: 100%;

        }

        #upload {
            display: none;

        }

        .uploadFile {
            display: inline-block;
            width: 110px;
            height: 60px;
            border: 1px dashed #e5e5ee;
            text-align: center;
            line-height: 60px;
            font-size: 18px;
            font-family: '楷体';
            color: gray;
            border-radius: 10px;
            margin: 0px;
        }

        .uploadFile:hover {
            cursor: pointer;
            border: 1px dashed rgb(51, 133, 255);
            color: rgb(51, 133, 255);
            transition: .5s border;
        }
    </style>
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/ajax.js"></script>
</head>

<body>
<div class="page-header">
    <h1>系统管理
        <small>课程设计系统管理</small>
    </h1>
</div>

<div class="row">
    <div class="col-lg-8 col-lg-offset-2 ">
        <div class="student-container">
            <div class="left">
                <div style="width:100%;text-align: center;">
                    <img src="/images/443625372.jpeg" class="img-thumbnail" style="width:50%;margin-top: 30px;"
                         alt="error">
                </div>
                <div style="text-align:center;">
                    <br/>
                    <span>姓名：${user.userName}</span>
                    <br/>
                    <span>工号：${user.userId}</span>
                    <br/>
                    <span><a href="/Logout">退出登录</a></span>
                </div>
            </div>
            <div class="right">
                <div>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist" id="myTabs">
                        <li role="presentation" class="active" onclick="foo(this)"><a href="#home"
                                                                                      aria-controls="home" role="tab"
                                                                                      data-toggle="tab">流程管理</a></li>
                        <li role="presentation" onclick="foo(this)"><a href="#result"
                                                                       aria-controls="profile" role="tab"
                                                                       data-toggle="tab">签到时间管理</a></li>
                        <li role="presentation" onclick="foo(this)"><a href="#score" aria-controls="messages"
                                                                       role="tab" data-toggle="tab">通知发布</a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <%-- 流程控制页面 --%>
                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                            <br/>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    系统流程控制管理
                                </div>
                                <div class="panel-body">
                                    <form action="/Admin/UpdateControl" method="post">
                                    <div class="form-horizontal">
                                        <span>课题发布时间</span><br />
                                        <div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label" for="pubSubjectStartTime">开始时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="pubSubjectStartTime"
                                                       id="pubSubjectStartTime">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="pubSubjectEndTime">结束时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="pubSubjectEndTime"
                                                       id="pubSubjectEndTime" >
                                                </div>
                                                </div>
                                        </div>
                                    </div>
                                    <div class="form-horizontal">
                                        <span>学生选题时间</span><br />
                                        <div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="selectSubjectStartTime">开始时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="selectSubjectStartTime"
                                                       id="selectSubjectStartTime">
                                                </div>
                                                </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="selectSubjectEndTime">结束时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="selectSubjectEndTime"
                                                       id="selectSubjectEndTime" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-horizontal">
                                        <span>成果提交时间</span> <br />
                                        <div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="uploadResultStartTime">开始时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="uploadResultStartTime"
                                                       id="uploadResultStartTime">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="uploadResultEndTime">结束时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="uploadResultEndTime"
                                                       id="uploadResultEndTime" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-horizontal">
                                        <span>成绩发布时间</span> <br />
                                        <div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="pubGradeStartTime">开始时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="pubGradeStartTime"
                                                       id="pubGradeStartTime">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"  for="pubGradeEndTime">结束时间</label>
                                                <div class="col-sm-10">
                                                <input type="date" class="form-control" name="pubGradeEndTime"
                                                       id="pubGradeEndTime" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-horizontal">
                                        <span>签到时间管理</span><br />
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="checkInTime">签到时间</label>
                                        <div class="col-sm-10">
                                            <input type="time" class="form-control" name="checkInTime"
                                                   id="checkInTime">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"  for="checkOutTime">签退时间</label>
                                        <div class="col-sm-10">
                                            <input type="time" class="form-control" name="checkOutTime"
                                                   id="checkOutTime" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"  for="checkInResetTime">重置时间</label>
                                        <div class="col-sm-10">
                                            <input type="time" class="form-control" name="checkInResetTime"
                                                   id="checkInResetTime" >
                                        </div>
                                    </div>
                                    </div>
                                    <input type="submit" class="col-sm-offset-2 btn btn-primary" value="提交">
                                </form>
                                </div>
                            </div>
                        </div>
                        <%-- 签到时间控制 --%>
                        <div role="tabpanel" class="tab-pane" id="result"><br/>
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading">签到时间管理</div>
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <span>签到时间管理</span><br />
                                        <div>

                                            <button class="col-sm-offset-2 btn btn-primary" >提交</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%-- 通知发布 --%>
                        <div role="tabpanel" class="tab-pane" id="score"><br/>
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading">通知发布</div>
                                <div class="panel-body">
                                    <form action="/Notify/New"  enctype="multipart/form-data"  method="post" >
                                    <div class="form-group">
                                        <label class="control-label"  for="Title">标题</label>
                                        <div>
                                            <input type="text" class="form-control" name="title"
                                                   id="Title" >
                                        </div>
                                    </div>
                                    <label class="control-label"  for="Context">正文</label>
                                    <div>
                                        <textarea name="text" class="form-control" rows="5"
                                               id="Context" ></textarea>
                                    </div>
                                    <label class="control-label"  for="file">附件</label>
                                    <div>
                                        <input name="file" type="file"
                                               id="file" >
                                    </div>
                                        <br />
                                        <input type="submit" class="btn-primary btn" />
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="arrive">
                            <br/>
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading">签到查看</div>
                                <div class="panel-body">
                                    <p>查看所有的签到。现已签到<span>12</span>次</p>
                                </div>

                                <!-- Table -->
                                <table class="table">
                                    <tr>
                                        <td>#</td>
                                        <td>签到时间</td>
                                        <td>签退时间</td>

                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>0:0:0</td>
                                        <td>12:0:0</td>

                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

</body>

<script>
    var tabList = $("#myTabs");

    $('#myTabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show')

    });

    function foo(e) {

        var liList = tabList.find("li");

        for (var i = 0; i < liList.length; i++) {
            liList[i].removeAttribute('class');
        }

        e.className = "active";

    }

    ajax("/Admin/controlTime", QueryControlTimeCallback, "GET");
</script>
</html>
