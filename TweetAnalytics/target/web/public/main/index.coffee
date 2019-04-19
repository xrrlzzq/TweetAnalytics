$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onopen=(event) ->
    message = $('#twitter').append "<a style='color:red'>WebSocket is connected,listening...  </a><br>"
 
  ws.onmessage = (event) ->
    message = JSON.parse event.data
    $('#twitter').append message.twitter + "<br>"
  
  ws.onclose=(event) ->
    message = $('#twitter').append "<a style='color:red'>WebSocket is disconnected..</a> <br>"
    
  ws.onerror=(event) ->
    message = $('#twitter').append " error occur! the error is: "+event.data+" <br>"