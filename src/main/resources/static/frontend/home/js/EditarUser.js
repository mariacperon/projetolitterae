var idUsuario = sessionStorage.getItem("idUsuario")
var IdBlibUser = sessionStorage.getItem("IdBlibUser")

CarregarElement()

function CarregarElement() {

    fetch("http://localhost:80/usuario/" + idUsuario, {method: 'GET'})
        .then(response => response.text())
        .then(function (result) {
            var bd_result = JSON.parse(result)
            document.querySelector('.user-img').src = bd_result.imagem
            document.querySelector('#photo').src = bd_result.imagem
            document.querySelector('#email').value = bd_result.email
            console.log(bd_result)

            document.getElementById('enviar').addEventListener("click", function (event) {
                //Preparando o Body para Envio ao Servidor
                var jsonCadastro = JSON.stringify({
                    "id": bd_result.id,
                    "cpf": bd_result.cpf,
                    "nome": bd_result.nome,
                    "sobrenome": bd_result.sobrenome,
                    "enderecoUsuario": {
                        "id": bd_result.enderecoUsuario.id,
                        "cep": bd_result.enderecoUsuario.cep,
                        "estado": bd_result.enderecoUsuario.estado,
                        "cidade": bd_result.enderecoUsuario.cidade,
                        "bairro": bd_result.enderecoUsuario.bairro,
                        "rua": bd_result.enderecoUsuario.rua,
                        "numero": bd_result.enderecoUsuario.numero,
                        "complemento": ""
                    },
                    "idBiblioteca": bd_result.biblioteca.id,
                    "email": $('#email').val(),
                    "dataNascimento": bd_result.dataNascimento,
                    "telefone1": bd_result.telefone2,
                    "telefone2": bd_result.telefone2,
                    "imagem": bd_result.imagem,
                    "tipoPerfil": 1,
                    "ativo": true
                });
                //faz a chamada da conexão
                fetch("http://localhost:80/usuario/alterar", {
                    method: 'PUT', headers: {
                        'Accept': 'application/json', 'Content-Type': 'application/json'
                    }, body: jsonCadastro
                }).then(function (result) {
                    //tratamento do erro exibindo mensagem
                    if (resposta.status >= 200 && resposta.status <= 300) {
                        var imagem = document.getElementById('photo-upload')
                        if (imagem != undefined) {
                            var formdata
                            formdata.append("imagem", imagem.files[0], imagem.files[0].name);
                            formdata.append(bd_result.id,);
                            // Endpoint cadastro da imagem Livro
                            fetch("http://localhost:80/usuario/salvar-imagem", {method: 'POST', body: formdata})
                                .then(function (result){
                                  console.log(result.json())
                                })
                        }
                        document.querySelector('#title').style.color = "#0c4900";
                        document.querySelector("#title").innerHTML = "Alteração Efetuada com Sucesso"
                        $("#alertaEr").fadeIn();
                        setTimeout(AlertaOut, 5000)
                       // setTimeout(reload, 2000)
                        console.log()
                    } else {
                        console.log(resposta.json())
                        document.querySelector('#title').style.color = "Red";
                        document.querySelector("#title").innerHTML = "Erro no Cadastro, Por Favor Contate um Administrador."
                        console.log(resposta.json())
                        $("#title").fadeIn();
                        setTimeout(AlertaOut, 3000)
                    }
                })

            })
        }).catch(error => console.log('error', error));
}
//-------------------------------------------------

//Eventos

//Animação Menu Dropdown
    $('.profile-menu').on('click', function () {
        $('#SidepanelMenu').slideToggle('slow');
    })

    function reload() {
        document.location.reload(true);
    }






