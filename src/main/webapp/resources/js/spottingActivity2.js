var errGNGN = "Attention, ce <strong>groupe nominal</strong> n'est pas vraiment le <strong>groupe nominal</strong> qui convient pour compléter la série. Essayez encore !";
var errGNPron = "Attention vous vous êtes trompé. Le <strong>groupe nominal</strong> que vous avez choisi n'est pas le bon choix ; on attendait peut-être un autre type d'<strong>unité textuelle</strong>. Essayez encore !";
var errPronGN = "Attention vous vous êtes trompé. L'<strong>unité textuelle</strong> que vous avez choisie n'est pas le bon choix ; on attendait peut-être un <strong>groupe nominal</strong>. Faites un nouveau essai !";
var errPronPron = "Attention, cette <strong>unité textuelle</strong> n'est pas vraiment la bonne <strong>unité textuelle</strong> qui convient pour compléter la série. Essayez encore !";

var success = ["Bravo !", "Excellent !", "Félicitations !", "Magnifique,bien joué !", "Excellent travail, félicitations !"];

var continu = "Continuez pour trouver les element restants.";
var finish = "Vous avez fini avec succès!";

$(function() {
    $(".coreferent[draggable='true'], .referent[draggable='true']").attr("style", "cursor: pointer");
    $(".coreferent[draggable='true'], .referent[draggable='true']").draggable({
        revert: "invalid",
        helper: "clone",
        cursor: "pointer",
        disabled: false
    });
    $(".column2 .cbody").droppable({
        hoverClass: "ui-state-highlight",
        activeClass: "ui-state-hover",
        drop: function(event, ui) {
            if (ui.draggable.attr("right") === "true" && ui.draggable.hasClass("coreferent")) {
                $(this).find(".placeholder").remove();
                var newRow = $('<div>' + ui.draggable.text() + '</div>');
                newRow.appendTo(this);
                $("#message").removeClass("warning");

                ui.draggable.draggable({disabled: true});
                ui.draggable.attr("draggable", false);
                ui.draggable.attr("style", "color:greenyellow;background:white;opacity:1");
                rightAnswers -= 1;
                if (rightAnswers === 0) {
                    $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)] + finish).show("pulsate", 500);
                    $("#next").fadeIn();

                    $(".coreferent[draggable='true'], .referent[draggable='true']").draggable({disabled: true});
                    $(".coreferent[draggable='true'], .referent[draggable='true']").attr("draggable", false);
                } else {
                    $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)] + continu).show("pulsate", 500, callback);
                }
            } else {
                $("#message").removeClass("success");
                if (ui.draggable.attr("subtype") === "GN") {
                    $("#message").addClass("warning").html(errGNGN).show("shake", 500, callback);
                } else
                if (ui.draggable.attr("type") === "Pron") {
                    $("#message").addClass("warning").html(errPronPron).show("shake", 500, callback);
                }
                if (ui.draggable.attr("right") !== "true") {
                    ui.draggable.draggable({disabled: true});
                    ui.draggable.attr("draggable", false);
                }
            }
        }
    });
    $(".column3 header").droppable({
        hoverClass: "ui-state-highlight",
        activeClass: "ui-state-default",
        drop: function(event, ui) {
            if (ui.draggable.attr("right") === "true" && ui.draggable.hasClass("referent")) {
                this.innerHTML = ui.draggable.text();
                $("#message").removeClass("warning");

                ui.draggable.draggable({disabled: true});
                ui.draggable.attr("draggable", false);
                ui.draggable.attr("style", "color:blue;background:white;opacity:1");
                rightAnswers -= 1;
                if (rightAnswers === 0) {
                    $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)] + finish).show("pulsate", 500);
                    $("#next").fadeIn();

                    $(".coreferent[draggable='true'], .referent[draggable='true']").draggable({disabled: true});
                    $(".coreferent[draggable='true'], .referent[draggable='true']").attr("draggable", false);
                } else {
                    $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)] + continu).show("pulsate", 500, callback);
                }
            } else {
                $("#message").removeClass("success");
                if (ui.draggable.attr("subtype") === "GN") {
                    $("#message").addClass("warning").html(errGNGN).show("shake", 500, callback);
                } else
                if (ui.draggable.attr("type") === "Pron") {
                    $("#message").addClass("warning").html(errPronPron).show("shake", 500, callback);
                }
                if (ui.draggable.attr("right") !== "true") {
                    ui.draggable.draggable({disabled: true});
                    ui.draggable.attr("draggable", false);
                }
            }
        }
    });
});
var callbackCalled = false;
function callback() {
    if (! callbackCalled) {
        callbackCalled = true;
        setTimeout(function() {
            $("#message").fadeOut();
            callbackCalled = false;
        }, 8000);
    }
}
;