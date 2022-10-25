<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/4/30 10:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>UltraCRM-admin</title>
    <%@include file="/ut_controls/PageHeaderMeta.jspf" %>
</head>
<body>

<%-- 内容 --%>
<div class="content">
    <div class="loginCard">
        <div id="vmApp">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span><i class="el-icon-platform-eleme"> UltraCRM-admin 后台管理系统</i></span>
                </div>
                <div>
                    <el-form :model="form" ref="form">
                        <el-form-item prop="ghid" :rules="[{required: true, message: '账号不能为空'}]">
                            <el-input v-model="form.ghid" clearable auto-complete="off" placeholder="账号"
                                      @keyup.enter.native="login"></el-input>
                        </el-form-item>
                        <el-form-item prop="password" :rules="[{required: true, message: '密码不能为空'}]">
                            <el-input id="password" v-model="form.password" show-password auto-complete="off" placeholder="密码"
                                      @keyup.enter.native="login"></el-input>
                        </el-form-item>
                    </el-form>
                    <div>
                        <el-checkbox v-model="checked" @change="rememberPwd">记住密码</el-checkbox>
                        <span style="color:red; padding-left: 15%;">
                        {{retInfo}}
                    </span>
                    </div>
                    <el-row style="padding-left:75%;">
                        <el-button type="success" @click="login">登录</el-button>
                    </el-row>
                </div>
            </el-card>

        </div>
    </div>
</div>

<script src="${rootURL}/common/other/L2Dwidget.min.js"></script>
<script>
    L2Dwidget.init({
        "model": {
            //jsonpath控制显示那个小萝莉模型，下面这个就是我觉得最可爱的小萝莉模型，替换时后面名字也要替换掉
            jsonPath: "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json",
            "scale": 1
        },
        "display": {
            "position": "right", //看板娘的表现位置
            "width": 150,  //小萝莉的宽度
            "height": 300, //小萝莉的高度
            "hOffset": 0,
            "vOffset": -20
        },
        "mobile": {
            "show": true,
            "scale": 0.5
        },
        "react": {
            "opacityDefault": 0.7,
            "opacityOnHover": 0.2
        }
    });
</script>
<%-- 背景线条 --%>
<script src="${rootURL}/common/other/canvas-nest.min.js"></script>
</body>


<script>

    let vData = {
        form: {
            ghid: '',
            password: ''
        },
        checked: false,
        retInfo: ''
    };

    let vm = new Vue({
        el: '#vmApp',
        data: vData,
        methods: {
            login() {
                vm.$refs['form'].validate((valid) => {
                    if (valid) {
                        vData.retInfo = '';
                        submitLogin();
                    } else {
                        return false;
                    }
                });
            },
            rememberPwd() {
                console.log('记住密码');
            }
        }
    });

    function submitLogin() {
        axios.post('${rootURL}/login/verification', vData.form)
            .then(function (response) {
                let res = response.data;
                console.log(res);
                if (res.code == 200) {
                    let token = res.data.token;
                    let user = res.data.content;
                    myUtil.setToken(token);
                    window.location.href = "/page/myindex"; //后台做跳转
                    myUtil.setPmAgent(JSON.stringify(user));
                } else {
                    vData.retInfo = res.msg;
                }
            })
            .catch(function (error) {
                console.log("系统异常");
            })

    }
</script>

<style>
    /* rgb对照表：
    https://bj.96weixin.com/tools/rgb */
    html,
    body {
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
        background-image: url("${rootURL}/images/bg.jpg");
        overflow-x: hidden;
        overflow-y: hidden;
        background-size: 100%;
    }

    .content {
        width: 99%;
        height: 99%;
        display: flex;
        align-items: center; /*垂直居中*/
        justify-content: center; /*水平居中*/
    }

    .loginCard {
        width: 400px;
        height: 300px;
        font-family: "lato", sans-serif;
        font-weight: 300;
        z-index: 1;
    }
</style>
</html>
