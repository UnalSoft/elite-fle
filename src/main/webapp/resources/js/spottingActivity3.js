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

$(function() {
    $(".coreferent[draggable='true']").attr("style", "cursor: pointer");
    $(".coreferent[draggable='true']").draggable({
        revert: "invalid",
        cursor: "pointer"
    });
    $(".column1 .pron").droppable({
        accept: ".column1 .gn .coreferent",
        hoverClass: "ui-state-default",
        activeClass: "ui-state-highlight",
        drop: function(event, ui) {
            ui.draggable.fadeOut();
            var coref = ui.draggable.clone();
            coref.appendTo(this);
            setTimeout(function() {
                ui.draggable.remove();
            }, 1000);
            coref.attr("style", "cursor:pointer");
            coref.draggable({
                revert: "invalid",
                cursor: "pointer"
            });
            if (ui.draggable.attr("type") === "Pron") {
                wrongC1GN -= 1;
            } else {
                wrongC1Pron += 1;
            }
            if (wrongC1GN === 0 && wrongC1Pron === 0) {
                $(".column1 .gn").css("background-color", "white");
                $(".column1 .coreferent[draggable='true']").draggable({disabled: true});
                $(".column1 .coreferent[draggable='true']").attr("draggable", false).attr("style", "background:white;opacity:1");
                validateFinish();
            } else {
                if (wrongC1GN === 0) {
                    $(".column1 .gn").css("background-color", "white");
                }
                if (wrongC1Pron > 0) {
                    $(".column1 .pron").css("background-color", "gray");
                }
            }
        }
    });
    $(".column1 .gn").droppable({
        accept: ".column1 .pron .coreferent",
        hoverClass: "ui-state-default",
        activeClass: "ui-state-highlight",
        drop: function(event, ui) {
            ui.draggable.fadeOut();
            var coref = ui.draggable.clone();
            coref.appendTo(this);
            setTimeout(function() {
                ui.draggable.remove();
            }, 1000);
            coref.attr("style", "cursor:pointer");
            coref.draggable({
                revert: "invalid",
                cursor: "pointer"
            });
            if (ui.draggable.attr("subtype") === "GN") {
                wrongC1Pron -= 1;
            } else {
                wrongC1GN += 1;
            }
            if (wrongC1GN === 0 && wrongC1Pron === 0) {
                $(".column1 .pron").css("background-color", "white");
                $(".column1 .coreferent[draggable='true']").draggable({disabled: true});
                $(".column1 .coreferent[draggable='true']").attr("draggable", false).attr("style", "background:white;opacity:1");
                validateFinish();
            } else {
                if (wrongC1Pron === 0) {
                    $(".column1 .pron").css("background-color", "white");
                }
                if (wrongC1GN > 0) {
                    $(".column1 .gn").css("background-color", "gray");
                }
            }
        }
    });
    $(".column2 .pron").droppable({
        accept: ".column2 .gn .coreferent",
        hoverClass: "ui-state-default",
        activeClass: "ui-state-highlight",
        drop: function(event, ui) {
            ui.draggable.fadeOut();
            var coref = ui.draggable.clone();
            coref.appendTo(this);
            setTimeout(function() {
                ui.draggable.remove();
            }, 1000);
            coref.attr("style", "cursor:pointer");
            coref.draggable({
                revert: "invalid",
                cursor: "pointer"
            });
            if (ui.draggable.attr("type") === "Pron") {
                wrongC2GN -= 1;
            } else {
                wrongC2Pron += 1;
            }
            if (wrongC2GN === 0 && wrongC2Pron === 0) {
                $(".column2 .gn").css("background-color", "white");
                $(".column2 .coreferent[draggable='true']").draggable({disabled: true});
                $(".column2 .coreferent[draggable='true']").attr("draggable", false).attr("style", "background:white;opacity:1");
                validateFinish();
            } else {
                if (wrongC2GN === 0) {
                    $(".column2 .gn").css("background-color", "white");
                }
                if (wrongC2Pron > 0) {
                    $(".column2 .pron").css("background-color", "gray");
                }
            }
        }
    });
    $(".column2 .gn").droppable({
        accept: ".column2 .pron .coreferent",
        hoverClass: "ui-state-default",
        activeClass: "ui-state-highlight",
        drop: function(event, ui) {
            ui.draggable.fadeOut();
            var coref = ui.draggable.clone();
            coref.appendTo(this);
            setTimeout(function() {
                ui.draggable.remove();
            }, 1000);
            coref.attr("style", "cursor:pointer");
            coref.draggable({
                revert: "invalid",
                cursor: "pointer"
            });
            if (ui.draggable.attr("subtype") === "GN") {
                wrongC2Pron -= 1;
            } else {
                wrongC2GN += 1;
            }
            if (wrongC2GN === 0 && wrongC2Pron === 0) {
                $(".column2 .pron").css("background-color", "white");
                $(".column2 .coreferent[draggable='true']").draggable({disabled: true});
                $(".column2 .coreferent[draggable='true']").attr("draggable", false).attr("style", "background:white;opacity:1");
                validateFinish();
            } else {
                if (wrongC2Pron === 0) {
                    $(".column2 .pron").css("background-color", "white");
                }
                if (wrongC2GN > 0) {
                    $(".column2 .gn").css("background-color", "gray");
                }
            }
        }
    });
    $(".column3 .pron").droppable({
        accept: ".column3 .gn .coreferent",
        hoverClass: "ui-state-default",
        activeClass: "ui-state-highlight",
        drop: function(event, ui) {
            ui.draggable.fadeOut();
            var coref = ui.draggable.clone();
            coref.appendTo(this);
            setTimeout(function() {
                ui.draggable.remove();
            }, 1000);
            coref.attr("style", "cursor:pointer");
            coref.draggable({
                revert: "invalid",
                cursor: "pointer"
            });
            if (ui.draggable.attr("type") === "Pron") {
                wrongC3GN -= 1;
            } else {
                wrongC3Pron += 1;
            }
            if (wrongC3GN === 0 && wrongC3Pron === 0) {
                $(".column3 .gn").css("background-color", "white");
                $(".column3 .coreferent[draggable='true']").draggable({disabled: true});
                $(".column3 .coreferent[draggable='true']").attr("draggable", false).attr("style", "background:white;opacity:1");
                validateFinish();
            } else {
                if (wrongC3GN === 0) {
                    $(".column3 .gn").css("background-color", "white");
                }
                if (wrongC3Pron > 0) {
                    $(".column3 .pron").css("background-color", "gray");
                }
            }
        }
    });
    $(".column3 .gn").droppable({
        accept: ".column3 .pron .coreferent",
        hoverClass: "ui-state-default",
        activeClass: "ui-state-highlight",
        drop: function(event, ui) {
            ui.draggable.fadeOut();
            var coref = ui.draggable.clone();
            coref.appendTo(this);
            setTimeout(function() {
                ui.draggable.remove();
            }, 1000);
            coref.attr("style", "cursor:pointer");
            coref.draggable({
                revert: "invalid",
                cursor: "pointer"
            });
            if (ui.draggable.attr("subtype") === "GN") {
                wrongC3Pron -= 1;
            } else {
                wrongC3GN += 1;
            }
            if (wrongC3GN === 0 && wrongC3Pron === 0) {
                $(".column3 .pron").css("background-color", "white");
                $(".column1 .coreferent[draggable='true']").draggable({disabled: true});
                $(".column1 .coreferent[draggable='true']").attr("draggable", false).attr("style", "background:white;opacity:1");
                validateFinish();
            } else {
                if (wrongC3Pron === 0) {
                    $(".column3 .pron").css("background-color", "white");
                }
                if (wrongC3GN > 0) {
                    $(".column3 .gn").css("background-color", "gray");
                }
            }
        }
    });
});
var callbackCalled = false;
function callback() {
    if (!callbackCalled) {
        callbackCalled = true;
        setTimeout(function() {
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