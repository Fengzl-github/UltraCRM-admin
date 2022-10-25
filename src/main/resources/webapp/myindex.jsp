<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/5/14 14:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>UltraCRM-admin</title>
    <%@include file="ut_controls/PageHeaderMeta.jspf" %>
</head>
<body>
<div id="appVM" v-cloak>
    <el-container :style="mainStyle" class="page-sub-main">
        <el-header class="header">
            <el-menu class="el-menu-demo" mode="horizontal" :background-color="styleColor" :text-color="textColor"
                     active-text-color="#FDFCFD52">
                <el-menu-item v-show="!asideStyle.isCollapse" class="flLogo">UltraCRM_admin</el-menu-item>
                <el-menu-item v-show="asideStyle.isCollapse" class="flLogo2" :style="{color:styleColor}">
                    <svg t="1563351300111" class="icon" viewBox="0 0 1024 1024" version="1.1"
                         xmlns="http://www.w3.org/2000/svg" p-id="3372" width="32" height="32">
                        <path
                            d="M224 272c-27.2 0-48-20.8-48-48s20.8-48 48-48 48 20.8 48 48-20.8 48-48 48z m576 0c-27.2 0-48-20.8-48-48s20.8-48 48-48 48 20.8 48 48-20.8 48-48 48zM512 864c-180.8 0-344-108.8-412.8-275.2-6.4-16 1.6-35.2 17.6-41.6 16-6.4 35.2 1.6 41.6 17.6C217.6 707.2 356.8 800 512 800c155.2 0 294.4-92.8 355.2-236.8 6.4-16 25.6-24 41.6-17.6 16 6.4 24 25.6 17.6 41.6C856 755.2 692.8 864 512 864z"
                            p-id="3373" :fill="textColor">
                        </path>
                    </svg>
                </el-menu-item>

                <el-menu-item style="float:left;" @click="toggleClick">
                    <svg :class="{'hamis-active':asideStyle.isCollapse}" class="hamburger"
                         viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="164" height="164">
                        <path fill="#FFF"
                              d="M408 442h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8zm-8 204c0 4.4 3.6 8 8 8h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56zm504-486H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 632H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM142.4 642.1L298.7 519a8.84 8.84 0 0 0 0-13.9L142.4 381.9c-5.8-4.6-14.4-.5-14.4 6.9v246.3a8.9 8.9 0 0 0 14.4 7z"/>
                    </svg>
                </el-menu-item>

                <el-menu-item class="fr">
                    <el-dropdown @command="handleCommand" trigger="click">
                        <div class="block">
                            <el-avatar :size="50" :src="userInfo.headUrl" @error="errorHandler">
                                --
                            </el-avatar>
                            <span :style="{color:textColor}"> {{userInfo.loginName}} </span>
                        </div>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="lout">注销</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-menu-item>
                <el-menu-item class="fr">
                    <div class="block">
                        <el-color-picker v-model="styleColor" show-alpha :predefine="predefineColors"
                                         @change="changeStyleColor(0)">
                        </el-color-picker>
                        <el-color-picker v-model="textColor" size="small" :predefine="predefineColors"
                                         @change="changeStyleColor(1)">
                        </el-color-picker>
                    </div>
                </el-menu-item>
                <el-menu-item class="fr">
                    <i class="el-icon-bell"></i>
                </el-menu-item>
            </el-menu>
        </el-header>
        <el-container>
            <el-aside :width="asideStyle.asidewidth">
                <el-menu :collapse="asideStyle.isCollapse" :collapse-transition="false" :background-color="styleColor"
                         :text-color="textColor" active-text-color="#ffd04b" style="height:100%;">
                    <template v-for="item in MenuItems">
                        <template v-if="item.children">
                            <el-submenu :index="item.id" :key="item.id">
                                <template slot="title">
                                    <i :class="item.icon" :style="iconStyle()"></i>
                                    <span slot="title">{{ item.title }}</span>
                                </template>
                                <template v-for="subItem2 in item.children">
                                    <template v-if="subItem2.children">
                                        <el-submenu :index="subItem2.id" :key="subItem2.id">
                                            <template slot="title">
                                                <i :class="subItem2.icon" :style="iconStyle()"></i>
                                                <span slot="title">{{ subItem2.title }}</span>
                                            </template>
                                            <template v-for="subItem3 in subItem2.children">
                                                <template v-if="subItem3.children">
                                                    <el-submenu :index="subItem3.id" :key="subItem3.id">
                                                        <template slot="title">
                                                            <i :class="subItem3.icon" :style="iconStyle()"></i>
                                                            <span slot="title">{{ subItem3.title }}</span>
                                                        </template>
                                                        <template v-for="subItem4 in subItem3.children">
                                                            <template v-if="subItem4.children">
                                                                <el-submenu :index="subItem4.id" :key="subItem4.id">
                                                                    <template slot="title">
                                                                        <i :class="subItem4.icon"
                                                                           :style="iconStyle()"></i>
                                                                        <span slot="title">{{ subItem4.title }}</span>
                                                                    </template>
                                                                    <template v-for="subItem5 in subItem4.children">
                                                                        <el-menu-item :index="subItem5.id"
                                                                                      :key="subItem5.id"
                                                                                      @click="addTab(subItem5.id, subItem5.title, subItem5.url)">
                                                                            <i :class="subItem5.icon"
                                                                               :style="iconStyle()"></i>
                                                                            <span
                                                                                slot="title">{{ subItem5.title }}</span>
                                                                        </el-menu-item>
                                                                    </template>
                                                                </el-submenu>
                                                            </template>
                                                            <template v-else>
                                                                <el-menu-item :index="subItem4.id" :key="subItem4.id"
                                                                              @click="addTab(subItem4.id, subItem4.title, subItem4.url)">
                                                                    <i :class="subItem4.icon" :style="iconStyle()"></i>
                                                                    <span slot="title">{{ subItem4.title }}</span>
                                                                </el-menu-item>
                                                            </template>
                                                        </template>
                                                    </el-submenu>
                                                </template>
                                                <template v-else>
                                                    <el-menu-item :index="subItem3.id" :key="subItem3.id"
                                                                  @click="addTab(subItem3.id, subItem3.title, subItem3.url)">
                                                        <i :class="subItem3.icon" :style="iconStyle()"></i>
                                                        <span slot="title">{{ subItem3.title }}</span>
                                                    </el-menu-item>
                                                </template>
                                            </template>
                                        </el-submenu>
                                    </template>
                                    <template v-else>
                                        <el-menu-item :index="subItem2.id" :key="subItem2.id"
                                                      @click="addTab(subItem2.id, subItem2.title, subItem2.url)">
                                            <i :class="subItem2.icon" :style="iconStyle()"></i>
                                            <span slot="title">{{ subItem2.title }}</span>
                                        </el-menu-item>
                                    </template>
                                </template>
                            </el-submenu>
                        </template>
                        <template v-else>
                            <el-menu-item :index="item.id" :key="item.id"
                                          @click="addTab(item.id, item.title, item.url)">
                                <i :class="item.icon" :style="iconStyle()"></i>
                                <span slot="title">{{ item.title }}</span>
                            </el-menu-item>
                        </template>
                    </template>
                </el-menu>
            </el-aside>
            <el-main>
                <div>
                    <el-tabs v-model="tabValue" @tab-remove="removeTab">
                        <el-tab-pane key='a' name="a">
                            <span slot="label"><i class="el-icon-bell"></i>首页</span>
                            <iframe src="/page/main" scrolling="auto" frameborder="0" marginheight="0"
                                    marginwidth="0" :style="frameStyle"></iframe>
                        </el-tab-pane>
                        <el-tab-pane closable v-for="(item,index) in ableTabs" :key="item.key" :name="item.name">
                            <span slot="label" @dblclick="dblclickIfream(item,index)">{{item.title}}</span>
                            <iframe :id="item.key"
                                    :src="(item.tabUrl).indexOf('?') != -1 ? item.tabUrl+'&index='+index : item.tabUrl+'?index='+index"
                                    scrolling="auto" frameborder="0"
                                    marginheight="0" marginwidth="0" :style="frameStyle"></iframe>
                        </el-tab-pane>
                    </el-tabs>
                    <div style="position: absolute;width: 40px;height: 40px;top: 75px;right: 0px;">
                        <el-dropdown @command="handleCommand">
                            <i class="el-icon-arrow-down el-icon--right"></i>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="closec">关闭当前选项卡</el-dropdown-item>
                                <el-dropdown-item command="closeother">关闭其他选项卡</el-dropdown-item>
                                <el-dropdown-item command="closeall">关闭全部选项卡</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </div>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script>
    var vData = {
        defalutColor: 'rgb(25, 50, 92)',
        userInfo: {
            loginName: '',
            headUrl: '${rootURL}/images/tx.jpg',
        },
        MenuItems: [],
        tabValue: 'a',
        tabIndex: 0,
        ableTabs: [],
        mainStyle: {
            minHeight: (window.innerHeight - 100) + 'px'
        },
        asideStyle: {
            asidewidth: "250px",
            isCollapse: false
        },
        frameStyle: "minHeight:" + (window.innerHeight - 140) + "px;width:100%;",
        styleColor: 'rgb(25, 50, 92)',
        textColor: '#DBE7E7',
        predefineColors: [
            '#ff4500',
            '#ff8c00',
            '#ffd700',
            '#90ee90',
            '#00ced1',
            '#1e90ff',
            '#c71585',
            'rgba(255, 69, 0, 0.68)',
            'rgb(255, 120, 0)',
            'hsv(51, 100, 98)',
            'hsva(120, 40, 94, 0.5)',
            'hsl(181, 100%, 37%)',
            'hsla(209, 100%, 56%, 0.73)',
            '#c7158577'
        ]
    };

    var vm = new Vue({
        el: "#appVM",
        data: vData,
        computed: {
            iconStyle() {
                return function () {
                    return "color:" + vData.textColor + " !important;";
                }
            }
        },
        methods: {
            changeStyleColor,
            handleCommand,
            toggleClick,
            errorHandler,
            addTab,
            removeTab,
            dblclickIfream
        }


    });

    function changeStyleColor(nType) {
        if (nType === 0) {
            ThemeColor(1);
        } else if (nType === 1) {
            TextColor(1);
        }
    }


    function handleCommand(command) {
        if (command === "lout") {
            logoutToRedict();
        } else if (command == "closec") {
            removeTab(vData.tabValue);
        } else if (command == "closeother") {
            vData.ableTabs = vData.ableTabs.filter(tab => tab.name === vData.tabValue);
        } else if (command == "closeall") {
            vData.ableTabs = [];
            vData.tabValue = 'a';
            vData.tabIndex = 0;
        }
    }

    function errorHandler() {
        return true;
    }

    function addTab(id, title, url) {
        let activeName = "";
        let tabs = vData.ableTabs;
        tabs.forEach((tab, index) => {
            if (tab.key == id) {
                activeName = tab.name;
            }
        });
        if (activeName.length > 0) {
            vData.tabValue = activeName;
            return;
        }

        let newTabName = ++vData.tabIndex + '';
        vData.ableTabs.push({
            key: id,
            name: newTabName,
            title: title,
            tabUrl: url
        });
        vData.tabValue = newTabName;
    }

    function removeTab(targetName) {
        let tabs = vData.ableTabs;
        let activeName = vData.tabValue;
        if (activeName == targetName) {
            tabs.forEach((tab, index) => {
                if (tab.name == targetName) {
                    let nexTab = tabs[index + 1] || tabs[index - 1];
                    if (nexTab) {
                        activeName = nexTab.name;
                    } else {
                        activeName = 'a';
                    }
                }
            });
        }
        vData.tabValue = activeName;
        vData.ableTabs = tabs.filter(tab => tab.name !== targetName);
    }

    function toggleClick() { //切换左侧菜单折叠效果
        vData.asideStyle.isCollapse = !vData.asideStyle.isCollapse;
        vData.asideStyle.asidewidth = (vData.asideStyle.isCollapse == true ? "65px" : "250px");
    }


    $(function () {
        /*获取用户数据*/
        var userInfo = myUtil.getPmAgent();
        const pmAgent = JSON.parse(userInfo);
        console.log("pmAgent", pmAgent);
        vData.userInfo.loginName = pmAgent.name;
        if (pmAgent['headUrl'] && pmAgent.headUrl.trim() !== '') {
            vData.userInfo.headUrl = pmAgent.headUrl;
        }
        ThemeColor(0);
        TextColor(0);
        getMenuData();

    });


    function getMenuData() {
        axios.post('${rootURL}/getMenuData')
            .then(function (response) {
                let res = response.data;
                console.log(res);
                vData.MenuItems = res.data;
            })
            .catch(function (error) {

            })

    }

    function ThemeColor(nType) {
        if (nType == 0) { //初始化读取主题颜色
            let lColor = myUtil.getMyTheme();
            if (lColor == null || lColor.length == 0) {
                lColor = vData.defalutColor;
            }
            vData.styleColor = lColor;

        } else if (nType == 1) { //设置主题颜色
            myUtil.setMyTheme(vData.styleColor);
        }
    }

    function TextColor(nType) {
        if (nType == 0) { //初始化读取字体颜色
            let lColor = myUtil.getMyText()
            if (lColor == null || lColor.length == 0) {
                lColor = vData.textColor;
            }
            vData.textColor = lColor;

        } else if (nType == 1) { //设置字体颜色
            myUtil.setMyText(vData.textColor);
        }
    }

    //双击刷新
    function dblclickIfream(item, index) {
        document.getElementById(item.key).contentWindow.location.reload(true);
    }

