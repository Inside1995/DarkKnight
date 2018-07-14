$(function(){
    $("[data-tooltip]").mousemove(function (eventObject) {
        $data_tooltip = $(this).attr("data-tooltip");
        $("#tooltip").html($data_tooltip)
            .css({
                "top" : eventObject.pageY + 5,
                "left" : eventObject.pageX + 5
            })
            .show();
    }).mouseout(function () {
        $("#tooltip").hide()
            .html("")
            .css({
                "top" : 0,
                "left" : 0
            });
    });
});
$(function () {
    $("#start-training").click(function () {
        return confirm("Вы уверены, что Вы верно выбрали условия тренировки? Тренировочный процесс можно проводить раз в 24 часа!");
    });
});