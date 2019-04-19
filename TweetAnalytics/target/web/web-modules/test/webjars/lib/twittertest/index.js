(function() {
  $(function() {
    var ws;
    ws = new WebSocket($("body").data("ws-url"));
    ws.onopen = function(event) {
      var message;
      return message = $('#twitter').append("<a style='color:red'>WebSocket is connected,listening...  </a><br>");
    };
    ws.onmessage = function(event) {
      var message;
      message = JSON.parse(event.data);
      return $('#twitter').append(message.twitter + "<br>");
    };
    ws.onclose = function(event) {
      var message;
      return message = $('#twitter').append("<a style='color:red'>WebSocket is disconnected..</a> <br>");
    };
    return ws.onerror = function(event) {
      var message;
      return message = $('#twitter').append(" error occur! the error is: " + event.data + " <br>");
    };
  });

}).call(this);

//# sourceMappingURL=index.js.map
