var imgPath = "https://image.tmdb.org/t/p/w500";
var req1 = null;
var req2 = null;
var dados = null;

var xhr1 = new XMLHttpRequest();

xhr1.addEventListener("readystatechange", function () {
  if (this.readyState === this.DONE) {
    dados = JSON.parse(this.response)
    console.log(dados);

    for (let i = 0; i < 10; i++) {
      $('#filme' + (i + 1)).css('background-image', `url(${imgPath + dados.results[i].poster_path})`);
      $('#filme' + (i + 1)).attr("href", `/filme?id=${dados.results[i].id}`);
    }
  }
});

xhr1.open("GET", "https://api.themoviedb.org/3/movie/now_playing?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");
xhr1.send(req1);


var xhr2 = new XMLHttpRequest();

xhr2.addEventListener("readystatechange", function () {
  if (this.readyState === this.DONE) {
    dados = JSON.parse(this.response)
    console.log(dados);

    $('#aleatorio').attr('href', "/filme?id="+dados.results[parseInt(Math.random()*19)].id)
  }
});
var pagina = parseInt(Math.random()*500)
xhr2.open("GET", "https://api.themoviedb.org/3/discover/movie?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR&page="+pagina+"&vote_average.gte=0.0&include_adult=false");
xhr2.send(req2);