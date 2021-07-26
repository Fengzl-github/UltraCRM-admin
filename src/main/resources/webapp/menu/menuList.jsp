<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
        <html>

        <head>
                <title>菜单管理</title>
                <%@include file="../ut_controls/PageHeaderMeta.jspf" %>
        </head>

        <body>
                <div id="vmApp" v-cloak>
                        <el-card>
                                <el-input placeholder="输入关键字进行过滤" v-model="filterText">
                                </el-input>
                                <!-- default-expand-all:是否默认展开所有节点
                                draggable:开启拖拽
                                -->
                                <el-tree ref="tree" class="filter-tree" empty-text="没有查到数据哦" :data="data" node-key="id"
                                        default-expand-all draggable :filter-node-method="filterNode"
                                        @node-drag-start="handleDragStart" @node-drag-enter="handleDragEnter"
                                        @node-drag-leave="handleDragLeave" @node-drag-over="handleDragOver"
                                        @node-drag-end="handleDragEnd" @node-drop="handleDrop" :allow-drop="allowDrop"
                                        :allow-drag="allowDrag" :expand-on-click-node="false">
                                        <span class="custom-tree-node" slot-scope="{ node, data }">
                                                <span :class="visibleClass(data.visible)"><i :class="data.icon"></i>{{
                                                        node.label
                                                        }}</span>
                                                <span>
                                                        <el-button type="text" size="mini" @click="() => edit(data)">
                                                                Edit
                                                        </el-button>
                                                        <el-button type="text" size="mini" @click="() => append(data)">
                                                                Append
                                                        </el-button>
                                                        <el-button type="text" size="mini"
                                                                @click="() => remove(node, data)">
                                                                Delete
                                                        </el-button>
                                                </span>
                                        </span>
                                </el-tree>
                        </el-card>
                </div>

                <script>
                        let id = 1000;
                        let vData = {
                                filterText: '',
                                data: [],
                                defaultProps: {
                                        children: 'children',
                                        label: 'label',
                                        icon: 'icon',
                                        url: 'url',
                                        role: 'role',
                                        visible: 'visible'
                                }
                        };

                        let vm = new Vue({
                                el: '#vmApp',
                                data: vData,
                                computed: {
                                        visibleClass() {
                                                return function (visible) {
                                                        return visible == 0 ? "disableClass" : "visibleClass";
                                                }
                                        }
                                },
                                methods: {
                                        //节点开始拖拽时触发的事件
                                        handleDragStart(node, ev) {
                                                //console.log('节点开始拖拽', node, ev);
                                        },
                                        //拖拽进入其他节点时触发的事件
                                        handleDragEnter(draggingNode, dropNode, ev) {
                                                //console.log('节点拖拽到其他节点: ', draggingNode.label, dropNode.label, ev);
                                        },
                                        //拖拽离开某个节点时触发的事件
                                        handleDragLeave(draggingNode, dropNode, ev) {
                                                //console.log('节点离开某一节点: ', draggingNode.label, dropNode.label, ev);
                                        },
                                        //在拖拽节点时触发的事件（类似浏览器的 mouseover 事件）
                                        handleDragOver(draggingNode, dropNode, ev) {
                                                //console.log('拖拽节点: ', draggingNode.label, dropNode.label, ev);
                                        },
                                        handleDragEnd(draggingNode, dropNode, dropType, ev) {
                                                //console.log('拖拽结束(可能未成功): ', dropNode && dropNode.label, dropType, ev);
                                        },
                                        handleDrop(draggingNode, dropNode, dropType, ev) {
                                                console.log(draggingNode.label, '拖拽成功,至: ', dropNode.label, dropType);
                                                updateMenuNode(draggingNode.key, dropNode.key, dropType);
                                        },
                                        //拖拽时判定目标节点能否被放置。type 参数有三种情况：'prev'、'inner' 和 'next'，分别表示放置在目标节点前、插入至目标节点和放置在目标节点后
                                        allowDrop(draggingNode, dropNode, type) {
                                                const nodeId = dropNode.key;
                                                let nodeIds = nodeId.split('-');
                                                if (nodeIds.length === 5) {
                                                        return type !== 'inner';
                                                } else {
                                                        return true
                                                }
                                        },
                                        //判断能否被拖拽，返回false不能拖拽
                                        allowDrag(draggingNode) {
                                                let data = draggingNode.data;
                                                if (data.visible == 0) {
                                                        return false;
                                                }
                                                return true;
                                        },
                                        filterNode(value, data) {
                                                if (!value) return true;
                                                return data.label.indexOf(value) !== -1;
                                        },
                                        append(data) {
                                                console.log("data==", data);
                                                const newChild = { id: id++, label: 'testtest', children: [] };
                                                if (!data.children) {
                                                        this.$set(data, 'children', []);
                                                }
                                                data.children.push(newChild);
                                        },
                                        remove(node, data) {
                                                const parent = node.parent;
                                                const children = parent.data.children || parent.data;
                                                const index = children.findIndex(d => d.id === data.id);
                                                children.splice(index, 1);
                                        },
                                        edit(data) {
                                                console.log('修改', data);
                                        }
                                },
                                watch: {
                                        filterText(val) {
                                                vm.$refs.tree.filter(val);
                                        }
                                },
                        });
                        //拖拽节点id  目标节点id   拖拽类型
                        function updateMenuNode(sourceId, targetId, dropType) {
                                axios.post('${rootURL}/updateMenuNode', { "sourceId": sourceId, "targetId": targetId, "dropType": dropType })
                                        .then(function (response) {
                                                let res = response.data;
                                                console.log("拖拽结果", res);
                                                if (res.code !== 200) {
                                                        myEl.elAlert(res.code, res.msg);
                                                        getMenuData();
                                                }
                                        })
                                        .catch(function (error) {
                                                console.log("系统异常");
                                        })
                        }

                        $(function () {
                                //获取数据
                                getMenuData();

                        });


                        function getMenuData() {
                                axios.post('${rootURL}/getEditMenuData', { "role": 0 })
                                        .then(function (response) {
                                                let res = response.data;
                                                vData.data = res.data;
                                        })
                                        .catch(function (error) {
                                                console.log("系统异常");
                                        })
                        }
                </script>
        </body>

        <style>
                [v-cloak] {
                        display: none;
                }

                .custom-tree-node {
                        flex: 1;
                        display: flex;
                        align-items: center;
                        justify-content: space-between;
                        font-size: 18px;
                        padding-right: 80px;
                }

                .disableClass {
                        color: darkgray;
                        padding-bottom: 5px;
                }

                .visibleClass {
                        color: black;
                        padding-bottom: 5px;
                }
        </style>

        </html>