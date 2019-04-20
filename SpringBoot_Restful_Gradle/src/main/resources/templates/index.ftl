<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <script type="text/javascript" src="/static/js/jquery-1.11.0.js"></script>
    <script>
        $(function () {
           $("#btn_get").click(function () {
               $.ajax({
                   url:"/user/query/r/",
                   type:"get",
                   dataType:"json",
                   statusCode: {
                       200:function (data) {
                       $(data).each(function (index) {
                           alert(data[index].username)
                       })
                       },

                       204:function () {
                          alert("查询失败")
                       }
                   }
               })
           });



            $("#btn_post").click(function () {
                var user={
                    "username":"sss",
                    "password":"0001"
                }
                var json=JSON.stringify(user)
                $.ajax({
                    url:"/user/add/",
                    type:"POST",
                    data:json,
                    contentType:"application/json",
                    statusCode: {
                        200:function () {
                            alert("增加成功")
                        }
                    }
                })
            });

                $("#btn_delete").click(function () {
                    $.ajax({
                        url:"/user/del/0/",
                        type:"delete",
                        statusCode: {
                            200:function () {
                                alert("删除成功")
                            }
                        }
                    })
                });

            $("#btn_put").click(function () {
                $.ajax({
                    url:"/user/put/0/ryanb",
                    type:"put",
                    dataType:"json",
                    statusCode: {
                        200:function () {
                            alert("修改成功")
                        }
                    }
                })
            });
        });
    </script>
</head>
<body>
<h2>GET</h2>
<button id="btn_get">测试get方法</button>
<h2>POST</h2>
<button id="btn_post">测试post方法</button>
<h2>DELETE</h2>
<button id="btn_delete">测试delete方法</button>

<h2>PUT</h2>
<button id="btn_put">测试put方法</button>
</body>
</html>