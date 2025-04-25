document.addEventListener("DOMContentLoaded", function () {
    const body = document.body;
    const button = document.getElementById("theme-toggle-btn");

    button.addEventListener("click", function () {
        body.classList.toggle("dark-theme");
        body.classList.toggle("light-theme");
    });
});
