const jsQR = require('jsqr');
const jpeg = require('jpeg-js');

class QrCodeReader {
    constructor() {
    }

    read(image, callback) {
        const rawImageData = jpeg.decode(image.toJPEG(100));
        const qrCode = jsQR(rawImageData.data, rawImageData.width, rawImageData.height);
        if(qrCode) {
            callback({
                data: qrCode.data,
                image: null
            });
        }
    }
}

module.exports = QrCodeReader;