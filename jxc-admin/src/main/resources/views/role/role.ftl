<!DOCTYPE html>
<html>
    <head>
        <title>角色管理</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">

        <form class="layui-form">
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="roleName"
                                   class="layui-input
					searchVal" placeholder="角色名"/>
                        </div>
                        <a class="layui-btn search_btn" data-type="reload"><i
                                    class="layui-icon">&#xe615;</i> 搜索</a>
                    </div>
                </form>
            </blockquote>
            <table id="roleList" class="layui-table" lay-filter="roles"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <@security.authorize access="hasAnyAuthority('102002')">
                        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                            <i class="layui-icon">&#xe608;</i>
                            添加角色
                        </a>
                    </@security.authorize>
                    <@security.authorize access="hasAnyAuthority('102005')">
                        <a class="layui-btn layui-btn-normal delNews_btn" lay-event="grant">
                            <i class="layui-icon">&#xe672;</i>
                            授权
                        </a>
                    </@security.authorize>
                </div>
            </script>
            <!--操作-->
            <script id="roleListBar" type="text/html">
                <@security.authorize access="hasAnyAuthority('102003')">
                    <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
                </@security.authorize>
                <@security.authorize access="hasAnyAuthority('102004')">
                    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                </@security.authorize>
            </script>
        </form>
        <script type="text/javascript" src="${ctx.contextPath}/js/role/role.js"></script>

    </body>
</html>