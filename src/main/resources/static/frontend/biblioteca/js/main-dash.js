document.addEventListener('DOMContentLoaded', function () {
    var modeSwitch = document.querySelector('.mode-switch');

    modeSwitch.addEventListener('click', function () {

        document.documentElement.classList.toggle('dark');
        modeSwitch.classList.toggle('active');
    });

    var listView = document.querySelector('.list-view');
    var gridView = document.querySelector('.grid-view');
    var projectsList = document.querySelector('.project-boxes');


});
//Evento Menu Active
var cont = 0
var MenuIcon = document.querySelector('.app-icon');
MenuIcon.addEventListener('click', function () {
    $(".sidenav").animate({width: '250px'})
    cont++
    if (cont == 2) {
        $(".sidenav").animate({width: '0px'})
        cont = 0
    }

})
//Evento Setar Nome Icon Profile
$(document).ready(function () {
    //var firstName = $('#firstName').text();
    //var lastName = $('#lastName').text();
    var Pnome = "Lucas"
    var Snome = "Testoni"
    //var intials = $('#firstName').text().charAt(0) + $('#lastName').text().charAt(0);
    var intials = Pnome.charAt(0) + Snome.charAt(0)
    $('#txtImage').text(intials);
});


const nextBtn = document.querySelector("#nextBtn"),
    backBtn = document.querySelector("#backbnt"),
    allInput = document.querySelectorAll("#form-book>input");


nextBtn.addEventListener("click", () => {
    var cont = 0


    var selectLang = document.querySelector('#idioma').value
    var selectGen = document.querySelector('#genero1').value

    allInput.forEach((input) => {
                console.log(cont++)

    })
})
/*
$(".form.second").toggleClass('secActive')
$(".form.first").toggleClass('secClear')
$("#backbnt").toggleClass('ativar')
$("#nextBtn").toggleClass('clear')
$("#Btn-Cancel-book").toggleClass('clear')
$("#Btn-Salvar-book").toggleClass('ativar')
$('.input-form').each(function(){})
document.querySelector('.title').innerHTML = 'Imagem Capa'

 */
backBtn.addEventListener("click", () => {
    console.log("Click")
    document.querySelector('.title').innerHTML = 'Informações Livro'
    $(".form.second").toggleClass('secActive')
    $(".form.first").toggleClass('secClear')
    $("#backbnt").toggleClass('ativar')
    $("#nextBtn").toggleClass('clear')
    $("#Btn-Cancel-book").toggleClass('clear')
    $("#Btn-Salvar-book").toggleClass('ativar')
});

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


//Evento Modal Open/exit

//-------------------------------------------------

//
const openMBook = () => document.getElementById('modalBok')
    .classList.add('active')

const closeModal = () => document.getElementById('modaluser')
    .classList.remove('active')
//Modal delete User
const closeModalDel = () => document.getElementById('modalDel-user')
    .classList.remove('active')
//Modal book close
const closeMBook = () => document.getElementById('modalBok')
    .classList.remove('active')
//Delete Book close
const closeMDbook = () => document.getElementById('modalDel-livro')
    .classList.remove('active')

//Modal Devolução close
const closeMDev = () => document.getElementById('modalLoc')
    .classList.remove('active')
//Modal Alterar Devolução close
const closeAltDev = () => document.getElementById('modalAlt-Loc')
    .classList.remove('active')
//-------------------------------------------------

//Modal Edit User
document.getElementById('Btn-Cancel').addEventListener('click', closeModal)

document.getElementById('modalClose-edituser').addEventListener('click', closeModal)

//Modal Delete User
document.getElementById('modalClose-del').addEventListener('click', closeModalDel)

document.getElementById('Btn-Canc-Del').addEventListener('click', closeModalDel)

//Modal Edit Book

document.getElementById('Btn-Cancel-book').addEventListener('click', closeMBook)

document.getElementById('modalClose-book').addEventListener('click', closeMBook)

//Modal Delete Book

document.getElementById('modalClose-Bok').addEventListener('click', closeMDbook)

document.getElementById('Btn-CancelD-book').addEventListener('click', closeMDbook)


//Modal Devolução Book
document.getElementById('Btn-Cancel-dev').addEventListener('click',closeMDev )

document.getElementById('modalClose-dev').addEventListener('click',closeMDev )

//Modal Altera Devolução Book
document.getElementById('modalClose-dev-Alt').addEventListener('click',closeAltDev )

document.getElementById('Btn-Cancel-dev-alt').addEventListener('click',closeAltDev )