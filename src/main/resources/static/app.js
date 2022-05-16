const fetchData = () => {
    fetchData('http://localhost:8088/api/movie').then(result => result.json())
}