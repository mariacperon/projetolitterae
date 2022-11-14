//Pega o id do usuario logado
//var idUsuario = sessionStorage.getItem("idUsuario");
var idUsuario = 100001

const form = document.querySelector("form"),
    nextBtn = form.querySelector(".nextBtn"),
    backBtn = form.querySelector(".backBtn"),
    allInput = form.querySelectorAll(".first input");

//Evita Reload da pagina
const attbloqueio = document.getElementById('some-form')
attbloqueio.addEventListener('submit', e => {
    e.preventDefault()
})
nextBtn.addEventListener("click", () => {

    allInput.forEach((input) => {
        var selectLang = document.querySelector('#idioma').value
        var selectGen = document.querySelector('#genero1').value
        if (input.value != "" && selectLang != "" && selectGen != "") {
            form.classList.add('secActive');
        } else {
            form.classList.remove('secActive')
        }

    })
})
backBtn.addEventListener("click", () => form.classList.remove('secActive'));

//Faz o upload de imagem
const inputFile = document.querySelector("#picture__input");
const pictureImage = document.querySelector(".picture__image");
const pictureImageTxt = "Upload";
pictureImage.innerHTML = pictureImageTxt;

inputFile.addEventListener("change", function (e) {
    const inputTarget = e.target;
    const file = inputTarget.files[0];

    if (file) {
        const reader = new FileReader();

        reader.addEventListener("load", function (e) {
            const readerTarget = e.target;

            const img = document.createElement("img");
            img.src = readerTarget.result;
            img.classList.add("picture__img");

            pictureImage.innerHTML = "";
            pictureImage.appendChild(img);
        });

        reader.readAsDataURL(file);
    } else {
        pictureImage.innerHTML = pictureImageTxt;
    }
});

function btnEnviar() {
    document.getElementById("botaoEnviar").disabled = true;
    var img = document.getElementById('picture__input');
    var fileName = img.value;
    var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
    if (ext == "jpg" || ext == "png" || ext == "PNG" || ext == "jpg" || ext == "JPG" || ext == "jpeg" || ext == "JPEG") {
        ext = true;
    } else {
        document.querySelector('#alertaEr').style.color = "Red";
        document.querySelector("#alertaEr").innerHTML = "Erro, Por favor tente enviar uma imagem em jpg ou png"
        $("#alertaEr").fadeIn();
        setTimeout(AlertaOut, 2000)
    }
    if ($("#txtarea").val() != "" && $('input[name="picture__input"]').val() != "" && ext == true) {
        CadastrarLivro();
    }
}

function CadastrarLivro(nome, autor, genero1, genero2, genero3, sinopse, idioma, faxaetaria, editora, edicao, datalanc, isbn, qtdLivro, jsonLivro) {
    nome = $("#nomeTitulo").val();
    autor = $("#autor").val();
    genero1 = $("#genero1").val();
    genero2 = $("#genero2").val();
    genero3 = $("#genero3").val();
    sinopse = $("#txtarea").val();
    idioma = $("#idioma").val();
    faxaetaria = $("#classificacao").val();
    editora = $("#editora").val();
    edicao = $("#edicao").val();
    datalanc = $("#datalanc").val();
    isbn = $("#isbn").val();
    qtdLivro = $("#qtdDisp").val();
    //Json de Cadastro do Livro
    qtdLivro = parseInt(qtdLivro)
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
    jsonLivro = JSON.stringify({
        "id": null,
        "nome": nome,
        "autor": autor,
        "generos":
        generos
        ,
        "sinopse": sinopse,
        "idioma": idioma,
        "classificacaoEtaria": faxaetaria,
        "editora": editora,
        "edicao": edicao,
        "dataLancamento": datalanc,
        "imagem": null,
        "isdb": isbn
    });
    //--------------------------------------------------
    //Endpoint Cadastro do Livro
    fetch('http://localhost:80/livro/cadastrar', {
        method: 'POST', headers: {
            'Content-Type': 'application/json',
        }, body: jsonLivro
    }).then(function (resposta) {
        return resposta.json()
            .then(function (json) {
                if (resposta.status >= 200 && resposta.status <= 300) {
                    var idliv = JSON.stringify(json.id);
                    JsonBibli = JSON.stringify({
                            "id": null,
                            "idLivro": idliv,
                            "idBiblioteca": idUsuario,
                            "qtdEstoque": qtdLivro
                        }

                    )
                    //Captura a file
                    const inputFile = document.querySelector("#picture__input");
                    var formdata = new FormData();
                    formdata.append("imagem", inputFile.files[0], inputFile.files[0].name);
                    formdata.append("id", idliv);
                    //-------------------------------------------------------------
                    // Endpoint cadastro da imagem Livro
                    fetch("http://localhost:80/livro/salvar-imagem", {method: 'POST', body: formdata})
                        .then(function (resposta2) {

                            if (resposta2.ok) {
                                //Endpoint cadastro do livro com a biblioteca
                                fetch('http://localhost:80/livro-biblioteca/cadastrar', {
                                    method: 'POST',
                                    headers: {'Content-Type': 'application/json',},
                                    body: JsonBibli
                                }).then(function (resposta3) {
                                        if (resposta3.ok) {
                                            document.querySelector('#alertaEr').style.color = "#0c4900";
                                            document.querySelector("#alertaEr").innerHTML = "Cadastrado com Sucesso"
                                            $("#alertaEr").fadeIn();
                                            setTimeout(AlertaOut, 5000)
                                            setTimeout(reload, 3000)
                                        } else {
                                            console.log("Erro no Cadastro do Livro na Biblioteca " + resposta3.json())
                                        }
                                    })
                                //---------------------------------------------------------
                                // Else Cadastro Blioteca Livro Livro
                            } else {
                                document.querySelector('#alertaEr').style.color = "Red";
                                document.querySelector("#alertaEr").innerHTML = "Erro no Cadastro, Contate um Administrador"
                                $("#alertaEr").fadeIn();
                                setTimeout(AlertaOut, 5000)
                                console.log(resposta2.json())
                            }
                        })
                    //Else Cadastro Livro
                } else {
                    document.querySelector('#alertaEr').style.color = "Red";
                    document.querySelector("#alertaEr").innerHTML = "Erro no Cadastro, Contate um Administrador"
                    $("#alertaEr").fadeIn();
                    setTimeout(AlertaOut, 5000)
                    console.log(json)
                }
            })

    })

}

//Função assíncrona de saída de mensagem Avisos
async function AlertaOut() {
    $("#alertaEr").fadeOut();
}
//Faz Reload na pagina
function reload() {
    document.location.reload(true);
}
