const SqreenCapture = require('./capture/screen-capture');
window.addEventListener('DOMContentLoaded', ()=>{
    new SqreenCapture({ types: ['screen'], thumbnailSize: {
            width: 1500,
            height: 1500
        }}).capture()
        .then(source => {
            console.log(source.length)
            document.getElementById('screen').src = source[0].thumbnail.toDataURL();
        });
});