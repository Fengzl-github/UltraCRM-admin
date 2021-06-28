<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/5/21 14:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>模板页</title>
    <%@include file="../ut_controls/PageHeaderMeta.jspf" %>
</head>
<body>
<div id="vmApp" v-cloak>

</div>

<script>
    let vData = {};

    let vm = new Vue({
        el: '#vmApp',
        data: vData,
        methods: {}
    });

    $(function () {

    })
</script>
</body>

<style>
    [v-cloak] {
        display: none;
    }
</style>
</html>
