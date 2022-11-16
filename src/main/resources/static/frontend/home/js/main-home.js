//var idUsuario = sessionStorage.getItem("idUsuario")
//var IdBlibUser = sessionStorage.getItem("IdBlibUser")
var IdBlibUser = "100001"
var idUsuario = "100001"
CarregarElement()

function CarregarElement() {
    fetch("http://localhost:80/usuario/" + idUsuario, {method: 'GET'})
        .then(response => response.text())
        .then(function (result) {
            var bd_result = JSON.parse(result)
            document.querySelector('.user-img').src = bd_result.imagem
        })
        .catch(error => console.log('error', error));
    PesquisaAllBook()
    PesquisaMAlugado()
}

createElementSlider()
function createElementSlider() {
    //Variavel Comando Div
    let BookPrincipal = '';

    for (let i = 0; i < 4; i++) {
        if (i < 10) {
            BookPrincipal += `
             <div class="book-cell">
               <div class="book-img">
                <img src="https://images-na.ssl-images-amazon.com/images/I/81WcnNQ-TBL.jpg" alt="" class="book-photo">
               </div>
                 <div class="book-content">
                    <div class="book-title">BIG MAGIC</div>
                    <div class="book-author">by Elizabeth Gilbert</div>
                    <div class="book-sum">Readers of all ages and walks of life have drawn inspiration and empowerment from Elizabeth Gilbert’s books for years. </div>
                    <div class="book-see">Reservar</div>
                </div>
              </div>
        `;
        }
    }
    $(".book.js-flickity").append(BookPrincipal);
}

/*
   */
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

//Menu Active Leituras da Semana
document.querySelectorAll(".book-type").forEach(function (button) {

    button.addEventListener("click", function (IdGenero) {
        document.querySelector('.book-cards').innerHTML = ""
        $('.book-type').removeClass('active');
        $(this).addClass('active');
        if ($(this).attr('value') == "PesqTodos") {
            PesquisaAllBook()
        } else if ($(this).attr('value') == 1) {
            IdGenero = 1
            Pesquisagen(IdGenero)
        } else if ($(this).attr('value') == 3) {
            IdGenero = 3
            Pesquisagen(IdGenero)
        } else if ($(this).attr('value') == 0) {
            IdGenero = 0
            Pesquisagen(IdGenero)
        } else if ($(this).attr('value') == 2) {
            IdGenero = 2
            Pesquisagen(IdGenero)
        } else if ($(this).attr('value') == 8) {
            document.querySelector('a.book-type:before').style.left = "20px"
            console.log(a)
            IdGenero = 8
            Pesquisagen(IdGenero)
        }
    });
})

//----------------------------------------
function PesquisaAllBook() {
    fetch("http://localhost:80/livro/biblioteca?id=" + IdBlibUser, {method: 'GET'})
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            createElementBook(bd_result)
        })
        .catch(error => console.log('error', error));
}

function Pesquisagen(IdGenero) {
    fetch(`http://localhost:80/livro/pesquisarpor?idUsuario=${idUsuario}&campo=genero&value=${IdGenero}`, {method: 'GET',})
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
    let Book = '';
    //Inciar Contado Para Definir Cor
    let cont = 0
    //Incia Color
    let color = ""

    for (let i = 0; i < bd_result.length; i++) {
        if (bd_result[i].sinopse.length > 90) {
            bd_result[i].sinopse = bd_result[i].sinopse.substring(0, 90) + "...";
        }
        if (i < 6) {
            Book += `
      <div class="book-card">
         <div class="content-wrapper">
           <img src="${bd_result[i].imagem}" alt="" class="book-card-img">
             <div class="card-content">
               <div class="book-name">${bd_result[i].nome}</div>
               <div class="book-by">Autor: ${bd_result[i].autor}</div>
               <div class="book-sum card-sum">${bd_result[i].sinopse} </div>
                <div onclick="BtnRerservar(${bd_result[i].id})" class="book-see">Reservar</div>
             </div>
         </div>
    </div>
  `;
        }
    }
    $(".book-cards").append(Book);
}

//----------------------------------------

//-----------------------------------------
function PesquisaMAlugado() {
    fetch("http://localhost:80/livro/biblioteca?id=100001", {method: 'GET'})
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            createElementMalugado(bd_result)
        })
        .catch(error => console.log('error', error));
}

function createElementMalugado(bd_result) {
    //Variavel Comando Div
    let BookAlugado = '';

    for (let i = 0; i < bd_result.length; i++) {
        if (i < 10) {
            BookAlugado += `
        <div class="year-book">
          <img src="${bd_result[i].imagem}" alt="" class="year-book-img">
            <div class="year-book-content">
             <div class="year-book-name">${bd_result[i].nome}</div>
             <div class="year-book-author">Autor: ${bd_result[i].autor}</div>
            </div>
          </div>
        `;
        }
    }
    $(".week.year").append(BookAlugado);
}

//-------------------------------------
//Função Scroll de Livros
$('.popular-books').scroll(function (event) {
    event.preventDefault()
    var scroll = $(this).scrollTop();
    if (scroll > 0) {
        $('.book-slide').fadeOut('slow');
        document.querySelector(".main-wrapper").style.marginTop = '0';
    } else if (scroll == 0) {
        $('.book-slide').fadeIn('slow');
        document.querySelector(".main-wrapper").style.marginTop = '100px';
    }
});
//Carrega a Pagina de Pesquisa Por Genero
document.querySelectorAll(".sidebar").forEach(function (button) {
    button.addEventListener("click", function (event) {
        const select = event.target || event.srcElement;
        const id = select.id;
        if (id >= 0 && id <= 17) {
            if (id != "") {
                sessionStorage.setItem('tipoGenero', id);
                window.location.href = 'pesquisaGen.html';
            }
        }
    });
})


//---------------------------------------------------------------------
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
    document.getElementById('btn-anterior').addEventListener("click", function (event) {
        var raw = JSON.stringify({
            "id": null,
            "idLivroBiblioteca": id,
            "idUsuario": 100001,
            "dataLocacao": "",
            "dataDevolucao": "",
            "dataDevolvida": null,
            "statusLocacao": null
        });
        fetch("http://localhost:80/locacao/cadastrar", {
            Headers: {
                "Content-Type": "application/json"
            }, method: 'POST', body: LocaLivro
        })
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