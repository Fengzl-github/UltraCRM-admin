<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/5/21 14:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>地图</title>
    <%@include file="../ut_controls/PageHeaderMeta.jspf" %>
    <script type="text/javascript" src="${rootURL}/common/map/bdMap.js"></script>
</head>
<body v-cloak>
<div id="vmApp">
    <div id="map" style="height:300px;width:400px;border:1px solid #bcbcbc;"></div>
</div>

<script>
    let vData = {};

    let vm = new Vue({
        el: '#vmApp',
        data: vData,
        methods: {}
    });

    $(function () {
        ShowMap("map", {
            city:'北京',
            addr:'锋创科技园',
            ismove:0
        });
        console.log("本地坐标："+getBDAddress());
    })
</script>
</body>

<style>

</style>
</html>
