
//verifica precionamento da tecla Enter
$('#input-data').on('keypress', function (e) {
    if (e.which == 13) {
        //passa os parametros dos inpus para loginbtn
        loginbtn($('#input-codcadastro').val(), $('#input-data').val());
    }
})

//Chama Evento onclick do botão faz requisão ao servidor
async function loginbtn(idCod, data) {
    //Define uma mensagem de erro no paragrafo alertaEr
    if ( idCod == "" && data == ""){
        document.querySelector("#alertaEr").innerHTML = "Preencha os Campos Código de Casdastro ou Data de Nascimento. Por favor"
        document.getElementById("container-er").style.opacity = "1"
    }else {
        //Parametros do body do POST
        var jsoniD = {
            id: idCod,
            dataNascimento: data
        }
        //Prepara a conexão com endpoint do back
        fetch('/usuario/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            //passa os parametros e converte eles pra json
            body: JSON.stringify(jsoniD)
        })  //retorna uma resposta do servidor
            .then(function (resposta) {
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
                    json = JSON.stringify(json.id);
                    //seta os dados em forma de texto na sessão
                    sessionStorage.setItem('idUsuario', json);
                    //Aciona load da pagina
                    window.location.href = 'teste.html';
                }
            })
    }
}


