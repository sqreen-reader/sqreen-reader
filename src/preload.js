const SqreenCapture = require('./capture/screen-capture');
const QrCodeReader = require('./qrcode/qr-code-reader');
const { desktopCapturer } = require('electron');

window.addEventListener('DOMContentLoaded', ()=>{
    setInterval(()=>{
        new SqreenCapture(desktopCapturer, { types: ['screen'], thumbnailSize: {
                width: 2000,
                height: 2000
            }}).capture()
            .then(source => {
                console.log(source.length)

                const qrCodeReader = new QrCodeReader();
                qrCodeReader.read(source[0].thumbnail, (qrCode) => {
                    console.log(qrCode);
                    qrCodeLink = document.getElementById('qr-code');
                    qrCodeLink.innerText = qrCode.data;
                    qrCodeLink.href = qrCode.data;
                });
            });
    }, 1000);
});