</script>
<style>
    [v-cloak] {
        display: none;
    }

    .hamburger {
        display: inline-block;
        vertical-align: middle;
        width: 25px;
        height: 25px;
    }

    .hamis-active {
        transform: rotate(180deg);
    }

    .sidebar {
        width: 250px;
        position: absolute;
        top: 0;
        bottom: 0;
        border-right: 1px solid #e6e6e6b6;
        background-color: #ffffff;
        z-index: 99;
    }

    .sidebar-hide {
        width: 65px;
    }

    .el-container {
        position: absolute;
        width: 100%;
        top: 0px;
        left: 0;
        bottom: 0;
    }

    .el-header {
        padding: 0;
        z-index: 1000;

    }


    .el-menu-item:hover {
        background-color: rgba(255, 255, 255, 0.3) !important;
    }

    /* header菜单需要靠右的添加.fr即可(如：<el-menu-item class="fr" index="3">消息中心</el-menu-item>) */
    .el-header .fr {
        float: right;
        vertical-align: middle;
    }

    .el-header .flLogo {
        float: left;
        width: 250px;
        font-size: larger;
        font-weight: bold;
        border-right: 1px solid #c0c4cc;
        text-align: center;
        cursor: pointer;
    }

    .el-header .flLogo2 {
        float: left;
        width: 65px;
        font-size: larger;
        font-weight: bold;
        border-right: 1px solid #c0c4cc;
        text-align: center;
        cursor: pointer;
    }


    .el-header .el-menu {
        border-bottom: none;
    }

    .el-aside,
    .el-main {
        padding-top: 60px;
    }

    .nav-img {
        width: 30px;
        height: 30px;
        margin-right: 10px;
        border-radius: 50%;
    }

    .el-tooltip {
        display: inherit !important;
    }

    * {
        padding: 0;
        margin: 0;
    }

    html,
    body {
        width: 100%;
        height: 100%;
        background: #545c640a;
    }
</style>
</html>
