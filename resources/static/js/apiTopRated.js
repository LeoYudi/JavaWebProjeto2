var imgPath = "https://image.tmdb.org/t/p/w500";
var req = null;
var dados = null;

var xhr = new XMLHttpRequest();

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === this.DONE) {
    dados = JSON.parse(this.response)
    console.log(dados);
    
    for (let i=0; i<10; i++){
      $('#filme'+(i+1)).css('background-image', `url(${imgPath+dados.results[i].poster_path})`);
      $('#filme'+(i+1)).attr('href', `/filme?id=${dados.results[i].id}`);
    }
  }
});

xhr.open("GET", "https://api.themoviedb.org/3/discover/movie?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR&vote_count.gte=1000&sort_by=vote_average.desc");
xhr.send(req);