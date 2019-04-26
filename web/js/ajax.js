function ajax(url, callback, method) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.send();
    xhr.onreadystatechange = function (ev) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.responseText);
        }
    }
}

function NotifyCallback(context) {
    var data = JSON.parse(context);
    var n = document.getElementById("notifications");
    for (var i = 0; i < 2; i++) {
        var a = document.createElement("a");
        a.setAttribute("href", '/Notify/Notify/' + data[i].notificationId);
        a.innerText = data[i].title + '-' + new Date(data[i].time).toLocaleString();
        n.appendChild(a);
        n.appendChild(document.createElement("br"));
    }
}

// 签到回调函数
function CheckInCallback(context) {
    var data = JSON.parse(context);
    if (data.checkin == 'checkin' && data.result == true) {
        document.getElementById("btn-check-in").innerHTML = "签退";
        document.getElementById("btn-check-in").onclick = function (ev) { ajax("/User/Checkout", CheckoutCallback, "GET"); };
        alert("签到成功");
    } else {
        alert(data.message);
    }
}

// 签退回调函数
function CheckoutCallback(context) {

    var data = JSON.parse(context);
    if (data.checkin == 'checkout' && data.result == true) {
        document.getElementById("btn-check-in").innerHTML = "已完成签到";
        document.getElementById("btn-check-in").disabled=true;
        alert("签退成功");
    } else {
        alert("签退失败" + data.message);
    }
}

function checkUserCheckin() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", '/User/isTodayCheckedIn', true);
    xhr.send();
    xhr.onreadystatechange = function (ev) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            if (data == null)
                return;
            if (data.CheckedIn== true) {
                document.getElementById("btn-check-in").innerHTML = "签退";
                document.getElementById("btn-check-in").onclick = function (ev) { ajax("/User/Checkout", CheckoutCallback, "GET"); };
            }
        }
    }
}

// 查询教师的回调函数
function QueryTeacherCallback(context) {
    var data = JSON.parse(context);
    for (var i = 0; i < data.length; i++) {
        var elem = document.createElement("li");
        elem.innerHTML = data[i].userName;
        elem.setAttribute("class", "list-group-item");
        elem.setAttribute("href", "/Subject/search_teacher_id?teacher_id=" + data[i].userId);
        elem.onclick = elemClick(elem);
        document.getElementById("teacher-list").appendChild(elem);
    }
}

// 学生端查询教师发布课题回调函数
function QueryTeacherSubjectCallback(context) {
    var data = JSON.parse(context);
    for (var i = 0; i < data.length; i++) {
        var rowNum = document.getElementById("subject-list").rows.length;
        var row = document.getElementById("subject-list").insertRow(rowNum);
        row.insertCell(0).innerHTML = data[i].subjectName;
        row.insertCell(1).innerHTML = data[i].subjectKind;
        row.insertCell(2).innerHTML = data[i].userName;
        row.insertCell(3).innerHTML = data[i].maxSelectNum;
        row.insertCell(4).innerHTML = data[i].currSelectNum;
        row.onclick = TableRowClick(data[i]);
    }
}

// 学生端表格点击事件
function TableRowClick(obj) {
    return function () {
        document.getElementById("subject-id").value = obj.subjectId;
        document.getElementById("subject-name").innerText = '课程名称：' + obj.subjectName;
        document.getElementById("subject-teacher").innerText = '指导教师：' + obj.userName;
    }
}


// 学生端教师列表点击事件
function elemClick(obj) {
    return function () {
        var lis = document.getElementById("teacher-list").children;
        for (var i = 0; i < lis.length; i++) {
            lis[i].setAttribute("class", "list-group-item");
        }
        var table = document.getElementById("subject-list");
        var len = table.rows.length;
        for (var i = 2; i < len; i++) {
            table.rows[2].remove();
        }
        obj.setAttribute("class", "list-group-item active");
        ajax(obj.getAttribute("href"), QueryTeacherSubjectCallback, "GET");
    }
}

