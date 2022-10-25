<%--
  Author: fengzhilong
  Desc: 
  Date: 2021/5/21 14:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>组列表</title>
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

        <%-- dialogForm --%>
        <el-dialog :visible.sync="dialog.dialogFormVisible" :close-on-click-modal="false" :destroy-on-close="true"
                   :close-on-press-escape="false" :before-close="beforeCloseDialog">
            <el-form :model="dialog.dialogForm" :inline="true">
                <el-form-item v-show="dialog.isEdit === 1" label="组id" :label-width="dialog.formLabelWidth">
                    <el-input v-model="dialog.dialogForm.id" autocomplete="off" disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="组名" :label-width="dialog.formLabelWidth">
                    <el-input v-model="dialog.dialogForm.groupName" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button @click="closeDialog()">取 消</el-button>
                <el-button type="primary" @click="saveData">保 存</el-button>
            </div>
        </el-dialog>

    </el-card>
</div>

<script>
    let vData = {
        queryForm: {
            queryField: [{fieldName: '组名', fieldValue: 'groupName'}],
            otherBut: [{name: '新增', methodName: 'addData', type: 'primary'}]
        },
        tableData: {
            type: 'index',
            tableKey: [{fieldName: '组id', fieldValue: 'id'}, {fieldName: '组名', fieldValue: 'groupName'}],
            tableData: [],
            tableBut: [{butName: '编辑', methodName: 'edit', type: 'info'},
                {butName: '用户管理', methodName: 'userEdit', type: 'success'},
                {butName: '删除', methodName: 'remove', type: 'danger'}]
        },
        pageable: {
            page: 1,
            size: 10,
            total: 0
        },
        dialog: {
            isEdit: 0, // 0-新增，1-修改
            dialogFormVisible: false,
            dialogForm: {},
            formLabelWidth: '100px'
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
            handleSizeChange,
            handleCurrentChange,
            /**关闭按钮 -关闭前回调**/
            beforeCloseDialog() {
                vData.dialog.dialogFormVisible = false;
                vData.dialog.dialogForm = {};
            },
            saveData() {
                saveData();
            }
        }
    });

    $(function () {
        getData();
    });

    //获取列表数据
    function getData() {
        let vJsonForm = {"pageable": vData.pageable};
        let query = vData.queryForm.queryField;
        for (let value of query) {
            vJsonForm[value.fieldValue] = value.fieldData;
        }
        axios.post('${rootURL}/listGroupPage', vJsonForm)
            .then(function (response) {
                let res = response.data;
                vData.tableData.tableData = res.data.content;
                vData.pageable.total = res.data.total;
            })
            .catch(function (error) {

            })
    }

    //查询
    function doQuery() {
        getData();
    }

    //重置
    function resetQuery() {
        let query = vData.queryForm.queryField;
        for (let value of query) {
            value.fieldData = '';
        }
    }

    //按钮操作事件
    function doEvent(methodName) {
        if (methodName === 'addData') {
            addData();
        }
    }

    //表格操作事件
    function doTableEvent(row, methodName) {
        if (methodName === 'remove') {
            removeGroup(row);
        } else if (methodName === 'edit') {
            editData(row);
        }else if (methodName === 'userEdit'){
            userEdit(row);
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

    //新增
    function addData() {
        vData.dialog.isEdit = 0;
        vData.dialog.dialogFormVisible = true;

    }

    //删除
    function removeGroup(row) {
        vm.$confirm("确定要删除吗？")
            .then(_ => {
                let vJsonForm = {"id": row.id};
                axios.post('${rootURL}/removeGroup', vJsonForm)
                    .then(function (response) {
                        let res = response.data;
                        myEl.elAlert(res.code, res.msg);
                        if (res.code === 200) {
                            getData();
                        }
                    })
                    .catch(function (error) {

                    })
            })
            .catch(_ => {

            })
    }

    //编辑
    function editData(row) {
        vData.dialog.isEdit = 1;
        let vJsonForm = {"id": row.id};
        axios.post('/getGroupInfo', vJsonForm)
            .then(function (response) {
                let res = response.data;
                if (res.code !== 200) {
                    myEl.elAlert(res.code, res.msg);
                } else {
                    vData.dialog.dialogForm = res.data;
                    vData.dialog.dialogFormVisible = true;
                }
            })
            .catch(function (error) {

            });
    }
    function userEdit(row) {
        win_openNew("/page/userManagement", "用户管理", 900, 600, 2, false);
    }

    function saveData() {
        let vJsonForm = vData.dialog.dialogForm;
        axios.post('/saveGroupInfo', vJsonForm)
            .then(function (response) {
                let res = response.data;
                if (res.code === 200) {
                    closeDialog();
                    getData();
                }
                myEl.elAlert(res.code, res.msg);

            })
            .catch(function (error) {

            });
    }

    /**关闭dialog**/
    function closeDialog() {
        vData.dialog.dialogFormVisible = false;
        vData.dialog.dialogForm = {};
    }
</script>
</body>

<style>
    [v-cloak] {
        display: none;
    }

    .el-dialog .el-input {
        width: 250px;
    }
</style>
</html>
