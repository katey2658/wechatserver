<html>
<head>
    <title>home</title>
</head>
<body>
<h1>你好${username}</h1>

<script type="application/javascript">
    var url='ws://'+window.location.host+'/wechatserver/message';
    //打开websocket
    var socket=new WebSocket(url);
    //处理连接开启事件
    socket.onopen=function () {
        console.log('websocket is opening...');
        sendMessage("这是世界上我想给你最好的礼物");
    }

    //处理消息
    socket.onmessage=function (e) {
        console.log("receive a message :"+e.data);
        document.body.innerHTML=e.data;
    }

    socket.onclose=function () {
        console.log('websocket is onclosing...');
    }

    function sendMessage(text){
        console.log("sendMessage is executed");
        socket.send(text)
    }

</script>
</body>
</html>