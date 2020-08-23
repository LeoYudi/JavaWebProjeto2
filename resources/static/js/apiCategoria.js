var req = null;
var dados = null;
var imgPath = "https://image.tmdb.org/t/p/w500"

var xhr = new XMLHttpRequest();

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === this.DONE) {
    dados = JSON.parse(this.response)
    console.log(dados);

    $('.image').css('background-image', `url(${imgPath+dados.poster_path})`)
    $('.titulo-filme h1').text(dados.title)
    $('.sinopse').append(dados.overview)
    $('#nota').append(dados.vote_average)
    $('#duracao').append(dados.runtime+" min")
    $('#data-lancamento').append(() => {
      var date = dados.release_date.split('-')
      return date[2]+"/"+date[1]+"/"+date[0]
    })

    $('#genero').append(() => {
      var generos = []
      dados.genres.forEach(gen => {
        generos.push(gen.name)
      });
      return generos.join(', ')
    })
    
  }
});

xhr.open("GET", "https://api.themoviedb.org/3/movie/tt4154796?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");
// xhr.open("GET", "https://api.themoviedb.org/3/movie/69?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");

xhr.send(req);
// xhr.open("GET", "https://api.themoviedb.org/3/movie/tt4154796/release_dates?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");