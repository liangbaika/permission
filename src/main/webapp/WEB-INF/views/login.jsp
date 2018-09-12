
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/xml_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<jsp:include page="../common/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <jsp:include page="../common/fheader.jsp" />
    <!-- Left side column. contains the logo and sidebar -->

    <jsp:include page="../common/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">


        <!-- Main content -->
        <section class="content container-fluid">
            <div class="login-box">
                <div class="login-logo">
                    <a href="../../index2.html"><b>基础管理系统</b>LQ</a>
                </div>
                <!-- /.login-logo -->
                <div class="login-box-body">
                    <p class="login-box-msg">欢迎登录</p>

                    <form action="../../index2.html" method="post">
                        <div class="form-group has-feedback">
                            <input type="text" class="form-control" name="username" placeholder="用户名">
                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control" name="password" placeholder="密码">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                                <div class="checkbox icheck">
                                    <label>
                                        <input type="checkbox"> 记住我
                                    </label>
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-xs-4">
                                <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                            </div>
                            <!-- /.col -->
                        </div>
                    </form>



                    <a href="#">忘记密码</a><br>
                    <a href="register.html" class="text-center">注册账号</a>

                </div>
                <!-- /.login-box-body -->
                <div class="control-sidebar-bg"></div>

            </div>
            <!--------------------------
              | Your Page Content Here |
              -------------------------->
        </section>

        <!-- /.content -->

    </div>


    <jsp:include page="../common/footer.jsp" />

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
<script src="static/plugins/iCheck/icheck.min.js"></script>
</body>
</html>
