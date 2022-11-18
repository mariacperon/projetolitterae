//var idUsuario = sessionStorage.getItem("idUsuario"); 
var idUsuario = 100001
var paginaatual = 0
//------------------- Notificação ----------------------------
createElementNotif(idUsuario,)

//Função para Criar as Notificaçãoes Biblioteca
function createElementNotif(idUsuario) {
    fetch("http://localhost:80/notificacao/biblioteca/" + idUsuario, {method: 'GET'})
        .then(response => response.text())
        .then(result => {
            var bd_result = JSON.parse(result)
            createMessage(bd_result)
        })
        .catch(error => console.log('error', error));
    //Variavel Comando Div
    let notific = '';

    function createMessage(bd_result) {
        for (let i = 0; i < bd_result.length; i++) {
            notific += `
    <div class="message-box">
            <!---- Content Menssagem ---->
            <div class="message-content">
              <div class="message-header">
                <div class="name">${bd_result[i].titulo}</div>
                <div onclick="deletNotifc(${bd_result[i].id})"  class="Del-Lixeira">
                    <svg version="1.0" xmlns="http://www.w3.org/2000/svg"
                        width="18" height="15" viewBox="0 0 512.000000 512.000000"
                        preserveAspectRatio="xMidYMid meet">
                            <g transform="translate(0.000000,512.000000) scale(0.100000,-0.100000)"
                            fill="#" stroke="none">
                                <path d="M1864 5111 c-95 -24 -195 -107 -237 -199 -20 -43 -22 -66 -25 -259
                                l-3 -212 -448 -3 -448 -3 -48 -30 c-33 -21 -57 -47 -79 -85 l-31 -55 0 -270 0
                                -270 31 -55 c22 -38 46 -64 79 -85 44 -27 57 -30 152 -33 l103 -4 0 -1607 c0
                                -1114 3 -1626 11 -1667 20 -112 76 -186 182 -242 l52 -27 1405 0 1405 0 50 25
                                c60 29 126 93 154 148 11 22 25 68 31 103 6 42 10 611 10 1666 l0 1601 103 4
                                c95 3 108 6 152 33 33 21 57 47 79 85 l31 55 0 270 0 270 -31 55 c-22 38 -46
                                64 -79 85 l-48 30 -448 3 -448 3 -3 212 c-3 193 -5 216 -25 259 -30 66 -105
                                143 -172 175 l-56 28 -685 2 c-377 1 -699 -2 -716 -6z m1356 -291 c19 -19 20
                                -33 20 -200 l0 -180 -680 0 -680 0 0 180 c0 167 1 181 20 200 20 20 33 20 660
                                20 627 0 640 0 660 -20z m1070 -825 l0 -165 -1730 0 -1730 0 0 165 0 165 1730
                                0 1730 0 0 -165z m-370 -2068 c0 -1249 -3 -1626 -12 -1635 -17 -17 -2679 -17
                                -2696 0 -9 9 -12 386 -12 1635 l0 1623 1360 0 1360 0 0 -1623z"/>
                                <path d="M1672 3081 c-18 -12 -41 -35 -52 -53 -20 -32 -20 -53 -20 -1138 0
                                -1051 1 -1107 18 -1139 54 -101 203 -91 248 16 12 27 14 222 14 1125 0 1021
                                -1 1095 -18 1127 -36 72 -126 101 -190 62z"/>
                                <path d="M2500 3083 c-19 -10 -45 -33 -57 -52 l-23 -34 0 -1104 c0 -1198 -3
                                -1129 55 -1183 22 -21 37 -25 85 -25 48 0 63 4 85 25 58 54 55 -15 55 1182 0
                                1076 -1 1104 -20 1135 -39 65 -117 89 -180 56z"/>
                                <path d="M3321 3083 c-19 -10 -44 -31 -55 -46 -21 -28 -21 -34 -24 -1135 -2
                                -1039 -1 -1109 15 -1142 51 -100 193 -106 245 -9 17 32 18 88 18 1139 0 1078
                                -1 1106 -20 1137 -39 65 -117 89 -179 56z"/>
                                </g>
                         </svg>
                </div>
              </div>
              <p class="message-line">
                ${bd_result[i].mensagem}
              </p>
            </div>
          </div>
  `;
        }
        $(".messages").append(notific);
    }

}

function deletNotifc(id) {
    fetch("http://localhost:80/notificacao/excluir/" + id, {method: 'DELETE',})
        .then(function (result) {
            console.log(result.status)
            if (result.status >= 200 && result.status <= 300) {
                document.querySelector('.messages').innerHTML = ""
                createElementNotif(idUsuario)
                console.log(result)
            } else {
                document.getElementById('msg-Notific').style.color = "#FF0000"
                document.getElementById('msg-Notific').innerHTML = "Erro ao Deletar"
            }
        })
        .catch(error => console.log('error', error));
}

