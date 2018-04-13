var i = 0;
var moveTime = 500;
$(document).ready(function(){
    closeOrOpen.value="off";
    // if(navigator.platform.toLowerCase().indexOf("win")>-1){
    //     document.writeln("请用手机打开");
    // }
})
function leftmove() {
    if (i % 2 == 0) {
        $("#main").animate({
            left: "+=300px"
        }, moveTime, function () {

        });
        $(".hamburger").removeClass("is-closed");
        $(".hamburger").addClass("is-open");
        closeOrOpen.value="open";
    } else {
        $("#main").animate({
            left: "-=300px"
        }, moveTime, function () {

        });
        $(".hamburger").removeClass("is-open");
        $(".hamburger").addClass("is-closed");
        closeOrOpen.value="off";
    }
    i++;
}

$(window).scroll(function () {
    if ($(document).scrollTop() < 0 && closeOrOpen.value=="off") {
        $("#hide_menu").hide();
    }else {
        $("#hide_menu").show();
    }
});

function scrollGoto(id){
    id = "#"+id;
    $(document).scrollTop($(id).offset().top);
}
