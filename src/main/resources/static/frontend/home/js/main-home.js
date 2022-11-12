//Animação Browse-category left show
$(".browse-category").click(function(){
    $(".sidebar").animate({width: '250px'});
});
//Animação Browse-category left hide
$(".closebtn").click(function(){
    $(".sidebar").animate({width: '0px'});
});

//Animação Menu Dropdown
$('.profile-menu').on('click', function() {
    $('#SidepanelMenu').slideToggle('slow');
})
//Menu Active Leituras da Semana
document.querySelectorAll(".book-type").forEach( function(button) {

    button.addEventListener("click", function(event) {
        const el = event.target || event.srcElement;
        const id = el.id;
        console.log(id);
        $('.book-type').removeClass('active');
        $(this).addClass('active');
    });
})
//Função Scroll de Livros
$('.popular-books').scroll(function (event) {
    event.preventDefault()
    var scroll = $(this).scrollTop();
    if (scroll > 0){
        $('.book-slide').fadeOut('500');
        document.querySelector(".main-wrapper").style.marginTop='0';
    }else if (scroll == 0){
        $('.book-slide').fadeIn('500');
        document.querySelector(".main-wrapper").style.marginTop='100px';
    }
});

document.querySelectorAll(".sidebar").forEach( function(button) {
    button.addEventListener("click", function(event) {
        const select = event.target || event.srcElement;
        const id = select.id;
        if(id >=0 && id <=17)
            sessionStorage.setItem('tipoGenero', id);
             window.location.href = 'pesquisaGen.html';
    });
})