// 提交课题选择
function selectSubject() {
    ajax("/Subject/SelectSubject?subjectId=" + document.getElementById("subject-id").value,
        selectSubjectCallback,
        "GET")
}

// 选课回调函数
function selectSubjectCallback(context) {
    var data = JSON.parse(context);
    alert(data.message);
}

// 依据教师ID查询发布的课题
function QueryTeacherIdSubjectCallback(context) {
    var data = JSON.parse(context);
    for (var i = 0; i < data.length; i++) {
        var row = document.getElementById("subject-list").insertRow();
        row.insertCell(0).innerHTML = data[i].subjectName;
        row.insertCell(1).innerHTML = data[i].subjectKind;
        row.insertCell(2).innerHTML = data[i].maxSelectNum;
        row.insertCell(3).innerHTML = data[i].currSelectNum;
    }
}

function QueryStudentSubjectCallback(context) {
    var data = JSON.parse(context);
    var table = document.getElementById("subject-student-selected");
    var len = table.rows.length;
    for (var i = 1; i < len; i++) {
        table.rows[1].remove();
    }
    var row = table.insertRow();
    row.insertCell(0).innerHTML = data.subjectName;
    row.insertCell(1).innerHTML = data.subjectKind;
    row.insertCell(2).innerHTML = data.userName;
    row.insertCell(3).innerHTML = data.resultSubmitted ? "已提交" : "未提交";
}

// 教师查询选择其课题的学生列表
function QueryStudentsForTeacher(context) {
    var data = JSON.parse(context);
    var table = document.getElementById("student-select-teacher-subject-list");
    var len = table.rows.length;
    for (var i = 2; i < len; i++) {
        table.rows[2].remove();
    }
    for (var i = 0; i < data.length; i++) {
        var row = table.insertRow();
        row.insertCell(0).innerHTML = data[i].subjectName;
        row.insertCell(1).innerHTML = data[i].userName;
        row.insertCell(2).innerHTML = data[i].resultSubmitted ? "已提交" : "未提交";
        var url = data[i].resultSubmitted ? data[i].resultUrl : '#';
        row.insertCell(3).innerHTML = "<a href=" + url + "> 下载</a>";
    }
}




function QueryControlTimeCallback(context) {
    var data = JSON.parse(context);
    $("#pubSubjectStartTime").val(convertDate(new Date(data.pubSubjectStartTime)));
    $("#pubSubjectEndTime").val(convertDate(new Date(data.pubSubjectEndTime)));
    $("#selectSubjectStartTime").val(convertDate(new Date(data.selectSubjectStartTime)));
    $("#selectSubjectEndTime").val(convertDate(new Date(data.selectSubjectEndTime)));
    $("#uploadResultStartTime").val(convertDate(new Date(data.uploadResultStartTime)));
    $("#uploadResultEndTime").val(convertDate(new Date(data.uploadResultEndTime)));
    $("#pubGradeStartTime").val(convertDate(new Date(data.pubGradeStartTime)));
    $("#pubGradeEndTime").val(convertDate(new Date(data.pubGradeEndTime)));
    $("#checkInTime").val(convertTime( new Date(data.checkInTime)));
    $("#checkOutTime").val(convertTime(new Date(data.checkOutTime)));
    $("#checkInResetTime").val(convertTime(new Date(data.checkInResetTime)));
}

function convertDate(date) {
    var str = "";
    str += date.getFullYear()+'-';
    str += ((date.getMonth() + 1) < 10 ? '0'+(date.getMonth() + 1):date.getMonth() + 1)+'-';
    str += (date.getDate() < 10 ? '0' + date.getDate(): date.getDate());
    return str
}

function convertTime(time) {
    var str = "";
    str += (time.getHours() < 10 ? '0'+time.getHours():time.getHours())+':';
    str += (time.getMinutes() < 10 ) ? '0' + time.getMinutes(): time.getMinutes();
    return str;
}