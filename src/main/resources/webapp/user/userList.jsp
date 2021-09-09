<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/5/21 14:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户列表</title>
    <%@include file="../ut_controls/PageHeaderMeta.jspf" %>
</head>
<body>
<div id="vmApp" v-cloak>
    <el-card>
        <%-- 查询模块 --%>
        <query-form :model="queryForm"></query-form>
        <%-- 列表模块 --%>
        <my-table :data="tableData"></my-table>
        <%--分页部分--%>
        <div class="block" v-if="pageable.total > 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageable.page"
                    :page-sizes="[10, 20, 50, 100]"
                    :page-size="pageable.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="pageable.total">
            </el-pagination>
        </div>


    </el-card>

</div>

<script>
    let vData = {
        queryForm: {
            queryField: [
                {fieldName: '工号', fieldValue: 'ghid'},
                {fieldName: '姓名', fieldValue: 'name'},
                {fieldName: '组', fieldValue: 'groupId', type: 'select', options: []},
                {fieldName: '手机号', fieldValue: 'mobile'}
            ],
            otherBut: [
                {name: '新增', methodName: 'addData', type: 'primary'},
                // {name: '下载', methodName: 'download', type: 'primary'}
            ]
        },
        tableData: {
            type: 'index',
            tableKey: [
                {fieldName: '工号', fieldValue: 'ghid'},
                {fieldName: '姓名', fieldValue: 'name'},
                {fieldName: '手机号', fieldValue: 'mobile'},
                {fieldName: '性别', fieldValue: 'sex', type: 'select'},
                {fieldName: '年龄', fieldValue: 'age'},
                {fieldName: '昵称', fieldValue: 'nickName'},
                {fieldName: '所在组', fieldValue: 'groupName'},
                {fieldName: '用户类型', fieldValue: 'userType', type: 'select'},
                {fieldName: '地址', fieldValue: 'address'}
            ],
            tableData: [],
            tableBut: [
                {butName: '编辑', methodName: 'edit', type: 'info'}
            ]
        },
        pageable: {
            page: 1,
            size: 10,
            total: 0
        }
    };

    let vm = new Vue({
        el: '#vmApp',
        data: vData,
        methods: {
            doQuery,
            resetQuery,
            doEvent,
            doTableEvent,
            colFormatter,
            handleSizeChange,
            handleCurrentChange
        }
    });

    $(function () {
        //获取下拉组信息
        getGroupOptions();
        //获取列表数据
        getData();
    });

    function getData() {
        let vJsonForm = {"pageable": vData.pageable};
        let query = vData.queryForm.queryField;
        for (let value of query) {
            vJsonForm[value.fieldValue] = value.fieldData;
        }
        axios.post('${rootURL}/listUserPage', vJsonForm)
            .then(function (response) {
                let res = response.data;
                vData.tableData.tableData = res.data.content;
                vData.pageable.total = res.data.total;
            })
            .catch(function (error) {

            })
    }

    function doTableEvent(row, methodName) {
        console.log(row);
        console.log(methodName);
    }

    function colFormatter(row, column, cellValue, index) {
        if (column.property === 'sex') {
            if (cellValue === 0) {
                return '男';
            } else if (cellValue === 1) {
                return '女';
            } else {
                return '不详';
            }
        }
        if (column.property === 'userType') {
            if (cellValue === 0) {
                return '普通用户';
            } else if (cellValue === 1) {
                return 'VIP用户';
            }
        }

    }

    function handleSizeChange(val) {
        vData.pageable.size = val;
        getData();
    }

    function handleCurrentChange(val) {
        vData.pageable.page = val;
        getData();
    }

    function doQuery() {
        getData();
    }

    /*function download(){
        console.log("测试下载");
        // var msg =window.location.href = "http://localhost:8090/pc/ortho/public/model/zip/modelZipDownload?orderNo=2020102832361000004&downFlag=cutt";
        var msg = window.open('http://localhost:8090/pc/ortho/public/model/zip/modelZipDownload?orderNo=2020102832361000004&downFlag=step')

        console.log("msg==", msg);
    }*/

    function resetQuery() {
        let query = vData.queryForm.queryField;
        for (let value of query) {
            value.fieldData = '';
        }
    }

    function doEvent(methodName) {
        if (methodName === 'addData') {
            console.log('新增')
        }
        /*if(methodName === 'download'){
            download();
        }*/
    }

    // 获取下拉组数据
    function getGroupOptions() {
        axios.post('/pub/getGroupOptions')
            .then(function (response) {
                let res = response.data;
                vData.queryForm.queryField[2].options = res.data;
            })
            .catch()
    }
</script>
</body>

<style>
    [v-cloak] {
        display: none;
    }
</style>
</html>
