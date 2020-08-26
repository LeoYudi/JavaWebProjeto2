var imgPath = "https://image.tmdb.org/t/p/w500";
var req = null;
var dados = null;
var i = 0;
var main = $('.main');


for (j = 0; j < favorites.length; j++) {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            dados = JSON.parse(this.response)
            console.log(dados);
            let filme = $(`<a class="filme" id="filme${i++}" style="background-size: cover; background-position: center;"></a>`);
            filme.css('background-image', `url(${imgPath + dados.poster_path})`);
            filme.attr("href", `/filme?id=${dados.id}`);
            main.append(filme);
        }
    });
    console.log(favorites);
    xhr.open("GET", "https://api.themoviedb.org/3/movie/" + favorites[j].movieId + "?api_key=332da2c194d16ef826b8f0b5a28e8da9&language=pt-BR");
    xhr.send(req);
}
