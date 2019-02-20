<!DOCTYPE html>
<html>
<head lang="en">
    <title>Spring Boot Demo - FreeMarker</title>
    <link href="/css/index.css" rel="stylesheet">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
</head>

<script type="text/javascript">
    var time = 0;

    function getClientMsg() {
        $.ajax({
            url: "/test/getChangedSocketClient",
            data: {},
            type: "get",
            timeout: 100000,
            success: function (result, textStatus) {
                //alert(result);
                if (result == '') alert("null");//getClientMsg();
                // else {
                document.getElementById("receive").innerText = "received : " + (++time).toString();
                for (var i in result) {
                    var item = result[i];
                    var row = document.getElementById(item.id);
                    row.getElementsByClassName("client_state")[0].innerText = item.state;
                    row.getElementsByClassName("client_id")[0].innerText = item.id;
                    row.getElementsByClassName("client_type")[0].innerText = item.type;
                    row.getElementsByClassName("client_host")[0].innerText = item.host;
                    row.getElementsByClassName("client_msg")[0].innerText = item.msg;
                    row.getElementsByClassName("client_port")[0].innerText = item.port;
                    row.getElementsByClassName("client_descr")[0].innerText = item.descr;
                }
                // }
                if (textStatus == "success") {
                    getClientMsg();
                }
            },
            error: function (xmlHttpRequest, textStatus, errorThrow) {
                if (textStatus == "timeout") {
                    getClientMsg();
                }
                else {
                    getClientMsg();
                }
            }
        });

    }
</script>

<body>
<h2>lab409 ThunderProject Socket</h2>
<h4 id="receive">receive : 0</h4>
<button type="button" onclick="getClientMsg()">test</button>
<br/>

<table>
    <tr>
        <td>id</td>
        <td>type</td>
        <td>host</td>
        <td>port</td>
        <td>message</td>
        <td>state</td>
        <td>description</td>
    </tr>
    <#list sensorList as list>
        <tr id="${list_index}">
            <td class="client_id">${list.getId()}</td>
            <td class="client_type">${list.getType()}</td>
            <td class="client_host">${list.getHost()}</td>
            <td class="client_port">${list.getPort()}</td>
            <td class="client_msg">${list.getMsg()}</td>
            <td class="client_state">${list.getState()}</td>
            <td class="client_descr">${list.getDescr()}</td>
        </tr>
    </#list>
</table>
<br/>

</body>
</html>