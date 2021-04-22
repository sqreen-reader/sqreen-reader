const SqreenCapture = require('./capture/screen-capture');
const QrCodeReader = require('./qrcode/qr-code-reader');
const { desktopCapturer } = require('electron');
const ReadQrCodeHandler = require('./handler/read-qr-code-handler');

window.addEventListener('DOMContentLoaded', ()=>{
    const sqreenCapture = new SqreenCapture(desktopCapturer,
        { types: ['screen'], thumbnailSize: {
            width: 2000,
            height: 2000
        }});

    const qrCodeReader = new QrCodeReader();
    const handler = new ReadQrCodeHandler(sqreenCapture, qrCodeReader);

    setInterval(()=>{
        handler.handle((qrCode) => {
            console.log(qrCode);
            const qrCodeLink = document.getElementById('qr-code');
            qrCodeLink.innerText = qrCode.data;
            qrCodeLink.href = qrCode.data;

            document.getElementById('screen').src = qrCode.image;
        });
    }, 1000);
});