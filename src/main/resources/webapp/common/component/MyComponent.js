/**
 * Author: fengzhilong
 * Desc: 注册全局组件（通用）
 * Date: 2021/06/08 14:55
 **/

/*===================================================查询模块===============================================*/
/**
 * 列表的查询部分-采用行内表单，包括查询项，查询重置按钮，其他按钮
 * 查询项：{fieldName:'名称', fieldValue: '值', type: '类型'} -- fieldName:label；fieldValue:字段；fieldData:属性值
 *      type: 默认input ；int-数字；select-选择框，需加options参数；date-日期
 * 查询/重置按钮：查询方法：doQuery；重置方法：resetQuery-需要清空fieldData属性值
 * 其他按钮：从右到左显示，方法doEvent('方法名')；{name: '新增', methodName: 'addData', type: 'primary'}
 **/
Vue.component('query-form', {
    props: [
        'model'
    ],
    data: function () {
        return {
            model: {
                queryField: [],// [{fieldName:'工号', fieldValue: 'ghid'}]
                otherBut: []  //[{name: '新增', methodName: 'addData', type: 'primary'}]
            }
        }
    },
    template: '<template id="myQueryForm">\n' +
        '    <el-form :inline="true" :model="model" class="demo-form-inline">\n' +
        '        <el-form-item v-for="item in model.queryField"\n' +
        '                      :label="item.fieldName">\n' +
        '            <el-input-number v-if="item.type == \'int\'" v-model="item.fieldData" size="medium"\n' +
        '                             :controls="false"></el-input-number>\n' +
        '            <el-select v-else-if="item.type == \'select\'" v-model="item.fieldData" size="medium">\n' +
        '                <el-option\n' +
        '                        v-for="opt in item.options"\n' +
        '                        :key="opt.value"\n' +
        '                        :label="opt.label"\n' +
        '                        :value="opt.value">\n' +
        '                </el-option>\n' +
        '            </el-select>\n' +
        '            <el-date-picker v-else-if="item.type == \'date\'" size="medium"\n' +
        '                            v-model="item.fieldData"\n' +
        '                            type="date">\n' +
        '            </el-date-picker>\n' +
        '            <el-input v-else v-model="item.fieldData" size="medium"></el-input>\n' +
        '        </el-form-item>\n' +
        '        <el-form-item>\n' +
        '            <el-button type="primary" size="medium" @click="doQuery">查询</el-button>\n' +
        '        </el-form-item>\n' +
        '        <el-form-item>\n' +
        '            <el-button type="info" size="medium" @click="resetQuery">重置</el-button>\n' +
        '        </el-form-item>\n' +
        '\n' +
        '        <template v-for="(butItem,idx) in model.otherBut">\n' +
        '            <el-button v-if="idx === 0" style="float: right;margin-right: 150px" :type="butItem.type" size="medium"\n' +
        '                       @click="doEvent(butItem.methodName)">{{butItem.name}}\n' +
        '            </el-button>\n' +
        '            <el-button v-else style="float: right;margin-right: 10px" :type="butItem.type" size="medium"\n' +
        '                       @click="doEvent(butItem.methodName)">{{butItem.name}}\n' +
        '            </el-button>\n' +
        '        </template>\n' +
        '    </el-form>\n' +
        '</template>'
});


/*===================================================table表格数据===============================================*/
/**
 * 列表部分-采用table，包括列集合，数据，操作按钮
 * 列：{fieldName:'名称', fieldValue: '值'} -- fieldName:label；fieldValue:prop
 * 数据：和原来一样
 * 操作按钮：从左到右显示，方法doTableEvent('行数据', '方法名')；{butName: '编辑', methodName: 'edit', type: 'primary'}
 **/
Vue.component('my-table', {
    props: [
        'data'
    ],
    data: function () {
        return {
            data: {
                type: 'index',
                tableData: [],
                tableKey: [], //[{fieldName: '组名', fieldValue: 'groupName'}]
                tableBut: [] //[{butName: '编辑', methodName: 'edit'}]
            }
        }
    },
    template: '<template id="myTable">\n' +
        '            <el-table :data="data.tableData" :max-height="$(document).height() - 150" stripe border\n' +
        '                      highlight-current-row style="width: 98%" :cell-style="{padding:0}">\n' +
        '                <el-table-column :type="data.type" width="50px"></el-table-column>\n' +
        '\n' +
        '                <template v-for="item in data.tableKey">\n' +
        '                    <el-table-column v-if="item.type === \'select\'" show-overflow-tooltip :prop="item.fieldValue"\n' +
        '                                     :label="item.fieldName" :formatter="colFormatter">\n' +
        '                    </el-table-column>\n' +
        '                    <el-table-column v-else show-overflow-tooltip :prop="item.fieldValue"\n' +
        '                                     :label="item.fieldName">\n' +
        '                    </el-table-column>\n' +
        '                </template>\n' +
        '                <el-table-column v-if="data.tableBut.length > 0" label="操作" :width="data.tableBut.length * 80">\n' +
        '                    <template slot-scope="scope">\n' +
        '                        <el-button v-for="butItem in data.tableBut" size="mini" :type="butItem.type"\n' +
        '                                   @click="doTableEvent(scope.row, butItem.methodName)">{{butItem.butName}}\n' +
        '                        </el-button>\n' +
        '                    </template>\n' +
        '                </el-table-column>\n' +
        '\n' +
        '            </el-table>\n' +
        '        </template>'
});