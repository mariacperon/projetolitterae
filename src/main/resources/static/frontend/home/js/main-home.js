//var idUsuario = sessionStorage.getItem("idUsuario")
//var IdBlibUser = sessionStorage.getItem("IdBlibUser")
var IdBlibUser = "100001"
var idUsuario = "100001"


fetch("http://localhost:80/livro/biblioteca?id=" + IdBlibUser, {method: 'GET'})
    .then(response => response.text())
    .then(function resultado(result) {
        var bd_result = JSON.parse(result)
        //
        createElementSlider(bd_result)
    })
    .catch(error => console.log('error', error));

function createElementSlider(bd_result) {

    //Variavel Comando Div
    let BookPrincipal = '';
    for (let i = 0; i < 3; i++) {
        if (bd_result[i].sinopse.length > 90) {
            bd_result[i].sinopse = bd_result[i].sinopse.substring(0, 90) + "...";
        }
        BookPrincipal += `
             <div class="book-cell">
               <div class="book-img">
                <img  src="${bd_result[i].imagem}" alt="" class="book-photo">
               </div>
                 <div class="book-content">
                    <div class="book-title">${bd_result[i].nome}</div>
                    <div class="book-author">Autor: ${bd_result[i].autor}</div>
                    <div class="book-sum">${bd_result[i].sinopse} </div>
                    <div onclick="BtnRerservar(${bd_result[i].id})"  class="book-see">Reservar</div>
                </div>
              </div>
        `;
    }

    $(".book.js-flickity").append(BookPrincipal);
}


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
    fetch("http://localhost:80/livro/biblioteca?id=" + IdBlibUser, {method: 'GET'})
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            createElementMalugado(bd_result)
            //

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

//Evento Sinopse
$("#btn-sinopse").click(function () {
    $("#btn-sinopse").addClass('active_button')
    $("#btn-resenha").removeClass('active_button')
    document.getElementById('Sinopse').style.display = "block"
    document.getElementById('Resenha').style.display = "none"
});
//Evento Resenha
$("#btn-resenha").click(function () {
    $("#btn-sinopse").removeClass('active_button')
    $("#btn-resenha").addClass('active_button')
    document.getElementById('Sinopse').style.display = "none"
    document.getElementById('Resenha').style.display = "flex"
});
var cont = 0
$(".btn-leiaM").click(function () {
    cont++
    document.getElementById('msg-vis').innerHTML = "Visualizar Menos"

    $('.Section-Resenha').toggleClass('show')
    if (cont == 2) {
        document.getElementById('msg-vis').innerHTML = "Visualizar todas as Resenhas"
        cont = 0
    }
})
//Botão Reserva


$("#btn-voltar").click(function () {
    document.getElementById('modal').classList.remove('active')
})

function BtnRerservar(id) {
    $('#btn-loc-enviar').removeClass('block')
    document.getElementById('btn-loc-enviar').style.cursor = "pointer"
    document.getElementById('msg-reserva').style.color = "#3d4954"
    document.getElementById('msg-reserva').innerText = ""
    document.getElementById('modal').classList.add('active')

    fetch("http://localhost:80/livro/" + id, {
        Headers: {
            "Content-Type": "application/json"
        }, method: 'GET'
    })
        .then(response => response.text())
        .then(function (result) {
            var bd_result = JSON.parse(result)
            document.getElementById('name-book').innerHTML = bd_result.nome
            document.getElementById('autor').innerHTML = "Autor: " + bd_result.autor
            document.getElementById('img-book').src = bd_result.imagem
            document.getElementById('Editora').innerHTML = "Editora: " + bd_result.editora
            document.getElementById('idioma').innerHTML = "Idioma:" + bd_result.idioma
            document.getElementById('Sinopse-text').innerHTML = bd_result.sinopse
            fetch(`http://localhost:80/livro-biblioteca?idBiblioteca=${IdBlibUser}&idLivro=${id}`, {method: 'GET'})
                .then(response => response.text())
                .then(function (result) {
                    result = JSON.parse(result)
                    document.getElementById('idlivroBlib').value = result.livro.id
                    var idblib = result.livro.id
                    qtdlivros(idblib)
                })
        })
        .catch(error => console.log('error', error));

    function qtdlivros(idblib) {
        fetch("http://localhost:80/livro-biblioteca/estoque/" + idblib, {method: 'GET'})
            .then(response => response.json())
            .then(function (result) {
                if (result <= 0) {
                    document.getElementById('qtd-disponivel').innerHTML = "Sem Livros em Estoque "
                } else {
                    document.getElementById('qtd-disponivel').innerHTML = "Quantidade Disponivel: " + result
                }
            })
            .catch(error => console.log('error', error));
    }


    fetch("http://localhost:80/resenha/livro/2", { method: 'GET'})
        .then(response => response.text())
        .then(function (result){
          var  bd_result = JSON.parse(result)
            createElementRes(bd_result)
        })
        .catch(error => console.log('error', error));

    function createElementRes(bd_result) {
        //Variavel Comando Div
        let resenha = '';
        //Inciar Contado Para Definir Cor

        for (let i = 0; i < bd_result.length; i++) {
            resenha += `
        <li>
          <img src="${bd_result[i].usuario.imagem}" alt="User"> <b>${bd_result[i].usuario.nome}</b>
          <p>${bd_result[i].resenha}</p>
        </li>           
            `;
        }
        $("#ul-resenha").append(resenha);
    }

    document.getElementById('btn-loc-enviar').addEventListener("click", function (event) {
        var LocacaoJson = JSON.stringify({
            "id": null,
            "idLivroBiblioteca": $('#idlivroBlib').val(),
            "idUsuario": idUsuario,
            "dataLocacao": null,
            "dataDevolucao": null,
            "dataDevolvida": null,
            "statusLocacao": 0
        });
        fetch("http://localhost:80/locacao/cadastrar", {
            method: 'POST',
            body: LocacaoJson,
            headers: {'Content-Type': 'application/json'}
        })
            .then(function (response) {
                if (response.status >= 200 && response.status <= 300) {
                    document.getElementById('msg-reserva').style.color = "#0c4900"
                    $('#msg-reserva').addClass('activetext')
                    document.getElementById('msg-reserva').innerText = "Livro Locado Com Sucesso"
                    $('#btn-loc-enviar').addClass('block')
                    setTimeout(function () {
                        document.getElementById('modal').classList.remove('active')
                        $('#msg-reserva').removeClass('activetext')
                    }, 3000)
                }
                return response.json()
            }).then(function (result) {
            if (result.mensagem == "Esse livro não está disponível para locação no momento.") {
                $('#msg-reserva').addClass('activetext')
                document.getElementById('msg-reserva').innerText = "Sem Estoque de Livro no Momento."
                $('#btn-loc-enviar').addClass('block')
                setTimeout(function () {
                    document.getElementById('modal').classList.remove('active')
                    $('#msg-reserva').removeClass('activetext')
                }, 3000)
            }
        })
            .catch(error => {
                document.getElementById('msg-reserva').style.color = "#FF0000"
                $('#msg-reserva').addClass('activetext')
                document.getElementById('msg-reserva').innerText = "Erro Contate um Administrador."

                setTimeout(function () {
                    document.getElementById('modal').classList.remove('active')
                    $('#msg-reserva').removeClass('activetext')
                }, 3000)
                console.log(error)
            })
    })

    $("#btn-enviar-rese").click(function () {
        $('#msg-resenha').val()
    })
}

//Eventos
const closeModal = () => document.getElementById('modal')
    .classList.remove('active')


document.getElementById('btn-voltar').addEventListener('click', closeModal)