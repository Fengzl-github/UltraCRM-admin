<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/5/21 14:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@include file="../ut_controls/PageHeaderMeta.jspf" %>
    <script type="text/javascript" src="${rootURL}/common/map/bdMap.js"></script>
</head>
<body v-cloak>

    <input id="text" type="text"/>
    <button onclick="send()">Send</button>
    <button onclick="close()">Close</button>
    <div id="message"></div>


<script>
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:9090/websocket/8600");//此处地址在分情况测试的时候需要修改
    } else {
        alert('Not support websocket')
    }

    //发生错误时
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };

    //成功建立连接时
    websocket.onopen = function (event) {
        //setMessageInnerHTML("open");
    }

    //接收到消息时
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //关闭连接时
    websocket.onclose = function () {
        setMessageInnerHTML("close");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    //显示服务器端返回的消息
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭连接按钮
    function close() {
        console.log('关闭');
        websocket.close();
    }

    //发送消息按钮
    function send() {
        console.log('发送');
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</body>

<style>

</style>
</html>
