var errGNGN = "Attention, ce <strong>groupe nominal</strong> n'est pas vraiment le <strong>groupe nominal</strong> qui convient pour compléter la série. Essayez encore !";
var errGNPron = "Attention vous vous êtes trompé. Le <strong>groupe nominal</strong> que vous avez choisi n'est pas le bon choix ; on attendait peut-être un autre type d'<strong>unité textuelle</strong>. Essayez encore !";
var errPronGN = "Attention vous vous êtes trompé. L'<strong>unité textuelle</strong> que vous avez choisie n'est pas le bon choix ; on attendait peut-être un <strong>groupe nominal</strong>. Faites un nouveau essai !";
var errPronPron = "Attention, cette <strong>unité textuelle</strong> n'est pas vraiment la bonne <strong>unité textuelle</strong> qui convient pour compléter la série. Essayez encore !";

var success = ["Bravo !", "Excellent !", "Félicitations !", "Magnifique,bien joué !", "Excellent travail, félicitations !"];

var continu = "Continuez pour trouver les element restants.";
var finish = "Vous avez fini avec succes!";

var wrongC1GN = r1Pron;
var wrongC1Pron = 0;
var wrongC2GN = r2Pron;
var wrongC2Pron = 0;
var wrongC3GN = r3Pron;
var wrongC3Pron = 0;

$(function () {
    $(".coreferent[selectable='true']").attr("style", "cursor: pointer");
});

function changeStyle(id) {
    var element = $(".coreferent[idn=" + id + "]");
    var selected = $('input[name=corefSelection]:checked');
    var isGN = element.attr("subtype") === selected.val();
    var isPron = element.attr("type") === selected.val();
    var isN = element.attr("subtype") === "N" && selected.val() == "GN";

    element.attr("style", selected.attr("style") + "; cursor: pointer");
    if (isGN || isPron || isN) {
        console.clear();
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
;

function validateFinish() {
    if (wrongC2GN === 0 && wrongC2Pron === 0 && wrongC3GN === 0 && wrongC3Pron === 0) {
        $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)] + finish).show("pulsate", 500);
        $(".column0").fadeIn();
        $("#next").fadeIn();
    }
}