//------------------- Input Pesuisa Controler ----------------------------
//input Select
$('#Search-Select').change(function (inputsearch) {
    document.getElementById('title-table').innerText = ""
    var thead = document.getElementById('title-table')
    //Cria Elemento Linha da tabela
    let tr = thead.insertRow();
    //Cria a coluna da tabela
    let td_1 = tr.insertCell();
    let td_2 = tr.insertCell();
    let td_3 = tr.insertCell();
    let td_4 = tr.insertCell();
    let td_5 = tr.insertCell();
    let td_6 = tr.insertCell();
    if ($('select#Search-Select').val() == "leitor") {
        clearTable();
        document.querySelector('#main').style.display = 'flex'
        document.querySelector('#legenda').style.display = 'none'
        $('.search-input').attr('placeholder', 'Digite Nome ou ID do Leitor');
        td_1.innerText = "ID"
        td_2.innerText = "Nome"
        td_3.innerText = "Celular"
        td_4.innerText = "Email"
        td_5.innerText = "CPF"
        td_6.innerText = "AÇÃO"
        PesqUser()
    } else if ($('#Search-Select').val() == "livros") {
        clearTable();
        document.querySelector('#main').style.display = 'flex'
        document.querySelector('#legenda').style.display = 'none'
        $('.search-input').attr('placeholder', 'Digite Nome ou ID de um Livro');
        td_1.innerText = "ISBN"
        td_2.innerText = "Livro"
        td_3.innerText = "Autor"
        td_4.innerText = "Idioma"
        td_5.innerText = "QTD"
        td_6.innerText = "Ação"
        PesqBook()
    } else if ($('#Search-Select').val() == "pendencia") {
        clearTable();

        document.querySelector('#main').style.display = 'flex'
        document.querySelector('#legenda').style.display = 'none'
        $('.search-input').attr('placeholder', 'Digite id da Pendência');
        td_1.innerText = "Nome Usuario"
        td_2.innerText = "Nome do Livro"
        td_3.innerText = "Data Devolução"
        td_4.innerText = "Valor Multa"
        td_5.innerText = "Status"
        td_6.innerText = "Ação"
    } else if ($('#Search-Select').val() == "locacao") {
        clearTable();
        document.querySelector('#main').style.display = 'flex'
        document.querySelector('#legenda').style.display = 'block'
        $('.search-input').attr('placeholder', 'Digite Nome ou ID de um Livro');
        td_1.innerText = "Nome Usuario"
        td_2.innerText = "Nome do Livro"
        td_3.innerText = "Data Locação"
        td_4.innerText = "Valor Devolução"
        td_5.innerText = "Status"
        td_6.innerText = "Ação"
        Pesqloc()
    }
});


//Input Pesquisa
function search_users() {
    clearTable();
    if ($('select#Search-Select').val() == null) {
        $('.search-input').val('')
        $('.search-input').attr('placeholder', 'Selecione um Tipo de Busca');
    } else if ($('select#Search-Select').val() == "leitor") {
        PesqUser()
    } else if ($('#Search-Select').val() == "livros") {
        console.log($('select#Search-Select').val())
    } else if ($('#Search-Select').val() == "pendencia") {
        console.log($('select#Search-Select').val())
    }
}

//------------------- User Table ----------------------------

//Evita Reload da pagina
document.querySelector('#form-user').addEventListener('submit', e => {
    e.preventDefault()
})


//Funcition Conexão com banco chamada Key Press
function PesqUser(inputsearch) {
    inputsearch = $(".search-input").val()
    fetch(`http://localhost:80/usuario/nome?nome=${inputsearch}&idbiblioteca=${idUsuario}`, {method: 'GET'})
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            CreatElementUser(bd_result)
        })
        .catch(error => console.log('error', error));
}

//Pesquisa o Usuário pelo id Biblioteca
function PesqUserBilib() {
    fetch("http://localhost:80/usuario/biblioteca/" + idUsuario, {
        headers: {'Content-Type': 'application/json'},
        method: 'GET',
    })
        .then(response => response.text())
        .then(function resultado(result) {
             bd_result = JSON.parse(result)
            CreatElementUser(bd_result)
            console.log(bd_result)
        })
        .catch(error => console.log('error', error));
}

