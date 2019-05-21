$(function () {
    $.get("spaceheader.html",function (data) {
        $("#spaceheader").html(data);
});
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});