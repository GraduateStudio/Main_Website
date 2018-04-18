$(document).ready(function () {
    Date.prototype.format = function (format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes() < 10 ? "0" + this.getMinutes() : this.getMinutes(),
            "s+": this.getSeconds() < 10 ? "0" + this.getSeconds() : this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ?
                    date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
    }
});

var websocket;
//如果浏览器支持WebSocket
if (window.WebSocket) {
    websocket = new WebSocket("ws://192.168.0.100:11110/ws");  //获得WebSocket对象

    //当有消息过来的时候触发
    websocket.onmessage = function (event) {
        var msg = event.data;
        if (msg.indexOf("!!A!A!A!!nowConnectCount:") > -1) {
            $("#count").html(msg.substr(25));
            return;
        }
        if (msg.indexOf("!!A!A!A!!initHistOryMessage:") > -1) {
            var resBox = $("#respMessage");
            msg = msg.substring(28);
            var msgarr = msg.split("!!A!A!A!!sOmeBodySay:");
            for (var i = 0; i < msgarr.length - 1; i++) {
                if (i % 2 == 0) {
                    resBox.val(resBox.val() + new Date(parseInt(msgarr[i])).format('yyyy-MM-dd h:m:s') + " 某人：\n");
                } else {
                    resBox.val(resBox.val() + msgarr[i] + "\n");
                }
            }
            return;
        }
        var respMessage = document.getElementById("respMessage");

        respMessage.value = event.data + "\n" + respMessage.value;
    }

    //连接关闭的时候触发
    websocket.onclose = function (event) {
        var respMessage = document.getElementById("respMessage");
        respMessage.value = respMessage.value + "\n断开连接";
    }

    //连接打开的时候触发
    websocket.onopen = function (event) {
        websocket.send("!!A!A!A!!"); //send()发送消息
        $("#respMessage").val("");
    }
} else {
    alert("浏览器不支持WebSocket");
}

function sendMsg(msg) { //发送消息
    var now = new Date().getTime();
    var min = (now - $.cookie('lastpush')) / 1000 / 60;
    var ss = 60 - Math.floor(min * 60);
    if (min < 1) {
        alert("性能有限,一分钟只能发一条\n距离下次发送时间:" + ss+"秒");
        return;
    }
    if (!msg || msg == "" ||msg.trim() == "") {
        alert("请勿发送空内容");
        $("#reqMessage").val("");
        return;
    }
    if (window.WebSocket) {
        if (websocket.readyState == WebSocket.OPEN) { //如果WebSocket是打开状态
            websocket.send("!!A!A!A!!sOmeBodySay:" + msg); //send()发送消息
            $("#reqMessage").val("");
            $.cookie('lastpush', new Date().getTime());
        }
    } else {
        return;
    }
}