//Funcition Botão de Editar Leitor
function editarUser(id) {

    $('#cpf').mask('000.000.000-00');
    $('#celular').mask('(00) 00000-0000');
    $('#telefone').mask('0000-0000');
    $('#cep').mask('00000-000');

    document.getElementById('msg-edituser').style.color = "#1f1d2b"
    document.getElementById('msg-edituser').innerHTML = "Editar Usuário"
    document.getElementById('modaluser').classList.add('active')

    //Chamada Api fetch conexão com banco para setar dados nos inputs
    fetch("http://localhost:80/usuario/" + id, {method: 'GET',})
        .then(response => response.text())
        .then(function resultado(result) {
            var data = JSON.parse(result)
            document.getElementById('nome').value = data.nome
            document.getElementById('sobrenome').value = data.sobrenome
            document.getElementById('data').value = data.dataNascimento
            document.getElementById('celular').value = data.telefone1
            document.getElementById('telefone').value = data.telefone2
            document.getElementById('cpf').value = data.cpf
            document.getElementById('email').value = data.email
            document.getElementById('cep').value = data.enderecoUsuario.cep
            document.getElementById('rua').value = data.enderecoUsuario.rua
            document.getElementById('numero').value = data.enderecoUsuario.numero
            document.getElementById('bairro').value = data.enderecoUsuario.bairro
            document.getElementById('cidade').value = data.enderecoUsuario.cidade
            document.getElementById('estado').value = data.enderecoUsuario.estado
            document.getElementById('idendereco').value = data.enderecoUsuario.id
            document.getElementById('idbiblioteca').value = data.biblioteca.id
            document.getElementById('scrImagem').value = data.imagem
        }).catch(error => console.log('error', error));

    // Limpa os dados de Endereço do Formulário
    const limparFormulario = (endereco) => {
        document.getElementById('rua').value = '';
        document.getElementById('bairro').value = '';
        document.getElementById('cidade').value = '';
        document.getElementById('estado').value = '';
    }

//Preenche os Dados do Endereço Conforme a resposta do Viacep
    const preencherFormulario = (endereco) => {
        document.getElementById('rua').value = endereco.logradouro;
        document.getElementById('bairro').value = endereco.bairro;
        document.getElementById('cidade').value = endereco.localidade;
        document.getElementById('estado').value = endereco.uf;
    }

    const eNumero = (numero) => /^[0-9]+$/.test(numero);

    const cepValido = (cep) => cep.length == 8 && eNumero(cep);

    const pesquisarCep = async () => {
        limparFormulario();
        //Chama Api do via cep e Valida ela
        const cep = document.getElementById('cep').value.replace("-", "");
        const url = `https://viacep.com.br/ws/${cep}/json/`;
        if (cepValido(cep)) {
            const dados = await fetch(url);
            const endereco = await dados.json();
            if (endereco.hasOwnProperty('erro')) {
                document.getElementById('endereco').value = 'CEP não encontrado!';
            } else {
                preencherFormulario(endereco);
            }
        } else {
            document.getElementById('endereco').value = 'CEP incorreto!';
        }

    }

    document.getElementById('cep')
        .addEventListener('focusout', pesquisarCep);

    //Evento de Confirmação botão confirmar modal Edit user
    const btnconfirm = document.querySelector('#Btn-Confirm');
    btnconfirm.addEventListener('click', function (result) {
        validarCPF(cpf)
        if (validarCPF(cpf) == true) {
            //Passa os Valores Para o Json
            var jsonCadastro = JSON.stringify({
                "id": id,
                "cpf": $("#cpf").val(),
                "nome": $("#nome").val(),
                "sobrenome": $("#sobrenome").val(),
                "enderecoUsuario": {
                    "id": $("#idendereco").val(),
                    "cep": $("#cep").val(),
                    "estado": $("#estado").val(),
                    "cidade": $("#cidade").val(),
                    "bairro": $("#bairro").val(),
                    "rua": $("#rua").val(),
                    "numero": $("#numero").val(),
                    "complemento": ""
                },
                "idBiblioteca": $("#idbiblioteca").val(),
                "email": $("#email").val(),
                "dataNascimento": $("#data").val(),
                "telefone1": $("#celular").val(),
                "telefone2": $("#celular").val(),
                "imagem": $("#scrImagem").val(),
                "tipoPerfil": 1,
                "ativo": true
            });
            if (validateEmail(result) == true) {
                AlteraDt()
            }
            console.log($("#cpf").val())

            function AlteraDt() {

                fetch("http://localhost:80/usuario/alterar", {
                    method: 'PUT', headers: {
                        'Accept': 'application/json', 'Content-Type': 'application/json'
                    }, body: jsonCadastro
                }).then(function (result) {
                    if (result.status >= 200 && result.status <= 300) {
                        document.querySelector('#msg-edituser').style.color = "#0c4900";
                        document.querySelector('#msg-edituser').innerHTML = 'Dados Alterados com Sucesso'
                        setTimeout(function () {
                            document.getElementById('modaluser').classList.remove('active')
                        }, 2000)
                        clearTable()
                    } else {

                        console.log(result.json())
                    }

                }).catch(error => console.log('error', error));
            }
        } else {
            document.querySelector('#AvisoCpf').style.color = "Red";
            document.querySelector('#AvisoCpf').innerHTML = 'CPF Invalído'
            setTimeout(function () {
                document.querySelector('#AvisoCpf').style.color = "#333";
                document.querySelector('#AvisoCpf').innerHTML = 'CPF'
            }, 4000);

        }

    });
}

