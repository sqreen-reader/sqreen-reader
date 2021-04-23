const SqreenCapture = require('./capture/screen-capture');
const QrCodeReader = require('./qrcode/qr-code-reader');
const { desktopCapturer } = require('electron');
const ReadQrCodeHandler = require('./handler/read-qr-code-handler');
const DefaultProtocolUrl = require('./url/default-protocol-url');

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
            try {
                const url = new DefaultProtocolUrl(qrCode.data);
                qrCodeLink.href = url.href;
            } catch (_) {
                qrCodeLink.href ='#';
            }

            const image =document.getElementById('qr-code-image');
            image.src = qrCode.image;

            qrCodeLink.style.visibility = 'visible';
            image.style.visibility = 'visible';
            document.getElementById('no-qr-code').style.visibility = 'hidden';

        });
    }, 1000);
});