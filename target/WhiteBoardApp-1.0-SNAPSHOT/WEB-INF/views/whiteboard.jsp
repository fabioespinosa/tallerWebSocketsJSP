<%-- 
    Document   : whiteboard
    Created on : Sep 17, 2016, 10:43:41 PM
    Author     : fabioespinosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Collaborative Whiteboard</h1>
        <h2 style="margin-left:400px">Bienvenido ${usuario}</h2>
        <button>Cerrar Sesión</button>
        <table>
            <tr>
                <td>
                    <canvas id="myCanvas" width="600" height="400" style="border:1px solid #000000;"></canvas>
                </td>
                <td>
                    <form name="inputForm">
                        <table>

                            <tr>
                                <th>Color</th>
                                <td><input type="radio" name="color" value="#FF0000" checked="true">Red</td>
                                <td><input type="radio" name="color" value="#0000FF">Blue</td>
                                <td><input type="radio" name="color" value="#FF9900">Orange</td>
                                <td><input type="radio" name="color" value="#33CC33">Green</td>
                            </tr>

                            <tr>
                                <th>Shape</th>
                                <td><input type="radio" name="shape" value="square" checked="true">Square</td>
                                <td><input type="radio" name="shape" value="circle">Circle</td>
                                <td> </td>
                                <td> </td>
                            </tr>                           
                        </table>
                    </form>
                </td>
            </tr>
        </table>    
        <label>Usuarios en linea: </label><p id="usuariosEnLinea"></p>
        <label>Usuarios en linea Usando EL:</label><br/>
        ${sesiones}
        <br/><button>Descargar</button>
        <script>
            //var wsUri = "ws://" + document.location.host + document.location.pathname + "whiteboardendpoint";
var wsUri = "ws://" + document.location.host + "/WhiteBoardApp" + "/whiteboardendpoint/${usuario}";
console.log(wsUri);
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

websocket.onmessage = function(evt) { 
    onMessage(evt) };

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                
function onMessage(evt) {
    console.log("received: " + evt.data);
    if(evt.data.startsWith("[")){
        var stringComas = evt.data.substring(1, evt.data.length-1);
        var arrayUsuarios = stringComas.split(",");
        document.getElementById("usuariosEnLinea").innerHTML = arrayUsuarios.toString();
        
    }else {
        drawImageText(evt.data);
    }
    
}
        </script>
        <script type="text/javascript" src="whiteboard.js"></script>
    </body>
</html>
