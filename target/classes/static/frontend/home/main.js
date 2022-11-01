//Animação Browse-category left show
$(".browse-category").click(function(){
    $("#mySidebar").animate({width: '250px'});
});
//Animação Browse-category left hide
$(".closebtn").click(function(){
    $("#mySidebar").animate({width: '0px'});
});

//Animação Menu Dropdown
$('.profile-menu').on('click', function() {
    $('#SidepanelMenu').slideToggle('slow');
})
