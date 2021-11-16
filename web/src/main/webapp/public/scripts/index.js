/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var userentry = "";

window.onload = function() {
    // retrieve and display game board
    createPinPad();
};

function createPinPad() {
    // get all associated elements to populate pinpad
    let divpinpad = document.getElementById("div-pinpad");

    // create div buttons for pinpad
    let divpinpadheight = 300;
    let divpinpadwidth = 300;
    // get number of buttons in each axis & total
    let buttonsx = 3;
    let buttonsy = 4;
    let numofbuttons = 12;
    // get height and width of each button
    // include borders - 1px each direction
    let xborderpxs = (buttonsx * 2);
    let yborderpxs = (buttonsy * 2);    
    let buttonheight = (divpinpadheight / buttonsy - yborderpxs);
    let buttonwidth = (divpinpadwidth / buttonsx - xborderpxs);
    // build grid of div buttons
    // for loop for total number of buttons
    for (i = 1; i <= numofbuttons; i++) {
        // creates div with css class
        let divnum = document.createElement("div");
        divnum.setAttribute("class","div-num");
        // creates blank 'filler' divs for formatting
        if (i === 10){
            // blank filler
            divnum.setAttribute("title","");
            divnum.setAttribute("id",`div-button-blank1`);
            divnum.setAttribute("class","div-num-blank");
        } else if (i === 11) {
            // middle button = 0
            divnum.setAttribute("title",0);
            divnum.setAttribute("id",`div-button-0`);
            divnum.innerHTML = "0";
            // builds the divs to the dynamic sizing
            divnum.style.height = buttonheight + "px";
            divnum.style.width = buttonwidth + "px";
        } else if (i === 12) {
            // righthand button = ' . '
            divnum.setAttribute("title",".");
            divnum.setAttribute("id",`div-button-dec`);
            // adds strong emphasis to the decimal point for 
            // visibility
            divnum.innerHTML = "<strong>.</strong>";
            // builds the divs to the dynamic sizing
            divnum.style.height = buttonheight + "px";
            divnum.style.width = buttonwidth + "px";
        } else {
            divnum.setAttribute("title",i);
            divnum.setAttribute("id",`div-button-${i}`);
            divnum.innerHTML = i;
            // builds the divs to the dynamic sizing
            divnum.style.height = buttonheight + "px";
            divnum.style.width = buttonwidth + "px";
        }        
        // adds to div-pinpad
        divpinpad.appendChild(divnum);
    };
};

/* finds the div name of the number button the user has clicked on */
document.onclick = function(event) {
    var el = event.target;
    if (el.className === "div-num" && el.nodeName === "DIV") {
        let divbutton = document.getElementById(el.id);
        let txtpininput = document.getElementById("txtpininput");
        let currenttext = txtpininput.value;
        console.log(currenttext)
        // checks for existing decimal points and decimal point button press
        if ((currenttext.includes(".")) && (divbutton.title.includes("."))) {
            // ignores if already present
        } else {
            // check decimal point has been entered already
            if (currenttext.includes(".")) {
                let dploc = currenttext.indexOf(".");
                // restricts to 2 dp
                if (currenttext.length <= (dploc + 2)) {
                    // adds button value to user entry
                    userentry += divbutton.title;
                }
            } else {
                // adds button value to user entry
                userentry += divbutton.title;
            }
        }
        // assigns userentry to input box value
        txtpininput.value = `${userentry}`;
    };
};

document.getElementById("btncancel").addEventListener("click", e=>{
    let txtpininput = document.getElementById("txtpininput");
    userentry = "";
    txtpininput.value = userentry;
});

document.getElementById("btnundo").addEventListener("click", e=>{
    let txtpininput = document.getElementById("txtpininput");
    let currentstring = txtpininput.value;
    userentry = currentstring.slice(0, currentstring.length-1);
    txtpininput.value = userentry;
});