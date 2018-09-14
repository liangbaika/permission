<%@ taglib prefix="c" uri="http://java.sun.com/jstl/xml_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<jsp:include page="../common/header.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <jsp:include page="../common/fheader.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->

    <jsp:include page="../common/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">


        <!-- Main content -->
        <section class="content container-fluid">
            <div id="tree">
            </div>
            <div id="tree2"  style="width: 250px">
            </div>



            <!--------------------------
              | Your Page Content Here |
              -------------------------->
        </section>

        <!-- /.content -->

    </div>


    <jsp:include page="../common/footer.jsp"/>

    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/adminlte.min.js"></script>
<script src="/static/plugins/iCheck/icheck.min.js"></script>
<script src="/static/dist/js/bootstrap-treeview.min.js"></script>
<script>
    $(function () {
        var data = [
            {
                text: "Parent 1",
                nodes: [
                    {
                        text: "Child 1",
                        nodes: [
                            {
                                text: "Grandchild 1"
                            },
                            {
                                text: "Grandchild 2"
                            }
                        ]
                    },
                    {
                        text: "Child 2"
                    }
                ]
            },
            {
                text: "Parent 2",
                id:2
            },
            {
                text: "Parent 3",
                id:3
            },
            {
                text: "Parent 4",
                id:4
            },
            {
                text: "Parent 5",
                id:5
            }
        ];
        var a = 1;

        function getTree() {

            return data;
        }

        $('#tree').treeview({
            data: getTree(),
            onNodeSelected:function (event,node) {
                alert(node.id+"---"+node.text)
                if(node.id==4){
                    $("#tree").treeview(true).addNode(getTree(),node,node.id,{ silent: true });
                }
            }
        })
        referModule();
    });

    var referModule = function () {

        $.ajax({
            type: "post",
            dateType: "json",
            url: "/test/tree.json",
            success: function (e) {
                defaultData = e.data;
                $('#tree2').treeview({
                    data: defaultData,//数据源参数
                    color: "#428bca",
                    showBorder: true,
                    // levels:2,
                    onNodeSelected: function (event, node) {
//                        alert(node.id + "前面是id，后面是名字" + node.text);//这里拿到id和name，就可以通过函数跳转触发点击事件
                    },
                    onNodeUnselected: function (event, node) {
                    }
                });
            }
            , error: function () {
                alert("加载树异常！");
            }

        })

    }






    function buildDomTree() {
        var data = [];
        function walk(nodes, data) {
            if (!nodes) { return; }
            $.each(nodes, function (id, node) {
                var obj = {
                    id: id,
                    text: node.nodeName + " - " + (node.innerText ? node.innerText : ''),
                    tags: [node.childElementCount > 0 ? node.childElementCount + ' child elements' : '']
                };
                if (node.childElementCount > 0) {
                    obj.nodes = [];
                    walk(node.children, obj.nodes);
                }
                data.push(obj);
            });
        }
        walk($('html')[0].children, data);
        return data;
    }
    $(function() {
        var options = {
            bootstrap2: false,
            showTags: true,
            levels: 5,
            data: buildDomTree()
        };
        $('#treeview').treeview(options);
    });
</script>
</body>
</html>
