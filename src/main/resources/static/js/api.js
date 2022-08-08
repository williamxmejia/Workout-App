const url = 'http://localhost:8080/users';
const url1 = 'http://localhost:8080/exercises';

async function fetchData(){
    let response = await fetch(url);

    if (!response.ok) {
        throw Error("Failed to fetch info");
    }
    const data = await response.json();

    console.log(data)
    document.getElementById("display").innerText = data[0].userName;

}

async function fetchExercises(){
    let response = await fetch(url1);

    if (!response.ok) {
        throw Error("Failed to fetch info");
    }
    const data = await response.json();

    console.log(data)
    document.getElementById("display1").innerText = data[5].content;

}




fetchData();
fetchExercises();