$.ajax({
    method:"POST",
    url: "usuario/login",
    data: JSON.stringify({id:"7711725",dataNascimento:"1999-02-19"}),
    contentType: "aplication/json; charset=utf-8",
    sucess: function(response){
        console.log("TA FUNCIONANDO CARAI")
    }
}).fail(function(xhr,status,errorThrown){
    console.log("não funcionou ")
})
/*var ajax =  new XMLHttpRequest();

ajax.onload = {
    "id":"4054833",
    "dataNascimento":"1999-02-19"
}
ajax.open("POST","http://localhost:80/usuario/login")

ajax.responseType = "json";

ajax.send();



*/
/*if (login==login) {
    
}else{
    document.getElementById("SenhaErro").innerHTML = "Sua conta ou senha está incorreta. Se você não lembra de sua senha solicite a um adm"
}*/


