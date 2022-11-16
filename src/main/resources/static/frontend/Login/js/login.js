$('#input-pass').on('keypress', function (e) {
    if (e.which == 13) {
        //passa os parametros dos inpus para loginbtn
        loginadm($('#input-cod').val(), $('#input-pass').val());
    }
})

function loginadm(idCod, senha) {
    if (idCod == "" && senha == "") {
        document.querySelector("#alertaEr").innerHTML = "Preencha os Campos Código de Casdastro ou Senha. Por favor"
        document.getElementById("container-er").style.opacity = "1"
    } else {
        //Parametros do body do POST
        var jsoniD = JSON.stringify({
            id: idCod,
            senha: senha
        })
        //Prepara a conexão com endpoint do back
        fetch('http://localhost:80/biblioteca/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            //passa os parametros para o body
            body: jsoniD
        })  //retorna uma resposta do servidor
            .then(function (resposta) {
                //tratamento do erro exibindo mensagem
                if (resposta.status >= 400 && resposta.status <= 500) {
                    document.querySelector("#alertaEr").innerHTML = "Ops.. Código de Cadastro ou Senha Inválido. <br> Por favor Tente Novamente!"
                    document.getElementById("container-er").style.opacity = "1"
                }
                //converte em json a resposta do servidor e retorna para json
                return resposta.json()
            })
            //Executa função de carregamento da pagina e armazenamento de dados
            .then(function (json) {
                if (json.id == idCod && json.senha == senha) {
                    //converte parametro de json para texto
                    json = JSON.stringify(json.id);
                    //seta os dados em forma de texto na sessão
                    sessionStorage.setItem('idUsuario', json);
                    //Aciona load da pagina
                    window.location.href = '../../frontend/biblioteca/Admin-dash.html';
                }
            })
    }
}
//-----------------------------Login Usuário-----------------------------------
// verifica precionamento da tecla Enter
    $('#input-data').on('keypress', function (e) {
    if (e.which == 13) {
        //passa os parametros dos inpus para loginbtn
        loginbtn($('#input-codcadastro').val(), $('#input-data').val());
    }
})

//Chama Evento onclick do botão faz requisão ao servidor
function loginbtn(idCod, data) {
    //Define uma mensagem de erro no paragrafo alertaEr
    if (idCod == "" && data == "") {
        document.querySelector("#alertaEr").innerHTML = "Preencha os Campos Código de Casdastro ou Data de Nascimento. Por favor"
        document.getElementById("container-er").style.opacity = "1"
    } else {
        //Parametros do body do POST
        var jsoniD = JSON.stringify({
            id: idCod,
            dataNascimento: data
        })
        //Prepara a conexão com endpoint do back
        fetch('http://localhost:80/usuario/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            //passa os parametros para o body
            body: jsoniD
        })  //retorna uma resposta do servidor
            .then(function (resposta) {
                console.log(jsoniD)
                //tratamento do erro exibindo mensagem
                if (resposta.status >= 400 && resposta.status <= 500) {
                    document.querySelector("#alertaEr").innerHTML = "Ops.. Código de Cadastro ou Data de Nascimento Inválido. <br> Por favor Tente Novamente!"
                    document.getElementById("container-er").style.opacity = "1"
                }
                //converte em json a resposta do servidor e retorna para json
                return resposta.json()
            })
            //Executa função de carregamento da pagina e armazenamento de dados
            .then(function (json) {
                if (json.id == idCod && json.dataNascimento == data) {
                    //converte parametro de json para texto
                    var idUsuario = JSON.stringify(json.id);
                    var IdBlibUser = JSON.stringify(json.biblioteca.id)
                    //seta os dados em forma de texto na sessão
                    sessionStorage.setItem('idUsuario', idUsuario);
                    sessionStorage.setItem('IdBlibUser', IdBlibUser);
                    //Aciona load da pagina
                    window.location.href = '../../frontend/home/home.html';
                }
            })
    }
}


