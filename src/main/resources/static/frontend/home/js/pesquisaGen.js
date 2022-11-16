//var idUsuario = sessionStorage.getItem("idUsuario")
var IdGenero = sessionStorage.getItem("tipoGenero")
var idUsuario = "100001"
var campo = "genero"

CarregarElement()
function CarregarElement(){
    fetch("http://localhost:80/usuario/"+idUsuario, {method:'GET'})
        .then(response => response.text())
        .then(function(result){
            var bd_result = JSON.parse(result)
            document.querySelector('.user-img').src = bd_result.imagem
        })
        .catch(error => console.log('error', error));
}

//-------------------------------------------------
/*Eventos Pagina Responsiva*/
document.querySelectorAll(".sidebar").forEach(function (button) {
    button.addEventListener("click", function (event) {
        const select = event.target || event.srcElement;
        const id = select.id;
        if (id >= 0 && id <= 17) {
            if (id != "") {
                sessionStorage.setItem('tipoGenero', id);
                location.reload()
            }
        }
    });
})
Pesquisagen()

async function Pesquisagen() {
    fetch(`http://localhost:80/livro/pesquisarpor?idUsuario=${idUsuario}&campo=${campo}&value=${IdGenero}`, {method: 'GET',})
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            createElementBook(bd_result)
        })
        .catch(error => console.log('error', error));
}

//Cria os Elementos
function createElementBook(bd_result) {
    var leiamais_limit = 60;
    //Variavel Comando Div
    let product = '';
    //Inciar Contado Para Definir Cor
    let cont = 0
    //Incia Color
    let color = ""

    for (let i = 0; i < bd_result.length; i++) {

        if (bd_result[i].sinopse.length > 90) {
            bd_result[i].sinopse = bd_result[i].sinopse.substring(0, 90) + "...";
        }
        cont++
        if (cont == 1) {
            color = "#cbb5e2"
        }
        if (cont == 2) {
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
    <div style="color: ${color};" onclick="BtnRerservar(${bd_result[i].id})" class="book-reserva">Reservar</div>
    </div>
   </div>
  `;
    }
    $(".book-list").append(product);
}

//Reservar Livro
function BtnRerservar(id) {
    document.getElementById('msg-edituser').style.color = "#3d4954"
    document.getElementById('msg-edituser').innerHTML = "Confirmar Locação "
    document.getElementById('modal')
        .classList.add('active')

    fetch("http://localhost:80/livro/" + id, {
        Headers: {
            "Content-Type": "application/json"
        }, method: 'GET'
    })
        .then(response => response.text())
        .then(function (result) {
            var bd_result = JSON.parse(result)
            console.log(bd_result)
            document.getElementById('tituloLivro').innerHTML = bd_result.nome
            document.getElementById('nomeAutor').innerHTML = bd_result.autor
            document.getElementById('imagemCapa').src = bd_result.imagem
        })
        .catch(error => console.log('error', error));
    document.getElementById('btn-anterior').addEventListener("click", function( event ) {
        var raw = JSON.stringify({
            "id": null,
            "idLivroBiblioteca": id,
            "idUsuario": 100001,
            "dataLocacao": "",
            "dataDevolucao": "",
            "dataDevolvida": null,
            "statusLocacao": null
        });
        fetch("http://localhost:80/locacao/cadastrar", {  Headers: {
                "Content-Type": "application/json"
            }, method: 'POST',body:LocaLivro})
            .then(response => response.text())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    })
}
//Manipulação das Dasas
function DateInserir() {
    var dataAtual = new Date();

    //Passa Zero para Data
    function zeroFill(n) {
        return n < 9 ? `0${n}` : `${n}`;
    }

    //Formata a Data a data Atual no padrão Mundial
    function formatDateAtual(dataAtual) {
        const d = zeroFill(dataAtual.getDate());
        const mo = zeroFill(dataAtual.getMonth() + 1);
        const y = zeroFill(dataAtual.getFullYear());
        return `${y}-${mo}-${d}`;
    }

    //Formata a Data a data Atual no padrão Mundial
    function formatDateFinal(dataFinal) {
        const d = zeroFill(dataFinal.getDate());
        const mo = zeroFill(dataFinal.getMonth() + 1);
        const y = zeroFill(dataFinal.getFullYear());
        return `${y}-${mo}-${d}`;
    }

    //Carrega os Elementos
    function CarregarData(dataFinal, dataAtual) {
        document.getElementById('dataLocacao').value = dataAtual
        document.getElementById('dataDevolucao').min = dataAtual
        document.getElementById('dataDevolucao').max = dataFinal
    }

    //Faz a Soma da Data
    const dataFinal = new Date(dataAtual);
    dataFinal.setDate(dataFinal.getDate() + 15);
    dataFinal.setMonth(dataFinal.getMonth() + 0);
    dataFinal.setFullYear(dataFinal.getFullYear() + 0);

    CarregarData(formatDateFinal(dataFinal), formatDateAtual(dataAtual));

    document.getElementById('btn-loc-enviar').addEventListener('click', (event) => {

        document.getElementById('aviso-loc').style.color = "#FF4949"
        var inputdtDevolucao = document.getElementById('dataDevolucao').value

        if (inputdtDevolucao != "") {
            if (inputdtDevolucao > formatDateFinal(dataFinal) || inputdtDevolucao < formatDateAtual(dataAtual)) {
                document.getElementById('aviso-loc').innerHTML = "insira uma data entre os dias delimitados "
                document.getElementById('container-aviso').style.opacity = "1"
                setTimeout(function () {
                    document.getElementById('container-aviso').style.opacity = "0"
                }, 3000)

            } else {
                //Função Armazena Locação
                document.getElementById('aviso-loc').style.color = "#0C4900FF"
                document.getElementById('aviso-loc').innerHTML = "Livro Alugado Com sucesso"
                document.getElementById('container-aviso').style.opacity = "1"
                setTimeout(function () {
                    document.getElementById('container-aviso').style.opacity = "0"
                }, 3000)
                console.log("Data Enviada é " + inputdtDevolucao)
            }

        } else {
            console.log("click")
            document.getElementById('aviso-loc').innerHTML = "Erro, Verifique o campo de Data de Devolução."
            document.getElementById('container-aviso').style.opacity = "1"
            setTimeout(function () {
                document.getElementById('container-aviso').style.opacity = "0"
            }, 3000)
        }
    });
}
DateInserir()




//Eventos
const closeModal = () => document.getElementById('modal')
    .classList.remove('active')

document.getElementById('modalClose').addEventListener('click', closeModal)

document.getElementById('button-loc-canc').addEventListener('click', closeModal)

//Animação Browse-category left show
$(".browse-category").click(function () {
    $(".sidebar").animate({width: '250px'});
});
//Animação Browse-category left hide
$(".closebtn").click(function () {
    $(".sidebar").animate({width: '0px'});
});

//Animação Menu Dropdown
$('.profile-menu').on('click', function () {
    $('#SidepanelMenu').slideToggle('slow');
})