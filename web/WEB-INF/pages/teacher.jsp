<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<style>
    body {
        padding: 00px 50px;
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
</style>

<body>

    <div class="page-header">
        <h1>教师课题管理
            <small>教师信息管理</small>
        </h1>
    </div>


    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 ">
            <div class="student-container">
                <div class="left">
                    <div style="width:100%;text-align: center;">
                        <img src="/images/443625372.jpeg" class="img-thumbnail" style="width:50%;margin-top: 30px;">
                    </div>
                    <div style="text-align:center;">
                        <br />
                        <span>工号:</span>
                        <br />
                        <span>姓名:</span>
                        <br />
                        <span><a href="#">退出登录</a></span>
                    </div>

                </div>
                <div class="right">
                    <div>

                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist" id="myTabs">
                            <li role="presentation" class="active" onclick="foo(this)"><a href="#home"
                                    aria-controls="home" role="tab" data-toggle="tab">课题管理</a></li>
                            <li role="presentation" onclick="foo(this)"><a href="#profile" aria-controls="profile"
                                    role="tab" data-toggle="tab">查看学生成果</a></li>
                            <li role="presentation" onclick="foo(this)"><a href="#course" aria-controls="course"
                                    role="tab" data-toggle="tab">查看学生选题</a></li>
                            <li role="presentation" onclick="foo(this)"><a href="#messages" aria-controls="messages"
                                    role="tab" data-toggle="tab">学生成绩录入</a></li>
                            <li role="presentation" onclick="foo(this)"><a href="#settings" aria-controls="settings"
                                    role="tab" data-toggle="tab">签到查看</a></li>
                        </ul>

                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                <br />
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">课题录入区域</div>
                                    <div class="panel-body">
                                        <p>在此可以添加、修改、删除选定的课题</p>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary btn-small" data-toggle="modal"
                                            data-target="#myModal">
                                            录入课题
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                            aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close"><span
                                                                aria-hidden="true">&times;</span></button>
                                                        <span class="modal-title" id="myModalLabel1">课题录入</span>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div>
                                                            <div class="form-group">
                                                                <label for="courseName">课题名称</label>
                                                                <input type="text" class="form-control" id="courseName"
                                                                    placeholder="请输入课题名称">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default"
                                                            data-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-primary"
                                                            onclick="addCourse(this)">提交
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Table -->
                                    <table class="table">
                                        <thead>
                                            <td>课题名称</td>
                                            <td>操作</td>
                                        </thead>
                                        <tr>
                                            <td style="display:table-cell; vertical-align:middle;font-size: 18px;">
                                                我是录入课题
                                            </td>
                                            <td>
                                                <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-primary btn-small"
                                                    data-toggle="modal" th:id=""
                                                    data-target="#courseUpdate" style="font-size:10px;">
                                                    修改课题
                                                </button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="courseUpdate" tabindex="-1" role="dialog"
                                                    aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close"><span
                                                                        aria-hidden="true">&times;</span></button>
                                                                <h4 class="modal-title" id="myModalLabel">课题修改</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form>
                                                                    <div class="form-group">
                                                                        <label for="updateCourseName">课题名称</label>
                                                                        <input type="text" class="form-control"
                                                                            id="updateCourseName" placeholder="请输入课题名称">
                                                                    </div>
                                                                </form>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default"
                                                                    data-dismiss="modal">Close
                                                                </button>
                                                                <button type="button" class="btn btn-primary">修改
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-primary btn-small"
                                                    data-toggle="modal" th:id="${'delete-'+course.id}"
                                                    data-target="#courseDelete" style="font-size:10px;">
                                                    删除课题
                                                </button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="courseDelete" tabindex="-1" role="dialog"
                                                    aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close"><span
                                                                        aria-hidden="true">&times;</span></button>
                                                                <h4 class="modal-title" id="myModalLabelText">课题删除</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                是否删除该课题
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default"
                                                                    data-dismiss="modal">Close
                                                                </button>
                                                                <button type="button" class="btn btn-primary">删除
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>

                                    </table>

                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="profile"><br />
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">成果查看区域</div>
                                    <div class="panel-body">
                                        <p>在此可以查看学生上传的文件信息</p>
                                    </div>

                                    <!-- Table -->
                                    <table class="table">
                                        <tr>
                                            <td style="width:50%;">课题名称</td>
                                            <td style="width:20%">学号</td>
                                            <td style="width:10%">姓名</td>
                                            <td style="width:20%">操作</td>
                                        </tr>
                                        <tr>
                                            <td>基于SSH的二维码线上点餐系统</td>
                                            <td>201610804024</td>
                                            <td>Impassive</td>
                                            <td>
                                                <a href="">下载</a>
                                            </td>
                                        </tr>
                                        <tr></tr>
                                    </table>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="course"><br />
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">选题信息</div>
                                    <div class="panel-body">
                                        <p>查看所有的学生选题信息，并审核选题是否满足</p>
                                    </div>

                                    <!-- Table -->
                                    <table class="table">
                                        <tr>
                                            <td style="width:50%">课题名称</td>
                                            <td style="width:10%">选题者</td>
                                            <td style="width:20%">状态</td>
                                            <td style="width:20%">操作</td>
                                        </tr>
                                        <tr>
                                            <td>基于SSH的二维码线上点餐系统</td>
                                            <td>Impassive</td>
                                            <td>未审核</td>
                                            <td>
                                                <button class="btn-primary">通过</button>
                                            </td>
                                        </tr>
                                        <tr></tr>
                                    </table>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="messages"><br />
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">成绩录入区域</div>
                                    <div class="panel-body">
                                        <p>在此处录入学生成绩，录入后不可修改</p>
                                    </div>

                                    <!-- Table -->
                                    <table class="table">
                                        <tr>
                                            <td>课题名称</td>
                                            <td>学生</td>
                                            <td>平时成绩</td>
                                            <td>期末成绩</td>
                                            <td>录入</td>
                                        </tr>
                                        <tr>
                                            <td>基于SSH的二维码线上点餐系统</td>
                                            <td>Impassive</td>
                                            <td><input type="text" value="90" style="width:50px;"></td>
                                            <td><input type="text" value="90" style="width:50px;"></td>
                                            <td>
                                                <button class="btn-primary">录入</button>
                                            </td>
                                        </tr>
                                        <tr></tr>
                                    </table>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="settings">
                                <br />
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">签到查看</div>
                                    <div class="panel-body">
                                        <p>查看所有的签到。</p>
                                    </div>

                                    <!-- Table -->
                                    <table class="table">
                                        <tr>
                                            <td>#</td>
                                            <td>签到时间</td>
                                            <td>签到时间</td>
                                            <td>签退时间</td>

                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>Impassive</td>
                                            <td>2019-04-13:18:00:00</td>
                                            <td>2019-04-13:18:00:00</td>
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
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/popper.min.js"></script>
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

    //foo();
</script>

</html>