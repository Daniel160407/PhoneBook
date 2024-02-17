async function addNewContact() {
    const contactName = document.getElementById("name").value;
    const contactNumber = document.getElementById("number").value;
    console.log(contactName);
    console.log(contactNumber);

    const response = await fetch(`/phoneBook/contacts?name=${contactName}&number=${contactNumber}`, {method: "POST"});
    const jsonArray = await response.json();

    for (let i = 0; i < jsonArray.length; i++) {
        document.getElementById("contactsList").innerHTML += `<li><h4>${jsonArray[i].name}</h4><h4>${jsonArray[i].number}</h4></li>`;
    }
}


async function getContacts() {
    const response = await fetch("/phoneBook/contacts", {method: "GET"});
    const jsonArray = await response.json();

    for (let i = 0; i < jsonArray.length; i++) {
        document.getElementById("contactsList").innerHTML += `<li><h4>${jsonArray[i].name}</h4><h4>${jsonArray[i].number}</h4></li>`;
    }
}