const jsQR = require('jsqr');
const jpeg = require('jpeg-js');
const QRCode = require('qrcode');

class QrCodeReader {
  constructor() {
  }

  read(image, callback) {
    const rawImageData = jpeg.decode(image.toJPEG(100));
    const qrCode = jsQR(rawImageData.data, rawImageData.width,
        rawImageData.height);
    if (qrCode) {
      QRCode.toDataURL(qrCode.data, (err, url)=>{
        callback({
          data: qrCode.data,
          image: url,
        });
      });
    }
  }
}

module.exports = QrCodeReader;
