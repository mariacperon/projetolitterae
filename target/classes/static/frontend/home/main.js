function openNav() {
    document.getElementById("mySidebar").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidebar").style.width = "0";
}
$('.profile-menu').on('click', function() {
    $('#SidepanelMenu').slideToggle('slow');
});