//Função Deletar User
function deletLeitor(id) {
    document.querySelector('#msgDelete').innerHTML = 'Você deseja Realmente deletar esse Usúario?'
    document.querySelector('#msgDelete').style.color = "#1f1d2b";
    document.getElementById('modalDel-user').classList.add('active')
    const delconf = document.querySelector('#Btn-Confirm-del');
    delconf.addEventListener('click', function () {
        fetch("http://localhost:80/usuario/excluir/" + id, {
            method: 'DELETE', headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(function (result) {
                if (result.status >= 200 && result.status <= 300) {
                    document.querySelector('#msgDelete').style.color = "#0c4900";
                    document.querySelector('#msgDelete').innerHTML = 'Dados Alterados com Sucesso'
                    setTimeout(function () {
                        document.getElementById('modalDel-user').classList.remove('active')
                    }, 2000)
                    clearTable()
                }
            })
            .catch(error => console.log('error', error));
    });
}

//Função Para Criar Tabela de Resultados Usuario
function CreatElementUser(bd_result) {

    var tbody = document.getElementById('users_table')
    for (var i = 0; i < bd_result.length; i++) {
        //Cria Elemento Linha da tabela
        let tr = tbody.insertRow();
        //Cria a coluna da tabela
        let td_id = tr.insertCell();
        let td_nome = tr.insertCell();
        let td_Celular = tr.insertCell();
        let td_email = tr.insertCell();
        let td_Cpf = tr.insertCell();
        let td_acoes = tr.insertCell();
        //Atribuindo valores
        td_id.innerText = bd_result[i].id;
        td_nome.innerText = bd_result[i].nome + " " + bd_result[i].sobrenome;
        td_Celular.innerText = bd_result[i].telefone1;
        td_email.innerText = bd_result[i].email;
        td_Cpf.innerHTML = bd_result[i].cpf.replace(/^(\d{3})\D*(\d{3})\D*(\d{3})\D*(\d{2})$/g, '$1.$2.$3-$4');
        //Cria os Botões e atribui eles ao td ações
        $('<button onclick="editarUser' + "(" + bd_result[i].id + ")" + '" value = ' + " " + bd_result[i].id + " " + 'class="button editar" type="button">Editar</button>').appendTo(td_acoes);
        $('<button onclick="deletLeitor' + "(" + bd_result[i].id + ")" + '" class="button deletar" type="button">Excluir</button>').appendTo(td_acoes);

    }
    carregaPage(bd_result)
}

//Função para Validar Email
const validateEmail = (email) => {
    email = document.getElementById('email').value
    const re = /\S+@\S+\.\S+/;
    var result = re.test(email);
    return result
};

//função validação de CPF
function validarCPF(cpf) {
    //Passa valor do input
    cpf = $("#cpf").val();
    //remove qualquer traço ou ponto
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf == '') return false;
    // Elimina CPFs invalidos conhecidos
    if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999") return false;
    // Valida 1º digito
    var add = 0;
    for (let i = 0; i < 9; i++) add += parseInt(cpf.charAt(i)) * (10 - i);
    let rev = 11 - (add % 11);
    if (rev == 10 || rev == 11) rev = 0;
    if (rev != parseInt(cpf.charAt(9))) return false;
    // Valida 2º digito
    add = 0;
    for (let i = 0; i < 10; i++) add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11) {
        rev = 0;
    }
    if (rev != parseInt(cpf.charAt(10))) {
        return false;
    }

    return true;
}

//------------------- Book Table ----------------------------

//Evita Reload da pagina
document.querySelector('#form-book').addEventListener('submit', e => {
    e.preventDefault()
})

//Cria Elementos do livro
function CreatElementBook(bd_result) {

    var tbody = document.getElementById('users_table')
    for (var i = 0; i < bd_result.length; i++) {
        //Cria Elemento Linha da tabela
        let tr = tbody.insertRow();
        //Cria a coluna da tabela
        let td_isdb = tr.insertCell();
        let td_nome = tr.insertCell();
        let td_autor = tr.insertCell();
        let td_idioma = tr.insertCell();
        let td_qtd = tr.insertCell();
        let td_acoes = tr.insertCell();
        //Atribuindo valores
        td_isdb.innerText = bd_result[i].livro.isdb;
        td_nome.innerText = bd_result[i].livro.nome;
        td_autor.innerText = bd_result[i].livro.autor
        td_idioma.innerText = bd_result[i].livro.idioma
        td_qtd.innerText = bd_result[i].quantidadeEstoque
        //Cria os Botões e atribui eles ao td ações
        $('<button onclick="editarBook' + "(" + bd_result[i].livro.id + ")" + '" value = ' + " " + bd_result[i].id + " " + 'class="button editar" type="button">Editar</button>').appendTo(td_acoes);
        $('<button onclick="deletBook' + "(" + bd_result[i].id + ")" + '" class="button deletar" type="button">Excluir</button>').appendTo(td_acoes);

    }
    carregaPage(bd_result)
}

