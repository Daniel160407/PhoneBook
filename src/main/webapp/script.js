document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("submit").addEventListener("submit", async function addNewContact(event) {
        event.preventDefault();

        const contactName = document.getElementById("name").value;
        const contactNumber = document.getElementById("number").value;

        document.getElementById("name").value = "";
        document.getElementById("number").value = "";

        const response = await fetch(`/phoneBook/contacts?name=${contactName}&number=${contactNumber}`, {method: "POST"});
        const jsonArray = await response.json();

        document.getElementById("contactsList").innerHTML = "";

        for (let i = 0; i < jsonArray.length; i++) {
            document.getElementById("contactsList").innerHTML += `<li><div><h4 class="contact">${jsonArray[i].name} </h4><h4 class="contact">${jsonArray[i].number}</h4></div></li>`;
        }
    });
});


async function getContacts() {
    const response = await fetch("/phoneBook/contacts", {method: "GET"});
    const jsonArray = await response.json();

    for (let i = 0; i < jsonArray.length; i++) {
        document.getElementById("contactsList").innerHTML += `<li><div><h4 class="contact">${jsonArray[i].name}</h4>
<h4 class="contact">${jsonArray[i].number}</h4></div></li>`;
    }
}