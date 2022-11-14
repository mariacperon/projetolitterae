//var idUsuario = sessionStorage.getItem("idUsuario")
var IdGenero = sessionStorage.getItem("tipoGenero")
var idUsuario = "100001"
var campo = "genero"


//Animação Browse-category left show
$(".browse-category").click(function () {
    $(".sidebar").animate({ width: '250px' });
});
//Animação Browse-category left hide
$(".closebtn").click(function () {
    $(".sidebar").animate({ width: '0px' });
});

//Animação Menu Dropdown
$('.profile-menu').on('click', function () {
    $('#SidepanelMenu').slideToggle('slow');
})
//-------------------------------------------------
/*Eventos Pagina Responsiva*/
document.querySelectorAll(".sidebar").forEach( function(button) {
    button.addEventListener("click", function(event) {
        const select = event.target || event.srcElement;
        const id = select.id;
        if(id >=0 && id <=17) {
            if (id  !=""){
                sessionStorage.setItem('tipoGenero', id);
                location.reload()
            }
        }
    });
})
carrega()
async function carrega(){
//window.location.href = '';
fetch(`http://localhost:80/livro/pesquisarpor?idUsuario=${idUsuario}&campo=${campo}&value=${IdGenero}`, {method: 'GET',})
    .then(response => response.text())
    .then(function resultado(result) {
        var bd_result = JSON.parse(result)
        createElementBook(bd_result)
    })
    .catch(error => console.log('error', error));
}

function createElementBook(bd_result) {
    var leiamais_limit = 60;
    //Variavel Comando Div
    let product = '';
    //Inciar Contado Para Definir Cor
    let cont = 0
    //Incia Color
    let color = ""

    for (let i = 0; i < bd_result.length; i++) {
        
        if (bd_result[i].sinopse.length > 90){
            bd_result[i].sinopse = bd_result[i].sinopse.substring(0,90)+"...";
        }
        cont++
        if (cont == 1) {
            color = "#cbb5e2"
        } if (cont == 2) {
            color = "#fbadaf"
        } else if (cont == 3) {
            color = "#fdca95"
        } else if (cont == 4) {
            color = "#fbadaf"
        } else if (cont == 5) {
            color = "#fdca95"
        } else if (cont == 6) {
            color = "#cbb5e2"
            cont = 0
        }
        product += `
    <div style="background-color: ${color};" class="book-product">
    <div class="book-image">
        <img class="book-photo" src="${bd_result[i].imagem}">
    </div >
    <div class="book-detail">
    <div class="Titulo-book">${bd_result[i].nome}</div>
    <div class="autor-book">Autor: ${bd_result[i].autor} </div>
    <div class="sinopse-book">${bd_result[i].sinopse}</div>
    <div style="color: ${color};" id= "${bd_result[i].id}"class="book-reserva">Reservar</div>
    </div>
   </div>
  `;
    }
    $(".book-list").append(product);
}
