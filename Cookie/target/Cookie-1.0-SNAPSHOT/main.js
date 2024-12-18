/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const banner = document.getElementById('cookie-banner');
const Scroll = document.getElementById("scroll-to-top");

let scrollTimeout;

if (!sessionStorage.getItem('cookieAccepted')) {
    setTimeout(function() {
        banner.classList.add('show');
    }, 5000);  
}

function sendCookieAction(action) {
    fetch('/cookie-handler', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `action=${action}`
    })
    .then(response => {
        if (!response.ok) {
            console.error("Erreur lors de l'enregistrement du cookie !");
        } else {
            console.log("Action enregistrée avec succès :", action);
            if (action === "accept") {
                startTrackingTime();
            }
        }
    });
}

function acceptCookie() {
    sendCookieAction("accept");
    closeCookie();
}

function rejectCookie() {
    sendCookieAction("reject");
    closeCookie();
}

function startTrackingTime() {
    fetch('/cookie-handler', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `action=track-time`
    })
    .then(response => response.text())
    .then(data => console.log(data));
}

function closeCookie() {
    banner.classList.remove('show');
};

window.addEventListener("scroll", () => {
    if (window.scrollY > 100) {
        Scroll.classList.add("show");

        clearTimeout(scrollTimeout);
        scrollTimeout = setTimeout(() => {
            Scroll.classList.remove("show");
        }, 5000);
    } else {
        Scroll.classList.remove("show");
    }
});

window.scrollToTop = function () {
    window.scrollTo({ top: 0, behavior: "smooth" });
};