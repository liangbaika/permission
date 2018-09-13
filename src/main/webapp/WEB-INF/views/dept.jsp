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
            text: "Parent 2"
        },
        {
            text: "Parent 3"
        },
        {
            text: "Parent 4"
        },
        {
            text: "Parent 5"
        }
    ];
    var a=1;
    function getTree() {
        alert(data);
        return data;
    }

    $('#tree').treeview({data: getTree()})
});


</script>
</body>
</html>
