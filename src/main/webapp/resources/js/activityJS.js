var errGNGN = "Attention, ce <strong>groupe nominal</strong> n'est pas vraiment le <strong>groupe nominal</strong> qui convient pour compléter la série. Essayez encore !";
var errGNPron = "Attention vous vous êtes trompé. Le <strong>groupe nominal</strong> que vous avez choisi n'est pas le bon choix ; on attendait peut-être un autre type d'<strong>unité textuelle</strong>. Essayez encore !";
var errPronGN = "Attention vous vous êtes trompé. L'<strong>unité textuelle</strong> que vous avez choisie n'est pas le bon choix ; on attendait peut-être un <strong>groupe nominal</strong>. Faites un nouveau essai !";
var errPronPron = "Attention, cette <strong>unité textuelle<strong> n'est pas vraiment la bonne <strong>unité textuelle</strong> qui convient pour compléter la série. Essayez encore !";

var success = ["Bravo !", "Excellent !", "Félicitations !", "Magnifique,bien joué !", "Excellent travail, félicitations !"];

$(function() {
    $(".coreferent[draggable='true'], .referent[draggable='true']").attr("style", "cursor: pointer");
    $(".coreferent[draggable='true'], .referent[draggable='true']").draggable({
        revert: "invalid",
        helper: "clone",
        cursor: "pointer",
        disabled: false
    });
    $(".selection").droppable({
        hoverClass: "ui-state-highlight",
        activeClass: "ui-state-hover",
        drop: function(event, ui) {
            if (ui.draggable.attr("right") === "true") {
                ui.draggable.attr("style", "color:orange");
                var newRow = $('<tr><td>' + ui.draggable.text() + '</td></tr>');
                newRow.appendTo(this);
                $("#message").removeClass("warning");
                $("#message").addClass("success").text(success[Math.floor(Math.random() * success.length)]).show("pulsate", 500);
                $("#next").fadeIn();
                
                $(".coreferent[draggable='true'], .referent[draggable='true']").draggable({disabled: true});
                $(".coreferent[draggable='true'], .referent[draggable='true']").attr("draggable", false);
            } else {
                ui.draggable.draggable({disabled: true});                
                ui.draggable.attr("draggable", false);
                $("#message").removeClass("success");                
                if (ui.draggable.attr("subtype") === "GN" && subTypeToFind === "GN") {
                    $("#message").addClass("warning").html(errGNGN).show("shake", 500, callback);
                } else
                if (ui.draggable.attr("subtype") === "GN" && typeToFind === "Pron") {
                    $("#message").addClass("warning").html(errGNPron).show("shake", 500, callback);
                } else
                if (ui.draggable.attr("type") === "Pron" && subTypeToFind === "GN") {
                    $("#message").addClass("warning").html(errPronGN).show("shake", 500, callback);
                } else
                if (ui.draggable.attr("type") === "Pron" && typeToFind === "Pron") {
                    $("#message").addClass("warning").html(errPronPron).show("shake", 500, callback);
                }                
            }
        }
    });
});

function callback() {
    setTimeout(function() {
        $("#message").fadeOut();
    }, 8000);
}
;