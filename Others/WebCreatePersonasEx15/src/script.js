const formulario = document.getElementById('formulario');
const body = document.body;

var listaPersonas = [];

//Mostrar la primera persona
var index = 0;
//Si no hay ninguna persona se recibe un CustomError*
getPersons();

function submitPersona(evento){
    console.log(evento);
    fetch('http://localhost:8080/persona',{
    method:'post',
    headers:{"Content-Type": "application/json"},
    body:JSON.stringify({
        'user': document.getElementById('username').value,
        'password': document.getElementById('passwd').value,
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value,
        'companyEmail': document.getElementById('emailcomp').value,
        'personalEmail': document.getElementById('emailpers').value,
        'city': document.getElementById('city').value,
        'active': document.getElementById('active').checked,
        'createdDate': document.getElementById('created_date').value,
        'imageUrl': document.getElementById('imagen_url').value,
        'terminationDate': document.getElementById('finish_date').value,
    })  
})
  .then(function(response){ return response.json()})
  .then( evento.preventDefault())
  
  resetForm();
}

function getPersons(){
    
    fetch('http://localhost:8080/persona')
  .then(function(resp){
    return resp.json();
  })
  .then(function(data){
    
    // Se recibe un custom error si no ha encontrado personas *
    console.log(data)
    listaPersonas = data;

    if(listaPersonas[0] != undefined){
        showPerson(0);
    }
    
  })
}

function nextPerson () {
    if (index < listaPersonas.length-1){
        index++;
        showPerson(index);
    }
}
function previousPerson () {
    if (index > 0){
        index--;
        showPerson(index);
    }
}

function showPerson(index){
    var persA = listaPersonas[index];

    document.getElementById('username').value = persA.user;
    document.getElementById('passwd').value = persA.password;
    document.getElementById('name').value = persA.name;
    document.getElementById('surname').value = persA.surname;
    document.getElementById('emailcomp').value = persA.companyEmail;
    document.getElementById('emailpers').value = persA.personalEmail;
    document.getElementById('city').value = persA.city;
    document.getElementById('active').checked = persA.active;
    document.getElementById('created_date').value = persA.createdDate;
    document.getElementById('imagen_url').value = persA.imageUrl;
    document.getElementById('finish_date').value = persA.terminationDate;
}

function resetForm(){
    document.getElementById('formulario').reset();
}