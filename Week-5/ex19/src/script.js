const formulario = document.getElementById("formulario");
const body = document.body;

var listaPersonas = []; //Array donde almacenamos la lista de personas que devuelve el servidor.

getPersons(); //Llamamos a getPersons nada mas iniciamos la aplicacion.

//Funcion para enviar una persona
function submitPersona(evento) {
  console.log(evento);
  fetch("http://localhost:8080/persona", {
    method: "post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      user: document.getElementById("username").value,
      password: document.getElementById("passwd").value,
      name: document.getElementById("name").value,
      surname: document.getElementById("surname").value,
      companyEmail: document.getElementById("emailcomp").value,
      personalEmail: document.getElementById("emailpers").value,
      city: document.getElementById("city").value,
      active: document.getElementById("active").checked,
      createdDate: document.getElementById("created_date").value,
      imageUrl: document.getElementById("imagen_url").value,
      terminationDate: document.getElementById("finish_date").value,
    }),
  })
    .then(function (response) {
      return response.json();
    })
    .then(evento.preventDefault());

  resetForm();
}

//Esta es la funcion que se llama al comienzo del JS para recuperar todas las personas de la BD.
function getPersons() {
  fetch("http://localhost:8080/persona")
    .then(function (resp) {
      return resp.json();
    })
    .then(function (data) {
      console.log(data);
      listaPersonas = data;

      if (listaPersonas[0] != undefined) {
        showPerson(0);
      }
    });
}

//Colocamos en cada hueco del formulario su correspondiente valor del objeto persona. 
function showPerson() {
  var persona = listaPersonas[0]; //Mostramos la primera persona que nos devuelve el servidor.

  document.getElementById("username").value = persona.user;
  document.getElementById("passwd").value = persona.password;
  document.getElementById("name").value = persona.name;
  document.getElementById("surname").value = persona.surname;
  document.getElementById("emailcomp").value = persona.companyEmail;
  document.getElementById("emailpers").value = persona.personalEmail;
  document.getElementById("city").value = persona.city;
  document.getElementById("active").checked = persona.active;
  document.getElementById("created_date").value = persona.createdDate;
  document.getElementById("imagen_url").value = persona.imageUrl;
  document.getElementById("finish_date").value = persona.terminationDate;
}

//Funcion para limiar todo el formulario.
function resetForm() {
  document.getElementById("formulario").reset();
}
