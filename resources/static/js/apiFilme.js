var imgPath = "https://image.tmdb.org/t/p/w500";
var dados = null;

var req1 = null;
var req2 = null;

var xhr1 = new XMLHttpRequest();
var xhr2 = new XMLHttpRequest();

$('#voltar').attr('href', '/');

xhr1.addEventListener("readystatechange", function () {
  if (this.readyState === this.DONE) {
    dados = JSON.parse(this.response)
    console.log(dados);

    $('.image').css('background-image', `url(${imgPath+dados.poster_path})`);
    $('.titulo-filme h1').text(dados.title);

    if(dados.overview != "") $('.sinopse').append(dados.overview);
    else $('.sinopse').append("Sinopse indisponível");

    if(dados.vote_average != "") $('#nota').append(dados.vote_average);
    else $('#nota').append("-");

    if(dados.runtime != "") $('#duracao').append(dados.runtime+" min");
    else $('#duracao').append("-");

    $('#data-lancamento').append(() => {
      var date = dados.release_date.split('-');
      if (date != "") return date[2]+"/"+date[1]+"/"+date[0];
      else return "-";
    })

    $('#genero').append(() => {
      var generos = [];
      dados.genres.forEach(gen => {
        generos.push(gen.name);
      });
      return generos.join(', ');
    })
    
  }
});

xhr2.addEventListener("readystatechange", function () {
  if (this.readyState === this.DONE) {
    dados = JSON.parse(this.response);
    console.log(dados);

    $('#classificacao').append(() => {
      var pg = dados.results.filter(pais => {return pais.iso_3166_1 === "BR"})[0];
      if (!pg || pg.release_dates[0].certification == "") {
        pg = dados.results.filter(pais => {return pais.iso_3166_1 === "US"})[0];
        if (pg && pg.release_dates[0].certification != "") return pg.release_dates[0].certification + " (EUA)";
        else return "-";
      } 
      else return pg.release_dates[0].certification;
    });
  }
});

xhr1.open("GET", "https://api.themoviedb.org/3/movie/"+idFilme+"?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");
xhr1.send(req1);

xhr2.open("GET", "https://api.themoviedb.org/3/movie/"+idFilme+"/release_dates?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");
xhr2.send(req2);