/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


let stage = 0;
let compSelected = "";
let prodSelected = "";
let modelSelected = "";
let numThings = "";
let dispArray = ["Company", "Product", "Model", "Number"];
const compDW = document.getElementById("form-select-company");
const prodDW = document.getElementById("form-select-product");
const modelDW = document.getElementById("form-select-model");
const numberFD = document.getElementById("numthings");
const compList = document.getElementById("complist");

//compDW.addEventListener("onchange", function() {
//    compSelected = compDW.value;
//    switchStage();
//});

compDW.onchange = function() {
    compSelected = compDW.value;
    switchStage();
};

prodDW.onchange = function() {
    prodSelected = prodDW.value;
    switchStage();
};

modelDW.onchange = function() {
    modelSelected = modelDW.value;
    switchStage();
};

function loadList(dw) {
    arr = (compList.value).split(",");   
    let listItem = "<option>Select "+dispArray[stage]+"</option>";
    for(let i=0; i<arr.length; i++) {
        listItem += "<option>" + arr[i] + "</option>";        
    }
    dw.innerHTML = listItem;
}

//https://data.giss.nasa.gov/gistemp/tabledata_v4/ZonAnn.Ts+dSST.csv

async function getdata() {
    const response = await fetch('GetDataServlet?name=comp');
    const data = await response.text();
    console.log(data);
}

function switchStage() {
    switch(stage){
        case 0:
            compDW.disabled = false;
            prodDW.disabled = true;
            modelDW.disabled = true;
            numberFD .disabled = true;
            loadList(compDW);
            stage++;
            break;
        case 1:    
            compDW.disabled = true;
            prodDW.disabled = false;
            modelDW.disabled = true;
            numberFD .disabled = true;
            loadList(prodDW);
            stage++;
            break;
        case 2:    
            compDW.disabled = true;
            prodDW.disabled = true;
            modelDW.disabled = false;
            numberFD .disabled = true;
            loadList(modelDW);
            stage++;
            break;
        case 3:    
            compDW.disabled = true;
            prodDW.disabled = true;
            modelDW.disabled = true;
            numberFD .disabled = false;
            stage++;
            break;
        case 4:    
            compDW.disabled = true;
            prodDW.disabled = true;
            modelDW.disabled = true;
            numberFD .disabled = true;
            break;

    }
}

//switchStage();
getdata();

