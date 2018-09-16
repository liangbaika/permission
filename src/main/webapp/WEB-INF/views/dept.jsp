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
        <div class="content container-fluid">
            <div class="row-fluid">
                <div    class="span4" id="tree3" >
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div   class="span8">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Data Table With Full Features</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>Rendering engine</th>
                                    <th>Browser</th>
                                    <th>Platform(s)</th>
                                    <th>Engine version</th>
                                    <th>CSS grade</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Misc</td>
                                    <td>Links</td>
                                    <td>Text only</td>
                                    <td>-</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>Lynx</td>
                                    <td>Text only</td>
                                    <td>-</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>IE Mobile</td>
                                    <td>Windows Mobile 6</td>
                                    <td>-</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>PSP browser</td>
                                    <td>PSP</td>
                                    <td>-</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Other browsers</td>
                                    <td>All others</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>U</td>
                                </tr>
                                </tbody>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>

            <!--------------------------
              | Your Page Content Here |
              -------------------------->
        </div>

        <!-- /.content -->

    </div>


    <jsp:include page="../common/footer.jsp"/>

    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <%--<div class="control-sidebar-bg"></div>--%>
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
<script src="/static/jquery.ztree.all.min.js"></script>
<script src="/static/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/static/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

<script>
    $(function () {
        $('#example1').DataTable({
            'paging'      : true,
            'lengthChange': false,
            'searching'   : true,
            'ordering'    : true,
            'info'        : false,
            'autoWidth'   : true
        })
    })
</script>

<SCRIPT type="text/javascript">
    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: true
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                rootPid: 0,
                pIdKey: "parentId"
            }
        },
        key: {
            children: "child"
        },
        edit: {
            enable: true
        }
    };
    var zNodes = [];

    $(document).ready(function () {
        $.ajax({
            type: "GET",
            dateType: "json",
            url: "/dept/sysDept.json",
            success: function (e) {
                zNodes = e.data.content;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            , error: function () {
                alert("加载树异常！");
            }

        })

    });

    var newCount = 1;

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
            "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);
        if (btn) btn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            $("#addBtn_" + treeNode.tId).click(function () {
                $("#myModalLabel").text("新增");

                $('#myModal').modal()
            });
            zTree.addNodes(treeNode, {
                id: (100 + newCount),
                pId: treeNode.id,
                name: "new node" + (newCount++)
            });
            return false;
        });
    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    };

</SCRIPT>
</body>
</html>
