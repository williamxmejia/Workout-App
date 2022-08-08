const url = 'http://localhost:8080/users';

async function fetchData(){
    let response = await fetch(url);

    if (!response.ok) {
        throw Error("Failed to fetch info");
    }
    const data = await response.json();

    console.log(data)
}




fetchData();