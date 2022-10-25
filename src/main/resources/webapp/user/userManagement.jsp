<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/6/28 15:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户管理</title>
    <%@ include file="/ut_controls/PageHeaderMeta.jspf" %>
</head>
<body>
<div id="vmApp" v-cloak>
    <el-header>
        <el-button type="danger" plain size="mini">关闭</el-button>
    </el-header>
    <div class="main">
        <div class="left">
            左边
        </div>
        <div class="right">
            右边
        </div>

    </div>
</div>


</body>

<script>
    let vData = {};

    let vm = new Vue({
        el: '#vmApp',
        data: vData,
        methods: {}
    })

</script>

<style>
    body #vmApp {
        width: 100%;
        height: 100%;
    }

    [v-cloak] {
        display: none;
    }

    .el-header {
        line-height: 5%;
    }

    .main {
        display: flex;
        width: 98%;
        height: 90%;
    }

    .left {
        width: 50%;
        height: 90%;
    }
    .right{
        width: 50%;
        height: 90%;
    }
</style>
</html>
