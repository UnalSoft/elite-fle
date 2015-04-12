// encapsulation
!(function() {

  var getTextNodesIn = function(el) {
    return $(el).find(":not(iframe)").addBack().contents().filter(function() {
      return this.nodeType == 3;
    });
  };

  var textNodes = getTextNodesIn(document.getElementById("spottingActivityForm:spottingActivityTextPanel_content"));

  // format all text node according to french typography
  _.forEach(textNodes, function(node) {
    node.data = node.data.replace(/\s*\'\s*/g, "\'") // no space around apostrophe
      .replace(/\s*\(\s*/g, " \(") // left space left parentheses
      .replace(/\s*\)\s*/g, "\) ") // right space right parentheses
      .replace(/\s*\.\s*/g, "\. ") // space after point
      .replace(/\s*\,\s*/g, "\, ") // space after coma
      .replace(/\s*\-\s*/g, "\-") // no space around dash
      .replace(/\s*\;\s*/g, "\; ") // space after semi-colon
      .replace(/\s*\:\s*/g, "\: ") // space after colon
      .replace(/\s*\?\s*/g, " \? ") // space around interrogation
      .replace(/\s*\!\s*/g, " \! "); // space around exclamation
  });
)();
