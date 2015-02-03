var rightGN = 0;
var rightPron = 0;
var totalGN = 0;
var totalN = 0;
var rightAnswers = 0;
var allSelected = 0;

var success = ["Bravo !", "Excellent !", "Félicitations !", "Magnifique,bien joué !", "Excellent travail, félicitations !"];
var finish = "Vous avez fini avec succes!";

/**
 * Set a pointer cursor to the selectable coreferents
 */
$(function () {
    $(".coreferent[selectable='true']").attr("style", "cursor: pointer");
});

/**
 * Evaluates if the element is correct or not, and update the number of selected and right answers
 * @param element the element to evaluate
 * @param isGN if the element is GN
 * @param isPron if the element is Pron
 * @param isN if the element is N
 * @param len total of answers
 * @returns {boolean} true if the number of selected answers is the same that the correct ones, else o.c
 */
function finished(element, isGN, isPron, isN, len) {
    if (typeof element.attr("selected") === 'undefined') {
        element.attr("selected", "true");
        allSelected++;
        if (isGN || isPron || isN) {
            element.attr("right", "true");
            rightAnswers++;
            if (isGN || isN)rightGN++;
            else rightPron++
        } else element.attr("right", "false");
    } else {
        if (element.attr("right") === "true" && !(isGN || isPron || isN)) {
            element.attr("right", "false");
            rightAnswers--;
            if (element.attr("subtype") === "GN" || element.attr("subtype") === "N")rightGN--;
            else if (element.attr("type") === "Pron") rightPron--;
        } else if (element.attr("right") === "false" && (isGN || isPron || isN)) {
            element.attr("right", "true");
            rightAnswers++;
            if (isGN || isN)rightGN++;
            else rightPron++
        }
    }
    //console.log("Right GN = " + rightGN + " Right Pron = " + rightPron);
    return (allSelected === rightAnswers && rightAnswers === len);
}

/**
 * Function called when the user clicks over a the selectable component
 * @param id id of the selectable component
 */
function changeStyle(id) {
    if (typeof  $(".coreferent[idn=" + id + "]").attr("aria-disabled") === 'undefined') {
        var element = $(".coreferent[idn=" + id + "]");
        var selected = $('input[name=corefSelection]:checked');
        var len = $("div").filter(".coreferent").length;
        var totalGN = $("[subtype=GN]").length + $("[subtype=N]").length;
        var totalPron = $("[type=Pron]").length;

        // Verify if the selection is correct or not
        var isGN = element.attr("subtype") === selected.val();
        var isPron = element.attr("type") === selected.val();
        var isN = element.attr("subtype") === "N" && selected.val() == "GN";

        // Set the same style to the element
        element.attr("style", selected.attr("style") + "; cursor: pointer");

        if (finished(element, isGN, isPron, isN, len)) {
            $("#message").removeClass("warning");
            $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)] + finish).show("pulsate", 1000);
            $("#next").fadeIn();
        } else if (allSelected === len && rightAnswers !== len) {
            $("#message").addClass("warning").html(errorMessage(len, totalGN, totalPron)).show("shake", 500, callback);

        }
    }
}

var callbackCalled = false;
function callback() {
    if (!callbackCalled) {
        callbackCalled = true;
        setTimeout(function () {
            $("#message").fadeOut();
            callbackCalled = false;
        }, 8000);
    }
}

function errorMessage(len, totalGN, totalPron) {
    var errors = allSelected - rightGN - rightPron;
    var hits = len - errors;
    var wrongGn = totalGN - rightGN;
    var wrongPron = totalPron - rightPron;
    return "Attention vous vous êtes Préjugés trompé " +
        errors + " fois: " +
        "\n1- (" + wrongPron + ")de catégorie pron " +
        "\n2- (" + wrongGn + ") de catégorie GN";
}
;