//Função para Deletar Livro
function deletBook(id) {
    document.querySelector('#msgDelete-book').style.color = "#1f1d2b";
    document.getElementById('modalDel-livro').classList.add('active')
    document.querySelector('#msgDelete-book').innerHTML = 'Editar Livro'

    const delconf = document.querySelector('#Btn-Confirm-del-book');
    delconf.addEventListener('click', function () {
        fetch("http://localhost:80/livro/excluir/" + id, {
            method: 'DELETE', headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (result) {
            console.log(result)
            if (result.status >= 200 && result.status <= 300) {
                document.querySelector('#msgDelete-book').style.color = "#0c4900";
                document.querySelector('#msgDelete-book').innerHTML = 'Livro Deletado Com Sucesso'

                setTimeout(function () {
                    document.getElementById('modalDel-livro').classList.remove('active')
                }, 3000)
                clearTable()
            } else {
                document.querySelector('#msgDelete-book').style.color = "#FF0000";
                document.querySelector('#msgDelete-book').innerHTML = 'Erro Contate Um administrador'
            }
        }).catch(error => console.log('error', error));
    });
}

//Função Para Editar Livro
function editarBook(id) {

    document.querySelector('#msg-editlivro').style.color = "#1f1d2b";
    document.querySelector('#msg-editlivro').innerHTML = 'Editar Livro'
    openMBook()
    fetch("http://localhost:80/livro-biblioteca/" + id, {
        method: 'GET', headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            const pictureImage = document.querySelector(".picture__image");
            document.getElementById('nomeTitulo').value = bd_result.livro.nome;
            document.getElementById('autor').value = bd_result.livro.autor;
            document.getElementById('editora').value = bd_result.livro.editora
            document.getElementById('classificacao').value = bd_result.livro.classificacaoEtaria
            document.getElementById('isbn').value = bd_result.livro.isdb
            document.getElementById('edicao').value = bd_result.livro.edicao
            document.getElementById('datalanc').value = bd_result.livro.dataLancamento
            document.getElementById('qtdDisp').value = bd_result.quantidadeEstoque
            document.getElementById('idioma').value = bd_result.livro.idioma
            document.getElementById('genero1').value = bd_result.livro.generos[0]
            document.getElementById('genero2').value = bd_result.livro.generos[1]
            document.getElementById('genero3').value = bd_result.livro.generos[2]
            document.getElementById('txtarea').value = bd_result.livro.sinopse
            //Armazena Dados
            document.getElementById('scrImagemLivro').value = bd_result.livro.imagem
            document.getElementById('idLivroBibli').value = bd_result.id
            document.getElementById('idLivro').value = bd_result.livro.id

            const img = document.createElement("img");
            img.src = "http://localhost" + bd_result.livro.imagem;
            img.classList.add("picture__img");
            pictureImage.innerHTML = "";
            pictureImage.appendChild(img);

        }).catch(error => console.log('error', error));
    //Salvar Dados Alterados
    document.querySelector('#Btn-Salvar-book').addEventListener('click', function (bd_result) {
        genero1 = $("#genero1").val();
        genero2 = $("#genero2").val();
        genero3 = $("#genero3").val();
        var generos
        if (genero2 == null || genero3 == null) {
            generos = [genero1];
        } else if (genero2 != null && genero3 == null) {
            generos = [genero1, genero2];
        } else if (genero3 != null && genero2 == null) {
            generos = [genero1, genero3];
        } else {
            generos = [genero1, genero2, genero3];
        }
        var jsonLivro = JSON.stringify({
            "id": $("#idLivro").val(),
            "nome": $("#nomeTitulo").val(),
            "autor": $("#autor").val(),
            "generos": generos,
            "sinopse": $("#txtarea").val(),
            "idioma": $("#idioma").val(),
            "classificacaoEtaria": $("#classificacao").val(),
            "editora": $("#editora").val(),
            "edicao": $("#edicao").val(),
            "dataLancamento": $("#datalanc").val(),
            "imagem": $("#scrImagemLivro").val(),
            "isdb": $("#isbn").val(),
        });

        var jsonlivroBlib = JSON.stringify({
            "id": $("#idLivroBibli").val(),
            "idLivro": $("#idLivro").val(),
            "idBiblioteca": idUsuario,
            "qtdEstoque": $("#qtdDisp").val()
        })
        console.log(jsonLivro)
        console.log(jsonlivroBlib)
        AlteraBK()

        function AlteraBK() {
            fetch("http://localhost:80/livro/alterar", {
                method: 'PUT',
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
                body: jsonLivro
            }).then(function (result) {
                if (result.ok) {
                    fetch("http://localhost:80/livro-biblioteca/alterar", {
                        method: 'PUT',
                        headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
                        body: jsonlivroBlib
                    }).then(function (resultado2) {
                        if (resultado2.ok) {

                            if ($("#scrImagemLivro").val() == "") {
                                const inputFile = document.querySelector("#picture__input");
                                var formdata = new FormData();
                                var idliv = $("#idLivro").val()
                                console.log("id Livro é" + idliv)
                                formdata.append("imagem", inputFile.files[0], inputFile.files[0].name);
                                formdata.append("id", idliv);
                                //-------------------------------------------------------------
                                // Endpoint cadastro da imagem Livro
                                fetch("http://localhost:80/livro/salvar-imagem", {method: 'POST', body: formdata})
                                    .then(function (resposta) {
                                        if (resposta.ok) {
                                            document.querySelector('#msg-editlivro').style.color = "#0c4900";
                                            document.querySelector('#msg-editlivro').innerHTML = 'Dados Alterados com Sucesso'
                                            setTimeout(function () {
                                                document.getElementById('modalBok').classList.remove('active')
                                            }, 3000)
                                            clearTable()
                                        }
                                    })
                            } else {
                                document.querySelector('#msg-editlivro').style.color = "#0c4900";
                                document.querySelector('#msg-editlivro').innerHTML = 'Dados Alterados com Sucesso'
                                setTimeout(function () {
                                    document.getElementById('modalBok').classList.remove('active')
                                }, 3000)
                                clearTable()
                            }
                        }
                    })

                } else {
                    console.log(result.json())
                    document.querySelector('#msg-editlivro').style.color = "#0c4900";
                    document.querySelector('#msg-editlivro').innerHTML = 'Erro no Cadastro do Livro'
                    setTimeout(reload, 3000)
                }
            }).catch(error => console.log('error', error));
        }
    })
}

//Pesquisa Livros Pelo Id da Bliblioteca
function PesqBook() {

    fetch("http://localhost:80/livro-biblioteca/biblioteca/" + idUsuario, {
        method: 'GET', headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            CreatElementBook(bd_result)
        })
        .catch(error => console.log('error', error));
}

//------------------- Devolução Table ----------------------------
document.querySelector('#form-loc').addEventListener('submit', e => {
    e.preventDefault()
})

document.querySelector('#form-loc-alt').addEventListener('submit', e => {
    e.preventDefault()
})

//Função Para Criar Tabela de Resultados Locação
function CreatElementloc(bd_result) {

    var tbody = document.getElementById('users_table')
    for (var i = 0; i < bd_result.length; i++) {
        //Cria Elemento Linha da tabela
        let tr = tbody.insertRow();
        //Cria a coluna da tabela
        let td_nomeU = tr.insertCell();
        let td_NomeL = tr.insertCell();
        let td_DataE = tr.insertCell();
        let td_DataD = tr.insertCell();
        let td_Status = tr.insertCell();
        let td_acoes = tr.insertCell();
        var dataDev = new Date(bd_result[i].dataDevolucao);

        function zeroFill(n) {
            return n < 9 ? `0${n}` : `${n}`;
        }

        //Formata a Data a data Atual no padrão Mundial
        function formatDev(dataDev) {
            const d = zeroFill(dataDev.getDate() + 1);
            const mo = zeroFill(dataDev.getMonth() + 1);
            const y = zeroFill(dataDev.getFullYear());
            return `${d}/${mo}/${y}`;
        }

        var Strdate = bd_result[i].dataLocacao
        var Rdate = Strdate.split("-")
        var   y = Rdate[0]
        var   m = Rdate[1]
        var   d = Rdate[2]
        var dataLoc = d+"/"+m+"/"+y

        //Atribuindo valores
        td_nomeU.innerText = bd_result[i].usuario.nome;
        td_NomeL.innerText = bd_result[i].livro.livro.nome;
        td_DataD.innerText = formatDev(dataDev);
        td_DataE.innerText = dataLoc
        var status = bd_result[i].statusLocacao;
        if (status == "ANDAMENTO") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#FAF89D;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        } else if (status == "PENDENTE") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#FF4843;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        } else if (status == "DEVOLVIDO") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#04D950;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        } else if (status == "FINALIZADO") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#73706A;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        }
        //Cria os Botões e atribui eles ao td ações
        $('<button onclick="devolucao' + "(" + bd_result[i].id + ")" + '" value = ' + " " + bd_result[i].id + " " + 'class="button editar" type="button">Devolver</button>').appendTo(td_acoes);
        $('<button onclick="alteraLoc' + "(" + bd_result[i].id + ")" + '" class="button devolver" type="button">alterar</button>').appendTo(td_acoes);
    }
    carregaPage(bd_result)

}

//Função Para pesquisa pelo id Blibioteca
function Pesqloc() {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
    fetch("http://localhost:80/locacao/biblioteca/" + idUsuario, requestOptions)
        .then(response => response.text())
        .then(function resultado(result) {
            var bd_result = JSON.parse(result)
            CreatElementloc(bd_result)
        })
        .catch(error => console.log('error', error));
}

//Função para fazer devolução Blibioteca

function devolucao(id) {
    console.log($('#ResulStatDev').val())
    document.getElementById('Btn-devolver').style.pointerEvents = "auto"
    document.getElementById('msg-titleDev').style.color = "#1f1c2e"
    document.getElementById('msg-titleDev').innerHTML = "Locação de Livro"
    document.getElementById('modalLoc').classList.add('active')
    document.getElementById('msg-editDev').innerHTML = "Informações"
    document.getElementById('msg-editDev').style.color = "#1f1c2e"

    fetch("http://localhost:80/locacao/" + id, {method: 'GET'})
        .then(response => response.text())
        .then(function (result) {
            var bd_result = JSON.parse(result)

            document.getElementById('nome-user').value = bd_result.usuario.nome
            document.getElementById('autorliv').value = bd_result.livro.livro.nome
            document.getElementById('dataEnt').value = bd_result.dataDevolucao
            document.getElementById('dataLoc').value = bd_result.dataLocacao
            document.getElementById('status').value = bd_result.statusLocacao
            document.getElementById('datadev').value = bd_result.dataDevolvida

        }).catch(error => console.log('error', error));
    document.getElementById('Btn-devolver').addEventListener('click', function () {
        fetch("http://localhost:80/locacao/devolver/" + id, {method: 'POST'})
            .then(function (response) {
                document.getElementById('ResulStatDev').value = response.status
                return response.text()
            })
            .then(function resposta(response,) {

                if ($('#ResulStatDev').val() == 409) {
                    document.getElementById('msg-editDev').style.color = "#FF0000"
                    var mensagemR = ""

                    mensagemR = JSON.parse(response)
                    console.log(mensagemR.mensagem)
                    if (mensagemR.mensagem == "Esta locação não pode ser devolvida, pois seu status é PENDENTE") {
                        document.getElementById('msg-editDev').innerHTML = "Essa Locação Não pode ser devolvida, Status Pendente"
                    }
                }

                else if ($('#ResulStatDev').val() >= 400 && $('#ResulStatDev').val() < 409 && $('#ResulStatDev').val() >= 410 && $('#ResulStatDev').val() <= 500) {
                    document.getElementById('msg-editDev').style.color = "#FF0000"
                    document.getElementById('msg-editDev').innerHTML = "Erro, Contate um Administrador"
                }

                else if ($('#ResulStatDev').val() >= 200 && $('#ResulStatDev').val() < 300) {
                    document.getElementById('msg-titleDev').style.color = "#0c4900"
                    document.getElementById('msg-titleDev').innerHTML = "Livro Devolvido Com sucesso"
                    setTimeout(function () {
                        document.getElementById('modalLoc').classList.remove('active')
                    }, 3000)
                    clearTable()
                }
                document.getElementById('Btn-devolver').style.pointerEvents = "none"
                document.getElementById('ResulStatDev').value =0
            }).catch(error => console.log('error', error));
    })
}

//Função altera data devolução Blibioteca
function alteraLoc(id) {

    document.getElementById('modalAlt-Loc').classList.add('active')
    document.getElementById('msg-editDev').innerHTML = "Informações"
    document.getElementById('msg-editDev').style.color = "#1f1c2e"
    fetch("http://localhost:80/locacao/" + id, {method: 'GET'})
        .then(response => response.text())
        .then(function (result) {
            var bd_result = JSON.parse(result)

            document.getElementById('nome-user-alt').value = bd_result.usuario.nome
            document.getElementById('autorliv-alt').value = bd_result.livro.livro.nome
            document.getElementById('dataEnt-alt').value = bd_result.dataDevolucao
            document.getElementById('dataLoc-alt').value = bd_result.dataLocacao
            document.getElementById('status-alt').value = bd_result.statusLocacao
            document.getElementById('datadev-alt').value = bd_result.dataDevolvida
            //Armazem de Dados
            document.getElementById('idLivroBibli-alt').value = bd_result.livro.id
            document.getElementById('idUser-alt').value = bd_result.usuario.id

        }).catch(error => console.log('error', error));

    document.getElementById('SalvarDev-alt').addEventListener('click', function () {
        var idSatus
        if ($("#status-alt").val() == "ANDAMENTO") {
            idSatus = 0
        } else if ($("#status-alt").val() == "DEVOLVIDO") {
            idSatus = 1
        } else if ($("#status-alt").val() == "PENDENTE") {
            idSatus = 2
        }
        var JsonLoc = JSON.stringify({
            "id": id,
            "idLivroBiblioteca": $("#idLivroBibli-alt").val(),
            "idUsuario": $("#idUser-alt").val(),
            "dataLocacao": $("#dataLoc-alt").val(),
            "dataDevolucao": $("#dataLoc-alt").val(),
            "dataDevolvida": $("#datadev-alt").val(),
            "statusLocacao": idSatus
        });

        fetch("http://localhost:80/locacao/alterar", {
            headers: {'Content-Type': 'application/json'},
            method: 'PUT',
            body: JsonLoc
        })
            .then(function (result) {
                if (result.status >= 400 && result.status <= 500) {
                    document.getElementById('SalvarDev-alt').style.pointerEvents = 'none'
                    document.getElementById('msg-edit-Alt').style.color = "red"
                    document.getElementById('msg-edit-Alt').innerHTML = "Erro ao Fazer Alteração, Contate um Administrador"
                } else {
                    document.getElementById('msg-titleAlt').style.color = "#0c4900"
                    document.getElementById('msg-titleAlt').innerHTML = "Data de Devolução Alterada"
                    setTimeout(function () {
                        document.getElementById('modalAlt-Loc').classList.remove('active')
                    }, 3000)
                }
                clearAltable()

            })
            .catch(error => console.log('error', error));
    })
}
//--------------Pendencia----------

function CreatElementPen(bd_result) {

    var tbody = document.getElementById('users_table')
    for (var i = 0; i < bd_result.length; i++) {
        //Cria Elemento Linha da tabela
        let tr = tbody.insertRow();
        //Cria a coluna da tabela

        let td_nomeU = tr.insertCell();
        let td_NomeL = tr.insertCell();
        let td_DataD = tr.insertCell();
        let td_Valor = tr.insertCell();
        let td_Status = tr.insertCell();
        let td_acoes = tr.insertCell();
        var dataDev = new Date(bd_result[i].dataDevolucao);
        var dataLoc = new Date(bd_result[i].dataLocacao);

        function zeroFill(n) {
            return n < 9 ? `0${n}` : `${n}`;
        }

        //Formata a Data a data Atual no padrão Mundial
        function formatDev(dataDev) {
            const d = zeroFill(dataDev.getDate() + 1);
            const mo = zeroFill(dataDev.getMonth() + 1);
            const y = zeroFill(dataDev.getFullYear());
            return `${d}/${mo}/${y}`;
        }

        //Atribuindo valores
        td_nomeU.innerText = bd_result[i].usuario.nome;
        td_NomeL.innerText = bd_result[i].livro.livro.nome;
        td_DataD.innerText = formatDev(dataDev);
        td_Valor.innerText = `R$ ${bd_result[i]}`
        var status = bd_result[i].statusLocacao;

        if (status == "ANDAMENTO") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#FAF89D;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        } else if (status == "PENDENTE") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#FF4843;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        } else if (status == "DEVOLVIDO") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#04D950;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        } else if (status == "FINALIZADO") {
            let statusEl = '';
            statusEl += `<a  style="background-color:#73706A;color: transparent;margin-left: 2px;" class="status">an</a>`;
            $(td_Status).append(statusEl);
        }
        //Cria os Botões e atribui eles ao td ações
        $('<button onclick="devolucao' + "(" + bd_result[i].id + ")" + '" value = ' + " " + bd_result[i].id + " " + 'class="button editar" type="button">Devolver</button>').appendTo(td_acoes);
        $('<button onclick="alteraLoc' + "(" + bd_result[i].id + ")" + '" class="button devolver" type="button">alterar</button>').appendTo(td_acoes);
    }
    carregaPage(bd_result)
}


