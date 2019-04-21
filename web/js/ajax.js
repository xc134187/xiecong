function ajax(url, callback, method) {
    xhr = new XMLHttpRequest();
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

function CheckInCallback(context) {
    var data = JSON.parse(context);
    if(data.Checkin =='checkin' && data.result==true){
        document.getElementById("btn-check-in").innerHTML = "签退";
        document.getElementById("btn-check-in").onclick=ajax("/User/Checkout", this, "GET");
        alert("签到成功");
    }else if(data.Checkin == "checkout" && data.result == true){
        alert("签到成功");
    }else {
        alert("签到失败"+data.message);
    }
}

function QueryTeacherCallback(context) {
    var data = JSON.parse(context);
    for (var i = 0; i < data.length; i++){
        var elem = document.createElement("li");
        elem.innerHTML = data[i].userName;
        elem.setAttribute("class", "list-group-item");
        elem.setAttribute("href", "/Subject/search_teacher_id?teacher_id="+data[i].userId);
        elem.onclick = elemClick(elem);
        document.getElementById("teacher-list").appendChild(elem);
    }
}

function QueryTeacherSubjectCallback(context) {
    var data = JSON.parse(context);
    for(var i = 0; i<data.length;i++ ){
        var rowNum = document.getElementById("subject-list").length;
        var row = document.getElementById("subject-list").insertRow();
        row.insertCell(0).innerHTML = data[i].subjectName;
        row.insertCell(1).innerHTML = data[i].subjectKind;
        row.insertCell(2).innerHTML = data[i].teacherName;
        row.insertCell(3).innerHTML = data[i].maxSelectNum;
        row.insertCell(4).innerHTML = data[i].currSelectNum;
        row.onclick = TableRowClick(data[i]);
    }
}

function TableRowClick(obj) {
    return function () {
        document.getElementById("subject-id").value = obj.subjectId;
        document.getElementById("subject-name").innerText = '课程名称：'+obj.subjectName;
        document.getElementById("subject-teacher").innerText = '指导教师：'+obj.teacherName;
    }
}

function elemClick(obj) {
    return function () {
        var lis = document.getElementById("teacher-list").children;
        for(var i =0; i<lis.length; i++){
            lis[i].setAttribute("class", "list-group-item");
        }
        var table = document.getElementById("subject-list");
        var len = table.rows.length;
        for(var i =1; i<len; i++){
            table.rows[i].remove();
        }
        obj.setAttribute("class", "list-group-item active");
        ajax(obj.getAttribute("href"), QueryTeacherSubjectCallback, "GET");
    }
}

function selectSubject() {
    ajax("/Subject/SelectSubject?subjectId="+document.getElementById("subject-id").value,
        selectSubjectCallback,
        "GET")
}

function selectSubjectCallback() {

}