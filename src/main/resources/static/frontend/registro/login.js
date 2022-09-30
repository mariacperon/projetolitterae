
carregaTela();
console.log('Teste');
async function carregaTela(){
    var json = {
        id:"52344058",
        dataNascimento:"1999-02-19"
    }
    var jsonStr = JSON.stringify(json)
    var resultado = await usarApi("POST", "/usuario/login",jsonStr);
    var resultadoJson = JSON.parse(resultado);
    console.log(resultado);
    console.log(resultadoJson.cpf);
    console.log('passo');
}
