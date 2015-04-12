// encapsulation
(function() {

  var getTextNodesIn = function(el) {
    return $(el).find(":not(iframe)").addBack().contents().filter(function() {
      return this.nodeType == 3;
    });
  };

  var textNodes = getTextNodesIn(document.getElementById("spottingActivityForm"));

  var fakeApostropheChar = String.fromCharCode(8217);
  var reFakeApostrophe = new RegExp(fakeApostropheChar, 'g');

  // format all text node according to french typography
  _.forEach(textNodes, function(node) {
    node.data = node.data.replace(reFakeApostrophe, "\'") // replace fake apostrophe by true apostrophe
      .replace(/\s*\;\s*/g, " \; ") // space around semi-colon
      .replace(/\s*\:\s*/g, " \: ") // space around colon
      .replace(/\s*\?\s*/g, " \? ") // space around interrogation
      .replace(/\s*\!\s*/g, " \! ") // space around exclamation
      .replace(/\s*\(\s*/g, " \(") // left space left parentheses
      .replace(/\s*\)\s*/g, "\) ") // right space right parentheses
      .replace(/\s*\,\s*/g, "\, ") // space after coma
      .replace(/\s*\-\s*/g, "\-") // no space around dash
      .replace(/\s*\%\s*/g, " \% ") // space around percentage
      .replace(/\s*\'\s*/g, "\'") // no space around apostrophe
      .replace(/\s*\.\s*/g, "\. "); // space after point

  });
})();
