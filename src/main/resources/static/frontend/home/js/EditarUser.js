//var idUsuario = sessionStorage.getItem("idUsuario")
//var IdGenero = sessionStorage.getItem("tipoGenero")
var idUsuario = "100001"
var campo = "genero"


function CarregarElement(){
    fetch("http://localhost:80/usuario/"+idUsuario, {method:'GET'})
        .then(response => response.text())
        .then(function(result){

        })
        .catch(error => console.log('error', error));
}

//-------------------------------------------------

//Eventos

//Animação Menu Dropdown
$('.profile-menu').on('click', function () {
    $('#SidepanelMenu').slideToggle('slow');
})


