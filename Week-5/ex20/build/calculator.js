"use strict";
//Declaracion de las variables globales
let primerNum = '';
let simbolo = '';
let segundoNum = '';
let limpiaVariables = () => {
    primerNum = "";
    simbolo = "";
    segundoNum = "";
};
let anadirNumero = (digito) => {
    if (digito === '.' && primerNum.includes('.'))
        return; //Verificamos si contiene ya un punto decimal.
    primerNum = primerNum + digito;
};
let elegirOperacion = (operacion) => {
    if (primerNum === '')
        return; //Verificamos que haya un primer numero.
    if (segundoNum !== '') {
        calcular();
    }
    simbolo = operacion;
    segundoNum = primerNum;
    primerNum = '';
};
let calcular = () => {
    let total;
    let anterior = parseFloat(segundoNum);
    let actual = parseFloat(primerNum);
    if (isNaN(anterior) || isNaN(actual))
        return; //En el caso de que el usuario pulse el igual sin que nada haya puesto.
    switch (simbolo) {
        case '+':
            total = anterior + actual;
            break;
        case '-':
            total = anterior - actual;
            break;
        case 'x':
            total = anterior * actual;
            break;
        case '/':
            total = anterior / actual;
            break;
        case 'exp':
            total = Math.pow(anterior, actual);
            break;
        default:
            return;
    }
    primerNum = total.toString();
    simbolo = '';
    segundoNum = '';
};
let refrescarPantalla = () => {
    valorPantalla.innerText = primerNum;
};
//Listeners y definicion de los selectores del HTML
const numero = document.querySelectorAll("[boton]");
const operacion = document.querySelectorAll("[boton-operacion]");
const igual = document.querySelectorAll("[boton-igual]");
const limpiar = document.querySelectorAll("[boton-limpiar]");
let valorPantalla = document.querySelector("[pantalla]");
numero.forEach((button) => {
    button.addEventListener("click", () => {
        anadirNumero(button.innerText);
        refrescarPantalla();
    });
});
operacion.forEach((button) => {
    button.addEventListener("click", () => {
        elegirOperacion(button.innerText);
        refrescarPantalla();
    });
});
igual.forEach((button) => {
    button.addEventListener("click", () => {
        calcular();
        refrescarPantalla();
    });
});
limpiar.forEach((button) => {
    button.addEventListener("click", () => {
        limpiaVariables();
        refrescarPantalla();
    });
});