//--------------Eventos----------
const clsTableElement = (i) => {
    var tbody = document.getElementById('users_table')
    tbody.deleteRow(i);
}

//Função para Limpar todos os Elementos da Tabela
const clearTable = () => {
    var tbody = document.getElementById('users_table');
    tbody.innerHTML = "";
}
const clearAltable = () => {
    const rows = document.querySelectorAll('#users_table')
    rows.forEach(row => row.parentNode.removeChild(row))
    var tbody = document.getElementById('users_table')
    let tr = tbody.insertRow();
}

//Função para Recarregar a pagina
function reload() {
    document.location.reload(true);
}

function carregaPage(bd_result){
    document.getElementById('pagination').innerHTML = ""
    var rowsShown = 8;
    var rowsTotal = bd_result.length;
    var numPages = rowsTotal/rowsShown;

    $('#pagination').append('<a class="button-anterior" id="btn-anterior" value="">Anterior</a> ');
    for(i = 0;i < numPages;i++) {
        var pageNum = i + 1;
        if (numPages<5){
            $('#pagination').append('<a class="button-num" id="bt-pagina'+i+'" value="'+i+'">'+pageNum+'</a> ');
        }
    }
    $('#pagination').append('<a class="button-proximo" id="btn-prox" value="">Próximo</a> ');

    $('#bt-pagina0').addClass('active')

    document.getElementById('btn-prox').addEventListener("click", function( event ) {
        var pagina = document.getElementById('bt-pagina'+(paginaatual+1));
        if(pagina){
            paginaatual += 1;
            pagina.click();
        }
    })

    document.getElementById('btn-anterior').addEventListener("click", function( event ) {
        var pagina = document.getElementById('bt-pagina'+(paginaatual-1));
        if(pagina){
            paginaatual -= 1;
            pagina.click();
        }
    })

    $('#users_table tr').hide();
    $('#users_table tr').slice(0, rowsShown).show();
    $('#pagination a:first').addClass('active');
    $('#pagination a.button-num').bind('click', function(){

        $('#pagination a').removeClass('active');
        $(this).addClass('active');

        var currPage = $(this).attr('value');
        paginaatual=Number.parseInt(currPage);
        var startItem = currPage * rowsShown;
        var endItem = startItem + rowsShown;
        $('#users_table tr').css('opacity','0.0').hide().slice(startItem, endItem).
        css('display','table-row').animate({opacity:1}, 300);

    });
};
